package com.blog.hahmlog.service;

import com.blog.hahmlog.model.Board;
import com.blog.hahmlog.model.Role;
import com.blog.hahmlog.model.User;
import com.blog.hahmlog.repository.BoardRepository;
import com.blog.hahmlog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    //글 생성 기능
    @Transactional
    public void createBoard(Board board,User user){ //title, content
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    //전체 글 목록 조회 기능
    @Transactional(readOnly = true)
    public Page<Board> getAllBoard(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }


    //글 상세보기 기능
    @Transactional(readOnly = true)
    public Board boardDetail(int id) {
        return boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 상세보기 실패: 게시글를 찾을 수 없습니다.");
                });
    }

    //글 삭제 기능
    @Transactional
    public void deleteBoard(int id) {
        boardRepository.deleteById(id);
    }

    //글 수정 기능
    @Transactional
    public void updateBoard(int id, Board requestBoard) {
        Board originBoard = boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("굴 수정 실패: 게시글을 찾을 수 없습니다.");
        }); //영속화 완료 => 영속 컨텍스트에 orginBoard 객체가 올라감
        originBoard.setContent(requestBoard.getTitle());
        originBoard.setContent(requestBoard.getContent());
        //updateBoard 함수가 종료될 때(Service가 종료될 때) 트랜잭션이 종료. 이때 더티체킹 수행발생
        //==> 영속성 컨텍스트에서 변화된 데이터를 감지해 DB로 flush가 수행되면서 자동 업데이트 됨
    }
}
