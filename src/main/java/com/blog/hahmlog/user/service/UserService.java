package com.blog.hahmlog.user.service;

import com.blog.hahmlog.user.dto.UserRequestDto;
import com.blog.hahmlog.user.dto.UserResponseDto;


public interface UserService {

    public void signUp(UserRequestDto userRequestDto);

    public void updateUserInfo(int userId, UserRequestDto userRequestDto);

    public UserResponseDto findUserById(int userId);
}
