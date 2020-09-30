package com.jlc.market.persistence.crud;

import com.jlc.market.persistence.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClienteCrudRepository extends CrudRepository<Cliente, Integer> {
    Optional<Cliente> findById(String idCliente);
}