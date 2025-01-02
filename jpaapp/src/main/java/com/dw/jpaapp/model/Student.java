package com.dw.jpaapp.model;

import com.dw.jpaapp.DTO.StudentDTO;
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
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @ManyToMany(mappedBy = "studentList")
    private List<Course> courseList = new ArrayList<>();


    public StudentDTO toDTO() {
//        StudentDTO studentDTO = new StudentDTO();
//        studentDTO.setId(this.id);
//        studentDTO.setName(this.name);
//        studentDTO.setEmail(this.email);
//        List<Long> courseIds = new ArrayList<>();
//        for (Course data : courseList){
//            courseIds.add(data.getId());
//        }
//        studentDTO.setCourseIds(courseIds);
//        return studentDTO;
//    }
        //람다식 표현법
        List<Long> courseIds = courseList.stream().map(Course::getId).toList();
        return new StudentDTO(this.id, this.name, this.email, courseIds);
    }
}

////lombok의 역할!!!!
//// 별도로의 클래스 내부에 생성자, 게터,세터를 직접 불러올 필요가 없음
//@NoArgsConstructor // 매개변수 없는 기본 생성자
//@AllArgsConstructor // 필드를 전부 매개변수로 가진 생성자
//@Getter // 게터
//@Setter // 세터
//@Entity
//@Table(name = "student") // 명시적으로 테이블 이름 설정
//public class Student {
//    @Id // PK로 설정
//    @GeneratedValue(strategy = GenerationType.IDENTITY)// 아이디 숫자 자동 증가
//    @Column(name ="id") // @Id를 사용하면 안써도 상관 없음
//    private Long id; // pk
//
//    @Column(name ="name",nullable = false)
//    private String name;
//
//    @Column(name ="email",nullable = false,unique = true)
//    private String email; //uk
//
//    @ManyToMany(mappedBy = "studentList")
//    private List<Course> courseList = new ArrayList<>();
//
//}