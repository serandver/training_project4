package com.training.library.model.services;

import com.training.library.model.dao.DaoFactory;
import com.training.library.model.entities.User;

import java.util.List;
import java.util.Optional;

public class UserService {
    private DaoFactory daoFactory;

    private static class Holder{
        static final UserService INSTANCE = new UserService( DaoFactory.getInstance() );
    }

    UserService(DaoFactory instance) {
        this.daoFactory = instance;
    }

    public static UserService getInstance(){
        return Holder.INSTANCE;
    }

    public Optional<User> find(int id) {
        return daoFactory.createUserDao().find(id);
    }

    public Optional<User> findByLogin(String login) {
        return daoFactory.createUserDao().findByLogin(login);
    }

    public List<User> findAll() {
        return daoFactory.createUserDao().findAll();
    }

    public int create(User user) {
        return daoFactory.createUserDao().create(user);
    }

    public int update(User user) {
        return daoFactory.createUserDao().create(user);
    }

    public int delete(int id) {
        return daoFactory.createUserDao().delete(id);
    }
}
