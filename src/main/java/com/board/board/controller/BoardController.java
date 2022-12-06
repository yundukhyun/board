package com.board.board.controller;

import com.board.board.dto.BoardRequestDto;
import com.board.board.dto.BoardResponseDto;
import com.board.board.entity.Board;
import com.board.board.service.BoardService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/api/board")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto1) {
//Board에서 반환 post방식이게에 바디가있고 거기에받아오기위한 @RequestBody
        //BoardRequestDto라는 객체로 받을꺼임
        return boardService.createBoard(requestDto1);
    }

    @GetMapping("/api/board")
    public List<BoardResponseDto> getBoard(){
        return boardService.getBoardlist();
    }

    @PutMapping("/api/board/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id,@RequestBody BoardRequestDto requestDto){
        return boardService.updateBoard(id,requestDto);
    }

    @DeleteMapping("/api/board/{id}")1
    public String deleteBoard(@PathVariable Long id,@RequestBody BoardRequestDto requestDto){
        return boardService.deleteBoard(id,requestDto);
    }
    @GetMapping("/api/board/{id}")
    public BoardResponseDto selectMemo(@PathVariable Long id) {
        return boardService.selectMemo(id);
    }
}




