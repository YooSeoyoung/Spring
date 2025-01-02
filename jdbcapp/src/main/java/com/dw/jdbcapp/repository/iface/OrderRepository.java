package com.dw.jdbcapp.repository.iface;

import com.dw.jdbcapp.model.Order;

import java.util.List;
import java.util.Map;

public interface OrderRepository {
    List<Order> getAllOrders();
    Order getOrderById(String orderNumber);
    List<Order> getOrderByIdAndCustomer(int productNumber, String customerId);
    int saveOrder(Order order);
    String updateOrderWithShippingDate(String id, String date);
    List<Map<String,Double>> getTopCitiesByTotalOrderAmount(int limit);
    List<Map<String,Double>> getOrderCountByYearForCity(String city);

}
