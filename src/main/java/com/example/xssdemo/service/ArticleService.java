package com.example.xssdemo.service;

import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.xssdemo.Utils;
import com.example.xssdemo.dto.ArticleFormDto;
import com.example.xssdemo.dto.ArticleResponseDto;
import com.example.xssdemo.model.Article;
import com.example.xssdemo.repository.ArticleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Service
public class ArticleService {
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ArticleRepository articleRepository;

    public ArticleResponseDto submitNewArticle(ArticleFormDto form) {
        Article article = mapper.convertValue(form, Article.class);
        article.setContent(Jsoup.clean(form.getContent(), Safelist.basicWithImages()));
        article.setUser(Utils.currentUser());
        article = articleRepository.save(article);
        return mapper.convertValue(article, ArticleResponseDto.class);
    }

    public ArticleResponseDto updateArticle(UUID id, ArticleFormDto form) {
        Article article = null;
        if (Utils.currentUser().getRole().equals("ROLE_ADMIN")) {
            article = articleRepository.findById(id).get();
        } else {
            article = articleRepository.findByIdAndUser(id, Utils.currentUser()).get();
        }
        mapper.convertValue(form, Article.class);
        article.setContent(Jsoup.clean(form.getContent(), Safelist.basicWithImages()));
        article = articleRepository.save(article);
        return mapper.convertValue(article, ArticleResponseDto.class);
    }

    public ArticleResponseDto getArticleByIdForEdit(UUID id) {
        if (Utils.currentUser().getRole().equals("ROLE_ADMIN")) {
            return getArticleById(id);
        }
        Article article = articleRepository.findByIdAndUser(id, Utils.currentUser()).get();
        return mapper.convertValue(article, ArticleResponseDto.class);
    }

    public ArticleResponseDto getArticleById(UUID id) {
        Article article = articleRepository.findById(id).get();
        return mapper.convertValue(article, ArticleResponseDto.class);
    }

    public Page<ArticleResponseDto> getArticleByUserId(UUID userId, Pageable paging){
        Page<Article> articles = articleRepository.findByUserId(userId, paging);
        return articles.map(article -> mapper.convertValue(article, ArticleResponseDto.class));
    }

    public Page<ArticleResponseDto> searchArticle(String search, Pageable paging){
        Page<Article> articles = articleRepository.findByTitleOrContentContaining(search, paging);
        return articles.map(article -> mapper.convertValue(article, ArticleResponseDto.class));
    }
}
