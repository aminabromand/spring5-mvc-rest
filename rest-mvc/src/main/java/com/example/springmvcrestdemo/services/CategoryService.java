package com.example.springmvcrestdemo.services;

import com.example.springmvcrestdemo.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryByName(String name);
}
