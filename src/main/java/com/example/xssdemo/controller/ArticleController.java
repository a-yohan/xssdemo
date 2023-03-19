package com.example.xssdemo.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.xssdemo.dto.ArticleFormDto;
import com.example.xssdemo.dto.ArticleResponseDto;
import com.example.xssdemo.service.ArticleService;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @GetMapping(path = "/")
    @PreAuthorize("permitAll")
    public String showArticles(
            @RequestParam(name = "q", defaultValue = "") String search,
            @RequestParam(name = "p", defaultValue = "0") int page,
            Model model) {
        Pageable pagging = PageRequest.of(page, 4).withSort(Sort.by(Direction.DESC, "createdAt"));
        model.addAttribute("result", articleService.searchArticle(search, pagging));
        return "home";
    }

    @GetMapping(path = "/u/{userId}")
    @PreAuthorize("permitAll")
    public String showArticlesById(
            @PathVariable(name = "userId") UUID userId,
            @RequestParam(name = "p", defaultValue = "0") int page,
            Model model) {
        Pageable pagging = PageRequest.of(page, 10).withSort(Sort.by(Direction.DESC, "createdAt"));
        model.addAttribute("result", articleService.getArticleByUserId(userId, pagging));
        return "home";
    }

    @GetMapping(path = "/new-article")
    @PreAuthorize("isAuthenticated()")
    public String showCreateArticlePage(Model model) {
        model.addAttribute("article", new ArticleFormDto());
        return "editor";
    }

    @PostMapping(path = "/new-article")
    @PreAuthorize("isAuthenticated()")
    public String submitNewArticle(
            @Valid @ModelAttribute("article") ArticleFormDto dto,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "editor";
        }
        ArticleResponseDto article = articleService.submitNewArticle(dto);
        return "redirect:/p/" + article.getId() + "/edit";
    }

    @GetMapping(path = "/p/{id}")
    @PreAuthorize("permitAll")
    public String showArticle(@PathVariable(name = "id") UUID id, Model model) {
        model.addAttribute("article", articleService.getArticleById(id));
        return "read";
    }

    @GetMapping(path = "/p/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String showEditArticleForm(@PathVariable(name = "id") UUID id, Model model) {
        model.addAttribute("article", articleService.getArticleByIdForEdit(id));
        return "editor";
    }

    @PostMapping(path = "/p/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String updateArticle(
            @PathVariable(name = "id") UUID id,
            @Valid @ModelAttribute("article") ArticleFormDto dto,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "editor";
        }
        ArticleResponseDto article = articleService.updateArticle(id, dto);
        model.addAttribute("article", article);
        return "editor";
    }
}
