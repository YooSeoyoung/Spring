package com.dw.companyapp.Repository;

import com.dw.companyapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,String> {

    @Query("select c from Customer c where c.mileage >=(select avg(c.mileage) from Customer c)")
    List<Customer> getCustomersWithHighMileThanAvg();

    @Query("select c from Customer c join MileageGrade m on c.mileage >= m.lowerMileage and c.mileage <=m.upperMileage where m.gradeName in :grade")
    List<Customer> getCustomersByMileageGrade(String grade);

//    @Query("select c from Customer c join MileageGrade m " +
//            "on c.mileage between m.lowerMileage and m.upperMileage " +
//            "where m.gradeName = :grade")
//    public List<Customer> getCustomersByMileageGrade(String grade);


}
