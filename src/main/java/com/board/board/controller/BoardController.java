package com.board.board.controller;

import com.board.board.dto.BoardRequestDto;
import com.board.board.dto.BoardResponseDto;
import com.board.board.entity.Board;
import com.board.board.service.BoardService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;
    //
    @PostMapping("/board")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto, HttpServletRequest request) {
        return boardService.createBoard(requestDto, request);
    }
///////////////////////////////////////////////////////////////////
    @GetMapping("/board")
    public List<BoardResponseDto> getBoard(){
        return boardService.getBoardlist();
    }
    //업데이트
//    @PutMapping("/board/{id}")
//    public BoardResponseDto updateBoard(@PathVariable Long id,@RequestBody BoardRequestDto requestDto){
//        return boardService.updateBoard(id,requestDto);
//    }
//    //삭제
//    @DeleteMapping("/board/{id}")
//    public String deleteBoard(@PathVariable Long id,@RequestBody BoardRequestDto requestDto){
//        return boardService.deleteBoard(id,requestDto);
//    }
    @GetMapping("/board/{id}")
    public BoardResponseDto selectMemo(@PathVariable Long id) {
        return boardService.selectMemo(id);
    }
}




