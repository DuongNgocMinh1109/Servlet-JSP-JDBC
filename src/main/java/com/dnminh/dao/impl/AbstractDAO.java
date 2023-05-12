package com.dnminh.dao.impl;

import com.dnminh.dao.GenericDAO;
import com.dnminh.mapper.RowMapper;
import com.dnminh.models.CategoryModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractDAO<T> implements GenericDAO<T> {
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url_db = "jdbc:mysql://localhost:3306/servlet-jsp-jdbc";
            String username_db = "root";
            String password_db = "root";
            return DriverManager.getConnection(url_db, username_db, password_db);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }

    @Override
    public List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
        List<T> resultQuery = new ArrayList<>();
        Connection connection = getConnection();;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
//            set param
            resultSet =  preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultQuery.add(rowMapper.mapRow(resultSet));
            }
            return resultQuery;
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException throwables) {
                return null;
            }
        }
    }
}
