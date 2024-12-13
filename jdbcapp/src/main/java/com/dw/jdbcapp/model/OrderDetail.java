package com.dw.jdbcapp.model;

public class OrderDetail { //주문세부
    private String orderId; //주문번호
    private int productId; //제품번호
    private double unitPrice; //단가
    private int orderQuantity; //주문수량
    private double discountPercent; //할인율

    public OrderDetail() {
    }

    public OrderDetail(String orderId, int productId, double unitPrice, int orderQuantity, double discountPercent) {
        this.orderId = orderId;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.orderQuantity = orderQuantity;
        this.discountPercent = discountPercent;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "orderId="+ orderId+"productId="+productId+"unitPrice="+unitPrice+"orderQuantity="+orderQuantity+
                "discountPercent="+discountPercent+'}';
    }
}

