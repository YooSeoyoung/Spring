package com.dw.jpaapp.service;

import com.dw.jpaapp.DTO.InstructorDTO;
import com.dw.jpaapp.model.Course;
import com.dw.jpaapp.model.Instructor;
import com.dw.jpaapp.repository.CourseRepository;
import com.dw.jpaapp.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InstructorService {

    @Autowired // hibernate가 주입되므로 jpa의 메서드 사용 가능
    InstructorRepository instructorRepository;

    @Autowired
    CourseRepository courseRepository;

    public List<InstructorDTO> getAllInstructors(){
        return instructorRepository.findAll().stream().map(Instructor::toDTO).collect(Collectors.toList());
    }

    public List<InstructorDTO> getInstructor(Long id){
        return instructorRepository.findById(id).stream().map(Instructor::toDTO).toList();
    }


    public InstructorDTO saveInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = new Instructor();
        instructor.setName(instructorDTO.getName());
        instructor.setCareer(instructorDTO.getCareer());
        instructor.setCourseList(instructorDTO.getCourseIds().stream()
                .map(id -> courseRepository.findById(id))
                .map(optional -> optional.orElseThrow(() -> new RuntimeException("No course")))
                .peek(course -> course.setInstructor_fk(instructor))// peek은 안에 있는 데이터를 바꾸거나 선택하는게 아니고 내부 메서드를 호출할 수 있는 기능을 가지고 있음
                // instructor가 추가가 되어 course가 변경이 됨으로 course에다가 변경된 instructor를 수정하는 작업
                .toList()
        );
        return instructorRepository.save(instructor).toDTO();
    }
}
//        Instructor instructor = new Instructor();
//        instructor.setName(instructorDTO.getName());
//        instructor.setCareer(instructorDTO.getCareer());
//
//        List<Course> courseList = new ArrayList<>();
//        for (Long id : instructorDTO.getCourseIds()){
//            Optional<Course> courseOptional = courseRepository.findById(id);
//            if (courseOptional.isPresent()){
//                Course course = courseOptional.get();
//                courseList.add(course);
//
//                course.setInstructor_fk(instructor);
//
//            }
//            }
//            instructor.setCourseList(courseList);
//            return instructorRepository.save(instructor).toDTO();
//        }


