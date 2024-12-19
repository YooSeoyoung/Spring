package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/find-all-products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{productNumber}")
    public ResponseEntity<Product> getProductById(@PathVariable int productNumber){
        return new ResponseEntity<>(productService.getProductById(productNumber),HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<Product> getProductById2(@RequestParam int productNumber){
        return new ResponseEntity<>(productService.getProductById(productNumber),HttpStatus.OK);
    }

    @PostMapping("/post/product")
    public ResponseEntity<Product> postProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.postProduct(product),HttpStatus.CREATED);
    }
    @PostMapping("/post/productlist")
    public ResponseEntity<List<Product>> postProductlist(@RequestBody List<Product> productList){
        return new ResponseEntity<>(productService.postProductlist(productList),HttpStatus.CREATED) ;
    }

    @PutMapping("/put/product/price")
    public ResponseEntity<Product> putProductPrice(@RequestBody Product product){
        return new ResponseEntity<>(productService.putProductPrice(product),HttpStatus.OK) ;
    }
    @DeleteMapping("/delete/product")
    public ResponseEntity<String> deleteProduct(@RequestParam int id){
        return new ResponseEntity<>("제품번호 : " +productService.deleteProduct(id)+"삭제됨",HttpStatus.OK);
    }
}
