package com.training.library.model.entities;

import java.util.Date;

/**
 * Created by Хоменко Сергій on 04.06.2017.
 */
public class BookOrder {
    private int id;
    private Date dateFrom;
    private Date dateTo;
    private ReadingPlace place;
    private OrderStatus status;

    public static enum ReadingPlace {
        READING_ROOM,
        SUBSCRIPTION
    }

    public static enum OrderStatus {
        OPEN,
        CLOSED
    }
}
