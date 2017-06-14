package com.training.library.model.entities;

import java.util.Date;

public class BookOrder {
    private int id;
    private User user;
    private Book book;
    private Date dateFrom;
    private ReadingPlace place;
    private boolean isReturned;

    public static class Builder{
        private int id;
        private User user;
        private Book book;
        private Date dateFrom;
        private ReadingPlace place;
        private boolean isReturned;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setBook(Book book) {
            this.book = book;
            return this;
        }

        public Builder setDateFrom(Date dateFrom) {
            this.dateFrom = dateFrom;
            return this;
        }

        public Builder setPlace(ReadingPlace place) {
            this.place = place;
            return this;
        }

        public Builder setReturned(boolean returned) {
            this.isReturned = returned;
            return this;
        }

        public BookOrder build(){
            BookOrder bookOrder = new BookOrder();
            bookOrder.setId(id);
            bookOrder.setUser(user);
            bookOrder.setBook(book);
            bookOrder.setDateFrom(dateFrom);
            bookOrder.setPlace(place);
            bookOrder.setReturned(isReturned);
            return bookOrder;
        }
    }

    public static enum ReadingPlace {
        READING_ROOM,
        SUBSCRIPTION
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public ReadingPlace getPlace() {
        return place;
    }

    public void setPlace(ReadingPlace place) {
        this.place = place;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookOrder)) return false;

        BookOrder bookOrder = (BookOrder) o;

        if (id != bookOrder.id) return false;
        if (isReturned != bookOrder.isReturned) return false;
        if (!user.equals(bookOrder.user)) return false;
        if (!book.equals(bookOrder.book)) return false;
        if (!dateFrom.equals(bookOrder.dateFrom)) return false;
        return place == bookOrder.place;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + user.hashCode();
        result = 31 * result + book.hashCode();
        result = 31 * result + dateFrom.hashCode();
        result = 31 * result + place.hashCode();
        result = 31 * result + (isReturned ? 1 : 0);
        return result;
    }
}
