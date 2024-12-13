package com.dw.firstapp.Controller;

import com.dw.firstapp.Service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //중요!! 이 어노테이션이 있기 때문에 이 클래스가 컨트롤러 역할을 하는 것임
public class HelloController {
    @Autowired //(자동임의연결) 바로 사용 가능한 클래스를 만들기 위해 자동으로 공유할 수 있게 하는 어노테이션
    HelloService helloService; // 인스턴스 객체를 참조 중

    //톰캣에다가 () 안에  문자가 도메인 주소 뒤에 있으면 내부에 있는 메서드를 호출해달라는 의미( 중복 불가)
    @GetMapping("/sayhello") // 가능하면 소문자로 쓰기
    public String hello(){
        //return "Hello World!"; // 톰캣이 return 값을 JSON 문자열 포멧으로 변경하여 화면으로 출력(직접 전달)
        // 서비스 클래스에 정의된 서비스 메서드를 호출해서 사용
        //HelloService helloService =new HelloService(); // 다른 클래스에 있는 기능을 사용하기 위해 인스턴스화 필요
        return helloService.hello(); // 정적 메서드가 아님!!!!!
    }
}
