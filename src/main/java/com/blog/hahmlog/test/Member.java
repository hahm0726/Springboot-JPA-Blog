package com.blog.hahmlog.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //모든 멤버변수를 필요로하는 생성자
@NoArgsConstructor //빈생성자
public class Member {

    private int id;
    private String username;
    private String password;
    private String email;


}
