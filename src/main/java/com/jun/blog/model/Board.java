package com.jun.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,length = 100)
    private String title;

    @Lob//대용량 데이터
    private String content; //섬머노트 라이브러리 <Html> 태그가 섞여서 디자인됨.


    private int count; //조회수

    @ManyToOne(fetch = FetchType.EAGER) // Many = Board, User=One
    @JoinColumn(name = "userId")
    private User user;//DB는 오브젝트를 저장할 수 없다.FK, 자바는 오브젝트를 저장할 수 있다.

    @OneToMany(mappedBy = "board" ,fetch = FetchType.EAGER)//mappedBy 연관관계의 주인관계X
    //FetchType.LAZY= 필요할 경우 테이블에 있는 attribute를 가져온다. -fetch 전략
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;
}
