package com.training.library.dao;

import com.training.library.model.entities.Author;

import java.util.List;

public interface AuthorDao extends GenericDao<Author> {

    public List<Author> findByLastName(String lastName);

    public List<Author> findByLastNameFirstName(String lastName, String firstName);
}
