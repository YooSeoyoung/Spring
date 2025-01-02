package com.dw.jdbcapp.repository.template;

import com.dw.jdbcapp.DTO.ProductDTO;
import com.dw.jdbcapp.exception.ResourceNotFoundException;
import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.repository.iface.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductTemplateRepository implements ProductRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<Product> productRowMapper= new RowMapper<Product>() {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();

            product.setProductId(rs.getInt("제품번호"));
            product.setProductName(rs.getString("제품명"));
            product.setPackagingUnit(rs.getString("포장단위"));
            product.setUnitPrice(rs.getDouble("단가"));
            product.setInventory(rs.getInt("재고"));

            return product;
        }
    };


    @Override
    public List<Product> getAllProducts() {
        String query ="select * from 제품";
        return jdbcTemplate.query(query,productRowMapper);
    }

    @Override
    public Product getProductById(int productNumber) {
        String query = "select * from 제품 where 제품번호 = ?";
        try {
            return jdbcTemplate.queryForObject(query, productRowMapper, productNumber);
        } catch (EmptyResultDataAccessException e) {
            //자바에 정의된 예외를 사용자 정의 예외로 바꿈으로 인해
            //CustomExceptionHandler의 코드를 단순하게 유지 가능
            //예외들을 비슷한 유형으로 그룹지울 수 있음
            throw new ResourceNotFoundException("제품번호가 올바르지 않습니다 : "+ productNumber);
        }
    }

    @Override
    public Product postProduct(Product product) {
        String query ="insert into 제품(제품번호, 제품명, 포장단위, 단가,재고) values(?,?,?,?,?)";
      jdbcTemplate.update(query, product.getProductId(),product.getProductName(),product.getPackagingUnit(),product.getUnitPrice(),product.getInventory());
      return product;
    }

    @Override
    public Product putProductPrice(Product product) {
        String query = "update 제품 set 제품명=?, 포장단위 =?, 단가=?, 재고=? where 제품번호=? ";
        jdbcTemplate.update(query,product.getProductName(),product.getPackagingUnit(),product.getUnitPrice(),product.getInventory(), product.getProductId());
        return product;
    }

    @Override
    public int deleteProduct(int id) {
        String query = "delete from 제품 where 제품번호 = ?";
         jdbcTemplate.update(query,id);
         return id;
    }


    @Override
    public List<Product> getProductsBelowPrice(double price){
        String query = "select * from 제품 where 단가 < ?";
        return jdbcTemplate.query(query,productRowMapper,price);
    }

    @Override
    public String updateProductWithStock(int id, int stock) {
        String query = "update 제품 set 재고 =? where 제품번호 = ?";
        jdbcTemplate.update(query,stock,id);
        return "제품번호 : " + id + "재고 : " + stock + "변경";
    }

    @Override
    public List<Product> getProductByProductName(String name) {
        String query ="select * from 제품 where 제품명 like ? ";
        String a = "%" + name + "%";
        return jdbcTemplate.query(query,productRowMapper,a); //or 직접 전달도 가능 ( "%"+ name+"%")
    }


    //다른 테이블에서 가지고 와서 합하는게 아닌 하나의 테이블에서 단가랑 재고만 곱하는 거이기때문에
    //굳이 ProductDTO로 rowmapper를 하나 더 만들 필요가 없음( 서비스에서 재고 *단가 수행)
    @Override
    public List<Product> getProductsByStockValue() {
        String query ="select * from 제품 ";
        return jdbcTemplate.query(query,productRowMapper);
    }


    //    @Override
//    public List<ProductDTO> getProductsByStockValue() {
//        String query = "select * from 제품";
//        return "";
////        return jdbcTemplate.query(query,productRowMapper);
//    }
//public List<Product> productStocks(){
//    String query = "select 제품번호, 재고 from 제품 ";
//    return jdbcTemplate.query(query,productRowMapper);
//}
}
