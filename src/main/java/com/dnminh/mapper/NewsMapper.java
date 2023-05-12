package com.dnminh.mapper;

import com.dnminh.models.NewsModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsMapper implements RowMapper<NewsModel> {

    @Override
    public NewsModel mapRow(ResultSet resultSet) {
        try {
            NewsModel newsModel = new NewsModel();
            newsModel.setId(resultSet.getLong("id"));
            newsModel.setTitle(resultSet.getString("title"));
            newsModel.setThumbnail(resultSet.getString("thumbnail"));
            newsModel.setShortDescription(resultSet.getString("shortdescription"));
            newsModel.setContent(resultSet.getString("content"));
            newsModel.setCategoryId(resultSet.getLong("categoryid"));
            newsModel.setCreatedDate(resultSet.getTimestamp("createddate"));
            newsModel.setCreatedBy(resultSet.getString("createdby"));
            if (resultSet.getTimestamp("modifieddate") != null) {
                newsModel.setModifiedDate(resultSet.getTimestamp("modifieddate"));
            }
            if (resultSet.getString("modifiedby") != null) {
                newsModel.setModifiedBy(resultSet.getString("modifiedby"));
            }
            return newsModel;
        } catch (SQLException throwables) {
            return null;
        }
    }
}
