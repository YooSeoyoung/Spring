package dw.gameshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration  //설정(세팅)
public class AppConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {  //  BCryptPasswordEncoder  : 암호화( 복호화가 불가능-> 원래 값을 돌려놓기)


        return new BCryptPasswordEncoder();
    }
}
