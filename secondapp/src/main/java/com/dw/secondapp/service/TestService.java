package com.dw.secondapp.service;

import com.dw.secondapp.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    TestRepository testRepository;
    public String success(){
        return testRepository.success();
    }
}
