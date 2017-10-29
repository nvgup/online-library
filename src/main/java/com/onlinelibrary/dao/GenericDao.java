package com.onlinelibrary.dao;

import java.io.Serializable;

public interface GenericDao<T, ID extends Serializable> {

    void save(T entity);

    void saveOrUpdate(T entity);

    void update(T entity);

    void delete(T entity);

    T findByID(Class type, ID id);
}
