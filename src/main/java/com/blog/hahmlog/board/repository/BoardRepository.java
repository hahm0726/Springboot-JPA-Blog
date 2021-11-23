package com.blog.hahmlog.board.repository;

import com.blog.hahmlog.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

//JpaRepository가 서비스로직을 기본적으로 다 들고있어 기본 CRUD를 별도로 구현하지 않아도 됨
//DAO의 역할을 해줌
//자동으로 bean등록이 됨
//@Repository // 생략 가능하다
public interface BoardRepository extends JpaRepository<Board,Integer> {

    @Query("select b from Board b join fetch b.user where b.id = ?1")
    Optional<Board> findBoardByIdWithUser(int id);
}
