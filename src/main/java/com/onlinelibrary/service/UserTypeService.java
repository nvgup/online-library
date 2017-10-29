package com.onlinelibrary.service;

import com.onlinelibrary.model.UserType;

import java.util.List;

public interface UserTypeService {

    List<UserType> getAllUserType();

    UserType getUserTypeByName(String type);
}
