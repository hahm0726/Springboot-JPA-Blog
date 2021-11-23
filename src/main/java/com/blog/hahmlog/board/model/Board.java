package com.blog.hahmlog.board.model;

import com.blog.hahmlog.user.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //Builder 패턴
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int id;

    //게시글 제목
    @Column(nullable = false, length = 100)
    private String title;

    //게시글 내용
    @Lob //대용량 데이터 => 섬머노트 라이브러리(html) 태그가 섞여있음
    private String content;

    //게시글 조회수
    @Column(name = "viewCnt")
    private int viewCount;

    //작성자
    @ManyToOne(fetch = FetchType.LAZY) //Many = Board, User = One ==> 한명의 유저는 여러 게시물을 쓸 수 있다
    @JoinColumn(name = "userId")
    private User user;

    @Formula("(SELECT count(1) FROM reply r WHERE r.boardId = id)")
    private int replyCount;

    @CreationTimestamp
    private Timestamp createDate;

}
