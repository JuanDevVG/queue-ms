package com.juandev.queuems.service;

import com.juandev.queuems.Exception.ConflictCategoryException;
import com.juandev.queuems.model.Category;
import com.juandev.queuems.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category saveCategory(Category category) {
        if (categoryRepository.existsByCategoryName(category.getCategoryName())){
            throw new ConflictCategoryException("La categoria "+category.getCategoryName()+" ya se encuentra creada");
        } else {
            return categoryRepository.save(category);
        }
    }
}
