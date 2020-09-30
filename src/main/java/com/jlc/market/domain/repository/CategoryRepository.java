package com.jlc.market.domain.repository;

import com.jlc.market.domain.model.Category;
import com.jlc.market.domain.model.Purchase;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<Category> getAll();

    Category getById(Integer idCategory);

    Category save(Category category);
}
