package com.blog.hahmlog.reply.api;

import com.blog.hahmlog.reply.dto.ReplySaveRequestDto;
import com.blog.hahmlog.dto.ResponseDto;
import com.blog.hahmlog.reply.service.ReplyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReplyApiController {

    @Autowired
    ReplyServiceImpl replyService;

    // 데이터 받을 때는 컨트롤러에서 dto를 만들어서 받는게 좋음
    // dto를 사용하지 않은 이유는? => 작은 프로젝트라 적용하지 않음
    // 하지만 dto를 사용하지 않아 많은 필드들이 생성됨
    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto){ //user = {username, password, email}
        replyService.createReply(replySaveRequestDto);
        return new ResponseDto<>(HttpStatus.OK.value(),1); // 자바 오브젝트를 json 으로 변환해 반환(Jackson)
    }

    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable int replyId){
        replyService.deleteReply(replyId);
        return new ResponseDto<>(HttpStatus.OK.value(),1);
    }
}
