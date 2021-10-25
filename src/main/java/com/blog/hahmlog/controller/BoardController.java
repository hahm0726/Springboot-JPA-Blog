package com.blog.hahmlog.controller;

import com.blog.hahmlog.config.auth.PrincipalDetail;
import com.blog.hahmlog.model.Board;
import com.blog.hahmlog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    // 글목록이 보여지는 메인페이지
    // http://localhost:8000/blog/
    @GetMapping({"","/"})
    public String index(Model model, @PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
        //model에 데이터를 담으면 view(index.jsp)까지 데이터를 끌고감
        model.addAttribute("boards",boardService.getAllBoard(pageable));
        return "index"; // viewResolver 작동!!
    }

    // id에 맞는 게시글 상세보기
    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id, Model model){
        Board board = boardService.boardDetail(id);
        model.addAttribute("board",board);
        return "board/detail";
    }

    // 글쓰기 페이지
    //USER 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }

    //글 수정 페이지
    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model){
        model.addAttribute("board",boardService.boardDetail(id));
        return "board/updateForm";
    }
}
