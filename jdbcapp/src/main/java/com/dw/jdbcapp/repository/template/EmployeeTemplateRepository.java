package com.dw.jdbcapp.repository.template;

import com.dw.jdbcapp.model.Employee;
import com.dw.jdbcapp.repository.iface.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class EmployeeTemplateRepository implements EmployeeRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<Employee> employeeRowMapper = new RowMapper<Employee>() {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setEmployeeId(rs.getString("사원번호"));
            employee.setEmployeeKoreanName(rs.getString("이름"));
            employee.setEmployeeEnglishName(rs.getString("영문이름"));
            employee.setPosition(rs.getString("직위"));
            employee.setGender(rs.getString("성별"));
            employee.setBirthday(rs.getDate("생일").toLocalDate());
            employee.setDateOfEmployment(rs.getDate("입사일").toLocalDate());
            employee.setAddress(rs.getString("주소"));
            employee.setCity(rs.getString("도시"));
            employee.setArea(rs.getString("지역"));
            employee.setHomePhone(rs.getString("집전화"));
            employee.setSupervisorId(rs.getString("상사번호"));
            employee.setDepartmentId(rs.getString("부서번호"));

            return employee;
        }
    };

    @Override
    public List<Employee> getAllEmployees() {
        String query = "select * from 사원";
        return jdbcTemplate.query(query,employeeRowMapper);
    }

    @Override
    public Employee getEmployeeById(String id) {
        String query = "select * from 사원 where 사원번호=?";
        return jdbcTemplate.queryForObject(query,employeeRowMapper,id);
    }

    @Override
    public List<Map<String, Object>> getEmployeesWithDepartName() {
        String query = "select 이름, 입사일, 부서명 from 사원 inner join 부서 on 사원.부서번호= 부서.부서번호";

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Map<String, Object> employee = new HashMap<>();
            employee.put("이름", rs.getString("이름"));
            employee.put("입사일", rs.getString("입사일"));
            employee.put("부서명", rs.getString("부서명"));

            return employee;
        });
    }
    //        RowMapper<Map<String, Object>> mapper = new RowMapper<Map<String, Object>>() {
//            @Override
//            public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Map<String, Object> employee = new HashMap<>();
//                employee.put("이름", rs.getString("이름"));
//                employee.put("입사일", rs.getString("입사일"));
//                employee.put("부서명", rs.getString("부서명"));
//                return employee;
//
//            }
//        };
//        return jdbcTemplate.query(query, mapper);
//    }

    @Override
    public List<Employee> getEmployeesWithDepartmentAndPosition(
            String departmentNumber, String position){
        String query = "select * from 사원 where 부서번호 = ? and 직위 = ?";
        return jdbcTemplate.query(query,employeeRowMapper,departmentNumber,position);
    }


        @Override
        public Employee postEmployee (Employee employee){
            String query ="insert into 사원 values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            jdbcTemplate.update(query,employee.getEmployeeId(),employee.getEmployeeKoreanName(),employee.getEmployeeEnglishName(),
                    employee.getPosition(),employee.getGender(),employee.getBirthday(),employee.getDateOfEmployment(),
                    employee.getAddress(),employee.getCity(),employee.getArea(),employee.getHomePhone(),
                    employee.getSupervisorId(),employee.getDepartmentId());

            return employee;
        }
    }
