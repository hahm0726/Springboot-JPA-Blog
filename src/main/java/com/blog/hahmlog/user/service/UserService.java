package com.blog.hahmlog.user.service;

import com.blog.hahmlog.user.domain.Role;
import com.blog.hahmlog.user.dto.UserRequestDto;
import com.blog.hahmlog.user.domain.model.User;
import com.blog.hahmlog.user.dto.UserResponseDto;
import com.blog.hahmlog.user.repository.UserRepository;
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
    public void signUp(UserRequestDto userRequestDto){

        User newUser = userRequestDto.toEntity();
        String encodedPassword = encoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);
        newUser.setRole(Role.USER);
        userRepository.save(newUser);
    }

    @Transactional
    public void updateUserInfo(int userId, UserRequestDto userRequestDto) {
        //수정 시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 해당 User를 수정하면 된다
        //select를 해서 User오브젝트를 DB로부터 가져오는 이유는 영속화를 하기 위해서!!
        //영속화된 오브젝트를 변경하면 자동으로 DB에 update문이 수행 ==> 더티체킹
        User originUser = userRepository.findById(userId).orElseThrow(()->{
            return new IllegalArgumentException("해당 유저를 찾을 수 없습니다.");
        });
        String rawPassword = userRequestDto.getPassword();
        originUser.setPassword(encoder.encode(rawPassword));
        originUser.setEmail(userRequestDto.getEmail());
    }

    @Transactional(readOnly = true) //select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성)
    public UserResponseDto findUserById(int userId) {
        User user = userRepository.findById(userId).orElseThrow(()->{
            return new IllegalArgumentException("유저 찾기 실패: 해당 유저가 존재하지 않습니다(id)");
        });

        return new UserResponseDto(user);
    }
}
