package com.dnminh.dao;

import com.dnminh.models.CategoryModel;

import java.util.List;

public interface ICategoryDAO extends GenericDAO<CategoryModel> {
    List<CategoryModel> findAll();
    CategoryModel findOneByCode(String code);
}
