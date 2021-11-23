package com.blog.hahmlog.user.dto;

import com.blog.hahmlog.user.domain.model.User;
import lombok.Data;

@Data
public class UserResponseDto {
    private String username;
    private String password;
    private String email;

    public UserResponseDto(User entity){
        this.username = entity.getUsername();
        this.password = entity.getPassword();
        this.email = entity.getEmail();
    }
}
