package com.example.xssdemo.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.xssdemo.dto.CommentFormDto;
import com.example.xssdemo.dto.CommentResponseDto;
import com.example.xssdemo.service.CommentService;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping(path = "/p/{id}/comment")
    @PreAuthorize("permitAll")
    public Page<CommentResponseDto> getArticleComments(
            @PathVariable(name = "id") UUID articleId,
            @RequestParam(name = "p", defaultValue = "0") int page) {

        Pageable pagging = PageRequest.of(page, 10).withSort(Sort.by(Direction.ASC, "createdAt"));
        return commentService.getCommentsByArticleId(articleId, pagging);
    }

    @PostMapping(path = "/p/{id}/comment")
    @PreAuthorize("isAuthenticated()")
    public CommentResponseDto sumbitNewComment(
            @PathVariable(name = "id") UUID articleId,
            @Valid @RequestBody CommentFormDto form) {
        return commentService.submitNewComment(articleId, form);
    }
}
