package com.blog.hahmlog.user.dto;

import com.blog.hahmlog.user.domain.model.User;
import lombok.Data;

@Data
public class UserRequestDto {
    private String username;
    private String password;
    private String email;

    public User toEntity(){
        return User.builder()
                .username(this.username)
                .password(this.password)
                .email(this.email).build();
    }
}
