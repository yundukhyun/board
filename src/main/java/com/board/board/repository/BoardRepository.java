package com.board.board.repository;

import com.board.board.dto.BoardResponseDto;
import com.board.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board,Long>{
    List<BoardResponseDto> findAllByOrderByModifiedAtDesc();
    List<Board> findAllByUserId(Long userId);
    Optional<Board> findByIdAndUserId(Long id, Long userId);
}
