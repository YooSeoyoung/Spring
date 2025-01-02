package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.DTO.EmployeeDepartmentDTO;
import com.dw.jdbcapp.exception.ResourceNotFoundException;
import com.dw.jdbcapp.model.Employee;
import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/find-all-employees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }
    //QUERY PARAMETERS 쿼리 문자열 사용
    @GetMapping("/employee")
    public ResponseEntity<Employee> getEmployeeById(@RequestParam String id){ // @RequestParam : parameter에 바인딩하는 역할)
       return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);

       // http://localhost:8080/employee?id=E01
    }
    //PATH PARAMETERS 경로매개변수 사용(두개를 사용할 경우에는 하나의 댑스를 한번 더 표기해주기)
//    @GetMapping("/employee/id/{id}")
//    @GetMapping("/employee/name/{name}")

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById_2(@PathVariable String id){ // @PathVariable : parameter에 바인딩하는 역할)
        return new ResponseEntity<>(employeeService.getEmployeeById(id),HttpStatus.OK);

        //http://localhost:8080/employee/E01
    }
    @GetMapping("/employees/department")
    public ResponseEntity<List<Map<String,Object>>> getEmployeesWithDepartName(){
        return new ResponseEntity<>(employeeService.getEmployeesWithDepartName(), HttpStatus.OK) ;
    }

    @GetMapping("/employees/department2")
    public ResponseEntity<List<EmployeeDepartmentDTO>> getEmployeesWithDepartName2(){
        return new ResponseEntity<>(employeeService.getEmployeesWithDepartName2(),HttpStatus.OK);
    }

    @GetMapping("/employees/{departmentNumber}/{position}")
    public ResponseEntity<List<Employee>> getEmployeesWithDepartmentAndPosition(
            @PathVariable String departmentNumber,
            @PathVariable String position
    ) {
        if (employeeService.getEmployeesWithDepartmentAndPosition(departmentNumber,position).isEmpty()){
           throw new ResourceNotFoundException("조회되는 데이터가 없습니다.");
        }
        return new ResponseEntity<>(employeeService.getEmployeesWithDepartmentAndPosition(
                departmentNumber, position),HttpStatus.OK);
    }

    @PostMapping("/post/employee")
    public ResponseEntity<Employee> postEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.postEmployee(employee),HttpStatus.CREATED);
    }

    @GetMapping("/employees/hiredate/{hiredate}")
    public ResponseEntity<List<Employee>> getEmployeesByHireDate(@PathVariable String hiredate){
        return new ResponseEntity<>(employeeService.getEmployeesByHireDate(hiredate),HttpStatus.OK);
    }
}
