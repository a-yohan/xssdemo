package com.example.xssdemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAdminDashboard() {
        return "activity";
    }

}
