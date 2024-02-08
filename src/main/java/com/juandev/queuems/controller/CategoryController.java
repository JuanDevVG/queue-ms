package com.juandev.queuems.controller;

import com.juandev.queuems.Exception.ConflictCategoryException;
import com.juandev.queuems.Exception.GetCategoryNotFoundException;
import com.juandev.queuems.Exception.GetConflictException;
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

@Controller
@RequestMapping("/api/category")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    private ResponseEntity<Response> createCategory(@RequestBody Category category){
        try {
            return new ResponseEntity<>(new Response("Se creo la categoria correctamente", categoryService.saveCategory(category)),HttpStatus.CREATED);
        } catch (ConflictCategoryException e) {
            return new ResponseEntity<>(new Response(e.getMessage(), category), HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/get")
    private ResponseEntity<?> getCategories(){
        try {
            List<Category> categories = categoryService.getAllCategories();
            return new ResponseEntity<>(new Response("Consulta exitosa", categories), HttpStatus.OK);
        } catch (GetCategoryNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    private ResponseEntity<?> updateCategory(@RequestBody Category category){
        try {
            Category newCategory = categoryService.updateCategory(category);
            return new ResponseEntity<>(new Response("Se actualizo la categoria correctamente.", newCategory), HttpStatus.OK);
        } catch (GetCategoryNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
