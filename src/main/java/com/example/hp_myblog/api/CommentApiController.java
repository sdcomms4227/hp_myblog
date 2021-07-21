package com.example.hp_myblog.api;

import com.example.hp_myblog.dto.CommentForm;
import com.example.hp_myblog.entity.Article;
import com.example.hp_myblog.entity.Comment;
import com.example.hp_myblog.repository.ArticleRepository;
import com.example.hp_myblog.repository.CommentRepository;
import com.example.hp_myblog.service.CommentService;
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

    private final CommentService commentService; // 서비스 레이어 객체

    @PostMapping("/api/comments/{articleId}")
    public Long create(@PathVariable Long articleId, @RequestBody CommentForm form){
        // 서비스 객체가 댓글 생성
        Comment saved = commentService.create(articleId, form);
        log.info("saved: " + saved.toString());
        return saved.getId();
    }
}
