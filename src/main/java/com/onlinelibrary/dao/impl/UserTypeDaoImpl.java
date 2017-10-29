package com.onlinelibrary.dao.impl;

import com.onlinelibrary.dao.UserTypeDao;
import com.onlinelibrary.model.UserType;
import com.onlinelibrary.utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class UserTypeDaoImpl extends GenericDaoImpl<UserType, Long> implements UserTypeDao {

    @Override
    public List<UserType> getAllUserType() {
        EntityManager entityManager = JpaUtil.getEntityManager();
        Query query = entityManager.createQuery("from UserType");
        return query.getResultList();
    }

    @Override
    public UserType getUserTypeByName(String type) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        Query query = entityManager.createQuery("from UserType where lower(type) = :type");
        query.setParameter("type", type.toLowerCase());
        List<UserType> userTupes = query.getResultList();
        return userTupes.isEmpty() ? null : userTupes.get(0);
    }
}
