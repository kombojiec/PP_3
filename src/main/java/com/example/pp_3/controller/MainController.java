package com.example.pp_3.controller;

import com.example.pp_3.entity.User;
import com.example.pp_3.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class MainController {

    private UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getMainPage() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/index")
    public String getIndexPage(Model model, Authentication auth) {
        User user = (User) auth.getPrincipal();
        List<String> authorities = auth.getAuthorities().stream().map(el -> el.toString().substring(5)).collect(Collectors.toList());
        model.addAttribute("user", user);
        model.addAttribute("auths", authorities);
        return "index";
    }
}
