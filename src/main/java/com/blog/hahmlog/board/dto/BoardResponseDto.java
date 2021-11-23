package com.blog.hahmlog.board.dto;

import com.blog.hahmlog.board.model.Board;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class BoardResponseDto {
    private int id;
    private String title;
    private String content;
    private Timestamp createDate;
    private int userId;
    private String username;
    private int viewCount;
    private int replyCount;

    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.createDate = entity.getCreateDate();
        this.userId = entity.getUser().getId();
        this.username = entity.getUser().getUsername();
        this.viewCount = entity.getViewCount();
        this.replyCount = entity.getReplyCount();
    }
}
