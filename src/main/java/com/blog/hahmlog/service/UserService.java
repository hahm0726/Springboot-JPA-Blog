package com.blog.hahmlog.service;

import com.blog.hahmlog.model.Role;
import com.blog.hahmlog.model.User;
import com.blog.hahmlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Transactional
    public void updateUser(User requestUser) {
        //수정 시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 해당 User를 수정하면 된다
        //select를 해서 User오브젝트를 DB로부터 가져오는 이유는 영속화를 하기 위해서!!
        //영속화된 오브젝트를 변경하면 자동으로 DB에 update문이 수행 ==> 더티체킹
        User originUser = userRepository.findById(requestUser.getId()).orElseThrow(()->{
            return new IllegalArgumentException("해당 유저를 찾을 수 없습니다.");
        });
        String rawPassword = requestUser.getPassword();
        originUser.setPassword(encoder.encode(rawPassword));
        originUser.setEmail(requestUser.getEmail());
    }

//    //전통적인 로그인 방식
//    @Transactional(readOnly = true) //select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성)
//    public User login(User user) {
//       return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
//    }
}
