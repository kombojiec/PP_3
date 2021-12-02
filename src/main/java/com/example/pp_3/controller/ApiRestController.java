package com.example.pp_3.controller;

import com.example.pp_3.entity.Role;
import com.example.pp_3.entity.User;
import com.example.pp_3.service.RoleService;
import com.example.pp_3.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class ApiRestController {

    private final UserService userService;
    private final RoleService roleService;
    private final Set<Role> roles;

    public ApiRestController(UserService userService, RoleService roleService) {
        this.roleService = roleService;
        this.userService = userService;
        roles = roleService.getRoles();
    }

    @GetMapping("/users")
    public Set<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/users/{id}")
    public User deleteUserById(@PathVariable int id) {
        return userService.deleteUser(id);
    }

}
