package com.training.library.controller.utils;

public final class LogMessage {
    public static final String USERDAO_GETALL_ERROR = "JdbcUserDao getAllUsers SQL exception: ";
    public static final String USERDAO_CREATEUSER_ERROR = "JdbcUserDao createUser SQL exception: ";
    public static final String USERDAO_FINDUSER_ERROR = "JdbcUserDao findUser SQL exception: ";
    public static final String USERDAO_FINDBY_EMAIL_ERROR = "JdbcUserDao findByEmail SQL exception: ";
    public static final String USERDAO_UPDATEUSER_ERROR = "JdbcUserDao updateUser SQL exception: ";
    public static final String USERDAO_DELETEUSER_ERROR = "JdbcUserDao deleteUser SQL exception: ";
    public static final String BOOKDAO_CREATE_ERROR = "JdbcBookDao createBook SQL exception: ";
    public static final String BOOKDAO_FINDALL_ERROR = "JdbcBookDao findAll SQL exception: ";
    public static final String BOOKDAO_FINDBY_ID_ERROR = "JdbcBookDao findById SQL exception: ";
    public static final String BOOKDAO_FINDBY_STRING_ERROR = "JdbcBookDao findByTitle SQL exception: ";
    public static final String BOOKDAO_FINDBY_STATUS_ERROR = "JdbcBookDao findByStatus SQL exception: ";
    public static final String BOOKDAO_UPDATE_ERROR = "JdbcBookDao updateBook SQL exception: ";
    public static final String BOOKDAO_DELETE_ERROR = "JdbcBookDao deleteBook SQL exception: ";
    public static final String ORDERDAO_CREATE_ERROR = "JdbcBookOrderDao create SQL exception: ";
    public static final String ORDERDAO_FINDALL_ERROR = "JdbcBookOrderDao findAll SQL exception: ";
    public static final String ORDERDAO_UPDATE_ERROR = "JdbcBookOrderDao update SQL exception: ";
    public static final String ORDERDAO_FINDBY_ERROR = "JdbcBookOrderDao findBy SQL exception: ";
    public static final String ORDERDAO_DELETE_ERROR = "JdbcBookOrderDao delete SQL exception: ";
    public static final String CONNECTION_FAILED = "Connection %d failed";
    public static final String GET_CONNECTION_FAILED = "Failed to get connection from pool";
    public static final String CLOSE_CONNECTION_FAILED = "Failed to close connection %d";
    public static final String CLOSE_STATEMENT_FAILED = "Failed to close statement";
    public static final String DRIVER_FOUND = "Driver found";
    public static final String CONNECTION_ESTABLISHED = "Connection %d established";
    public static final String BOOK_ALREADY_ORDERED = "Book is already ordered. You should delete this order";
}
