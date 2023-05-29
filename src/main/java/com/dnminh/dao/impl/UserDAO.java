package com.dnminh.dao.impl;

import com.dnminh.dao.IUserDAO;
import com.dnminh.mapper.UserMapper;
import com.dnminh.models.UserModel;

import java.util.List;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {

    @Override
    public UserModel findByCredentials(String username, String password, Integer status) {
        StringBuilder sql = new StringBuilder("SELECT * FROM user AS u");
        sql.append(" INNER JOIN role AS r ON r.id = u.roleid");
        sql.append(" WHERE username = ? AND password = ? AND status = ?");
        List<UserModel> users = query(sql.toString(), new UserMapper(), username, password, status);
        return users.isEmpty() ? null : users.get(0);
    }
}
