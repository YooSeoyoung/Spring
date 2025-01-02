package com.dw.jpaapp.service;

import com.dw.jpaapp.DTO.CourseDTO;
import com.dw.jpaapp.DTO.StudentDTO;
import com.dw.jpaapp.model.Course;
import com.dw.jpaapp.model.Instructor;
import com.dw.jpaapp.model.Student;
import com.dw.jpaapp.repository.CourseRepository;
import com.dw.jpaapp.repository.InstructorRepository;
import com.dw.jpaapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired // hibernate가 주입되므로 jpa의 메서드 사용 가능
    CourseRepository courseRepository;

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    StudentRepository studentRepository;

    public List<CourseDTO> getAllCourses(){
//       List<CourseDTO> courseDTOS = new ArrayList<>();
//       for (Course data : courseRepository.findAll()){
//           courseDTOS.add(data.toDTO());
//       }
//        return courseDTOS;
        return courseRepository.findAll().stream().map(Course :: toDTO).collect(Collectors.toList());
    } // stream 반복을 대신 // map은 stream의 개수만큼 동작(toDTO)

    public List<CourseDTO> getCoursesLike(String title){

        return courseRepository.findByTitleLike("%"+ title+"%").stream().map(Course::toDTO).toList();
    }

    public CourseDTO saveCourse(CourseDTO courseDTO){
        Course course = new Course();
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setInstructor_fk(instructorRepository.findById(courseDTO.getInstructorId())
                .orElseThrow(()->new RuntimeException("No instructor"))); // orElseThrow를 넣으면 get 메서드를 안써도돼(즉, optional을 벗어날 수 있음)
        course.setStudentList(courseDTO.getStudentIds().stream()
                .map(id->studentRepository.findById(id))
                .map(optional->optional.orElseThrow(()->new RuntimeException("No Student")))
                .toList()
        );
        return courseRepository.save(course).toDTO();

//        Course course = new Course();
//        course.setTitle(courseDTO.getTitle());
//        course.setDescription(courseDTO.getDescription());
//        course.setInstructor_fk(instructorRepository.findById(courseDTO.getInstructorId()).get());
//        List<Student> studentList = new ArrayList<>();
//        for (Long id : courseDTO.getStudentIds()){
//            Optional<Student> studentOptional = studentRepository.findById(id);
//            if (studentOptional.isPresent()){
//                Student student = studentOptional.get();
//                studentList.add(student);
//            }
//        }
//        course.setStudentList(studentList);
//        return  courseRepository.save(course).toDTO();

    }
}
