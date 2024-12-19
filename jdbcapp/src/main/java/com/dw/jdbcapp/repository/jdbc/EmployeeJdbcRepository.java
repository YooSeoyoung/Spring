package com.dw.jdbcapp.repository.jdbc;

import com.dw.jdbcapp.model.Employee;
import com.dw.jdbcapp.repository.iface.EmployeeRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeJdbcRepository implements EmployeeRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        String query = "select * from 사원";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("데이터베이스 연결 성공");
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getString("사원번호"));
                employee.setEmployeeKoreanName(resultSet.getString("이름"));
                employee.setEmployeeEnglishName(resultSet.getString("영문이름"));
                employee.setPosition(resultSet.getString("직위"));
                employee.setGender(resultSet.getString("성별"));
                employee.setBirthday(resultSet.getDate("생일").toLocalDate());
                employee.setDateOfEmployment(resultSet.getDate("입사일").toLocalDate());
                employee.setAddress(resultSet.getString("주소"));
                employee.setCity(resultSet.getString("도시"));
                employee.setArea(resultSet.getString("지역"));
                employee.setHomePhone(resultSet.getString("집전화"));
                employee.setSupervisorId(resultSet.getString("상사번호"));
                employee.setDepartmentId(resultSet.getString("부서번호"));

                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;

    }
    @Override
    public Employee getEmployeeById(String id) {
        Employee employee = new Employee();

        String query = "select * from 사원 where 사원번호=?";

        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement pstmt = connection.prepareStatement(query);

        ) {
            System.out.println("데이터베이스 연결 성공");
            pstmt.setString(1, id);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    employee.setEmployeeId(resultSet.getString("사원번호"));
                    employee.setEmployeeKoreanName(resultSet.getString("이름"));
                    employee.setEmployeeEnglishName(resultSet.getString("영문이름"));
                    employee.setPosition(resultSet.getString("직위"));
                    employee.setGender(resultSet.getString("성별"));
                    employee.setBirthday(resultSet.getDate("생일").toLocalDate());
                    employee.setDateOfEmployment(resultSet.getDate("입사일").toLocalDate());
                    employee.setAddress(resultSet.getString("주소"));
                    employee.setCity(resultSet.getString("도시"));
                    employee.setArea(resultSet.getString("지역"));
                    employee.setHomePhone(resultSet.getString("집전화"));
                    employee.setSupervisorId(resultSet.getString("상사번호"));
                    employee.setDepartmentId(resultSet.getString("부서번호"));
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
    @Override
    public List<Map<String, Object>> getEmployeesWithDepartName() {
        String query = "select 이름, 입사일, 부서명 from 사원 "
                + "inner join 부서 on 사원.부서번호 = 부서.부서번호";
        List<Map<String, Object>> employees = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(
                URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Map<String, Object> employee = new HashMap<>();
                employee.put("이름", resultSet.getString("이름"));
                employee.put("입사일", resultSet.getString("입사일"));
                employee.put("부서명", resultSet.getString("부서명"));
                employees.add(employee);
            }
            for (Map<String, Object> employee : employees) {
                System.out.println(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }
    @Override
    public List<Employee> getEmployeesWithDepartmentAndPosition(
            String departmentNumber, String position
    ) {
        List<Employee> employees = new ArrayList<>();
        String query = "select * from 사원 where 부서번호 = ? and 직위 = ?";
        try (
                Connection connection = DriverManager.getConnection(
                        URL, USER, PASSWORD);
                PreparedStatement pstmt = connection.prepareStatement(query)) {
            System.out.println("데이터베이스 연결 성공");
            pstmt.setString(1, departmentNumber);
            pstmt.setString(2, position);
            try(ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    Employee employee = new Employee();

                    employee.setEmployeeId(resultSet.getString("사원번호"));
                    employee.setEmployeeKoreanName(resultSet.getString("이름"));
                    employee.setEmployeeEnglishName(resultSet.getString("영문이름"));
                    employee.setPosition(resultSet.getString("직위"));
                    employee.setGender(resultSet.getString("성별"));
                    employee.setBirthday(LocalDate.parse(resultSet.getString("생일")));
                    employee.setDateOfEmployment(LocalDate.parse(resultSet.getString("입사일")));
                    employee.setAddress(resultSet.getString("주소"));
                    employee.setCity(resultSet.getString("도시"));
                    employee.setArea(resultSet.getString("지역"));
                    employee.setHomePhone(resultSet.getString("집전화"));
                    employee.setSupervisorId(resultSet.getString("상사번호"));
                    employee.setDepartmentId(resultSet.getString("부서번호"));

                    employees.add(employee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
    @Override
    public Employee postEmployee(Employee employee){
        String query ="insert into 사원 values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){

            preparedStatement.setString(1,employee.getEmployeeId());
            preparedStatement.setString(2,employee.getEmployeeKoreanName());
            preparedStatement.setString(3,employee.getEmployeeEnglishName());
            preparedStatement.setString(4,employee.getPosition());
            preparedStatement.setString(5,employee.getGender());
            preparedStatement.setString(6,employee.getBirthday().toString());
            preparedStatement.setString(7,employee.getDateOfEmployment().toString());
            preparedStatement.setString(8,employee.getAddress());
            preparedStatement.setString(9,employee.getCity());
            preparedStatement.setString(10,employee.getArea());
            preparedStatement.setString(11,employee.getHomePhone());
            preparedStatement.setString(12,employee.getSupervisorId());
            preparedStatement.setString(13,employee.getDepartmentId());

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return employee;
    }
}
