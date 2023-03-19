package com.example.xssdemo;

import org.springframework.security.core.context.SecurityContextHolder;

import com.example.xssdemo.model.User;

public class Utils {
    public final static User currentUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
