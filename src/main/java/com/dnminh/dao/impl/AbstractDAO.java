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
                setParameters(preparedStatement, parameters); // set param
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    resultQuery.add(rowMapper.mapRow(resultSet));
                }
                return resultQuery;
            } catch (SQLException e) {
                return null;
            } finally {
                try {
                    connection.close();
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException ex) {
                    System.out.println("[-] AbstractDAO SQLException: " + ex.getMessage());
                }
            }
        }
        return null;
    }

    @Override
    public void update(String sql, Object... parameters) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        if (connection != null) {
            try {
                connection.setAutoCommit(false);
                preparedStatement = connection.prepareStatement(sql);
                setParameters(preparedStatement, parameters);
                preparedStatement.executeUpdate();

                System.out.println("[+] Successfully!");
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.out.println("[-] AbstractDAO update() SQLException: " + ex.getMessage());
                }
            } finally {
                try {
                    connection.close();
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                } catch (SQLException ex2) {
                    System.out.println("[-] AbstractDAO update() SQLException: " + ex2.getMessage());
                }
            }
        }
    }

    @Override
    public Long insert(String sql, Object... parameters) {
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long id = null;
        if (connection != null) {
            try {
                connection.setAutoCommit(false);
                preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                setParameters(preparedStatement, parameters);
                preparedStatement.executeUpdate();

                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    id = resultSet.getLong(1);
                }
                connection.commit();
                return id;
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.out.println("[-] AbstractDAO insert() SQLException: " + ex.getMessage());
                }
                return null;
            } finally {
                try {
                    connection.close();
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException ex2) {
                    System.out.println("[-] AbstractDAO insert() SQLException: " + ex2.getMessage());
                }
            }
        }
        return null;
    }

    @Override
    public int count(String sql, Object... parameters) {
        int count = 0;
        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(sql);
                setParameters(preparedStatement, parameters); // set param
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
                return count;
            } catch (SQLException e) {
                return 0;
            } finally {
                try {
                    connection.close();
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException ex) {
                    System.out.println("[-] AbstractDAO SQLException: " + ex.getMessage());
                }
            }
        }
        return 0;
    }

    private void setParameters(PreparedStatement preparedStatement, Object... parameters) {
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
