package com.training.library.dao.jdbc;

import com.training.library.dao.UserDao;
import com.training.library.model.entities.User;

import java.util.List;
import java.util.Optional;

public class JdbcUserDao implements UserDao{
    @Override
    public Optional<User> find(int id) {
        return null;
    }

    @Override
    public User findByLogin(String login) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void create(User user) {
    }

    @Override
    public void update(User user) {
    }

    @Override
    public void delete(int id) {
    }
}
