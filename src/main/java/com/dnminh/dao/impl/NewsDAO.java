package com.dnminh.dao.impl;

import com.dnminh.dao.INewsDAO;
import com.dnminh.mapper.NewsMapper;
import com.dnminh.models.NewsModel;

import java.util.List;

public class NewsDAO extends AbstractDAO<NewsModel> implements INewsDAO {

    @Override
    public List<NewsModel> findByCategoryId(Long categoryId) {
        String sql_query = "SELECT * FROM news WHERE categoryid = ?";
        return query(sql_query, new NewsMapper(), categoryId);
    }
}
