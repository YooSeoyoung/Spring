package com.dw.jdbcapp.service;

import com.dw.jdbcapp.DTO.EmployeeDepartmentDTO;
import com.dw.jdbcapp.model.Employee;
import com.dw.jdbcapp.repository.iface.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    @Autowired
            @Qualifier("employeeTemplateRepository")
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        return employeeRepository.getAllEmployees();
    }
    public Employee getEmployeeById(String id){
        return employeeRepository.getEmployeeById(id);
    }

    public List<Map<String,Object>> getEmployeesWithDepartName(){
        return employeeRepository.getEmployeesWithDepartName();
    }

    public List<EmployeeDepartmentDTO> getEmployeesWithDepartName2() {
        List<EmployeeDepartmentDTO> employeeDepartmentDTOList = new ArrayList<>();
        List<Map<String,Object>> mapList = employeeRepository.getEmployeesWithDepartName();  //repository에서 list형태로 만들기 위한 작업

        for (Map<String,Object> data : mapList){
            EmployeeDepartmentDTO temp = new EmployeeDepartmentDTO( // DTO 형식에 맞게 데이터 타입을 변환해주는 역할
                    LocalDate.parse((String)data.get("입사일")),
                    (String)data.get("부서명"),
                    (String) data.get("이름")
            );
            employeeDepartmentDTOList.add(temp);
        }
        return employeeDepartmentDTOList;
    }
    public List<Employee> getEmployeesWithDepartmentAndPosition(
            String departmentNumber, String position) {
        return employeeRepository.getEmployeesWithDepartmentAndPosition(
                departmentNumber, position);
    }

    public Employee postEmployee(Employee employee){
        return employeeRepository.postEmployee(employee);
    }

}
