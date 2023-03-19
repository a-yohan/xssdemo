package com.example.xssdemo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.xssdemo.dto.RegisterFormDto;
import com.example.xssdemo.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    @PreAuthorize("permitAll")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/register")
    @PreAuthorize("permitAll")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new RegisterFormDto());
        return "register";
    }

    @PostMapping(path = "/register")
    @PreAuthorize("permitAll")
    public String doRegister(
        @Valid @ModelAttribute("user") RegisterFormDto dto, 
        BindingResult result, 
        Model model) {
            
        if (result.hasErrors()) {
            return "register";
        }
        userService.registerNewUser(dto);
        return "redirect:/";
    }

}
