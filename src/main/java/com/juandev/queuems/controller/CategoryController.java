package com.juandev.queuems.controller;

import com.juandev.queuems.Exception.ConflictCategoryException;
import com.juandev.queuems.model.Category;
import com.juandev.queuems.service.CategoryService;
import com.juandev.queuems.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
