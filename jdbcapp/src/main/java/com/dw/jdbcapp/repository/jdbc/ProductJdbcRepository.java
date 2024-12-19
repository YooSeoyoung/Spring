package com.dw.jdbcapp.repository.jdbc;

import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.repository.iface.ProductRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductJdbcRepository implements ProductRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    @Override
    public List<Product> getAllProducts(){
        List<Product> products=new ArrayList<>();

        String query = "select * from 제품";
        try (Connection connection = DriverManager.getConnection(
                URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("데이터베이스 연결 성공");
            // 조회 결과가 resultSet에 담겨있음
            // resultSet으로부터 데이터를 꺼내서 Customer클래스의 인스턴스에 저장
            // 생성된 인스턴스들은 List<Customer>에 추가해야 함
            while (resultSet.next()) {
               Product product = new Product();

                product.setProductId(resultSet.getInt("제품번호"));
                product.setProductName(resultSet.getString("제품명"));
                product.setPackagingUnit(resultSet.getString("포장단위"));
                product.setUnitPrice(resultSet.getDouble("단가"));
                product.setInventory(resultSet.getInt("재고"));


                products.add(product); // 리스트에 추가
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 예외가 발생한 과정의 정보를 출력
        }
        return products;
    }
    @Override
    public  Product getProductById(int productNumber){
        Product product =new Product();

        String query = "select * from 제품 where 제품번호 = ?";

        try(
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement pstmt = connection.prepareStatement(query);
                ){
            System.out.println("데이터베이스 연결 성공");
            pstmt.setInt(1, productNumber);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                   product.setProductId(resultSet.getInt("제품번호"));
                    product.setProductName(resultSet.getString("제품명"));
                    product.setPackagingUnit(resultSet.getString("포장단위"));
                    product.setUnitPrice(resultSet.getDouble("단가"));
                    product.setInventory(resultSet.getInt("재고"));

                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
    @Override
    public Product postProduct(Product product){
        String query = "insert into 제품 values(?,?,?,?,?)"; // ? => 플레이스 홀더 (? 자리에 매개변수를 넣을 예정)
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) { // int를 리턴(가서 작업한 행의 개수)// -> 쿼리 구문을 완성시키는 구문(동적 바인딩을 사용하기 위함)
            // 장점 : 조금 더 보안적인 측면을 고려(쿼리 따로 값 따로 보냄)
            preparedStatement.setInt(1,product.getProductId()); // -> 인덱스 번호 입력(mysql은 0번 인덱스가 없기 때문에 1번)
            preparedStatement.setString(2,product.getProductName()); // -> 인덱스 번호 입력(mysql은 0번 인덱스가 없기 때문에 2번)
            preparedStatement.setString(3,product.getPackagingUnit());
            preparedStatement.setDouble(4,product.getUnitPrice());
            preparedStatement.setInt(5,product.getInventory());
            preparedStatement.executeUpdate();
            System.out.println("Insert 성공");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
    @Override
    public Product putProductPrice(Product product){
        String query = "update 제품 set 제품명=?, 포장단위 =?, 단가=?, 재고=? where 제품번호=? ";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) { // -> 쿼리 구문을 완성시키는 구문(동적 바인딩을 사용하기 위함)
            // 장점 : 조금 더 보안적인 측면을 고려(쿼리 따로 값 따로 보냄)
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2,product.getPackagingUnit());
            preparedStatement.setDouble(3,product.getUnitPrice());
            preparedStatement.setInt(4,product.getInventory());
            preparedStatement.setInt(5,product.getProductId());


            preparedStatement.executeUpdate();
            System.out.println("update 성공");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
    @Override
    public int deleteProduct(int id){
        String query = "delete from 제품 where 제품번호 = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("DELETE 성공");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;

    }
}
