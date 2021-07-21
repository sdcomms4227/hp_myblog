package com.example.hp_myblog.api;

import com.example.hp_myblog.dto.CommentForm;
import com.example.hp_myblog.entity.Article;
import com.example.hp_myblog.entity.Comment;
import com.example.hp_myblog.repository.ArticleRepository;
import com.example.hp_myblog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final ArticleRepository articleRepository;

    private final CommentRepository commentRepository;

    @PostMapping("/api/comments/{articleId}")
    public Long create(@PathVariable Long articleId, @RequestBody CommentForm form){
        //댓글 확인
        log.info("form: " + form.toString());
        Comment comment = form.toEntity();
        log.info("comment: " + comment.toString());

        // 댓글이 달릴 게시글을 가져옴!
        Article article = articleRepository.findById(articleId)
                .orElseThrow(
                        () -> new IllegalArgumentException("댓글을 작성할 Article이 없습니다.")
                );

        //댓글 엔티티에 게시글 엔티티를 등록
        comment.stickTo(article);
        log.info("written: " + comment.toString());
        Comment saved = commentRepository.save(comment);
        log.info("saved: " + saved.toString());
        return saved.getId();
    }
}
