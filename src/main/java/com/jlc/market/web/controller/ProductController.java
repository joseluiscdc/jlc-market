package com.jlc.market.web.controller;

import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.jlc.market.domain.model.Product;
import com.jlc.market.domain.service.ProductService;

@RestController
@RequestMapping("/products")
@Api(tags = "Products", description = "Operations about product resource")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    @ApiOperation(value = "Get all supermarket products.", notes = "Resource to get products of supermarket.")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Product>> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Search a product with an id.", notes = "Resource to get product for id.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Product not found"),
    })
    public ResponseEntity<Product> getProduct(
            @ApiParam(value = "The id of the product", required = true, example = "7")
            @PathVariable("id") int productId) {
        return productService.getProduct(productId).map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{categoryId}")
    @ApiOperation(value = "Get products with by category id.", notes = "Resource to get product for category id.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Products not found"),
    })
    public ResponseEntity<List<Product>> getByCategory(
            @ApiParam(value = "The id of the category", required = true, example = "1")
            @PathVariable("categoryId") int categoryId) {
        return productService.getByCategory(categoryId).map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    @ApiOperation(value = "Save a new product.", notes = "Resource to save product.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Product created"),
    })
    public ResponseEntity<Product> save(
            @ApiParam(value = "The product json object. Check product model.", required = true)
            @RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a product by id.", notes = "Resource to delete product.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product deleted"),
            @ApiResponse(code = 404, message = "Product not found"),
    })
    public ResponseEntity<String> delete(
            @ApiParam(value = "The id of the product", required = true, example = "7")
            @PathVariable("id") int productId) {
        if (productService.delete(productId)) {
            return new ResponseEntity<String>(HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }
}
