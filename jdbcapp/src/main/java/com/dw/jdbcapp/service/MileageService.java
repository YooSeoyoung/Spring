package com.dw.jdbcapp.service;

import com.dw.jdbcapp.model.MileGrade;
import com.dw.jdbcapp.repository.iface.MileageRepository;
import com.dw.jdbcapp.repository.jdbc.MileageJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MileageService {
    @Autowired
            @Qualifier("mileageTemplateRepository")
    MileageRepository mileageRepository;

    public List<MileGrade> getAllMileages(){
        return mileageRepository.getAllMileages();
    }
}
