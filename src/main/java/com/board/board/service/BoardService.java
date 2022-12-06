package com.board.board.service;

import com.board.board.dto.BoardRequestDto;
import com.board.board.dto.BoardResponseDto;
import com.board.board.entity.Board;
import com.board.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    //실제로 데이터를 뭔가 로직을 작성하고 연결하는부분을 Service임

    private final BoardRepository boardRepository;
    //데이터와 연결을 할수있게해주는 BoardRepository추가한것
    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto requestDto2) {
        Board board = new Board(requestDto2);
        //requestDto생성자를 사용해 객체를 만들어줌
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }

    //전체 조회
    @Transactional(readOnly = true)
    public List<BoardResponseDto> getBoardlist() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }
    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("없는글입니다.")
        );
        boolean passwordTrue = requestDto.getPassword().equals(board.getPassword());
        if(passwordTrue){
            board.update(requestDto);
            System.out.println("성공");
        }
        else{
            System.out.println("비밀번호가 틀립니다.");
        }
        return new BoardResponseDto(board);
    }
    @Transactional
    public String deleteBoard(Long id,BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("없는글입니다.")
        );
        boolean passwordTrue = requestDto.getPassword().equals(board.getPassword());
        String a = "";
        if(passwordTrue){
            boardRepository.deleteById(id);
            a= "삭제완료";
        }else{
            a = "비밀번호를 확인해주세요";
        }

        return a;
    }
    @Transactional(readOnly = true)
    public BoardResponseDto selectMemo(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("없는글입니다.")
        );
        return new BoardResponseDto(board);
    }
}
