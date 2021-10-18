package com.blog.hahmlog.api;

import com.blog.hahmlog.config.auth.PrincipalDetail;
import com.blog.hahmlog.dto.ResponseDto;
import com.blog.hahmlog.model.Board;

import com.blog.hahmlog.model.User;
import com.blog.hahmlog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){ //user = {username, password, email}
        boardService.createBoard(board, principal.getUser());
        return new ResponseDto<>(HttpStatus.OK.value(),1); // 자바 오브젝트를 json 으로 변환해 반환(Jackson)
    }

}
