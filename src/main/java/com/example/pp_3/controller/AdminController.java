package com.example.pp_3.controller;

import com.example.pp_3.entity.Role;
import com.example.pp_3.entity.User;
import com.example.pp_3.service.RoleService;
import com.example.pp_3.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;
    private Set<Role> roles;

    public AdminController(UserService userService, RoleService roleService) {
        this.roleService = roleService;
        this.userService = userService;
        roles = roleService.getRoles();
    }

    @GetMapping("")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "index";
    }

    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("user",new User());
        model.addAttribute("roles", roles);
        return "user-form";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "user-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult,
                           @RequestParam(value = "params", required = false) String[] params,
                           Model model) {

        params = params != null? params: new String[]{};
        Set<Role> rolesSet = Arrays.stream(params)
                .map(item -> roles.stream().filter(el -> el.getRole().equals(item)).findFirst().get())
                        .collect(Collectors.toSet());
        user.setRoles(rolesSet);

        if(bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("roles", roles);
            return "user-form";
        }

        userService.saveUser(user);
        return "redirect:/admin";
    }
}
