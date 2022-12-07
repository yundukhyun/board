package com.board.board.entity;

import com.board.board.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Board extends Timestamped{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //PK id;

    @Column(nullable = false)
    private String username;   //필드값

    @Column(nullable = false)
    private String contents;   // 필드값

    @Column(nullable = false)
    private String title;      //필드값

    @ManyToOne
    @JoinColumn
    private User user;

    public Board(BoardRequestDto requestDto, String username) { //requestDto생성자의 요소
        this.username =username;
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
//        this.password = requestDto.getPassword();

    }

//    public void update(BoardRequestDto requestDto) {
////        this.username = requestDto.getUsername();
//        this.contents = requestDto.getContents();
//        this.title = requestDto.getTitle();
//    }



}
