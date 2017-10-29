package com.onlinelibrary.dao.factory;

import com.onlinelibrary.dao.*;
import com.onlinelibrary.dao.impl.*;

public class DaoFactoryImpl implements DaoFactory {

    @Override
    public AuthorDao createAuthorDao() {
        return new AuthorDaoImpl();
    }

    @Override
    public BookDao createBookDao() {
        return new BookDaoImpl();
    }

    @Override
    public GenreDao createGenreDao() {
        return new GenreDaoImpl();
    }

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl();
    }

    @Override
    public UserTypeDao createUserTypeDao() {
        return new UserTypeDaoImpl();
    }
}
