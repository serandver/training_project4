package com.training.library.dao;

import com.training.library.model.entities.User;

public interface UserDao extends GenericDao<User> {

    public User findByLogin(String login);
}
