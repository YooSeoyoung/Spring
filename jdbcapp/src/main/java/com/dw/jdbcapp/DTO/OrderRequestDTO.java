package com.dw.jdbcapp.DTO;

import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.model.OrderDetail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderRequestDTO {
    private String orderId; //주문번호
    private String customerId; //고객번호
    private String employeeId; //사원번호
    private LocalDate requestDate; //요청일
    private List<OrderDetail> orderDetails;

    public OrderRequestDTO() {
    }

    public OrderRequestDTO(String orderId, String customerId, String employeeId, LocalDate requestDate, List<OrderDetail> orderDetails) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.requestDate = requestDate;
        this.orderDetails = orderDetails;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Order toOrder(){ // 형태의 변환을 본인 클래스에 넣으면 훨씬 더 편함( 서비스 상에 있는 코드가 간결해짐)
        Order order = new Order();
        order.setOrderId(this.orderId);
        order.setCustomerId(this.customerId);
        order.setEmployeeId(this.employeeId);
        order.setOrderDate(LocalDate.now());
        order.setRequestDate(this.requestDate);
        return order;
    }
}
