package com.example.pp_3.controller;

import com.example.pp_3.entity.Role;
import com.example.pp_3.entity.User;
import com.example.pp_3.service.RoleService;
import com.example.pp_3.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userservice;
    private RoleService roleService;
    private Set<Role> roles;

    public UserController(UserService userService, RoleService roleService) {
        this.userservice = userService;
        this.roleService = roleService;
        roles = roleService.getRoles();
    }

    @GetMapping("")
    public String getUserPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userservice.getUserByName(auth.getName());
        model.addAttribute("user", user);
        Role admin = roles.stream().filter(el -> el.getRole().equals("ROLE_ADMIN")).findFirst().get();
        model.addAttribute("admin", admin);
        return "user";
    }
}
