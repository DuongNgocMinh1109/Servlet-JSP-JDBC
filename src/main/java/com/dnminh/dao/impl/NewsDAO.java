package com.dnminh.dao.impl;

import com.dnminh.dao.INewsDAO;
import com.dnminh.models.CategoryModel;
import com.dnminh.models.NewsModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO extends AbstractDAO<NewsModel> implements INewsDAO {
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
    public List<NewsModel> findByCategoryId(Long categoryId) {
        List<NewsModel> resultFindByCategoryId = new ArrayList<>();
        String sql_query = "SELECT * FROM news WHERE categoryid=?";
//        Open connection
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(sql_query);
                preparedStatement.setLong(1, categoryId);
                resultSet =  preparedStatement.executeQuery();
                while (resultSet.next()) {
                    NewsModel newsModel = new NewsModel();
                    newsModel.setId(resultSet.getLong("id"));
                    newsModel.setTitle(resultSet.getString("title"));
                    resultFindByCategoryId.add(newsModel);
                }
                return resultFindByCategoryId;
            } catch (SQLException throwables) {
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
        return null;
    }
}
