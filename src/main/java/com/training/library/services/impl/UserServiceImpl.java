package com.training.library.services.impl;

import com.training.library.dao.DaoFactory;
import com.training.library.dao.UserDao;
import com.training.library.model.User;
import com.training.library.services.UserService;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    private DaoFactory daoFactory;

    public UserServiceImpl(DaoFactory instance) {
        this.daoFactory = instance;
    }

    private static class Holder{
        static final UserServiceImpl INSTANCE = new UserServiceImpl( DaoFactory.getInstance() );
    }

    public static UserServiceImpl getInstance(){
        return Holder.INSTANCE;
    }

    @Override
    public List<User> findAll() {
        UserDao userDao = daoFactory.createUserDao();
        return userDao.findAll();
    }

    @Override
    public int create(User user) {
        UserDao userDao = daoFactory.createUserDao();
        return userDao.create(user);
    }

    @Override
    public Optional<User> find(int id) {
        UserDao userDao = daoFactory.createUserDao();
        return userDao.find(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        UserDao userDao = daoFactory.createUserDao();
        return userDao.findByEmail(email);
    }

    @Override
    public int update(User user) {
        UserDao userDao = daoFactory.createUserDao();
        return userDao.update(user);
    }

    @Override
    public int delete(int id) {
        UserDao userDao = daoFactory.createUserDao();
        return userDao.delete(id);
    }

    @Override
    public Optional<User> login(String email, String password){
        UserDao dao = daoFactory.createUserDao();
        return dao.findByEmail(email)
                .filter( user-> password.equals(user.getPassword()));
    }
}

