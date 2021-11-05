package com.blog.hahmlog.like.model;

import com.blog.hahmlog.board.model.Board;
import com.blog.hahmlog.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class BoardLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int id;

    @JoinColumn(name = "boardId")
    @ManyToOne //Many->Like , One->Board = 1개의 게시글은 여러개의 라이크 가능
    private Board board;

    @JoinColumn(name="userId")
    @ManyToOne //Many = Like, User = One ==> 한명의 유저는 좋아요를 여러번 할 수 있다
    private User user;

    @CreationTimestamp
    private Timestamp createDate;
}
