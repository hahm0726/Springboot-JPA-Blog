package com.blog.hahmlog.test;

import com.blog.hahmlog.model.Board;
import com.blog.hahmlog.model.Reply;
import com.blog.hahmlog.repository.BoardRepository;
import com.blog.hahmlog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReplyControllerTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @GetMapping("/test/board/{id}")
    public Board getBoard(@PathVariable int id){
        System.out.println(id);
        return boardRepository.findById(id).get(); //jackson 라이브러리(오브젝트를 json으로 반환) ==> 모델의 getter 호출
    }

    @GetMapping("/test/reply/{id}")
    public Reply getReply(@PathVariable int id){
        System.out.println(id);
        return replyRepository.findById(id).get(); //jackson 라이브러리(오브젝트를 json으로 반환) ==> 모델의 getter 호출
    }}
