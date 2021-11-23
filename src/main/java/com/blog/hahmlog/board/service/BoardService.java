package com.blog.hahmlog.board.service;

import com.blog.hahmlog.board.dto.BoardResponseDto;
import com.blog.hahmlog.board.dto.BoardRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {

    //글 생성 기능
    void createBoard(int userId, BoardRequestDto boardSaveRequestDto);

    //전체 글 목록 조회 기능
    Page<BoardResponseDto> getAllBoard(Pageable pageable);

    //글 상세보기 기능
    BoardResponseDto boardDetail(int id);

    //글 삭제 기능
    void deleteBoard(int id);

    //글 수정 기능
    void updateBoard(int id, BoardRequestDto boardUpdateRequestDto);
}
