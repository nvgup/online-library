package com.onlinelibrary.service.impl;

import com.onlinelibrary.dao.UserDao;
import com.onlinelibrary.dao.factory.DaoFactory;
import com.onlinelibrary.model.User;
import com.onlinelibrary.service.UserService;
import com.onlinelibrary.templates.DataAccessTemplate;
import com.onlinelibrary.templates.JpaTemplate;

public class UserServiceImpl implements UserService {

    private DaoFactory daoFactory;
    private UserDao userDao;
    private DataAccessTemplate dataAccessTemplate;

    public UserServiceImpl(DaoFactory daoFactory) {
        this(daoFactory, new JpaTemplate());
    }

    public UserServiceImpl(DaoFactory daoFactory, DataAccessTemplate dataAccessTemplate) {
        this.daoFactory = daoFactory;
        userDao = daoFactory.createUserDao();
        this.dataAccessTemplate = dataAccessTemplate;
    }

    @Override
    public User getUserById(Long userId) {
        return dataAccessTemplate.findData(res -> userDao.findByID(User.class, userId));
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        return dataAccessTemplate.findData(res -> userDao.getUserByEmailAndPassword(email, password));
    }

    @Override
    public void createNewUser(User user) {
        dataAccessTemplate.executeOperation(() -> userDao.save(user));
    }

    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        userDao = daoFactory.createUserDao();
    }
}
