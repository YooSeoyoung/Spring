package com.dw.thirdapp.controller;

import org.aspectj.weaver.patterns.DeclareTypeErrorOrWarning;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController_1 {
    @GetMapping("/restcontrollertest")
    public String restcontroller(){
        return "Rest Controller Test is doing well!";
    }

}

