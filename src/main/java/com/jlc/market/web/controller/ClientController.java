package com.jlc.market.web.controller;

import com.jlc.market.domain.model.Client;
import com.jlc.market.domain.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@Api(tags = "Clients")
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
    public ResponseEntity<Client> getById(@PathVariable("idClient") String idClient) {
        return new ResponseEntity<>(clientService.getById(idClient), HttpStatus.OK);
    }

    @PostMapping()
    @ApiOperation(value = "Save a new client.", notes = "Resource to save client.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Client created"),
    })
    public ResponseEntity<Client> save(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
    }
}