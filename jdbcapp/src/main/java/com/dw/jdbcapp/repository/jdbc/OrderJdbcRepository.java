package com.dw.jdbcapp.repository.jdbc;

import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.repository.iface.OrderRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderJdbcRepository implements OrderRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    @Override
    public List<Order> getAllOrders(){
        List<Order> orders =new ArrayList<>();

        String query = "select * from 주문";
        try (Connection connection = DriverManager.getConnection(
                URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("데이터베이스 연결 성공");
            // 조회 결과가 resultSet에 담겨있음
            // resultSet으로부터 데이터를 꺼내서 Customer클래스의 인스턴스에 저장
            // 생성된 인스턴스들은 List<Customer>에 추가해야 함
            while (resultSet.next()) {
                Order order = new Order();

                order.setOrderId(resultSet.getString("주문번호"));
                order.setCustomerId(resultSet.getString("고객번호"));
                order.setEmployeeId(resultSet.getString("사원번호"));
                order.setOrderDate(resultSet.getDate("주문일").toLocalDate());
                order.setRequestDate(resultSet.getDate("요청일").toLocalDate());
                order.setShippingDate(resultSet.getDate("발송일").toLocalDate());

                orders.add(order); // 리스트에 추가
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 예외가 발생한 과정의 정보를 출력
        }
        return orders;
    }
    @Override
    public Order getOrderById(String orderNumber){
        Order order =new Order();

        String query = "select * from 주문 where 주문번호 = ?";

        try(
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement pstmt = connection.prepareStatement(query);
        ){
            System.out.println("데이터베이스 연결 성공");
            pstmt.setString(1, orderNumber);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    order.setOrderId(resultSet.getString("주문번호"));
                    order.setCustomerId(resultSet.getString("고객번호"));
                    order.setEmployeeId(resultSet.getString("사원번호"));
                    order.setOrderDate(resultSet.getDate("주문일").toLocalDate());
                    order.setRequestDate(resultSet.getDate("요청일").toLocalDate());
                    order.setShippingDate(resultSet.getDate("발송일").toLocalDate());

                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
    @Override
    public List<Order> getOrderByIdAndCustomer(int productNumber, String customerId) {
        List<Order> orders = new ArrayList<>();
        String query = "select * from 주문 where 고객번호 = ? and" +
                " 주문번호 in (select 주문번호 from 주문세부 where 제품번호 = ?)";
        try (
                Connection connection = DriverManager.getConnection(
                        URL, USER, PASSWORD);
                PreparedStatement pstmt = connection.prepareStatement(query)) {
            System.out.println("데이터베이스 연결 성공");
            pstmt.setString(1, customerId);
            pstmt.setInt(2, productNumber);
            try(ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    Order order = new Order();
                    order.setOrderId(resultSet.getString("주문번호"));
                    order.setCustomerId(resultSet.getString("고객번호"));
                    order.setEmployeeId(resultSet.getString("사원번호"));
                    order.setOrderDate(LocalDate.parse(resultSet.getString("주문일")));
                    order.setRequestDate(LocalDate.parse(resultSet.getString("요청일")));
                    order.setShippingDate(LocalDate.parse(resultSet.getString("발송일")));
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public int saveOrder(Order order) {
        return 0;
    }

    @Override
    public String updateOrderWithShippingDate(String id, String date) {
        return "";
    }

    @Override
    public List<Map<String, Double>> getTopCitiesByTotalOrderAmount(int limit) {
        return List.of();
    }

    @Override
    public List<Map<String, Double>> getOrderCountByYearForCity(String city) {
        return List.of();
    }


}
