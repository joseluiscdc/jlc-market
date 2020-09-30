package com.jlc.market.domain.repository;

import com.jlc.market.domain.model.Category;
import com.jlc.market.domain.model.Client;

import java.util.List;

public interface ClientRepository {
    List<Client> getAll();

    Client getById(String idClient);

    Client save(Client client);
}
