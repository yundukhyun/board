package com.board.board.service;

import com.board.board.dto.BoardRequestDto;
import com.board.board.dto.BoardResponseDto;
import com.board.board.dto.BoardUpdateRequestDto;
import com.board.board.dto.MsgResponseDto;
import com.board.board.entity.Board;
import com.board.board.entity.User;
import com.board.board.jwt.JwtUtil;
import com.board.board.repository.BoardRepository;
import com.board.board.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    //실제로 데이터를 뭔가 로직을 작성하고 연결하는부분을 Service임

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    //데이터와 연결을 할수있게해주는 BoardRepository추가한것

    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto requestDto, HttpServletRequest request) {
        // request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        //jwtUtil에 있는 resolveToken메서드를 돌리고 값을 request를 준다. 토큰을 가져오는 메서드
        //그값을 token에 준다
        // 토큰이 있는 경우에만 관심상품 추가 가능
        Claims claims;
        if (token != null) {//token이 null이아닐때
            if (jwtUtil.validateToken(token)) { //validateToken메서드를 실행한다.  토큰을 검증하는 단계
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    //토큰안에 있는 유저네임
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            //User user = User타입의 user객체안에 userRepository.findByUsername(claims.getSubject())을 넣는다
//            Product product = productRepository.saveAndFlush(new Product(requestDto, user.getId()));
            // 요청받은 DTO 로 DB에 저장할 객체 만들기
            Board board = new Board(requestDto, user);
            //board - contents title username password id

            boardRepository.save(board);
            return new BoardResponseDto(board);
        } else {
            return null;
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //전체 조회
    @Transactional(readOnly = true)
    public List<BoardResponseDto> getBoardlist() {
        List<BoardResponseDto> boards = boardRepository.findAllByOrderByModifiedAtDesc();


        return boards;
    }

    //    업데이트
    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardUpdateRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        //jwtUtil에 있는 resolveToken메서드를 돌리고 값을 request를 준다. 토큰을 가져오는 메서드
        //그값을 token에 준다
        // 토큰이 있는 경우에만 관심상품 추가 가능
        Claims claims;
        if (token != null) {//token이 null이아닐때
            if (jwtUtil.validateToken(token)) { //validateToken메서드를 실행한다.  토큰을 검증하는 단계
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    //토큰안에 있는 유저네임
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Board board = boardRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("없는글입니다.")
            );
            if (board.getUser().getUsername().equals(user.getUsername())) {
                    board.update(requestDto);
                    System.out.println("성공");
            } else {
                throw new IllegalArgumentException("자기글만 수정가능합니다.");
            }
            return new BoardResponseDto(board);
        } else {
            throw new IllegalArgumentException("토큰이 없습니다.");
        }
    }

    @Transactional
    public MsgResponseDto deleteBoard(Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        //jwtUtil에 있는 resolveToken메서드를 돌리고 값을 request를 준다. 토큰을 가져오는 메서드
        //그값을 token에 준다
        // 토큰이 있는 경우에만 관심상품 추가 가능
        Claims claims;
        if (token != null) {//token이 null이아닐때
            if (jwtUtil.validateToken(token)) { //validateToken메서드를 실행한다.  토큰을 검증하는 단계
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    //토큰안에 있는 유저네임
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Board board = boardRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("없는글입니다.")
            );
            if (board.getUser().getUsername().equals(user.getUsername())) {
                boardRepository.deleteById(id);
            } else {
                throw new IllegalArgumentException("자기글만 수정가능합니다.");
            }
            return new MsgResponseDto("삭제성공",HttpStatus.OK.value());
        } else {
            throw new IllegalArgumentException("토큰이 없습니다.");
        }
    }

    @Transactional(readOnly = true)
    public BoardResponseDto selectMemo(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("없는글입니다.")
        );
        return new BoardResponseDto(board);
    }
}
