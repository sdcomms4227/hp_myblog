package com.example.hp_myblog.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter // 게터를 자동 생성!
@NoArgsConstructor // 디폴트 생성자 넣어 줌!
@Entity // DB 테이블에 저장될 클래스 임!
public class Article extends BaseTime {
    @Id // 이게 ID임! 즉 사람으로 따지면 주민등록 번호! DB에서는 PK(Primary Key)라고 함!
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에서 자동 관리. 매 생성 시, 1, 2, ... 증가
    private Long id;

    @Column(length = 100, nullable = false) // 최대 100글자, 비어 있으면 안됨! 추후 SQL 학습
    private String author;

    @Column(length = 100, nullable = false) // 최대 100글자, 비어 있으면 안됨! 추후 SQL 학습
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) // 텍스트 타입, 비어있으면 안됨! 추후 SQL 학습
    private String content;

    @Builder // 빌더 패턴 적용! 추후 설명..!
    public Article(Long id, String author, String title, String content){
        this.id = id;
        this.author = author;
        this.title = title;
        this.content  = content;
    }
    // 내용을 재작성!
    public void rewrite(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    // 하나의 Article은 여러 개의 Comment를 가질 수 있다!
    // fetch: 연결 방법 설정
    // mappedBy: comments를 연결할 테이블명 설정!
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
    @OrderBy("id desc")
    private List<Comment> comments;

    @Override // toString() 메소드를 직접 오버라이딩(재정의) 함!
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", comments=" + (comments == null ? null : comments.size()) +
                '}';
    }
}
