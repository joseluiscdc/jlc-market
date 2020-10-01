package com.jlc.market.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.jlc.market.domain.model.Purchase;
import com.jlc.market.domain.service.PurchaseService;

@RestController
@RequestMapping("/purchases")
@Api(tags = "Purchases", description = "Operations about purchase resource")
public class PurchaseController {
    private PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping()
    @ApiOperation(value = "Get all purchases market.", notes = "Resource to get purchases market.")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{idPurchase}")
    @ApiOperation(value = "Get purchase by id.", notes = "Resource to get purchase by id.")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<Purchase> getById(@PathVariable("idPurchase") Integer idPurchase) {
        return new ResponseEntity<>(purchaseService.getById(idPurchase), HttpStatus.OK);
    }

    @GetMapping("/client/{idClient}")
    @ApiOperation(value = "Get purchase by client id.", notes = "Resource to get purchase by client.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Puchase by client"),
            @ApiResponse(code = 404, message = "Purchase by client not found"),
    })
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable("idClient") String clientId) {
        return purchaseService.getByClient(clientId).map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    @ApiOperation(value = "Save a new purchase.", notes = "Resource to save purchase.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Purchase created"),
    })
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }
}