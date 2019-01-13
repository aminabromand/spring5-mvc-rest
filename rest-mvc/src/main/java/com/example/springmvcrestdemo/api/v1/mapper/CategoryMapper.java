package com.example.springmvcrestdemo.api.v1.mapper;

import com.example.springmvcrestdemo.api.v1.model.CategoryDTO;
import com.example.springmvcrestdemo.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
