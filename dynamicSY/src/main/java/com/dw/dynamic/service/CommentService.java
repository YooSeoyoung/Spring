package com.dw.dynamic.service;

import com.dw.dynamic.DTO.CommentDTO;
import com.dw.dynamic.exception.InvalidRequestException;
import com.dw.dynamic.exception.PermissionDeniedException;
import com.dw.dynamic.exception.ResourceNotFoundException;
import com.dw.dynamic.model.Board;
import com.dw.dynamic.model.Comment;
import com.dw.dynamic.model.User;
import com.dw.dynamic.repository.BoardRepository;
import com.dw.dynamic.repository.CommentRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserService userService;

    @Autowired
    BoardRepository boardRepository;

    public List<CommentDTO> getAllComments(){
        try {
            return commentRepository.findAll().stream().map(Comment::toDTO).toList();
        }catch (InvalidRequestException e){
            throw  new InvalidRequestException("정상적인 요청이 아닙니다");
        }
    }

    public List<CommentDTO> getCommentByBoardId(Long boardId){
        try {
            List<Comment> commentList = commentRepository.findByBoard_fkId(boardId);
            return commentList.stream().map(Comment::toDTO).toList();
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("존재하지 않은 게시판ID입니다");
        }
    }

    public List<CommentDTO> getCommentByBoardTitle(String boardTitle){
        try {
            List<Comment> commentList= commentRepository.findByBoard_fkTitle("%"+boardTitle+"%");
            return commentList.stream().map(Comment::toDTO).toList();
        }catch (ResourceNotFoundException e){
            throw  new ResourceNotFoundException("존재하지 않은 게시판 제목입니다");
        }
    }

//    public CommentDTO saveCommentByBoardId(Long boardId,CommentDTO commentDTO, HttpServletRequest request){
//    User currentUser = userService.getCurrentUser(request);
//       if (!currentUser.getAuthority().getAuthorityName().equals("USER")){
//        throw new PermissionDeniedException("비회원은 댓글 작성이 불가능합니다. 회원가입을 해주세요");
//    }
//
//       Board board= boardRepository.findById(boardId).orElseThrow(()-> new ResourceNotFoundException("존제하지 않은 게시판ID입니다"));
//
//       if (boardRepository.findById(boardId).isPresent()){
//
//       }
//    }
}
