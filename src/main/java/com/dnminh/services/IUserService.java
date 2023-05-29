package com.dnminh.services;

import com.dnminh.models.UserModel;

public interface IUserService {
    UserModel findByCredentials(String username, String password, Integer status);
}
