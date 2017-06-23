package com.training.library.model.services;

import com.training.library.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> find(int id);
    Optional<User> findByEmail(String email);
    int create(User user);
    int update(User user);
    int delete(int id);
}
