package com.dnminh.services;

import com.dnminh.models.NewsModel;

import java.util.List;

public interface INewsService {
    List<NewsModel> findByCategoryId(Long categoryId);
}
