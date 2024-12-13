package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.model.Employee;
import com.dw.jdbcapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/find-all-employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
    //쿼리 문자열 사용
    @GetMapping("/employee")
    public Employee getEmployeeById(@RequestParam String id){ // @RequestParam : parameter에 바인딩하는 역할)
       return employeeService.getEmployeeById(id);
    }

}
