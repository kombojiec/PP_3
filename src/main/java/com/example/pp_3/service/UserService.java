package com.example.pp_3.service;

import com.example.pp_3.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface UserService extends UserDetailsService {
    Set<User> getUsers();
    User getUserById(int id);
    User deleteUser(int id);
    User getUserByName(String name);
    User saveUser(User user);
    User getUserByEmail(String email);
    void processOAuthPostLogin(String email);
}
