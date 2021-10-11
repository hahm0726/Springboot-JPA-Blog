package com.blog.hahmlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    // http://localhost:8000/blog/
    @GetMapping({"","/"})
    public String index(){
        // /WEB-INF/views/index.jsp
        return "index";
    }
}
