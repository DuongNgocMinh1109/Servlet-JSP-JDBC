package com.dnminh.dao.impl;

import com.dnminh.dao.ICategoryDAO;
import com.dnminh.mapper.CategoryMapper;
import com.dnminh.models.CategoryModel;

import java.util.List;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {

    @Override
    public List<CategoryModel> findAll() {
        String sql_query = "SELECT * FROM category";
        return query(sql_query, new CategoryMapper());
    }
}
