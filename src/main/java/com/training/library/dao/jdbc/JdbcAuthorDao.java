package com.training.library.dao.jdbc;

import com.training.library.dao.AuthorDao;
import com.training.library.model.entities.Author;

import java.util.List;
import java.util.Optional;

public class JdbcAuthorDao implements AuthorDao{
    @Override
    public Optional<Author> find(int id) {
        return null;
    }

    @Override
    public List<Author> findAll() {
        return null;
    }

    @Override
    public void create(Author author) {

    }

    @Override
    public List<Author> findByLastName(String lastName) {
        return null;
    }

    @Override
    public void update(Author author) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Author> findByLastNameFirstName(String lastName, String firstName) {
        return null;
    }
}
