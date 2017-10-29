package com.onlinelibrary.service;

import com.onlinelibrary.model.User;

public interface UserService {

    User getUserById(Long userId);

    User getUserByEmailAndPassword(String email, String password);

    void createNewUser(User user);
}
