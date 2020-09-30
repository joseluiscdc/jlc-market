package com.jlc.market.persistence.crud;

import com.jlc.market.persistence.entity.Categoria;
import com.jlc.market.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaCrudRepository extends CrudRepository<Categoria, Integer> {
    Categoria findByIdCategoria(Integer idCategoria);
}