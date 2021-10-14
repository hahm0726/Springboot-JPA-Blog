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

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){ //user = {username, password, email}
        //실제로 DB에 insert 하고 아래에서 return
        System.out.println("username = " + user.getUsername());
        System.out.println("password = " + user.getPassword());
        System.out.println("email = " + user.getEmail());
        user.setRole(Role.USER);
        userService.signUp(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1); // 자바 오브젝트를 json 으로 변환해 반환(Jackson)
    }
}
