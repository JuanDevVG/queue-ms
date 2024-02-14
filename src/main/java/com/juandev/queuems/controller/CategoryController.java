package com.juandev.queuems.controller;

import com.juandev.queuems.Exception.ConflictCategoryException;
import com.juandev.queuems.Exception.GetCategoryNotFoundException;
import com.juandev.queuems.Exception.GetConflictException;
import com.juandev.queuems.dto.CategoryDTO;
import com.juandev.queuems.model.Category;
import com.juandev.queuems.service.CategoryService;
import com.juandev.queuems.util.Response;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    private ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
        try {
            categoryService.saveCategory(categoryDTO);
            return new ResponseEntity<>("Se creo el registro correctamente", HttpStatus.CREATED);
        } catch (ConflictCategoryException e) {
            return new ResponseEntity<>(new Response(e.getMessage(), categoryDTO), HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/get")
    private ResponseEntity<?> getCategories(){
        try {
            List<CategoryDTO> categoryDTOList = categoryService.getAllCategories();
            return new ResponseEntity<>(new Response("Consulta exitosa", categoryDTOList), HttpStatus.OK);
        } catch (GetCategoryNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    private ResponseEntity<?> updateCategory(@RequestBody CategoryDTO categoryDTO){
        try {
            categoryService.updateCategory(categoryDTO);
            return new ResponseEntity<>("Se actualizo la categoria correctamente.", HttpStatus.OK);
        } catch (GetCategoryNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
