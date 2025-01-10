package com.dw.companyapp.Repository;

import com.dw.companyapp.dto.CitiesByTotalOrderAmountDTO;
import com.dw.companyapp.dto.OrderCountByYearForCityDTO;
import com.dw.companyapp.model.Department;
import com.dw.companyapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,String> {

    @Query("select o from Order o where o.customer.customerId =:customerId and o.orderId in(select od.order.orderId from OrderDetail od where od.product.productId =:productNumber)")
    List<Order> getOrderByIdAndCustomer(int productNumber, String customerId);

   @Query("select new com.dw.companyapp.dto.CitiesByTotalOrderAmountDTO(c.city, sum(od.unitPrice*od.orderQuantity)) from OrderDetail od join od.order o join o.customer c group by c.city order by sum(od.unitPrice*od.orderQuantity) desc limit:limit")
    List<CitiesByTotalOrderAmountDTO> getTopCitiesByTotalOrderAmount(int limit);

   @Query("select new com.dw.companyapp.dto.OrderCountByYearForCityDTO(year(o.orderDate),count(o)) from OrderDetail od join od.order o join o.customer c where c.city= :city group by year(o.orderDate)")
    List<OrderCountByYearForCityDTO> getOrderCountByYearForCity(String city);
}
