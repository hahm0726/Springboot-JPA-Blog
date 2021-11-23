package com.blog.hahmlog.reply.repository;

import com.blog.hahmlog.reply.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//생략가능
//@Repository
public interface ReplyRepository extends JpaRepository<Reply,Integer> {

    @Query("select r from Reply r join fetch r.user where r.board.id = ?1")
    List<Reply> findRepliesByBoardIdWithUser(int boardId);
}
