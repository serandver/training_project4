package com.training.library.dao.jdbc;

import com.training.library.dao.*;

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
