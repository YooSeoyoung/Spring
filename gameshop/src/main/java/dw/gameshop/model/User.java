package dw.gameshop.model;

import dw.gameshop.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
@Table(name="user")
public class User {  //setter가 없음( 처음 만들어놓은 이후에는 변경을 안하겠다는 의미) - > 가입할때 이외로는 절대로 바꿀 수 없다는 의미

    @Id
    @Column(name="user_name")
    private String userName; // ID의 역할
    @Column(name="password", nullable = false)
    private String password;
    @Column(name="email", nullable = false, unique = true)
    private String email;
    @Column(name = "real_name", nullable = false)
    private String realName;

    @ManyToOne
    @JoinColumn(name = "user_authority")
    private Authority authority;

    @Column(name="created_at", updatable = false)
    private LocalDateTime createdAt;

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDTO toDto() {
        return new UserDTO(
                this.userName,
                null,  // 패스워드 보호용(서버에서)
                this.email,
                this.realName,
                authority.getAuthorityName()
        );
    }
}
