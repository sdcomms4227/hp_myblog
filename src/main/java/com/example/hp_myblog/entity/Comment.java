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
}
