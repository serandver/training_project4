package com.training.library.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookOrder {
    private int id;
    private User user;
    private Book book;
    private Date dateOfReceive = null;
    private Date dateOfReturn = null;
    private ReadingPlace place;
    private OrderStatus orderStatus;

    public static class Builder{
        private int id;
        private User user;
        private Book book;
        private Date dateOfReceive = null;
        private Date dateOfReturn = null;
        private ReadingPlace place;
        private OrderStatus orderStatus;

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

        public Builder setDateOfReceive(Date dateOfReceive) {
            this.dateOfReceive = dateOfReceive;
            return this;
        }

        public Builder setDateOfReturn(Date dateOfReturn) {
            this.dateOfReturn = dateOfReturn;
            return this;
        }

        public Builder setPlace(ReadingPlace place) {
            this.place = place;
            return this;
        }

        public Builder setOrderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public BookOrder build(){
            BookOrder bookOrder = new BookOrder();
            bookOrder.setId(id);
            bookOrder.setUser(user);
            bookOrder.setBook(book);
            bookOrder.setDateOfReceive(dateOfReceive);
            bookOrder.setDateOfReturn(dateOfReturn);
            bookOrder.setPlace(place);
            bookOrder.setOrderStatus(orderStatus);
            return bookOrder;
        }
    }

    public static enum ReadingPlace {
        READING_ROOM,
        SUBSCRIPTION
    }


    public static enum OrderStatus {
        OPEN,
        CLOSED
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

    public Date getDateOfReceive() {
        return dateOfReceive;
    }

    public void setDateOfReceive(Date dateOfReceive) {
        this.dateOfReceive = dateOfReceive;
    }

    public ReadingPlace getPlace() {
        return place;
    }

    public void setPlace(ReadingPlace place) {
        this.place = place;
    }

    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(Date dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookOrder)) return false;

        BookOrder bookOrder = (BookOrder) o;

        if (id != bookOrder.id) return false;
        if (!user.equals(bookOrder.user)) return false;
        if (!book.equals(bookOrder.book)) return false;
        if (!dateOfReceive.equals(bookOrder.dateOfReceive)) return false;
        if (dateOfReturn != null ? !dateOfReturn.equals(bookOrder.dateOfReturn) : bookOrder.dateOfReturn != null)
            return false;
        if (place != bookOrder.place) return false;
        return orderStatus == bookOrder.orderStatus;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + user.hashCode();
        result = 31 * result + book.hashCode();
        result = 31 * result + dateOfReceive.hashCode();
        result = 31 * result + (dateOfReturn != null ? dateOfReturn.hashCode() : 0);
        result = 31 * result + place.hashCode();
        result = 31 * result + orderStatus.hashCode();
        return result;
    }

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("dd mm yyyy");
        return "BookOrder{" +
                "id=" + id +
                ", user=" + user +
                ", book=" + book +
                ", dateOfReceive=" + df.format(dateOfReceive) +
                ", dateOfReturn=" + df.format(dateOfReturn) +
                ", place=" + place +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
