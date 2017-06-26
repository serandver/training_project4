package com.training.library.model.services.impl;

import com.training.library.model.dao.DaoFactory;
import com.training.library.model.entities.User;
import com.training.library.model.services.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private DaoFactory daoFactory;

    public UserServiceImpl() {
        this.daoFactory = DaoFactory.getInstance();
    }

    public UserServiceImpl(DaoFactory instance) {
        this.daoFactory = instance;
    }

    @Override
    public List<User> findAll() {
        return daoFactory.createUserDao().findAll();
    }

    @Override
    public int create(User user) {
        return daoFactory.createUserDao().create(user);
    }

    @Override
    public Optional<User> find(int id) {
        return daoFactory.createUserDao().find(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return daoFactory.createUserDao().findByEmail(email);
    }

    @Override
    public void update(User user) {
        daoFactory.createUserDao().update(user);
    }

    @Override
    public int delete(int id) {
        return daoFactory.createUserDao().delete(id);
    }
}
