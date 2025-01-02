package com.dw.jdbcapp.service;

import com.dw.jdbcapp.DTO.EmployeeDepartmentDTO;
import com.dw.jdbcapp.exception.InvalidRequestException;
import com.dw.jdbcapp.exception.ResourceNotFoundException;
import com.dw.jdbcapp.model.Employee;
import com.dw.jdbcapp.repository.iface.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    @Autowired
    @Qualifier("employeeTemplateRepository")
    EmployeeRepository employeeRepository;


    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public Employee getEmployeeById(String id) {
        return employeeRepository.getEmployeeById(id);
    }

    public List<Map<String, Object>> getEmployeesWithDepartName() {
        return employeeRepository.getEmployeesWithDepartName();
    }

    public List<EmployeeDepartmentDTO> getEmployeesWithDepartName2() {
        List<EmployeeDepartmentDTO> employeeDepartmentDTOList = new ArrayList<>();
        List<Map<String, Object>> mapList = employeeRepository.getEmployeesWithDepartName();  //repository에서 list형태로 만들기 위한 작업

        for (Map<String, Object> data : mapList) {
            EmployeeDepartmentDTO temp = new EmployeeDepartmentDTO( // DTO 형식에 맞게 데이터 타입을 변환해주는 역할
                    LocalDate.parse((String) data.get("입사일")),
                    (String) data.get("부서명"),
                    (String) data.get("이름")
            );
            employeeDepartmentDTOList.add(temp);
        }
        return employeeDepartmentDTOList;
    }

    public List<Employee> getEmployeesWithDepartmentAndPosition(String departmentNumber, String position) {
        return employeeRepository.getEmployeesWithDepartmentAndPosition(
                departmentNumber, position);
    }

    public Employee postEmployee(Employee employee) {

        return employeeRepository.postEmployee(employee);
    }

    public List<Employee> getEmployeesByHireDate(String hiredate) {
//        // hiredate도 아니고 0도 아니면 예외처리
//
//        if (!hiredate.equals("0")) { // 만약 0이 아니면!!!
//            try { // String으로 받은 hiredate를 Localdate형식(문자열을 년-월-일)으로 바꾸기
//                LocalDate date = LocalDate.parse(hiredate); // localdate형식이 아닐 경우 예외 발생
//            } catch (DateTimeParseException e) { // 이 예외 처리를 발견할 경우 예외문자 발송
//                throw new InvalidRequestException("예외발생 ");
//            } // -> 결국 0이 아니면 hiredate의 정상 여부까지 확인 // 0이면 둘중에 하나라도 해당되기 때문에
//
//        }
//            if (hiredate.equals("0")) { // 문자끼리의 비교는 스택이 아닌 힙에서 비교를 해야되기 때문에 == 말고 .equals쓰기
//                return employeeRepository.getEmployeesByHireDateNum();
//            } else {
//                return employeeRepository.getEmployeesByHireDate(hiredate);
//            }
//        }
        // 선생님이 해준 코드~~~
        if (hiredate == null || hiredate.isEmpty()) {
            throw new InvalidRequestException("입력값이 없습니다");
        }
        try {
            if (hiredate.equals("0")) {
                return employeeRepository.getEmployeesByHireDateNum();
            }else {
                LocalDate date = LocalDate.parse(hiredate);
                return employeeRepository.getEmployeesByHireDate(date.toString());
            }
        }catch (DateTimeParseException e) {
            throw new InvalidRequestException("입력이 올바르지 않습니다");
        }
    }

    }















