package com.training.library.model.entities;

/**
 * Created by Хоменко Сергій on 04.06.2017.
 */
public class Book {
    private int id;
    private String title;
    private Author author;
    private Genre genre;

    public static enum Genre {
        FICTION,
        COMEDY,
        DETECTIVE,
        DRAMA,
        FAIRY_TALE,
        FANTASY,
        HORROR,
        MYTHOLOGY,
        REALISTIC,
        ROMANTIC,
        SATIRE,
        TRAGEDY,
        TRAGICOMEDY
    }
}
