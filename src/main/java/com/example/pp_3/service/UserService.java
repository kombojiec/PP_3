package com.example.pp_3.service;

import com.example.pp_3.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface UserService extends UserDetailsService {
    Set<User> getUsers();
    User getUserById(int id);
    void deleteUser(int id);
    User getUserByName(String name);
    void saveUser(User user);
}
