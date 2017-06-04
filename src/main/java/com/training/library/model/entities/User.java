package com.training.library.model.entities;

/**
 * Created by Хоменко Сергій on 04.06.2017.
 */
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Role role;

    public static enum Role {
        LIBRARIAN,
        READER
    }
}
