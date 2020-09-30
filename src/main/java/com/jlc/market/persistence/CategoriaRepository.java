package com.jlc.market.persistence;

import com.jlc.market.domain.model.Category;
import com.jlc.market.domain.model.Purchase;
import com.jlc.market.domain.repository.CategoryRepository;
import com.jlc.market.domain.repository.PurchaseRepository;
import com.jlc.market.persistence.crud.CategoriaCrudRepository;
import com.jlc.market.persistence.crud.CompraCrudRepository;
import com.jlc.market.persistence.entity.Categoria;
import com.jlc.market.persistence.entity.Compra;
import com.jlc.market.persistence.mapper.CategoryMapper;
import com.jlc.market.persistence.mapper.PurchaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaRepository implements CategoryRepository {
    private CategoriaCrudRepository categoriaCrudRepository;
    private CategoryMapper mapper;

    public CategoriaRepository(CategoriaCrudRepository categoriaCrudRepository, CategoryMapper mapper) {
        this.categoriaCrudRepository = categoriaCrudRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Category> getAll() {
        return mapper.toCategories((List<Categoria>) categoriaCrudRepository.findAll());
    }

    @Override
    public Category getById(Integer idCategory) {
        return mapper.toCategory(categoriaCrudRepository.findByIdCategoria(idCategory));
    }

    @Override
    public Category save(Category category) {
        Categoria categoria = mapper.toCategoria(category);
        return mapper.toCategory(categoriaCrudRepository.save(categoria));
    }
}
