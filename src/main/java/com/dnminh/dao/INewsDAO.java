package com.dnminh.dao;

import com.dnminh.models.NewsModel;

import java.util.List;

public interface INewsDAO extends GenericDAO<NewsModel> {
    NewsModel findOne(Long newsId);
    List<NewsModel> findByCategoryId(Long categoryId);
    Long save(NewsModel newsModel);
    void update(NewsModel newsModelUpdate);
    void delete(long id);
}
