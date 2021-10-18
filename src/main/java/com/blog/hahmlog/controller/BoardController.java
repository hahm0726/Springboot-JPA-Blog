package com.blog.hahmlog.controller;

import com.blog.hahmlog.config.auth.PrincipalDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {


    // http://localhost:8000/blog/
    @GetMapping({"","/"})
    public String index(@AuthenticationPrincipal PrincipalDetail principal){ //컨트롤러에서 세션을 어떻게 찾는지?

        System.out.println("로그인 사용자 아이디 = " + principal);
        // /WEB-INF/views/index.jsp
        return "index";
    }

    //USER 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }
}
