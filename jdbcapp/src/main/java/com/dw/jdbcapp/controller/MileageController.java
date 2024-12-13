package com.dw.jdbcapp.controller;


import com.dw.jdbcapp.model.MileGrade;
import com.dw.jdbcapp.service.MileageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MileageController {
    @Autowired
    MileageService mileageService;

    @GetMapping("/find-all-mileage")
    public List<MileGrade> getAllMileages(){
        return mileageService.getAllMileages();
    }
}
