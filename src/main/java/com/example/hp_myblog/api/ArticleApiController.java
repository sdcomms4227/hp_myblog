package com.example.hp_myblog.api;

import com.example.hp_myblog.dto.ArticleForm;
import com.example.hp_myblog.entity.Article;
import com.example.hp_myblog.repository.ArticleRepository;
import com.example.hp_myblog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ArticleApiController {

    @Autowired // 리파티터리 객체를 알아서 가져옴! 자바는 new ArticleRepository() 해야 했음!
    private ArticleRepository articleRepository;

    // 서비스 레이어 연결! 서비스 레이어란?
    private final ArticleService articleService;

    @PostMapping("/api/articles") // Post 요청이 "/api/articles" url로 온다면, 메소드 수행!
    public Long create(@RequestBody ArticleForm form){ // JSON 데이터를 받아옴!
        log.info(form.toString()); // 받아온 데이터 확인!

        Article article = form.toEntity();

        // 리파지터리에게(db-관리-객체) 전달
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        //저장 엔티티의 id(PK)값 반환!
        return saved.getId();
    }

    @GetMapping("/api/articles/{id}")
    public ArticleForm getArticle(@PathVariable Long id) {
        Article entity = articleRepository.findById(id) // id로 article을 가져옴!
            .orElseThrow( // 만약에 없다면,
                    () -> new IllegalArgumentException("해당 Article이 없습니다.") // 에러를 던짐!
            );

        // article을 form으로 변경! 궁극적으로는 JSON으로 변경 됨! 왜? RestController 때문!
        return new ArticleForm(entity);
    }

    @GetMapping("/api/articles")
    public Iterable<ArticleForm> getArticleList(){
        ArrayList<Article> entityList = (ArrayList<Article>) articleRepository.findAll();
        return entityList.stream().map(Article -> new ArticleForm(Article)).collect(Collectors.toList());
    }

    @PutMapping("/api/articles/{id}") // HTTP 메소드 PUT으로 "/api/articles/{id}" 요청이 들어오면 수행!
    public Long update(@PathVariable Long id, @RequestBody ArticleForm form){
        Article saved = articleService.update(id, form); // 서비스 객체가 update를 수행
        return saved.getId();
    }

    @DeleteMapping("/api/articles/{id}") // HTTP 메소드 DELETE 메소드로 "/api/articles/{id}" 요청이 온다면,
    public Long destroy(@PathVariable Long id){
        return articleService.destroy(id); // 서비스 객체가 destroy()를 수행!
    }
}
