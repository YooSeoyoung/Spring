package com.dw.firstapp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/sayhello") // 가능하면 소문자로 쓰기 / ()안에 있는 문자를 도메인 뒤에 작성하면 내부에 있는 메서드를 호출
    public String hello(){
        return "Hello World!";
    }
}
