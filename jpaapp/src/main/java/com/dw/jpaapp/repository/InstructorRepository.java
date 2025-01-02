package com.dw.jpaapp.repository;

import com.dw.jpaapp.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor,Long> {
}
