package com.dnminh.dao.impl;

import com.dnminh.dao.GenericDAO;
import com.dnminh.mapper.RowMapper;

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
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(sql);
                setparameters(preparedStatement, parameters); // set param
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    resultQuery.add(rowMapper.mapRow(resultSet));
                }
                return resultQuery;
            } catch (SQLException e) {
                return null;
            } finally {
                try {
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException throwables) {
                    System.out.println("[-] AbstractDAO SQLException: " + throwables.getMessage());
                }
            }
        }
        return null;
    }

    private void setparameters(PreparedStatement preparedStatement, Object... parameters) {
        try {
            for (int i = 0; i < parameters.length; i++) {
                Object parameter = parameters[i];
                int index = i + 1; // index of parameter in preparedStatement begin by 1
                if (parameter instanceof Long) {
                    preparedStatement.setLong(index, (Long) parameter);
                } else if (parameter instanceof String) {
                    preparedStatement.setString(index, (String) parameter);
                } else if (parameter instanceof Integer) {
                    preparedStatement.setInt(index, (Integer) parameter);
                } else if (parameter instanceof Timestamp) {
                    preparedStatement.setTimestamp(index, (Timestamp) parameter);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
