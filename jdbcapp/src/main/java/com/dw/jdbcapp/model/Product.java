package com.dw.jdbcapp.model;

public class Product { //제품
    private int productId; //제품번호
    private String productName; //제품명
    private String packagingUnit; //포장단위
    private double unitPrice; //단가
    private int inventory; //재고

    public Product() {
    }

    public Product(int productId, String productName, String packagingUnit, double unitPrice, int inventory) {
        this.productId = productId;
        this.productName = productName;
        this.packagingUnit = packagingUnit;
        this.unitPrice = unitPrice;
        this.inventory = inventory;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPackagingUnit() {
        return packagingUnit;
    }

    public void setPackagingUnit(String packagingUnit) {
        this.packagingUnit = packagingUnit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Product {"+ "productId="+ productId+"productName="+productName+ "packagingUnit="+packagingUnit+
                "unitPrice="+ unitPrice + "inventory="+inventory+'}';
    }
}
