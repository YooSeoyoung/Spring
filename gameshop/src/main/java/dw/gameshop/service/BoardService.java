package dw.gameshop.service;

import dw.gameshop.dto.BoardDTO;
import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Board;
import dw.gameshop.repository.BoardRepository;
import dw.gameshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UserRepository userRepository;

    public List<BoardDTO> getAllBoards() {  // 활성화(true)로 된 게시판에 있는 게시물만 조회하는 코드
        return boardRepository.findAll().stream()
                .filter(Board::getIsActive)  //
                .map(Board::toDto)
                .toList();
    }

    public BoardDTO saveBoard(BoardDTO boardDTO) {
        return boardRepository.findById(boardDTO.getId()) // 기존에 작성한 게시물이 있는 확인
                .map((board)->{ // 기존 게시물이 존재하면 업데이트
                    board.setModifiedDate(LocalDateTime.now());  // 수정한 날짜만 갱신하고 save하기
                    return boardRepository.save(board).toDto();
                })
                .orElseGet(()-> { // 기존 게시물이 없으면 새로운 게시물 추가
                    Board board = new Board(
                            null,  // 왜 null일까 ???  : 자동으로 생성이 되기 때문에 null로 명시
                            boardDTO.getTitle(),
                            boardDTO.getContent(),
                            userRepository.findById(boardDTO.getAuthorName())
                                    .orElseThrow(()->new ResourceNotFoundException("No UserName")),
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            true); // 새 글이니까 언제나 true로
                    return boardRepository.save(board).toDto();
                });
    }

    public String deleteBoard(long id) {
        return boardRepository.findById(id)
                .map(board -> {
                    board.setIsActive(false); // 실제로 db를 삭제하는게 아니라 활성화를 false로 바꾸는 것
                    boardRepository.save(board);
                    return "successfully deleted";
                })
                .orElseThrow(() -> new ResourceNotFoundException("게시판 글이 없습니다. ID : " + id));
    }
}
