package com.dw.jdbcapp.service;

import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.repository.iface.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
            @Qualifier("orderTemplateRepository")
    OrderRepository orderRepository;

    public List<Order> getAllOrders(){
        return orderRepository.getAllOrders();
    }
    public Order getOrderById(String orderNumber){
        return orderRepository.getOrderById(orderNumber);
    }
    public List<Order> getOrderByIdAndCustomer(int productNumber, String customerId) {
        return orderRepository.getOrderByIdAndCustomer(productNumber,customerId);
    }

}
