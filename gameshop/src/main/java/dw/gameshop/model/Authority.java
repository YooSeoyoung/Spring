package dw.gameshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "authority")
public class Authority { // 권한 (관리자(모든 데이터에 접근 가능(pw) 제외), 유저(), 게스트 )
    @Id
    @Column(name = "authority_name", length = 50)
    private String authorityName;
}
