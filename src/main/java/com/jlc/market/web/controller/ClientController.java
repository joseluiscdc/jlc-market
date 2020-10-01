package com.jlc.market.web.controller;

import com.jlc.market.domain.model.Client;
import com.jlc.market.domain.service.ClientService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@Api(tags = "Clients", description = "Operations about client resource")
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    @ApiOperation(value = "Get all clients market.", notes = "Resource to get clients market.")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Client>> getAll() {
        return new ResponseEntity<>(clientService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{idClient}")
    @ApiOperation(value = "Get client by id.", notes = "Resource to get client by id.")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<Client> getById(
            @ApiParam(value = "The id of the client", required = true, example = "dni/rut")
            @PathVariable("idClient") String idClient) {
        return new ResponseEntity<>(clientService.getById(idClient), HttpStatus.OK);
    }

    @PostMapping()
    @ApiOperation(value = "Save a new client.", notes = "Resource to save client.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Client created"),
    })
    public ResponseEntity<Client> save(
            @ApiParam(value = "The client json object. Check client model.", required = true)
            @RequestBody Client client) {
        return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
    }
}