package com.blog.hahmlog.reply.repository;

import com.blog.hahmlog.reply.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

//생략가능
//@Repository
public interface ReplyRepository extends JpaRepository<Reply,Integer> {
}
