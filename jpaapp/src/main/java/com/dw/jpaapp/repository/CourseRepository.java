package com.dw.jpaapp.repository;

import com.dw.jpaapp.DTO.CourseDTO;
import com.dw.jpaapp.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {// <T, ID> => 어떤 모델의 레포지토리// PK의 데이터  타입

    List<Course> findByTitleLike(String title);
}
