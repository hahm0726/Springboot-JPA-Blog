package com.blog.hahmlog.repository;

import com.blog.hahmlog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//JpaRepository가 서비스로직을 기본적으로 다 들고있어 기본 CRUD를 별도로 구현하지 않아도 됨
//DAO의 역할을 해줌
//자동으로 bean등록이 됨
//@Repository // 생략 가능하다
public interface UserRepository extends JpaRepository<User,Integer> {
    // JPA Naming 쿼리 => JPA가 실제로 들고있는 함수가 아니지만 이름을 보고 작동할 수 있도록 해줌
    // select * from user where username=? and password=?
    User findByUsernameAndPassword(String username, String password);

//    @Query(value = "SELECT * FROM user WHERE username=?1 AND password=?2", nativeQuery = true)
//    User login(String username, String password);
}
