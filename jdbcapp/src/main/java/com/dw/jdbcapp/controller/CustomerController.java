package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.model.Customer;
import com.dw.jdbcapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api") // 만드는 api 앞에 자동으로 /api를 붙인다는 뜻
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/find-all-customer")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return new ResponseEntity<>( customerService.getAllCustomers(), HttpStatus.OK); // ( 가지고 올 데이터, 상태코드 )


    }
}
