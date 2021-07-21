package com.example.hp_myblog.repository;

import com.example.hp_myblog.entity.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
