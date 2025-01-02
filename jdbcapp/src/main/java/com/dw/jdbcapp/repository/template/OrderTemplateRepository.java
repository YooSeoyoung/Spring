package com.dw.jdbcapp.repository.template;

import com.dw.jdbcapp.exception.ResourceNotFoundException;
import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.model.OrderDetail;
import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.repository.iface.OrderDetailRepository;
import com.dw.jdbcapp.repository.iface.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderTemplateRepository implements OrderRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<Order> orderRowMapper = new RowMapper<Order>() {
        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();
            order.setOrderId(rs.getString("주문번호"));
            order.setCustomerId(rs.getString("고객번호"));
            order.setEmployeeId(rs.getString("사원번호"));
            order.setOrderDate(rs.getDate("주문일").toLocalDate());
            order.setRequestDate(rs.getDate("요청일").toLocalDate());
            order.setShippingDate(rs.getDate("발송일").toLocalDate());

            return order;
        }
    };


    @Override
    public List<Order> getAllOrders() {
        String query = "select * from 주문";
        return jdbcTemplate.query(query, orderRowMapper);
    }

    @Override
    public Order getOrderById(String orderNumber) {
        String query = "select * from 주문 where 주문번호 = ?";
        try {
            return jdbcTemplate.queryForObject(query, orderRowMapper, orderNumber);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("주문번호가 올바르지 않습니다 : " + orderNumber);
        }
    }


    @Override
    public List<Order> getOrderByIdAndCustomer(int productNumber, String customerId) {
        String query = "select * from 주문 where 고객번호 = ? and" +
                " 주문번호 in (select 주문번호 from 주문세부 where 제품번호 = ?)";
        return jdbcTemplate.query(query, orderRowMapper, productNumber, customerId);
    }

    @Override
    public int saveOrder(Order order) {
        String query = "insert into 주문(주문번호, 고객번호, 사원번호, 주문일,요청일) values(?,?,?,?,?)";
        return jdbcTemplate.update(query, order.getOrderId(), order.getCustomerId(), order.getEmployeeId(), order.getOrderDate().toString(), order.getRequestDate().toString());
    }


    @Override
    public String updateOrderWithShippingDate(String id, String date) {
        String query = "update 주문 set 발송일 = ? where 주문번호 =?";
        jdbcTemplate.update(query, date, id);
        return "주문번호 : " + id + "의 배송일을 " + date+"으로 업데이트하였습니다.";
    }

    @Override
    public List<Map<String, Double>> getTopCitiesByTotalOrderAmount(int limit) {
        String query = "select sum(주문세부.단가* 주문세부.주문수량) as 도시별주문금액합,고객.도시 \n" +
                "from 주문 inner join 고객\n" +
                "on 주문.고객번호 =고객.고객번호 \n" +
                "inner join 주문세부\n" +
                "on 주문.주문번호 =주문세부.주문번호 \n" +
                "group by 도시\n" +
                "order by 도시별주문금액합 desc\n" +
                "limit ?";

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Map<String, Double> order = new HashMap<>();
            order.put(rs.getString("도시"), rs.getDouble("도시별주문금액합"));
            return order;
        }, limit);
    }

    @Override
    public List<Map<String, Double>> getOrderCountByYearForCity(String city) {
        String query = "select year(주문일) as 년도별, count(*) as 주문건수\n" +
                "from 주문 inner join 고객\n" +
                "on 주문.고객번호 = 고객.고객번호\n" +
                "where 도시 in (?)\n" +
                "group by 도시,년도별";

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Map<String, Double> order = new HashMap<>();
            order.put(rs.getString("년도별"), rs.getDouble("주문건수"));
            return order;
        }, city);
    }
}

