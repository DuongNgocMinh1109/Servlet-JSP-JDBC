package com.dnminh.services.impl;

import com.dnminh.dao.IUserDAO;
import com.dnminh.models.UserModel;
import com.dnminh.services.IUserService;

import javax.inject.Inject;

public class UserService implements IUserService {
    @Inject
    private IUserDAO userDAO;

    @Override
    public UserModel findByCredentials(String username, String password, Integer status) {
        return userDAO.findByCredentials(username, password, status);
    }
}
