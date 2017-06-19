package com.training.library.model.entities;

public class Book {
    private int id;
    private String title;
    private String author;
    private String inventoryNumber;

    public static class Builder{
        private int id;
        private String title;
        private String author;
        private String inventoryNumber;

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
        public Builder setInventoryNumber(String inventoryNumber) {
            this.inventoryNumber = inventoryNumber;
            return this;
        }
        public Book build(){
            Book book = new Book();
            book.setId(id);
            book.setTitle(title);
            book.setAuthor(author);
            book.setInventoryNumber(inventoryNumber);
            return book;
        }
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (!title.equals(book.title)) return false;
        return author.equals(book.author);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode();
        result = 31 * result + author.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
