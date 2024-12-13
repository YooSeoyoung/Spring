package com.dw.secondapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepository {

    public String success(){
        return "Test is success!";
    }
}
