package com.dnminh.dao;

import com.dnminh.models.NewsModel;
import com.dnminh.paging.Pageable;

import java.util.List;

public interface INewsDAO extends GenericDAO<NewsModel> {
    List<NewsModel> findAll(Pageable pageable);

    NewsModel findOne(Long newsId);

    List<NewsModel> findByCategoryId(Long categoryId);

    Long save(NewsModel newsModel);

    void update(NewsModel newsModelUpdate);

    void delete(long id);

    int getTotalItem();
}
