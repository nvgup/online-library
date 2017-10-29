package com.onlinelibrary.service.impl;

import com.onlinelibrary.dao.GenreDao;
import com.onlinelibrary.dao.factory.DaoFactory;
import com.onlinelibrary.model.Genre;
import com.onlinelibrary.service.GenreService;
import com.onlinelibrary.templates.DataAccessTemplate;
import com.onlinelibrary.templates.JpaTemplate;

import java.util.Set;

public class GenreServiceImpl implements GenreService {

    private DaoFactory daoFactory;
    private GenreDao genreDao;
    private DataAccessTemplate dataAccessTemplate;

    public GenreServiceImpl(DaoFactory daoFactory) {
        this(daoFactory, new JpaTemplate());
    }

    public GenreServiceImpl(DaoFactory daoFactory, DataAccessTemplate dataAccessTemplate) {
        this.daoFactory = daoFactory;
        this.dataAccessTemplate = dataAccessTemplate;
        genreDao = daoFactory.createGenreDao();
    }

    @Override
    public Set<Genre> getAllGenres() {
        return dataAccessTemplate.findData(res -> genreDao.getAllGenres());
    }
}
