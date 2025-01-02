package com.dw.jdbcapp.DTO;

import com.dw.jdbcapp.model.Product;

public class ProductDTO {
    private int productId; // 제품번호
    String productName;//제품명
    double unitPrice;//단가
    int stock;//재고
    double stockValue;// 재고금액(단가 *재고)

    public ProductDTO() {
    }

    public ProductDTO(int productId, String productName, double unitPrice, int stock, double stockValue) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.stockValue = stockValue;
    }
    public ProductDTO(Product product){
        this.productId =product.getProductId();
        this.productName = product.getProductName();
        this.unitPrice= product.getUnitPrice();
        this.stock=product.getInventory();
        this.stockValue=product.getUnitPrice()*product.getInventory();
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getStockValue() {
        return stockValue;
    }

    public void setStockValue(double stockValue) {
        this.stockValue = stockValue;
    }
    //우리가 정보를 조회를해서 전달을 하기 때문에
    public static ProductDTO fromProduct(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setUnitPrice(product.getUnitPrice());
        productDTO.setStock(product.getInventory());
        productDTO.setStockValue(product.getUnitPrice()*product.getInventory());
        return productDTO;
    }
}
