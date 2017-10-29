package com.onlinelibrary.dao;

import com.onlinelibrary.model.Genre;

import java.util.Set;

public interface GenreDao extends GenericDao<Genre, Long> {

    Set<Genre> getAllGenres();
}
