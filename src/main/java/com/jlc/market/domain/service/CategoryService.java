package com.jlc.market.domain.service;

import com.jlc.market.domain.model.Category;
import com.jlc.market.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    public Category getById(Integer idCategory) {
        return categoryRepository.getById(idCategory);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}

