package com.dnminh.mapper;

import com.dnminh.models.CategoryModel;
import com.dnminh.models.NewsModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<CategoryModel> {

    @Override
    public CategoryModel mapRow(ResultSet resultSet) {
        try {
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setId(resultSet.getLong("id"));
            categoryModel.setName(resultSet.getString("name"));
            categoryModel.setCode(resultSet.getString("code"));
            categoryModel.setCreatedDate(resultSet.getTimestamp("createddate"));
            categoryModel.setCreatedBy(resultSet.getString("createdby"));
            if (resultSet.getTimestamp("modifieddate") != null) {
                categoryModel.setModifiedDate(resultSet.getTimestamp("modifieddate"));
            }
            if (resultSet.getString("modifiedby") != null) {
                categoryModel.setModifiedBy(resultSet.getString("modifiedby"));
            }
            return categoryModel;
        } catch (SQLException throwables) {
            return null;
        }
    }
}
