package com.example.pp_3.controller;

import com.example.pp_3.entity.Role;
import com.example.pp_3.entity.User;
import com.example.pp_3.service.RoleService;
import com.example.pp_3.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
        public ResponseEntity<?> getUsers(@RequestHeader("isAdmin") boolean isAdmin) {
        ResponseEntity<?> response;
        if(isAdmin) {
            response = new ResponseEntity(userService.getUsers(), HttpStatus.OK);
        } else {
            response = new ResponseEntity("Forbidden page", HttpStatus.FORBIDDEN);
        }
        return response;

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/users")
    public ResponseEntity<?> saveUser(@RequestBody User user, @RequestHeader("isAdmin") boolean isAdmin) {
        ResponseEntity<?> response;
        if(isAdmin) {
            response = new ResponseEntity(userService.saveUser(user), HttpStatus.OK);
        } else {
            response = new ResponseEntity("Forbidden page", HttpStatus.FORBIDDEN);
        }
        return response;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/users")
    public ResponseEntity<?> updateUser(@RequestBody User user, @RequestHeader("isAdmin") boolean isAdmin) {
        ResponseEntity<?> response;
        if(isAdmin) {
            response = new ResponseEntity(userService.saveUser(user), HttpStatus.OK);
        } else {
            response = new ResponseEntity("Forbidden page", HttpStatus.FORBIDDEN);
        }
        return response;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable int id, @RequestHeader("isAdmin") boolean isAdmin) {
        ResponseEntity<?> response;
        if(isAdmin) {
            response = new ResponseEntity(userService.deleteUser(id), HttpStatus.OK);
        } else {
            response = new ResponseEntity("Forbidden page", HttpStatus.FORBIDDEN);
        }
        return response;
    }

}


