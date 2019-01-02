package com.example.springmvcrestdemo.repositories;

import com.example.springmvcrestdemo.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
