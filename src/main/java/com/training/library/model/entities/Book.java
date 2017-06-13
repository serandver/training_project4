package com.training.library.model.entities;

public class Book {
    private int id;
    private String title;
    private String author;

    public static class Builder{
        private int id;
        private String title;
        private String author;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }
        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }
        public Book build(){
            Book book = new Book();
            book.setId(id);
            book.setTitle(title);
            book.setAuthor(author);
            return book;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
