package com.training.library.model.dao.jdbc;

import com.training.library.model.dao.*;

public class JdbcDaoFactory extends DaoFactory {
    @Override
    public BookDao createBookDao() {
        return new JdbcBookDao();
    }

    @Override
    public BookOrderDao createBookOrderDao() {
        return new JdbcBookOrderDao();
    }

    @Override
    public UserDao createUserDao() {
        return new JdbcUserDao();
    }
}
