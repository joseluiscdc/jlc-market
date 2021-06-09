package com.jlc.market.domain.repository;

import java.util.List;

import com.jlc.market.domain.model.Client;

public interface ClientRepository {
    List<Client> getAll();

    Client getById(String idClient);

    Client save(Client client);
}
