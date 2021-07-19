package com.example.hp_myblog.dto;

import com.example.hp_myblog.entity.Article;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data // 생성자(디폴트, All), 게터, 세터, toString 등 다 만들어 줌!
public class ArticleForm {
    private Long id; // id 필드 추가!
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
    public ArticleForm(Article entity){
        this.id = entity.getId();
        this.author = entity.getAuthor();
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
