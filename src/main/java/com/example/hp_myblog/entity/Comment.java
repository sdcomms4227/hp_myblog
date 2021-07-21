package com.example.hp_myblog.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@Builder
@NoArgsConstructor // 디폴트 생성자 자동 기입
@AllArgsConstructor // 모든 필드 포함 생성자 자동 기입
@Entity
public class Comment extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String author;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    // 다대일 관계 설정
    @ManyToOne // 여러 Comment가 하나의 Article 에 포함 됨!
    @JoinColumn(name = "article_id") // 포함 대상 정보는 article_id에 기록!
    private  Article article;

    // 해당 댓글이 어느 게시글에 작성된 것인지를 기록!
    public void stickTo(Article article) {
        this.article = article;
    }
}
