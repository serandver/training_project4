package com.training.library.services;

import com.training.library.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> find(int id);
    Optional<User> findByEmail(String email);
    int create(User user);
    int update(User user);
    int delete(int id);
    Optional<User> login(String email, String password);
}
