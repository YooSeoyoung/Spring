package com.dw.jpaapp.repository;

import com.dw.jpaapp.DTO.StudentSummaryDTO;
import com.dw.jpaapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {

    //Student findByName(String name);
    //return형을 List에 담아서 응답하도록 선언할 수 있음

    //List<Student> findByName(String name);
    //null을 리턴하는 것은 프로그램 종료로 연결될 수 있으므로 위험함

    // 단일 객체(Student) 리턴 함수의 경우, null을 리턴할 가능성이 있으므로
    // 아래 3가지 방법 중에 하나를 사용하는 것이 좋음
    //1. null 체크를 수행하는 예외처리
    //2. null 데이터에 안전한 List 컬렉션을 사용(List는 컬렉션에 상속되므로 상대적으로 무거움): null값을 허용하지 않기 때문에 null시 빈배열로 응답
    //3. Optional 객체를 사용  ==>null 값이 있을 수 있는 객체를 감싸는 래퍼(wrapper) 클래스(null을 가진 상태로 있음)

    Optional<Student> findByName(String name);

    //JPQL
    //JPA 메서드쿼리의 작명법에 없는 메서드를 사용하고자 하면 이 메서드가 수행해야할 sql쿼리를 직접 작성해주면 JPA는 수행 가능
    @Query("select s from Student s where s.name= :name ")
    Optional<Student> findByName2(String name);

    List<Student> findByEmail(String email);
    List<Student> findByNameAndEmail(String name, String email);
    List<Student> findByNameLike(String name);


    @Query("select new com.dw.jpaapp.DTO.StudentSummaryDTO(s.id, s.name, c.title, i.name) " +
            "from Course c join c.studentList s join c.instructor_fk i order by s.id, c.id")
    List<StudentSummaryDTO> getStudentSummary();

    // Native SQL
    @Query(value = "select student.id, student.name, course.title , instructor.name " +
            "from course " +
            "join instructor on course.instructor_id = instructor.id " +
            "join course_student on course.id = course_student.course_id " +
            "join student on student.id = course_student.student_id " +
            "order by student.id, course.id", nativeQuery = true)
    List<Object[]> getStudentSummaryNativeSQL();

}
