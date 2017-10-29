package com.onlinelibrary.dao.impl;

import com.onlinelibrary.dao.GenericDao;
import com.onlinelibrary.utils.JpaUtil;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {

    @Override
    public void delete(T entity) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        entityManager.remove(entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        entityManager.merge(entity);
    }

    @Override
    public T findByID(Class type, ID id) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        return (T) entityManager.find(type, id);
    }

    @Override
    public void save(T entity) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        entityManager.persist(entity);
    }

    @Override
    public void update(T entity) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        entityManager.merge(entity);
    }
}
