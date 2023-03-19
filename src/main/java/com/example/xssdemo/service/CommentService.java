package com.example.xssdemo.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.xssdemo.Utils;
import com.example.xssdemo.dto.CommentFormDto;
import com.example.xssdemo.dto.CommentResponseDto;
import com.example.xssdemo.model.Article;
import com.example.xssdemo.model.Comment;
import com.example.xssdemo.repository.ArticleRepository;
import com.example.xssdemo.repository.CommentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CommentService {
    private ObjectMapper mapper = new ObjectMapper();
    
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ArticleRepository articleRepository;

    public CommentResponseDto submitNewComment(UUID articleId, CommentFormDto form) {
        Article article = articleRepository.findById(articleId).orElseThrow();
        Comment comment = mapper.convertValue(form, Comment.class);
        comment.setUser(Utils.currentUser());
        comment.setArticle(article);
        comment = commentRepository.save(comment);
        return mapper.convertValue(comment, CommentResponseDto.class);
    }

    public Page<CommentResponseDto> getCommentsByArticleId(UUID articleId, Pageable pagging) {
        Page<Comment> comments = commentRepository.findByArticleId(articleId, pagging);
        return comments.map(c -> mapper.convertValue(c, CommentResponseDto.class));
    }
}
