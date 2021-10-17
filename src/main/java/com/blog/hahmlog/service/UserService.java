package com.blog.hahmlog.service;

import com.blog.hahmlog.model.Role;
import com.blog.hahmlog.model.User;
import com.blog.hahmlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//서비스 1. 트랜잭션 관리, 2. 서비스의 의미
//2.
// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌. IoC를 해준다
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public void signUp(User user){

        String rawPassword = user.getPassword();
        String encodedPassword = encoder.encode(rawPassword);
        user.setPassword(encodedPassword);
        user.setRole(Role.USER);
        userRepository.save(user);
    }

//    //전통적인 로그인 방식
//    @Transactional(readOnly = true) //select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성)
//    public User login(User user) {
//       return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
//    }
}
