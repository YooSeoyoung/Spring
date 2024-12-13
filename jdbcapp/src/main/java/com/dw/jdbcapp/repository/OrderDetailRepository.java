package com.dw.jdbcapp.repository;

import com.dw.jdbcapp.model.Customer;
import com.dw.jdbcapp.model.OrderDetail;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDetailRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public List<OrderDetail> getAllOrderDetails(){
        List<OrderDetail> orderDetails = new ArrayList<>();

        String query = "select * from 주문세부";
        try (Connection connection = DriverManager.getConnection(
                URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("데이터베이스 연결 성공");
            // 조회 결과가 resultSet에 담겨있음
            // resultSet으로부터 데이터를 꺼내서 Customer클래스의 인스턴스에 저장
            // 생성된 인스턴스들은 List<Customer>에 추가해야 함
            while (resultSet.next()) {
                OrderDetail orderDetail = new OrderDetail();

                orderDetail.setOrderId(resultSet.getString("주문번호"));
                orderDetail.setProductId(resultSet.getInt("제품번호"));
                orderDetail.setUnitPrice(resultSet.getDouble("단가"));
                orderDetail.setOrderQuantity(resultSet.getInt("주문수량"));
                orderDetail.setDiscountPercent(resultSet.getDouble("할인율"));


                orderDetails.add(orderDetail); // 리스트에 추가
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 예외가 발생한 과정의 정보를 출력
        }
        return orderDetails;


    }
}
