package com.blog.hahmlog.reply.dto;

import com.blog.hahmlog.reply.model.Reply;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReplyResponseDto {
    private int id;
    private String content;
    private Timestamp createDate;
    private int userId;
    private String username;

    public ReplyResponseDto(Reply entity){
        this.id = entity.getId();
        this.content = entity.getContent();
        this.createDate = entity.getCreateDate();
        this.userId = entity.getUser().getId();
        this.username = entity.getUser().getUsername();
    }


}
