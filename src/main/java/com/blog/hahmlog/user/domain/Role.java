package com.blog.hahmlog.user.domain;


public enum Role {
    // enum의 장점
    // 도메인(어떤 데이터의 범위)를 제공해줌으로써 데이터의 오류를 줄일 수 있음
    // 도메인을 지정해 제공해 줌으로 써 사용자의 입력으로부터 오는 데이터의 오류를 줄일 수 있다
    ADMIN,MANAGER,USER
}
