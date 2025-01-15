package com.dw.dynamic.service;

import com.dw.dynamic.DTO.CategoryEnrollmentAndIncomeDTO;
import com.dw.dynamic.DTO.CourseEnrollmentAndIncomeDTO;
import com.dw.dynamic.DTO.PayrollSubscriptionsEnrollmentAndIncomeDTO;
import com.dw.dynamic.exception.InvalidRequestException;
import com.dw.dynamic.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserService userService;

    public List<CourseEnrollmentAndIncomeDTO> getCoursesEnrollmentsAndIncomes(){
        try {
            return productRepository.getCoursesEnrollmentsAndIncomes();
        }catch (InvalidRequestException e){
            throw new InvalidRequestException("정상적인 요청이 아닙니다");
        }

    }

    public List<PayrollSubscriptionsEnrollmentAndIncomeDTO> getPayrollSubscriptionsEnrollmentsAndIncomes(){
        try {
            return productRepository.getPayrollSubscriptionsEnrollmentsAndIncomes();
        }catch (InvalidRequestException e){
            throw new InvalidRequestException("정상적인 요청이 아닙니다");
        }
    }

    public List<CategoryEnrollmentAndIncomeDTO> getCategoryEnrollmentsAndIncomes(){
        try {
            return productRepository.getCategoryEnrollmentsAndIncomes();
        }catch (InvalidRequestException e){
            throw new InvalidRequestException("정상적인 요청이 아닙니다");
        }
    }
}
