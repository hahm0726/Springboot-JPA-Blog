package com.blog.hahmlog.config.auth;

import com.blog.hahmlog.model.User;
import com.blog.hahmlog.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service //Bean 등록
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // 스프링이 로그인 요청을 가로챌 때, username, password 변수 2개를 가로채는데
    // password 부분 처리는 알아서 함
    // username이 DB에 있는지만 확인해주면됨 => 이 동작을 아래 함수가 수행
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername 호출됨");
        User principal = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다 : " + username));
        System.out.println("username = " + principal.getUsername());
        System.out.println("password = " + principal.getPassword());
        return new PrincipalDetail(principal); //시큐리티의 세션저장소에 유저정보가 저장된다
    }
}
