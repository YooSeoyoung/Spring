package com.dw.jdbcapp.repository.template;

import com.dw.jdbcapp.model.OrderDetail;
import com.dw.jdbcapp.repository.iface.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class OrderDetailTemplateRepository implements OrderDetailRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<OrderDetail> orderDetailRowMapper = new RowMapper<OrderDetail>() {
        @Override
        public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
            OrderDetail orderDetail = new OrderDetail();

            orderDetail.setOrderId(rs.getString("주문번호"));
            orderDetail.setProductId(rs.getInt("제품번호"));
            orderDetail.setUnitPrice(rs.getDouble("단가"));
            orderDetail.setOrderQuantity(rs.getInt("주문수량"));
            orderDetail.setDiscountPercent(rs.getDouble("할인율"));

            return orderDetail;
        }
    };

    @Override
    public List<OrderDetail> getAllOrderDetails() {
        String query = "select * from 주문세부";
        return jdbcTemplate.query(query,orderDetailRowMapper);
    }
}
