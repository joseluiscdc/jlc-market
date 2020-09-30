package com.jlc.market.persistence;

import com.jlc.market.domain.model.Client;
import com.jlc.market.domain.repository.ClientRepository;
import com.jlc.market.persistence.crud.ClienteCrudRepository;
import com.jlc.market.persistence.entity.Cliente;
import com.jlc.market.persistence.mapper.ClientMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteRepository implements ClientRepository {
    private ClienteCrudRepository clienteCrudRepository;
    private ClientMapper mapper;

    public ClienteRepository(ClienteCrudRepository clienteCrudRepository, ClientMapper mapper) {
        this.clienteCrudRepository = clienteCrudRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Client> getAll() {
        return mapper.toClients((List<Cliente>) clienteCrudRepository.findAll());
    }

    @Override
    public Client getById(String idClient) {
        return mapper.toClient(clienteCrudRepository.findById(idClient).get());
    }

    @Override
    public Client save(Client client) {
        Cliente cliente = mapper.toClient(client);
        return mapper.toClient(clienteCrudRepository.save(cliente));
    }
}
