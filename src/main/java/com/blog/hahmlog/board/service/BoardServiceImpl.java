package com.blog.hahmlog.board.service;

import com.blog.hahmlog.board.dto.BoardResponseDto;
import com.blog.hahmlog.board.dto.BoardRequestDto;
import com.blog.hahmlog.board.model.Board;
import com.blog.hahmlog.user.domain.model.User;
import com.blog.hahmlog.board.repository.BoardRepository;
import com.blog.hahmlog.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserRepository userRepository;

    //글 생성 기능
    @Transactional
    @Override
    public void createBoard(int userId, BoardRequestDto boardSaveRequestDto){ //title, content
        User user = userRepository.findById(userId).orElseThrow(()->{
            return new IllegalArgumentException("게시글 생성 실패: 해당 유저를 찾을 수 없습니다.");
        });

        Board board = boardSaveRequestDto.toEntity();
        board.setUser(user);
        board.setViewCount(0);

        boardRepository.save(board);
    }

    //전체 글 목록 조회 기능
    //Page<Board> -> Page<BoardResponseDto>로 변환 후 반환
    @Transactional(readOnly = true)
    @Override
    public Page<BoardResponseDto> getAllBoard(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        List<BoardResponseDto> dtos = new ArrayList<>();

        boards.getContent().stream()
                .forEach(board->{
                   dtos.add(new BoardResponseDto(board));
                });
        return new PageImpl<>(dtos,pageable,boards.getTotalElements());
    }


    //글 상세보기 시 조회수 증가 후 게시글 Dto 반환
    @Transactional
    @Override
    public BoardResponseDto boardDetail(int id) {
        System.out.println("찾기 시작");
        Board board = boardRepository.findBoardByIdWithUser(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패: 게시글를 찾을 수 없습니다.");
                });

        System.out.println("찾기 성공");
        board.setViewCount(board.getViewCount()+1); //조회수 증가
        System.out.println("board.getViewCount() = " + board.getViewCount());
        return new BoardResponseDto(board);
    }


    //글 삭제 기능
    @Transactional
    @Override
    public void deleteBoard(int id) {
        boardRepository.deleteById(id);
    }

    //글 수정 기능
    @Transactional
    @Override
    public void updateBoard(int id, BoardRequestDto requestDto) {
        Board originBoard = boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("굴 수정 실패: 게시글을 찾을 수 없습니다.");
        }); //영속화 완료 => 영속 컨텍스트에 orginBoard 객체가 올라감

        originBoard.setContent(requestDto.getTitle());
        originBoard.setContent(requestDto.getContent());
        //updateBoard 함수가 종료될 때(Service가 종료될 때) 트랜잭션이 종료. 이때 더티체킹 수행발생
        //==> 영속성 컨텍스트에서 변화된 데이터를 감지해 DB로 flush가 수행되면서 자동 업데이트 됨
    }
}
