package com.blog.hahmlog.board.controller;

import com.blog.hahmlog.board.dto.BoardResponseDto;
import com.blog.hahmlog.board.service.BoardService;
import com.blog.hahmlog.config.auth.PrincipalDetail;
import com.blog.hahmlog.reply.dto.ReplyResponseDto;
import com.blog.hahmlog.reply.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    ReplyService replyService;

    // 글목록이 보여지는 메인페이지
    // http://localhost:8000/blog/
    @GetMapping({"", "/"})
    public String index(Model model,@RequestParam(value = "page", defaultValue = "0") int page) {
        //model에 데이터를 담으면 view(index.jsp)까지 데이터를 끌고감
        Pageable pageable = PageRequest.of(page,3);

        model.addAttribute("boards", boardService.getAllBoard(pageable));
        return "index"; // viewResolver 작동!!
    }

    // id에 맞는 게시글 상세보기
    @GetMapping("/board/{boardId}")
    public String findById(@PathVariable int boardId, Model model,
                           @AuthenticationPrincipal PrincipalDetail principal) {

        BoardResponseDto board = boardService.boardDetail(boardId);

        List<ReplyResponseDto> replies = replyService.getBoardsReplies(boardId);


        model.addAttribute("board", board);
        model.addAttribute("replies",replies);
        return "board/detail";
    }

    // 글쓰기 페이지
    //USER 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    //글 수정 페이지
    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.boardDetail(id));
        return "board/updateForm";
    }
}
