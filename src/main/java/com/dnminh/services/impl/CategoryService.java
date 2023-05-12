package com.dnminh.services.impl;

import com.dnminh.dao.ICategoryDAO;
import com.dnminh.models.CategoryModel;
import com.dnminh.services.ICategoryService;

import javax.inject.Inject;
import java.util.List;

public class CategoryService implements ICategoryService {
//    private ICategoryDAO categoryDAO;
//    public CategoryService() {
//        categoryDAO = new CategoryDAO();
//    }

    @Inject
    private ICategoryDAO categoryDAO;

    @Override
    public List<CategoryModel> findAll() {
        return categoryDAO.findAll();
    }
}
