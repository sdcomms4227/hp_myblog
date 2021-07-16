package com.example.hp_myblog.dto;

import com.example.hp_myblog.entity.Article;
import lombok.Data;

@Data // 생성자(지폴트, All), 게터, 세터, toString 등 다 만들어 줌!
public class ArticleForm {
    private String author;
    private String title;
    private String content;

    public Article toEntity() {
        return Article.builder()
                .id(null)
                .author(author)
                .title(title)
                .content(content)
                .build();
    }
}
