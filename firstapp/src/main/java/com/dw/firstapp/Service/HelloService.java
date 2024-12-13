package com.dw.firstapp.Service;

import com.dw.firstapp.Repository.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //이 어노테이션이 있기 때문에 이 클래스가 서비스 역할을 하는 것임
public class HelloService {
    @Autowired
    HelloRepository helloRepository;
    public String hello(){
       return helloRepository.hello();

        //return "Hello world from service.";
    }

}
