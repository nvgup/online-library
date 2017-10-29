package com.onlinelibrary.dao.factory;

import com.onlinelibrary.dao.*;

public interface DaoFactory {

    AuthorDao createAuthorDao();

    BookDao createBookDao();

    GenreDao createGenreDao();

    UserDao createUserDao();

    UserTypeDao createUserTypeDao();
}
