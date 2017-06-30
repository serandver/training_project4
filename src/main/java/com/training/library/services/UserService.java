package com.training.library.services;

import com.training.library.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> find(int id);
    Optional<User> findByEmail(String email);
    int create(User user);
    void update(User user);
    int delete(int id);
    Optional<User> login(String email, String password);
}
