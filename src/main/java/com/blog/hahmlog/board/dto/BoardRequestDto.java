package com.blog.hahmlog.board.dto;

import com.blog.hahmlog.board.model.Board;
import lombok.Data;


@Data
public class BoardRequestDto {
    private String title;
    private String content;

    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content).build();
    }
}
