package com.juandev.queuems.service;

import com.juandev.queuems.Exception.ConflictCategoryException;
import com.juandev.queuems.Exception.GetCategoryNotFoundException;
import com.juandev.queuems.Exception.GetConflictException;
import com.juandev.queuems.Exception.GetPatientNotFoundException;
import com.juandev.queuems.dto.CategoryDTO;
import com.juandev.queuems.model.Category;
import com.juandev.queuems.model.Patient;
import com.juandev.queuems.repository.CategoryRepository;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        if (categoryRepository.existsByCategoryName(categoryDTO.getCategoryName())){
            throw new ConflictCategoryException("La categoria "+categoryDTO.getCategoryName()+" ya se encuentra creada");
        } else {
            Category newCategory = new Category();

            newCategory.setCategoryName(categoryDTO.getCategoryName());
            newCategory.setActive(categoryDTO.isActive());

            Category createdCategory = categoryRepository.save(newCategory);

            CategoryDTO createdCategoryDto = new CategoryDTO();
            createdCategoryDto.setCategoryId(createdCategory.getCategoryId());
            createdCategoryDto.setCategoryName(createdCategory.getCategoryName());
            createdCategoryDto.setActive(createdCategoryDto.isActive());

            return createdCategoryDto;
        }
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        if (!categories.isEmpty()){
            List<CategoryDTO> categoryDTOList = new ArrayList<>();
            for (Category category : categories) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setCategoryId(category.getCategoryId());
                categoryDTO.setCategoryName(category.getCategoryName());
                categoryDTO.setActive(category.isActive());
                // Agrega más campos según sea necesario
                categoryDTOList.add(categoryDTO);
            }
            return categoryDTOList;
        } else {
            throw new GetCategoryNotFoundException("Aun no se encuentran categorias registradas en la base de datos");
        }
    }

    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryDTO.getCategoryId());

        if (categoryOptional.isPresent()){
            Category existingCategory = categoryOptional.get();

            existingCategory.setCategoryName(categoryDTO.getCategoryName());
            existingCategory.setActive(categoryDTO.isActive());

            Category updatedCategory = categoryRepository.save(existingCategory);

            CategoryDTO updatedCategoryDTO = new CategoryDTO();

            updatedCategoryDTO.setCategoryId(updatedCategory.getCategoryId());
            updatedCategoryDTO.setCategoryName(updatedCategory.getCategoryName());
            updatedCategoryDTO.setActive(updatedCategory.isActive());

            return updatedCategoryDTO;
        } else {
            throw new GetCategoryNotFoundException("La categoria aun no se encuentra registrada");
        }
    }
}
