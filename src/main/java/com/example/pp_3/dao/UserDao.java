package com.example.pp_3.dao;

import com.example.pp_3.entity.User;

import java.util.Set;

public interface UserDao {
    Set<User> getUsers();
    User getUserById(int id);
    void deleteUser(int id);
    User getUserByName(String name);
    void saveUser(User user);
}
