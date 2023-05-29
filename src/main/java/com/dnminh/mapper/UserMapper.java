package com.dnminh.mapper;

import com.dnminh.models.RoleModel;
import com.dnminh.models.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserModel> {

    @Override
    public UserModel mapRow(ResultSet resultSet) {
        try {
            UserModel userModel = new UserModel();
            userModel.setId(resultSet.getLong("id"));
            userModel.setUsername(resultSet.getString("username"));
            userModel.setPassword(resultSet.getString("password"));
            userModel.setFullName(resultSet.getString("fullname"));
            userModel.setStatus(resultSet.getInt("status"));
            try {
                RoleModel role = new RoleModel();
                role.setCode(resultSet.getString("code"));
                role.setName(resultSet.getString("name"));
                userModel.setRole(role);
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
            return userModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
