package com.juandev.queuems.repository;

import com.juandev.queuems.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    public boolean existsByCategoryName(String categoryName);
}
