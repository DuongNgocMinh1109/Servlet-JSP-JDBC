package com.dnminh.services;

import com.dnminh.models.NewsModel;

import java.util.List;

public interface INewsService {
    List<NewsModel> findByCategoryId(Long categoryId);
    NewsModel save(NewsModel newsModel);
    NewsModel update(NewsModel newsModelUpdate);
    void delete(long[] arrayId);
}
