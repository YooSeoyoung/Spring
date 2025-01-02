package com.dw.jpaapp.service;

import com.dw.jpaapp.DTO.StudentDTO;
import com.dw.jpaapp.DTO.StudentSummaryDTO;
import com.dw.jpaapp.model.Course;
import com.dw.jpaapp.model.Student;
import com.dw.jpaapp.repository.CourseRepository;
import com.dw.jpaapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired // hibernate가 주입되므로 jpa의 메서드 사용 가능
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    public List<StudentDTO> getAllStudents(){
       return studentRepository.findAll().stream().map(Student ::toDTO).collect(Collectors.toList());
    }
    //JPA 메서드 쿼리를 연습하기 위한 예제
    //여러 방식의 메서드 쿼리를 수행해보는 연습 메서드
//    public String getStudentInfo(){
//        // findByName : 이름 필드 기준으로 학생 데이터를 조회하는 메서드 쿼리
//        // return studentRepository.findByName("Steve").toDTO().toString();
//        //findByName이 List<Student>를 리턴하는 경우도 있음
//    }
//    public String getStudentInfo(){
//        // List형태로 담아서 리턴
//        return studentRepository.findByName("Sam").stream().map(Student::toDTO).toList().toString();
//    }

    public String getStudentInfo(){
        // Optional형태로 담아서 리턴// 내부에 데이터를 안전하게 가질 수 있음
        Optional<Student> student = studentRepository.findByName2("Steve");
        if (student.isEmpty()){
            throw new RuntimeException("없는 데이터");
        }
        return student.get().toDTO().toString();  // get : student 값을 리턴하라 ( null 아니면)
        //람다식 표현방법
        //return studentRepository.findByName("Steve").map(Student::toDTO).map(StudentDTO::toString).orElseThrow(()->new RuntimeException("없는 데이터"));
        //orElseThrow(()->new RuntimeException("없는 데이터")) -> 예외처리 !!
    }
    public StudentDTO saveStudent(StudentDTO studentDTO){  //id는 insert 하지 않음( 자동으로 1씩 증가하기 때문에) // update일 경우에는 id를 입력해야됨
    Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setCourseList(studentDTO.getCourseIds().stream()
                .map(id->courseRepository.findById(id))
            .map(optional->optional.orElseThrow(()->new RuntimeException("No course")))
            .peek(course->course.getStudentList().add(student))
            .toList()
        );
        return studentRepository.save(student).toDTO();
    }
//    Student student = new Student();
//        student.setName(studentDTO.getName());
//        student.setEmail(studentDTO.getEmail());
//        List<Course> courseList = new ArrayList<>();
//        for (Long id : studentDTO.getCourseIds()){
//            Optional<Course> courseOptional = courseRepository.findById(id);
//            if (courseOptional.isPresent()){
//                Course course = courseOptional.get();
//                course.getStudentList().add(student);
//                courseList.add(course);
//            }
//        }
//        student.setCourseList(courseList);
//        return studentRepository.save(student).toDTO();
        public List<StudentSummaryDTO> getStudentSummary(){
        return  studentRepository.getStudentSummary();
        }

        //NativeSQL을 사용
    public List<StudentSummaryDTO> getStudentSummaryNativeSQL() {
        List<Object[]> objects = studentRepository.getStudentSummaryNativeSQL();
        List<StudentSummaryDTO> studentSummaryDTOS = new ArrayList<>();
        for (Object[] data : objects) {
            StudentSummaryDTO studentSummaryDTO = new StudentSummaryDTO(
                    (Long)data[0],
                    data[1] != null ? data[1].toString() : "",
                    data[2] != null ? data[2].toString() : "",
                    data[3] != null ? data[3].toString() : ""
            );
            studentSummaryDTOS.add(studentSummaryDTO);
        }
        return studentSummaryDTOS;
    }
}
