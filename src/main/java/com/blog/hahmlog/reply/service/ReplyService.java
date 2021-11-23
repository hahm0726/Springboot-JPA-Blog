package com.blog.hahmlog.reply.service;

import com.blog.hahmlog.reply.dto.ReplyResponseDto;
import com.blog.hahmlog.reply.dto.ReplySaveRequestDto;

import java.util.List;

public interface ReplyService {

    public List<ReplyResponseDto> getBoardsReplies(int boardId);

    public void createReply(ReplySaveRequestDto replySaveRequestDto);

    public void deleteReply(int replyId);
}
