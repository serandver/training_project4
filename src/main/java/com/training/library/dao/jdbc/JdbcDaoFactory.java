package com.training.library.dao.jdbc;

import com.training.library.dao.*;

/**
 * Created by Хоменко Сергій on 04.06.2017.
 */
public class JdbcDaoFactory extends DaoFactory {
    @Override
    public DaoConnection getConnection() {
        return null;
    }

    @Override
    public AuthorDao createAuthorDao() {
        return null;
    }

    @Override
    public BookDao createBookDao() {
        return null;
    }

    @Override
    public BookOrderDao createBookOrderDao() {
        return null;
    }

    @Override
    public UserDao createUserDao() {
        return null;
    }

    @Override
    public AuthorDao createAuthorDao(DaoConnection connection) {
        return null;
    }

    @Override
    public BookDao createBookDao(DaoConnection connection) {
        return null;
    }

    @Override
    public BookOrderDao createBookOrderDao(DaoConnection connection) {
        return null;
    }

    @Override
    public UserDao createUserDao(DaoConnection connection) {
        return null;
    }
}
