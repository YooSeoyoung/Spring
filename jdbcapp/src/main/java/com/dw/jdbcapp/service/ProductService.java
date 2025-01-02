package com.dw.jdbcapp.service;

import com.dw.jdbcapp.DTO.ProductDTO;
import com.dw.jdbcapp.exception.InvalidRequestException;
import com.dw.jdbcapp.exception.ResourceNotFoundException;
import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.repository.iface.ProductRepository;
import com.dw.jdbcapp.repository.jdbc.ProductJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    @Qualifier("productTemplateRepository")
    ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.getAllProducts();
    }


    public Product getProductById(int productNumber){
        if (productNumber<0){
            throw new InvalidRequestException("존재하지 않는 제품번호 : "+ productNumber);
        }
        return productRepository.getProductById(productNumber);
    }

    public Product postProduct(Product product){
        return productRepository.postProduct(product);
    }

    public List<Product> postProductlist(List<Product> productList){
        for(Product data:productList){
            productRepository.postProduct(data);
        }
        return productList;
    }
    public Product putProductPrice(Product product){
        return productRepository.putProductPrice(product);
    }
    public int deleteProduct(int id){
        return productRepository.deleteProduct(id);
    }

    public List<Product> getProductsBelowPrice(double price){
        return productRepository.getProductsBelowPrice(price);
    }
    public String updateProductWithStock(int id, int stock){
        return productRepository.updateProductWithStock(id, stock);
    }
    public List<Product> getProductByProductName(String name){
        return productRepository.getProductByProductName(name);
    }
    public List<ProductDTO> getProductsByStockValue(){
//        List<ProductDTO> productDTOList= new ArrayList<>();
//        for ( Product data :productRepository.getProductsByStockValue()){ // productDTO에다가 product에서 받은걸 하나씩 넣는 역할) + stockvalue도 같이 !!!!
//            ProductDTO productDTO = new ProductDTO();
//                productDTO.setProductId(data.getProductId());
//                productDTO.setProductName(data.getProductName());
//                productDTO.setUnitPrice(data.getUnitPrice());
//                productDTO.setStock(data.getInventory());
//                productDTO.setStockValue(data.getUnitPrice()*data.getInventory()); // 재고 * 단가를 제품별로 넣는 역할
//            productDTOList.add(productDTO);
//        }
//        return productDTOList;
        // 선생님이 쓴 코드~
        List<Product> products = productRepository.getAllProducts();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product data : products) {
            productDTOList.add(ProductDTO.fromProduct(data));
        }
        return productDTOList;
    }
}


