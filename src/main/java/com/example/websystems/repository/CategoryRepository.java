package com.example.websystems.repository;

import com.example.websystems.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository
        extends JpaRepository<Category, Integer> {
}
