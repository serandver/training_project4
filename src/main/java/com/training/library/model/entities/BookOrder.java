package com.training.library.model.entities;

import java.util.Date;

public class BookOrder {
    private int id;
    private User user;
    private Book book;
    private Date dateFrom;
    private ReadingPlace place;
    private boolean isReturned;

    public static enum ReadingPlace {
        READING_ROOM,
        SUBSCRIPTION
    }
}
