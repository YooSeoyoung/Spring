package com.dw.jdbcapp.service;

import com.dw.jdbcapp.DTO.OrderRequestDTO;
import com.dw.jdbcapp.exception.InvalidRequestException;
import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.model.OrderDetail;
import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.repository.iface.OrderDetailRepository;
import com.dw.jdbcapp.repository.iface.OrderRepository;
import com.dw.jdbcapp.repository.iface.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Service
public class OrderService {
    @Autowired
    @Qualifier("orderTemplateRepository")
    OrderRepository orderRepository;

    @Autowired
    @Qualifier("orderDetailTemplateRepository")
    OrderDetailRepository orderDetailRepository;

    @Autowired
    @Qualifier("productTemplateRepository")
    ProductRepository productRepository;

    public List<Order> getAllOrders(){
        return orderRepository.getAllOrders();
    }
    public Order getOrderById(String orderNumber){
        return orderRepository.getOrderById(orderNumber);
    }
    public List<Order> getOrderByIdAndCustomer(int productNumber, String customerId) {
        return orderRepository.getOrderByIdAndCustomer(productNumber,customerId);
    }
    @Transactional
    // 선언된 메서드 수행 도중 예외가 발생하면 이미 수행되었던 동작으로 모두 롤백(rollback=원상복귀)시키도록 명령하는 어노테이션임
    public OrderRequestDTO saveOrder(OrderRequestDTO orderRequestDTO){
        //1. DTO에서 주문정보를 꺼내 주문테이블에 insert
        orderRepository.saveOrder(orderRequestDTO.toOrder());
        //2. DTO에서 주문세부정보를 꺼내 주문세부테이블에 insert(반복문 필요 : 주문을 1개만 하는게 아니기 때문에)
        for(OrderDetail data: orderRequestDTO.getOrderDetails()){
            // for문 안의 조건식( for문에서 orderDetail의 정보를 하나씩 data 꺼내고 있기 때문에 for문 안에서만 orderDetail의 정보를 확인 할 수 있음
//            int stock = productRepository.getProductById(data.getProductId()).getInventory(); // stock에다가 기존 제품별 재고수량을 찾는 방법
//            if (stock<data.getOrderQuantity()){ // 기존 제품의 수량과 고객으로부터 주문받은 해당 제품의 수량을 비교 ( for문이 돌아갈떄마다 stock의 제품번호랑 data의 제품번호의 변경이 이루어짐)
//                throw new InvalidRequestException("주문수량을 초과하여 주문하였습니다.");
//            }
//            orderDetailRepository.saveOrderDetail(data);
            // 선생님이 해주신거~
            Product product = productRepository.getProductById(
                    data.getProductId());
            System.out.println(product.getInventory() + " " + data.getOrderQuantity());
            if (product.getInventory() - data.getOrderQuantity() < 0) {
                throw new InvalidRequestException(
                        "요청하신 수량은 현재 재고를 초과합니다: " +
                                product.getProductName() + ", 현재 재고 " +
                                product.getInventory());
            }
            orderDetailRepository.saveOrderDetail(data);

        } return orderRequestDTO;

}


    public String updateOrderWithShippingDate(String id, String date){
        return orderRepository.updateOrderWithShippingDate(id,date);
    }
    public List<Map<String, Double>> getTopCitiesByTotalOrderAmount(int limit){
        return orderRepository.getTopCitiesByTotalOrderAmount(limit);
    }
    public List<Map<String, Double>> getOrderCountByYearForCity(String city){
        return orderRepository.getOrderCountByYearForCity(city);
    }

}
