package com.training.library.model.dao;

import com.training.library.model.entities.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    public Optional<User> findByLogin(String login);
}
