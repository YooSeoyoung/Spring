package com.dw.jdbcapp.repository.iface;

import com.dw.jdbcapp.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAllProducts();
    Product getProductById(int productNumber);
    Product postProduct(Product product);
    Product putProductPrice(Product product);
    int deleteProduct(int id);
}