package com.jlc.market.web.controller;

import java.util.List;

import com.jlc.market.domain.model.Category;
import com.jlc.market.domain.service.CategoryService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/categories")
@Api(tags = "Categories", description = "Operations about category resource")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    @ApiOperation(value = "Get all categories market.", notes = "Resource to get categories market.")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Category>> getAll() {
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{idCategory}")
    @ApiOperation(value = "Get category by id.", notes = "Resource to get category by id.")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<Category> getById(
            @ApiParam(value = "The id of the category", required = true, example = "1")
            @PathVariable("idCategory") Integer idPurchase) {
        return new ResponseEntity<>(categoryService.getById(idPurchase), HttpStatus.OK);
    }

    @PostMapping()
    @ApiOperation(value = "Save a new category.", notes = "Resource to save category.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Category created"),
    })
    public ResponseEntity<Category> save(
            @ApiParam(value = "The category json object. Check category model.", required = true)
            @RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }
}