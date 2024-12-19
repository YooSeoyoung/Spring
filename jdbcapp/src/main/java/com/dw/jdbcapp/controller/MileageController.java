package com.dw.jdbcapp.controller;


import com.dw.jdbcapp.model.MileGrade;
import com.dw.jdbcapp.service.MileageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MileageController {
    @Autowired
    MileageService mileageService;

    @GetMapping("/find-all-mileage")
    public ResponseEntity<List<MileGrade>> getAllMileages(){
        return new ResponseEntity<>(mileageService.getAllMileages(), HttpStatus.OK);
    }
}
