package com.blog.hahmlog.controller;

import com.blog.hahmlog.config.auth.PrincipalDetail;
import com.blog.hahmlog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    // http://localhost:8000/blog/
    @GetMapping({"","/"})
    public String index(Model model){
        //model에 데이터를 담으면 view(index.jsp)까지 데이터를 끌고감
        model.addAttribute("boards",boardService.getAllBoard());
        return "index"; // viewResolver 작동!!
    }

    //USER 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }
}
