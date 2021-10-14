package com.blog.hahmlog.api;

import com.blog.hahmlog.dto.ResponseDto;
import com.blog.hahmlog.model.Role;
import com.blog.hahmlog.model.User;
import com.blog.hahmlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){ //user = {username, password, email}
        //실제로 DB에 insert 하고 아래에서 return
        System.out.println("UserApiController: save 호출됨");
        user.setRole(Role.USER);
        userService.signUp(user);
        return new ResponseDto<>(HttpStatus.OK.value(),1); // 자바 오브젝트를 json 으로 변환해 반환(Jackson)
    }

    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
        System.out.println("UserApiController: login 호출됨");
        User principal= userService.login(user); // principal(접근주체)

        if(principal != null){
            session.setAttribute("principal", principal);
        }
        return new ResponseDto<>(HttpStatus.OK.value(),1);
    }
}
