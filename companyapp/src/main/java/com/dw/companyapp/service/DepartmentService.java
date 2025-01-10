package com.dw.companyapp.service;

import com.dw.companyapp.Repository.DepartmentRepository;
import com.dw.companyapp.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);

    }

    public List<Department> saveDepartmentList(List<Department> departmentList) {
        return departmentRepository.saveAll(departmentList);
    }

    public Department updateDepartment(Department department) {
        Department department1 = departmentRepository.findById(
                department.getDepartmentId()).orElseThrow(()-> new RuntimeException("존재하지 않은 부서번호입니다.")
        );
        department1.setDepartmentName(department.getDepartmentName());
        return departmentRepository.save(department1);
    }

    public String deleteDepartment(String id) {

        departmentRepository.deleteById(id);

        return id + "에 있는 정보에 대하여 삭제처리를 하였습니다.";
    }
}








