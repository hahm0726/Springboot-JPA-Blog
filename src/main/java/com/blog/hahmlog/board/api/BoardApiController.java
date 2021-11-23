package com.blog.hahmlog.board.api;

import com.blog.hahmlog.board.dto.BoardRequestDto;
import com.blog.hahmlog.board.service.BoardService;
import com.blog.hahmlog.config.auth.PrincipalDetail;
import com.blog.hahmlog.dto.ResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    BoardService boardService;


    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody BoardRequestDto boardSaveRequestDto,
                                     @AuthenticationPrincipal PrincipalDetail requestUser){
        boardService.createBoard(requestUser.getUser().getId(),boardSaveRequestDto);
        return new ResponseDto<>(HttpStatus.OK.value(),1); // 자바 오브젝트를 json 으로 변환해 반환(Jackson)
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> delete(@PathVariable int id){
        boardService.deleteBoard(id);
        return new ResponseDto<>(HttpStatus.OK.value(),1); // 자바 오브젝트를 json 으로 변환해 반환(Jackson)
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody BoardRequestDto boardUpdateRequestDto){
        boardService.updateBoard(id,boardUpdateRequestDto);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

}
