package com.dw.jdbcapp.repository.template;

import com.dw.jdbcapp.model.Customer;
import com.dw.jdbcapp.repository.iface.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerTemplateRepository implements CustomerRepository {
    // JDBC Template은 반드시 JDBCTemplate 객체를 의존성 주입 받아 사용해야 함
    @Autowired
    JdbcTemplate jdbcTemplate;
        // 테이블에 내용을 그대롤 가지고 와서 모델에 있는 필드와 1대1로 매핑해주는 역할
    private final RowMapper<Customer> customerRowMapper= new RowMapper<Customer>() {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setCustomerId(rs.getString("고객번호"));
            customer.setCompanyName(rs.getString("고객회사명"));
            customer.setContactName(rs.getString("담당자명"));
            customer.setContactTitle(rs.getString("담당자직위"));
            customer.setAddress(rs.getString("주소"));
            customer.setCity(rs.getString("도시"));
            customer.setRegion(rs.getString("지역"));
            customer.setPhone(rs.getString("전화번호"));
            customer.setMileage(rs.getInt("마일리지"));

            return customer;
        }
    };

    @Override
    public List<Customer> getAllCustomers() {
        String query = "select * from 고객";
        return jdbcTemplate.query(query,customerRowMapper);
    }
    //jdbc
}
