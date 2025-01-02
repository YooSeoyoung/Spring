package com.dw.jpaapp.repository;

import com.dw.jpaapp.DTO.InstructorGithubDTO;
import com.dw.jpaapp.JpaappApplication;
import com.dw.jpaapp.model.InstructorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InstructorProfileRepository extends JpaRepository<InstructorProfile,Long> {

    @Query("SELECT new com.dw.jpaapp.DTO.InstructorGithubDTO(i.id,i.name,ip.githubUrl)"+" FROM Instructor i JOIN InstructorProfile ip ON i.id = ip.instructor.id")
    List<InstructorGithubDTO> getInstructorGithub();
}
