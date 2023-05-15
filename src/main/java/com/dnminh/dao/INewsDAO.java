package com.dnminh.dao;

import com.dnminh.models.NewsModel;

import java.util.List;

public interface INewsDAO extends GenericDAO<NewsModel> {
    List<NewsModel> findByCategoryId(Long categoryId);
    Long save(NewsModel newsModel);
}
