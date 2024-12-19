package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/find-all-orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<> (orderService.getAllOrders(), HttpStatus.OK);
    }
    @GetMapping("/orders/{orderNumber}")
    public ResponseEntity<Order> getOrderById(@PathVariable String orderNumber){
        return new ResponseEntity<>(orderService.getOrderById(orderNumber),HttpStatus.OK);
    }
    @GetMapping("/orders/{productNumber}/{customerId}")
    public ResponseEntity<List<Order>> getOrdersWithProductNumberAndCustomerId(@PathVariable int productNumber, @PathVariable String customerId){
        return new ResponseEntity<>(orderService.getOrderByIdAndCustomer(productNumber,customerId),HttpStatus.OK);
    }
    @GetMapping("/orders")
    public ResponseEntity<Order> getOrderById2(@RequestParam String orderNumber){
        return new ResponseEntity<>(orderService.getOrderById(orderNumber),HttpStatus.OK);
    }

    @GetMapping("/orders1")
    public ResponseEntity<List<Order>> getOrdersWithProductNumberAndCustomerId2(@RequestParam int productNumber, @RequestParam String customerId){
        return new ResponseEntity<>(orderService.getOrderByIdAndCustomer(productNumber, customerId),HttpStatus.OK);
    }

}
