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

    public void saveCategory(CategoryDTO categoryDTO) {
        if (categoryRepository.existsByCategoryName(categoryDTO.getCategoryName())){
            throw new ConflictCategoryException("La categoria "+categoryDTO.getCategoryName()+" ya se encuentra creada");
        } else {

            //Mapear el CategoryDTO en una entidad Category
            Category newCategory = Category.builder()
                            .categoryName(categoryDTO.getCategoryName())
                            .active(categoryDTO.isActive())
                            .build();
            //Guardar la Categoria en la base de datos
            categoryRepository.save(newCategory);
        }
    }

    public List<CategoryDTO> getAllCategories() {
        //Obtener lista de categorias de la base de datos
        List<Category> categories = categoryRepository.findAll();

        if (!categories.isEmpty()){
            List<CategoryDTO> categoryDTOList = new ArrayList<>();

            for (Category category : categories) {

                //Mapea datos de categories en lista de CategoryDTO
                CategoryDTO categoryDTO = CategoryDTO.builder()
                                    .categoryId(category.getCategoryId())
                                    .categoryName(category.getCategoryName())
                                    .active(category.isActive())
                                    .build();
                //Agrega la CategoryDTO creada a la lista de CategoryDTOList
                categoryDTOList.add(categoryDTO);
            }
            return categoryDTOList;
        } else {
            throw new GetCategoryNotFoundException("Aun no se encuentran categorias registradas en la base de datos");
        }
    }

    public void updateCategory(CategoryDTO categoryDTO) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryDTO.getCategoryId());

        if (categoryOptional.isPresent()){
            //Asignar datos nuevos de CategoryDTO a category
            Category category = categoryOptional.get();
            category.setCategoryName(categoryDTO.getCategoryName());
            category.setActive(categoryDTO.isActive());

            categoryRepository.save(category);
        } else {
            throw new GetCategoryNotFoundException("La categoria aun no se encuentra registrada");
        }
    }
}
