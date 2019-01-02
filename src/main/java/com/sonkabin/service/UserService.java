package com.sonkabin.service;

import com.sonkabin.entity.User;

import java.util.List;

public interface UserService {

    User login(String username, String password);

    List<User> findAll();

    void saveUser(User user);

    User findOne(Integer id);

    void deleteUser(Integer id);
}
