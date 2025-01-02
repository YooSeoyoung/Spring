package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.DTO.OrderRequestDTO;
import com.dw.jdbcapp.exception.ResourceNotFoundException;
import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        if (orderService.getOrderByIdAndCustomer(productNumber, customerId).isEmpty()){
            throw new ResourceNotFoundException("조회되는 데이터가 없습니다.");
        }
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
    @PostMapping("/orders")
    public ResponseEntity<OrderRequestDTO> saveOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        return new ResponseEntity<>(orderService.saveOrder(orderRequestDTO),HttpStatus.CREATED);
    }
    @PutMapping("/orders/update")
    public ResponseEntity<String> updateOrderWithShippingDate(@RequestParam  String id, @RequestParam String date){
        return new ResponseEntity<>(orderService.updateOrderWithShippingDate(id, date),HttpStatus.OK);
    }
    @GetMapping("/orders/city/orderamount/{limit}")
    public ResponseEntity< List<Map<String, Double>>> getTopCitiesByTotalOrderAmount(@PathVariable int limit){
        return new ResponseEntity<>(orderService.getTopCitiesByTotalOrderAmount(limit), HttpStatus.OK);
    }
    @GetMapping("/orders/ordercount/year/{city}")
    public ResponseEntity<List<Map<String, Double>> > getOrderCountByYearForCity(@PathVariable String city){
        return new ResponseEntity<>(orderService.getOrderCountByYearForCity(city),HttpStatus.OK);
    }
}
