package com.blog.hahmlog.repository;

import com.blog.hahmlog.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JpaRepository가 서비스로직을 기본적으로 다 들고있어 기본 CRUD를 별도로 구현하지 않아도 됨
//DAO의 역할을 해줌
//자동으로 bean등록이 됨
//@Repository // 생략 가능하다
public interface BoardRepository extends JpaRepository<Board,Integer> {

}
