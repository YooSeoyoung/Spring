package com.dw.companyapp.service;

import com.dw.companyapp.Repository.*;
import com.dw.companyapp.dto.CitiesByTotalOrderAmountDTO;
import com.dw.companyapp.dto.OrderCountByYearForCityDTO;
import com.dw.companyapp.dto.OrderRequestDTO;
import com.dw.companyapp.exception.InvalidRequestException;
import com.dw.companyapp.exception.ResourceNotFoundException;
import com.dw.companyapp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class OrderService {

    OrderRepository orderRepository;
    ProductRepository productRepository;
    EmployeeRepository employeeRepository;
    CustomerRepository customerRepository;
    OrderDetailRepository orderDetailRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, EmployeeRepository employeeRepository, CustomerRepository customerRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // 과제 1-2 주문번호를 기준으로 주문 정보를 조회하는 API
    // 과제 3-2 주문정보를 조회할때 주문번호가 올바르지 않은 경우의 예외 처리
    public Order getOrderById(String orderNumber) {
        return orderRepository.findById(orderNumber).orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 주문번호입니다"+orderNumber));
    }

    // 과제 1-4 제품번호와 고객번호를 기준으로 해당 제품을 주문한 특정 고객의 주문 내역을 조회하는 API
    // 과제 3-4 제품번호와 고객번호로 주문정보를 조회할때 데이터가 없는 경우의 예외처리
    public List<Order> getOrderByIdAndCustomer(int productNumber, String customerId) {
       List<Order> orders= orderRepository.getOrderByIdAndCustomer(productNumber, customerId);
            if (orders.isEmpty()){
                throw new ResourceNotFoundException("해당되는 정보가 없습니다:" + productNumber+","+customerId);
            }
        return orders;
    }

    public OrderRequestDTO saveOrder(OrderRequestDTO orderRequestDTO) {
        Order order = new Order();
        order.setOrderId(orderRequestDTO.getOrderId());
        order.setEmployee(employeeRepository.findById(orderRequestDTO.getEmployeeId()).orElseThrow(()-> new RuntimeException("사원번호 오류")));
        order.setCustomer(customerRepository.findById(orderRequestDTO.getCustomerId()).orElseThrow(()-> new RuntimeException("고객번호 오류")));
        order.setOrderDate(LocalDate.now());
        order.setRequestDate(orderRequestDTO.getRequestDate());

        orderRepository.save(order);

//        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (OrderDetail data : orderRequestDTO.getOrderDetails()){
//            if (data.getProduct() == null){
//                throw new ResourceNotFoundException("존재하지 않은 제품정보입니다.");
//            }
            Product product = productRepository.findById(data.getProduct().getProductId()).orElseThrow(() -> new ResourceNotFoundException("존재하지 않은 제품번호입니다."));
           if (product.getStock()-data.getOrderQuantity()<0){
               throw new InvalidRequestException("요청하신 수량은  현재 재고를 초과합니다:"+
                       product.getProductName()+", 현재 재고 "+ product.getStock());
           }
//            OrderDetail orderDetail = new OrderDetail();
//            orderDetail.setOrder(data.getOrder());
//            orderDetail.setProduct(data.getProduct());
//            orderDetail.setUnitPrice(data.getUnitPrice());
//            orderDetail.setOrderQuantity(data.getOrderQuantity());
//            orderDetail.setDiscountRate(data.getDiscountRate());
//            orderDetailList.add(orderDetail);
            orderDetailRepository.save(data);
        }
//        orderRequestDTO.setOrderDetails(orderDetailList);
        return orderRequestDTO;
    }

    // 과제 4-4 주문번호와 발송일을 매개변수로 해당 주문의 발송일을 수정하는 API
    public String updateOrderWithShippingDate(String id, LocalDate date) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new InvalidRequestException("없는 주문번호"));
        order.setShippingDate(date);
        orderRepository.save(order);


        return "성공적으로 수정하였습니다.";

    }

    // 과제 4-5 도시별로 주문금액합 결과를 내림차순 정렬하여 조회하는 API
    public List<CitiesByTotalOrderAmountDTO> getTopCitiesByTotalOrderAmount(int limit) {
//            List<CitiesByTotalOrderAmountDTO> cities = new ArrayList<>();
//            for (CitiesByTotalOrderAmountDTO data : orderRepository.getTopCitiesByTotalOrderAmount(limit)){
//                CitiesByTotalOrderAmountDTO citiesByTotalOrderAmountDTO = new CitiesByTotalOrderAmountDTO();
//                citiesByTotalOrderAmountDTO.setCity(data.getCity());
//                citiesByTotalOrderAmountDTO.setTotalAmount(data.getTotalAmount());
//                cities.add(citiesByTotalOrderAmountDTO);
//            }
//        return cities;
        return orderRepository.getTopCitiesByTotalOrderAmount(limit);
    }

    // 과제 4-6 도시를 매개변수로 해당 도시의 년도별 주문건수를 조회하는 API
    public List<OrderCountByYearForCityDTO> getOrderCountByYearForCity(String city) {
//        List<OrderCountByYearForCityDTO> yearForCityDTO = new ArrayList<>();
//        for (OrderCountByYearForCityDTO data : orderRepository.getOrderCountByYearForCity(city)){
//            OrderCountByYearForCityDTO orderCountByYearForCityDTO = new OrderCountByYearForCityDTO();
//            orderCountByYearForCityDTO.setYear(data.getYear());
//            orderCountByYearForCityDTO.setOrderCount(data.getOrderCount());
//            yearForCityDTO.add(orderCountByYearForCityDTO);
//        }
//        return yearForCityDTO;
        return orderRepository.getOrderCountByYearForCity(city);
    }
}

//saveOrder
//{
//    "orderId": "Q1111",
//    "customerId": "ACDDR",
//    "employeeId": "E01",
//    "requestDate": "2025-02-01",
//    "orderDetails": [
//        {
//            "order": {
//                "orderId": "Q1111" // id만 입력해도 가능함
//            },
//            "product": {
//                "productId": 1
//            },
//            "unitPrice": 8000,
//            "orderQuantity": 10,
//            "discountRate": 0
//        }
//    ]
//}