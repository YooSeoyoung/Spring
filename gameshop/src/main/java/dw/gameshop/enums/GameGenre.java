package dw.gameshop.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GameGenre { // 게임 장르(게임 당 하나의 장르로 설정 근데 보통 List로 관리만 1개의 게임이 2개의 장르를 가질 수 있기 때문에)
    // 게임 장를 string으로 선언을 하게 되면 맞지 않은 문자열을 쓸 수 있기 때문에 enum으로 설정을 하여 이 중에서만 장르를 설정할 수 있도록 함( 성별 등도 가능함)
    ACTION("액션"),
    ADVENTURE("어드벤처"),
    ROLE_PLAYING("롤플레잉"),
    SIMULATION("시뮬레이션"),
    STRATEGY("전략"),
    SPORTS("스포츠"),
    RACING("레이싱"),
    FIGHTING("격투"),
    PUZZLE("퍼즐");

    private final String genreName;
}
