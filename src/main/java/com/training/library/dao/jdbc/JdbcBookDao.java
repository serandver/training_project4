package com.training.library.dao.jdbc;

import com.training.library.dao.BookDao;
import com.training.library.model.entities.Author;
import com.training.library.model.entities.Book;

import java.util.List;
import java.util.Optional;

public class JdbcBookDao implements BookDao {
    @Override
    public Optional<Book> find(int id) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public void create(Book book) {

    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Book> findByTitle(String title) {
        return null;
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        return null;
    }

    @Override
    public List<Book> findByBookGenre(Book.Genre genre) {
        return null;
    }
}
