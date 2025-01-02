package com.dw.jpaapp.model;

import com.dw.jpaapp.DTO.InstructorDTO;
import com.dw.jpaapp.DTO.InstructorProfileDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "instructor_profile")
public class InstructorProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 글자수 3000로 지정
    private Long id; // 강사 소개

    @Column(name = "bio", length = 3000)
    private String bio;

    @Column(name = "github_url")
    private String githubUrl;

    @OneToOne
    @JoinColumn(name = "instructor_id")// 단방향 참조(instructor은 instructorprofile에 대한 정보를 안가지고 있다는 뜻)
    private Instructor instructor;

    //InstructorProfile 매핑 메서드
    public InstructorProfileDTO toDTO(){
            return new InstructorProfileDTO(this.id,this.bio,this.githubUrl,this.instructor.getId());
    }

}
