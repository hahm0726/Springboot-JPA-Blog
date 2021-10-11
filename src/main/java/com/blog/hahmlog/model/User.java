package com.blog.hahmlog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

//@DynamicInsert // insert 시 null인 값을 제외하고 insert
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //Builder 패턴
@Entity //User 클래스가 MySQL에 테이블이 생성된다, ORM이라는 어노테이션으로 클래스 정의에 가장 가까이 두는것이 좋음
public class User {

    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다
    private int id; //시퀀스, auto_increment

    @Column(nullable = false, length = 30, unique = true)
    private String username; //아이디

    @Column(nullable = false, length = 100) //length를 100으로 넉넉히 잡는 이유 => 해쉬(비밀번호 암호화를 위해)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    //@ColumnDefault("user")
    //DB는 Role이라는 타입이 없다
    @Enumerated(EnumType.STRING)
    private Role role; //Enum 을 쓰는게 좋다. - admin, user, manager

    @CreationTimestamp //시간이 자동 입력
    private Timestamp createDate;
}
