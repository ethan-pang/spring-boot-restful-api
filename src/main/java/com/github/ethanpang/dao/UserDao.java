package com.github.ethanpang.dao;

import com.github.ethanpang.model.User;

import java.util.List;

public interface UserDao {
    User findUserByUsername(String username);
    Integer register(User user);
    List<User> listUser();
}