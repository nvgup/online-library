package com.onlinelibrary.service.impl;

import com.onlinelibrary.dao.UserTypeDao;
import com.onlinelibrary.dao.factory.DaoFactory;
import com.onlinelibrary.model.UserType;
import com.onlinelibrary.service.UserTypeService;
import com.onlinelibrary.templates.DataAccessTemplate;
import com.onlinelibrary.templates.JpaTemplate;

import java.util.List;

public class UserTypeServiceImpl implements UserTypeService {

    private DaoFactory daoFactory;
    private UserTypeDao userTypeDao;
    private DataAccessTemplate dataAccessTemplate;

    public UserTypeServiceImpl(DaoFactory daoFactory) {
        this(daoFactory, new JpaTemplate());
    }

    public UserTypeServiceImpl(DaoFactory daoFactory, DataAccessTemplate dataAccessTemplate) {
        this.daoFactory = daoFactory;
        userTypeDao = daoFactory.createUserTypeDao();
        this.dataAccessTemplate = dataAccessTemplate;
    }

    @Override
    public List<UserType> getAllUserType() {
        return dataAccessTemplate.findData(res -> userTypeDao.getAllUserType());
    }

    @Override
    public UserType getUserTypeByName(String type) {
        return dataAccessTemplate.findData(res -> userTypeDao.getUserTypeByName(type));
    }

    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        userTypeDao = daoFactory.createUserTypeDao();
    }
}
