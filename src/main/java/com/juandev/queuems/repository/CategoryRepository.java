package com.juandev.queuems.repository;

import com.juandev.queuems.model.Category;
import com.juandev.queuems.util.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    public Category findByCategoryName(CategoryName categoryName);
}
