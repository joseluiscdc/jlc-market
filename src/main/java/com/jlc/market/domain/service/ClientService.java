package com.jlc.market.domain.service;

import com.jlc.market.domain.model.Client;
import com.jlc.market.domain.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    public Client getById(String idClient) {
        return clientRepository.getById(idClient);
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }
}

