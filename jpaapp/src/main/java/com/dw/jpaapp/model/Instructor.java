package com.dw.jpaapp.model;

import com.dw.jpaapp.DTO.InstructorDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "career")
    private String career;

    @OneToMany(mappedBy = "instructor_fk")
    private List<Course> courseList = new ArrayList<>();


    //InstructorDTO 매핑 메서드 작성
    public InstructorDTO toDTO(){
//        InstructorDTO instructorDTO = new InstructorDTO();
//        instructorDTO.setId(this.id);
//        instructorDTO.setName(this.name);
//        instructorDTO.setCareer(this.career);
//        List<Long> courseIds = new ArrayList<>();
//        for (Course data: courseList){
//            courseIds.add(data.getId());
//    }
//        instructorDTO.setCourseIds(courseIds);
//        return instructorDTO;

        //람다식 표현법
        List<Long> instructorIds = courseList.stream().map(Course::getId).toList();
        return new InstructorDTO(this.id,this.name,this.career,instructorIds);
}
}

//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@Entity
//@Table(name = "instructor")
//public class Instructor {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;
//    @Column(name = "name", nullable = false)
//    private String name;
//    @Column(name = "career")
//    private String career;
//
//    @OneToMany(mappedBy = "instructor_fk") //instructor_fk이라는 필드를 가지고 있는 주체가 주인(즉, 왜래키 필드명)
//    private List<Course> courseList = new ArrayList<>();
//}