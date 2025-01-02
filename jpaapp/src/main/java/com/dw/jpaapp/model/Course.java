package com.dw.jpaapp.model;

import com.dw.jpaapp.DTO.CourseDTO;
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
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor_fk;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> studentList = new ArrayList<>();

    //CourseDTO 매핑 메서드 작성
    public CourseDTO toDTO() {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(this.id);
        courseDTO.setTitle(this.title);
        courseDTO.setDescription(this.description);
        courseDTO.setInstructorId(this.instructor_fk.getId());
        List<Long> studentIds = new ArrayList<>();
        for (Student data : studentList) {
            studentIds.add(data.getId());

        }
        courseDTO.setStudentIds(studentIds);
        return courseDTO;


    }
}

//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@Entity
//@Table(name = "course")
//public class Course {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;
//
//    @Column(name = "title",nullable = false)
//    private String title;
//
//    @Column(name = "description")
//    private String description;
//
//    @ManyToOne
//    @JoinColumn(name = "instructor_id") // 외래키로 instructor id를 쓰겠다는 의미
//    private Instructor instructor_fk;
//
//    @ManyToMany
//    @JoinTable(name = "course_student",
//            joinColumns = @JoinColumn(name = "course_id"),
//            inverseJoinColumns = @JoinColumn(name = "student_id")) //영향을 받는 사람
//    private List<Student> studentList = new ArrayList<>();
//}