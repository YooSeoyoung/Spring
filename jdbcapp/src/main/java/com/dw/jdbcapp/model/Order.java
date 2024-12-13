
package com.dw.jdbcapp.model;
import java.time.LocalDate;

public class Order { // 주문
    private String orderId; // 주문번호
    private String customerId; //고객번호
    private String employeeId; //사원번호
    private LocalDate orderDate; //주문일
    private LocalDate requestDate; //요청일
    private LocalDate shippingDate; //발송일

    public Order() {
    }

    public Order(String orderId, String customerId, String employeeId, LocalDate orderDate, LocalDate requestDate, LocalDate shippingDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.orderDate = orderDate;
        this.requestDate = requestDate;
        this.shippingDate = shippingDate;
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

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    @Override
    public String toString() {
        return "Order{"+ "orderId="+ orderId+"customerId="+customerId+"employeeId="+employeeId+"orderDate="+orderDate+
                "requestDate="+requestDate+ "shippingDate="+shippingDate+'}';
    }
}
