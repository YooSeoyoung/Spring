package com.dw.dynamic.service;

import com.dw.dynamic.DTO.BoardDTO;
import com.dw.dynamic.exception.InvalidRequestException;
import com.dw.dynamic.exception.ResourceNotFoundException;
import com.dw.dynamic.model.Board;
import com.dw.dynamic.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserService userService;

    public List<BoardDTO>  getAllBoards(){
        try {
            return boardRepository.findAll().stream().map(Board::toDTO).toList();
        }catch (InvalidRequestException e){
            throw new InvalidRequestException("정상적인 요청이 아닙니다");
        }

    }
    public BoardDTO getBoardById(Long id){
      Board board =  boardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 ID입니다"));

      return board.toDTO();
    }


}
