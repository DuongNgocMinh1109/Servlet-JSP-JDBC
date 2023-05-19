package com.dnminh.services.impl;

import com.dnminh.dao.ICategoryDAO;
import com.dnminh.dao.INewsDAO;
import com.dnminh.models.CategoryModel;
import com.dnminh.models.NewsModel;
import com.dnminh.services.INewsService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class NewsService implements INewsService {
    @Inject
    private INewsDAO newsDAO;
    @Inject
    private ICategoryDAO categoryDAO;

    @Override
    public List<NewsModel> findByCategoryId(Long categoryId) {
        return newsDAO.findByCategoryId(categoryId);
    }

    @Override
    public NewsModel save(NewsModel newsModel) {
        newsModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        newsModel.setCreatedBy("Duong Ngoc Minh");
/*        CategoryModel category = categoryDAO.findOneByCode(newsModel.getCategoryCode());
        newsModel.setCategoryId(category.getId());*/
        Long newsId = newsDAO.save(newsModel);
        return newsDAO.findOne(newsId);
    }

    @Override
    public NewsModel update(NewsModel newsModelUpdate) {
        NewsModel oldNews = newsDAO.findOne(newsModelUpdate.getId());

//        get the unupdated fields of old news
        newsModelUpdate.setCreatedDate(oldNews.getCreatedDate());
        newsModelUpdate.setCreatedBy(oldNews.getCreatedBy());
        newsModelUpdate.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        newsModelUpdate.setModifiedBy("DNMinh Update");
/*
        CategoryModel category = categoryDAO.findOneByCode(newsModelUpdate.getCategoryCode());
        newsModelUpdate.setCategoryId(category.getId());*/
        newsDAO.update(newsModelUpdate);
        return newsDAO.findOne(newsModelUpdate.getId());
    }

    @Override
    public void delete(long[] arrayId) {
        for (long id : arrayId) {
            //1.delete comment (FOREIGN KEY new_id)
            //2.delete news
            newsDAO.delete(id);
        }
    }
}
