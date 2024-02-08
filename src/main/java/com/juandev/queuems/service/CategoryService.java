package com.juandev.queuems.service;

import com.juandev.queuems.Exception.ConflictCategoryException;
import com.juandev.queuems.Exception.GetCategoryNotFoundException;
import com.juandev.queuems.Exception.GetConflictException;
import com.juandev.queuems.Exception.GetPatientNotFoundException;
import com.juandev.queuems.model.Category;
import com.juandev.queuems.model.Patient;
import com.juandev.queuems.repository.CategoryRepository;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
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

    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (!categories.isEmpty()){
            return categories;
        } else {
            throw new GetCategoryNotFoundException("Aun no se encuentran categorias registradas en la base de datos");
        }
    }

    public Category updateCategory(Category category) {
        if (categoryRepository.existsById(category.getCategoryId())){
            return categoryRepository.save(category);
        } else {
            throw new GetCategoryNotFoundException("La categoria aun no se encuentra registrada");
        }
    }
}
