package com.training.library.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<E> {
    Optional<E> find(int id);
    List<E> findAll();
    int create(E e);
    void update(E e);
    int delete(int id);
}
