package com.onlinelibrary.dao;

import com.onlinelibrary.model.UserType;

import java.util.List;

public interface UserTypeDao extends GenericDao<UserType, Long> {

    List<UserType> getAllUserType();

    UserType getUserTypeByName(String type);
}
