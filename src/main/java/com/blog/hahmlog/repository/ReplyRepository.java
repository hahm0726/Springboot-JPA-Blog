package com.blog.hahmlog.repository;

import com.blog.hahmlog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//생략가능
//@Repository
public interface ReplyRepository extends JpaRepository<Reply,Integer> {
}
