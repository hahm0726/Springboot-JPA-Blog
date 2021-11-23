package com.blog.hahmlog.reply.service;

import com.blog.hahmlog.board.model.Board;
import com.blog.hahmlog.board.repository.BoardRepository;
import com.blog.hahmlog.reply.dto.ReplyResponseDto;
import com.blog.hahmlog.reply.dto.ReplySaveRequestDto;
import com.blog.hahmlog.reply.model.Reply;
import com.blog.hahmlog.reply.repository.ReplyRepository;
import com.blog.hahmlog.user.domain.model.User;
import com.blog.hahmlog.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService{

    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserRepository userRepository;


    @Transactional(readOnly = true)
    @Override
    public List<ReplyResponseDto> getBoardsReplies(int boardId){
        System.out.println("댓글 찾기 시작");
        List<Reply> replies = replyRepository.findRepliesByBoardIdWithUser(boardId);
        System.out.println("댓글 찾기 성공");

        List<ReplyResponseDto> dtos = new ArrayList<>();
        replies.stream().forEach(reply ->{
            dtos.add(new ReplyResponseDto(reply));
        });
        return dtos;
    }

    @Transactional
    @Override
    public void createReply(ReplySaveRequestDto replySaveRequestDto) {

        Board board = boardRepository.findById(replySaveRequestDto.getBoardId()).orElseThrow(()->{
            return new IllegalArgumentException("댓글쓰기 실패: 게시글을 찾을 수 없습니다");
        }); //board 영속화 완료

        User user = userRepository.findById(replySaveRequestDto.getUserId()).orElseThrow(() -> {
            return new IllegalArgumentException("댓글쓰기 실패: 유저를 찾을 수 없습니다");
        }); //user 영속화 완료

        Reply reply = Reply.builder()
                .board(board)
                .user(user)
                .content(replySaveRequestDto.getContent())
                .build();

        replyRepository.save(reply);
    }

    @Transactional
    @Override
    public void deleteReply(int replyId) {
        replyRepository.deleteById(replyId);
    }
}
