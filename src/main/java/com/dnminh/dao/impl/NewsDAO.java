package com.dnminh.dao.impl;

import com.dnminh.dao.INewsDAO;
import com.dnminh.mapper.NewsMapper;
import com.dnminh.models.NewsModel;
import com.dnminh.paging.Pageable;

import java.sql.*;
import java.util.List;

public class NewsDAO extends AbstractDAO<NewsModel> implements INewsDAO {
    @Override
    public List<NewsModel> findAll(Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT * FROM news");
        if (pageable.getSorter() != null) {
            sql.append(" ORDER BY " + pageable.getSorter().getSortField() + " " + pageable.getSorter().getSortBy() + "");
        }
        if (pageable.getOffset() != null && pageable.getLimit() != null) {
            sql.append(" LIMIT " + pageable.getOffset() + ", " + pageable.getLimit() + "");
        }
        return query(sql.toString(), new NewsMapper());
    }

    @Override
    public NewsModel findOne(Long newsId) {
        String sql_query = "SELECT * FROM news WHERE id = ?";
        List<NewsModel> news = query(sql_query, new NewsMapper(), newsId);
        return news.isEmpty() ? null : news.get(0);
    }

    @Override
    public List<NewsModel> findByCategoryId(Long categoryId) {
        String sql_query = "SELECT * FROM news WHERE categoryid = ?";
        return query(sql_query, new NewsMapper(), categoryId);
    }

    @Override
    public Long save(NewsModel newsModel) {
        StringBuilder sql = new StringBuilder("INSERT INTO news (title, thumbnail, shortdescription, content,");
        sql.append(" categoryid, createddate, createdby) VALUES(?, ?, ?, ?, ?, ?, ?)");
        return insert(sql.toString(), newsModel.getTitle(), newsModel.getThumbnail(), newsModel.getShortDescription(),
                newsModel.getContent(), newsModel.getCategoryId(), newsModel.getCreatedDate(),
                newsModel.getCreatedBy());
    }

    @Override
    public void update(NewsModel newsModelUpdate) {
        StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?, shortdescription = ?, content = ?,");
        sql.append(" categoryid = ?, createddate = ?, modifieddate = ?, createdby = ?, modifiedby = ? WHERE id = ?");
        update(sql.toString(), newsModelUpdate.getTitle(), newsModelUpdate.getThumbnail(),
                newsModelUpdate.getShortDescription(), newsModelUpdate.getContent(),
                newsModelUpdate.getCategoryId(), newsModelUpdate.getCreatedDate(),
                newsModelUpdate.getModifiedDate(), newsModelUpdate.getCreatedBy(),
                newsModelUpdate.getModifiedBy(), newsModelUpdate.getId());
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM news WHERE id = ?";
        update(sql, id);
    }

    @Override
    public int getTotalItem() {
        String sql = "SELECT count(*) FROM news";
        return count(sql);
    }
}
