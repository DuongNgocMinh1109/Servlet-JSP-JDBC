package com.dnminh.services;

import com.dnminh.models.CategoryModel;

import java.util.List;

public interface ICategoryService {
    List<CategoryModel> findAll();
}
