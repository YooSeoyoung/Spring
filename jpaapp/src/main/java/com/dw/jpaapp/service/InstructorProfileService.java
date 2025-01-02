package com.dw.jpaapp.service;

import com.dw.jpaapp.DTO.InstructorGithubDTO;
import com.dw.jpaapp.DTO.InstructorProfileDTO;
import com.dw.jpaapp.model.Instructor;
import com.dw.jpaapp.model.InstructorProfile;
import com.dw.jpaapp.repository.InstructorProfileRepository;
import com.dw.jpaapp.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class InstructorProfileService {

    @Autowired
    InstructorProfileRepository instructorProfileRepository;

    @Autowired
    InstructorRepository instructorRepository;

    public List<InstructorProfileDTO>  getAllInstructorProfiles(){
        return instructorProfileRepository.findAll().stream().map(InstructorProfile ::toDTO).toList();
    }

   public List<Object[]> getInstructorGithub() {
       List<Object[]> objects = new ArrayList<>();
        for (InstructorProfile temp: instructorProfileRepository.findAll() ) {
            Object[] data = new Object[3];
           data[0] = temp.getInstructor().getId();
           data[1] = temp.getInstructor().getName();
           data[2] = temp.getGithubUrl();
           objects.add(data);
        }
        return objects;
        }

        public List<InstructorGithubDTO> getInstructorProfile(){
        return instructorProfileRepository.getInstructorGithub();
        }
    }
