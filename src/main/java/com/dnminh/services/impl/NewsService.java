package com.dnminh.services.impl;

import com.dnminh.dao.INewsDAO;
import com.dnminh.models.NewsModel;
import com.dnminh.services.INewsService;

import javax.inject.Inject;
import java.util.List;

public class NewsService implements INewsService {
    @Inject
    private INewsDAO newsDAO;

    @Override
    public List<NewsModel> findByCategoryId(Long categoryId) {
        return newsDAO.findByCategoryId(categoryId);
    }

    @Override
    public NewsModel save(NewsModel newsModel) {
        Long newsId = newsDAO.save(newsModel);
        System.out.println("[+] NewsService save() newsId: " + newsId);
        return null;
    }
}
