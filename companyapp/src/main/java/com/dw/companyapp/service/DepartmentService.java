package com.dw.companyapp.service;

import com.dw.companyapp.Repository.DepartmentRepository;
import com.dw.companyapp.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department saveDepartment(Department department) {
        
    }

    public List<Department> saveDepartmentList(
                           List<Department> departmentList) {
        return null;
    }

    public Department updateDepartment(Department department) {
        return null;
    }

    public String deleteDepartment(String id) {
        return null;
    }
}








