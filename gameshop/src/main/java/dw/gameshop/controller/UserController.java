package dw.gameshop.controller;

import dw.gameshop.dto.UserDTO;
import dw.gameshop.exception.UnauthorizedUserException;
import dw.gameshop.model.User;
import dw.gameshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")  // 회원가입
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(
                userService.registerUser(userDTO),
                HttpStatus.CREATED);
    }

    @PostMapping("/login") // 로그인
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO,
                                        HttpServletRequest request) { //톰캣이 가지고 있는 요소 중 하나(요청이 온 rest api를 관리)
        String username = userDTO.getUserName();
        String password = userDTO.getPassword();

        if (userService.validateUser(username, password)) { // 아이디랑 비번이 일치하면 true
            HttpSession session = request.getSession();  // 세션을 만들어서 가지고 옴(세션 :클라이언트와 서버 간의 연결 상태를 유지하기 위해 사용)
            session.setAttribute("username", username); // username이라는 세션을 하나 만듬
            return  new ResponseEntity<>(
                    "Login successful",
                    HttpStatus.OK);
        } else {
            throw new UnauthorizedUserException("Authentication Failed");
        }
    }

    @PostMapping("/logout")  // 로그아웃
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate(); // 세션 종료
        return new ResponseEntity<>(
                "You have been logged out.",
                HttpStatus.OK);
    }

    @GetMapping("/current-user") // 현재 유저
    public ResponseEntity<UserDTO> getCurrentUser(HttpServletRequest request) {
        User user = userService.getCurrentUser(request);
        return new ResponseEntity<>(user.toDto(), HttpStatus.OK);
    }
}
