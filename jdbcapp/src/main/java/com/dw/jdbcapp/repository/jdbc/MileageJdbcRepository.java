package com.dw.jdbcapp.repository.jdbc;

import com.dw.jdbcapp.model.MileGrade;
import com.dw.jdbcapp.repository.iface.MileageRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MileageJdbcRepository implements MileageRepository {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    @Override
    public List<MileGrade> getAllMileages(){
        List<MileGrade> mileGrades = new ArrayList<>();

        String query = "select * from 마일리지등급";
        try (
                Connection connection = DriverManager.getConnection(
                        URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)){
            while (resultSet.next()) {
               MileGrade mileGrade = new MileGrade();
                mileGrade.setRatingName(resultSet.getString("등급명"));
                mileGrade.setLowerLimitMileage(resultSet.getInt("하한마일리지"));
                mileGrade.setUpperLimitMileage(resultSet.getInt("상한마일리지"));
                mileGrades.add(mileGrade);
            }
        }catch (SQLException e) {
            e.printStackTrace(); // 예외가 발생한 과정의 정보를 출력
        }
        return mileGrades;

    }
}
