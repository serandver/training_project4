package com.training.library.model.services.impl;

import com.training.library.model.dao.DaoFactory;
import com.training.library.model.dao.jdbc.JdbcUserDao;
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

    public List<User> findAll() {
        return daoFactory.createUserDao().findAll();
    }

    public int create(User user) {
        return daoFactory.createUserDao().create(user);
    }

    public Optional<User> find(int id) {
        return daoFactory.createUserDao().find(id);
    }

    public Optional<User> findByLogin(String login) {
        return daoFactory.createUserDao().findByLogin(login);
    }


    public int update(User user) {
        return daoFactory.createUserDao().update(user);
    }

    public int delete(int id) {
        return daoFactory.createUserDao().delete(id);
    }


}
