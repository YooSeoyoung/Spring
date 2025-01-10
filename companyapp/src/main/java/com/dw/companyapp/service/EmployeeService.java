package com.dw.companyapp.service;

import com.dw.companyapp.Repository.EmployeeRepository;
import com.dw.companyapp.dto.EmployeeDepartmentDTO;
import com.dw.companyapp.exception.InvalidRequestException;
import com.dw.companyapp.exception.ResourceNotFoundException;
import com.dw.companyapp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // 과제 3-1 사원정보를 조회할때 사원번호가 올바르지 않은 경우의 예외 처리
    public Employee getEmployeeById(String id) {
             return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("사원번호가 올바르지 않습니다."));
    }



    public List<Map<String,Object>> getEmployeesWithDepartName() {
        List<Map<String,Object>> mapList = new ArrayList<>();
        for (Employee data : employeeRepository.findAll()){
            Map<String,Object> employee =new HashMap<>();
            employee.put("name",data.getName());
            employee.put("hireDate", data.getHireDate());
            employee.put("departmentName", data.getDepartment() !=null?data.getDepartment().getDepartmentName():"");
            mapList.add(employee);
        }
        return mapList;

//        List<Object[]> objects = employeeRepository.getEmployeesWithDepartName();
//        List<Map<String,Object>> maps = new ArrayList<>();
//        for (Object[] data : objects) {
//            Map<String, Object> employee = new HashMap<>();
//            employee.put("입사일", data[0] != null ? data[0] : 0);
//            employee.put("부서명", data[1] != null ? data[1] : "");
//            employee.put("이름", data[2] != null ? data[2] : "");
//            maps.add(employee);
//        }
//        return maps;
    }



    public List<EmployeeDepartmentDTO> getEmployeesWithDepartName2() {
        List<EmployeeDepartmentDTO> employeeDepartmentDTOList = new ArrayList<>();
        for (Employee data : employeeRepository.findAll()){
            EmployeeDepartmentDTO employeeDepartmentDTO = new EmployeeDepartmentDTO();
            employeeDepartmentDTO.setHireDate(data.getHireDate());
            employeeDepartmentDTO.setEmployeeName(data.getName());
            employeeDepartmentDTO.setDepartmentName(data.getDepartment()!= null ? data.getDepartment().getDepartmentName() : "");
            employeeDepartmentDTOList.add(employeeDepartmentDTO);
        }
       return employeeDepartmentDTOList;
       // return employeeRepository.getEmployeesWithDepartName2();
    }

    // 과제 1-3 부서번호와 직위를 기준으로 해당 부서에 근무하는 특정 직위의 사원 정보를 조회하는 API
    // 과제 3-3 부서번호와 직위로 사원정보를 조회할때 데이터가 없는 경우의 예외처리
    public List<Employee> getEmployeesWithDepartmentAndPosition(
            String departmentNumber, String position) {
        List<Employee> employeeList= employeeRepository.findByDepartmentIdAndPosition(departmentNumber, position);
        if (employeeList.isEmpty()){
            throw new ResourceNotFoundException("해당되는 정보가 없습니다:" + departmentNumber+","+position);
        }
        return employeeList;
    }

    // 과제 2-3 사원테이블에 사원 1명을 새로 추가하는 API
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // 과제 4-3 입사일을 매개변수로 해당 입사일 이후로 입사한 사원들을 조회하는 API
    // hiredate를 0으로 입력하면 가장 최근 입사한 사원의 정보를 조회하시오.
    public List<Employee> getEmployeesByHiredate(String hiredate) {
        if (hiredate ==null || hiredate.isEmpty()){
            throw new InvalidRequestException("입력값이 없습니다");
        } try {
            if (hiredate.equals("0")){
                return employeeRepository.getLastHiredEmployees();
            }else {
                LocalDate date = LocalDate.parse(hiredate);
                return employeeRepository.getEmployeesByHiredate(date);
            }
        }catch (DateTimeParseException e){
            throw new InvalidRequestException("입력이 올바르지 않습니다.");
        }

    }
}








