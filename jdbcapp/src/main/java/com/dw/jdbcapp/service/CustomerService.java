package com.dw.jdbcapp.service;

import com.dw.jdbcapp.model.Customer;
import com.dw.jdbcapp.repository.iface.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    @Qualifier("customerTemplateRepository")
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.getAllCustomers();
    }

    public List<Customer> getCustomersHighMileThanAvg(){
        return customerRepository.getCustomersHighMileThanAvg();
    }
    public List<Customer> getCustomersByMileageGrade(String grade){
        return customerRepository.getCustomersByMileageGrade(grade);
    }
}
