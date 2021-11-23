package com.blog.hahmlog.user.api;

import com.blog.hahmlog.config.auth.PrincipalDetail;
import com.blog.hahmlog.dto.ResponseDto;
import com.blog.hahmlog.user.dto.UserRequestDto;
import com.blog.hahmlog.user.dto.UserResponseDto;
import com.blog.hahmlog.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody UserRequestDto userRequestDto){
        userService.signUp(userRequestDto);
        return new ResponseDto<>(HttpStatus.OK.value(),1); // 자바 오브젝트를 json 으로 변환해 반환(Jackson)
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody UserRequestDto userRequestDto, @AuthenticationPrincipal PrincipalDetail principal){
        int userId = principal.getUser().getId();
        userService.updateUserInfo(userId,userRequestDto);
        // 여기서 트랜잭션이 종료되기 때문에(서비스가 종료될 때) DB값은 변경이 됐음
        // 하지만 세션값이 변경되지 않은 상태이기 때문에 우리가 직접 세션값을 변경해줘야함
        //세션 등록
        UserResponseDto userResponseDto = userService.findUserById(userId);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequestDto.getUsername(),userResponseDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseDto<>(HttpStatus.OK.value(),1);
    }

}
