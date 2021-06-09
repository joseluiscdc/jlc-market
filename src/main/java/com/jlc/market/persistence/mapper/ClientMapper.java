package com.jlc.market.persistence.mapper;

import java.util.List;

import com.jlc.market.domain.model.Client;
import com.jlc.market.persistence.entity.Cliente;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mappings({
            @Mapping(source = "id", target = "idClient"),
            @Mapping(source = "nombre", target = "firstName"),
            @Mapping(source = "apellidos", target = "lastName"),
            @Mapping(source = "celular", target = "phone"),
            @Mapping(source = "direccion", target = "address"),
            @Mapping(source = "correoElectronico", target = "email"),
    })
    Client toClient(Cliente cliente);

    List<Client> toClients(List<Cliente> cliente);

    @InheritInverseConfiguration
    @Mapping(target = "compras", ignore = true)
    Cliente toClient(Client client);
}
