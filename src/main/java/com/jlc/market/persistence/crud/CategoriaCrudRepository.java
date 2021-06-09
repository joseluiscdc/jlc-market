package com.jlc.market.persistence.crud;

import com.jlc.market.persistence.entity.Categoria;

import org.springframework.data.repository.CrudRepository;

public interface CategoriaCrudRepository extends CrudRepository<Categoria, Integer> {
    Categoria findByIdCategoria(Integer idCategoria);
}