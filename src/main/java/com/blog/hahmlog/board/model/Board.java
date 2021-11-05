package com.blog.hahmlog.board.model;

import com.blog.hahmlog.like.model.BoardLike;
import com.blog.hahmlog.reply.model.Reply;
import com.blog.hahmlog.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //Builder 패턴
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //auto_increment
    private int id;

    //게시글 제목
    @Column(nullable = false, length = 100)
    private String title;

    //게시글 내용
    @Lob //대용량 데이터
    private  String content; //섬머노트 라이브러리(html) 태그가 섞여있음

    //게시글 조회수
    @Column(name = "viewCnt")
    private int viewCount;

    //작성자
    @ManyToOne(fetch = FetchType.EAGER) //Many = Board, User = One ==> 한명의 유저는 여러 게시물을 쓸 수 있다
    @JoinColumn(name="userId")
    private User user; //DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다

    //댓글 목록
    @OneToMany(mappedBy = "board", fetch=FetchType.LAZY, cascade = CascadeType.ALL) // mappedBy 연관관계의 주인이 아니다(난 FK가 아니예요). DB에 칼럼을 만들지 마세요
    @OrderBy("id desc")
    private List<Reply> replies;

    @OneToMany(mappedBy = "board", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BoardLike> boardLikes;

    @CreationTimestamp
    private Timestamp createDate;

    public int getLikeNum(){
        return this.boardLikes.size();
    }
}
