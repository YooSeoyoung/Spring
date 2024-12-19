package com.dw.jdbcapp.repository.jdbc;

import com.dw.jdbcapp.model.Department;
import com.dw.jdbcapp.repository.iface.DepartmentRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentJdbcRepository implements DepartmentRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    @Override
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();

        String query = "select * from 부서";
        try (
                Connection connection = DriverManager.getConnection(
                        URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Department department = new Department();
                department.setDepartmentId(resultSet.getString("부서번호"));
                department.setDepartmentName(resultSet.getString("부서명"));
                departments.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 예외가 발생한 과정의 정보를 출력
        }
        return departments;
    }
    @Override
    public Department saveDepartment(Department department) {
        // 매개변수로 전달받은 deartment 객체 정보를 mysql에 insert한 후 성공이면 해당 객체를 리턴함
        String query = "insert into 부서 values(?, ?)"; // ? => 플레이스 홀더 (? 자리에 매개변수를 넣을 예정)
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) { // int를 리턴(가서 작업한 행의 개수)// -> 쿼리 구문을 완성시키는 구문(동적 바인딩을 사용하기 위함)
            // 장점 : 조금 더 보안적인 측면을 고려(쿼리 따로 값 따로 보냄)
            preparedStatement.setString(1, department.getDepartmentId()); // -> 인덱스 번호 입력(mysql은 0번 인덱스가 없기 때문에 1번)
            preparedStatement.setString(2, department.getDepartmentName()); // -> 인덱스 번호 입력(mysql은 0번 인덱스가 없기 때문에 2번)
            preparedStatement.executeUpdate();
            System.out.println("Insert 성공");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }

    @Override
    public Department updateDepartment(Department department) {
        String query = "update 부서 set 부서명=? where 부서번호=?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) { // -> 쿼리 구문을 완성시키는 구문(동적 바인딩을 사용하기 위함)
            // 장점 : 조금 더 보안적인 측면을 고려(쿼리 따로 값 따로 보냄)
            preparedStatement.setString(1, department.getDepartmentName()); // 첫번째 물음표에는 뭐
            preparedStatement.setString(2, department.getDepartmentId()); // 두번째 물음표에는 뭐
            preparedStatement.executeUpdate();
            System.out.println("update 성공");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }
    @Override
    public String deleteDepartment(String id) {
        String query = "delete from 부서 where 부서번호 = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            System.out.println("DELETE 성공");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
