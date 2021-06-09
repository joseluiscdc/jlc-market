package com.jlc.market.domain.repository;

import java.util.List;

import com.jlc.market.domain.model.Category;

public interface CategoryRepository {
    List<Category> getAll();

    Category getById(Integer idCategory);

    Category save(Category category);
}
