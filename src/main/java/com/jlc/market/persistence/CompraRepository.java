package com.jlc.market.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.jlc.market.domain.Purchase;
import com.jlc.market.domain.repository.PurchaseRepository;
import com.jlc.market.persistence.crud.CompraCrudRepository;
import com.jlc.market.persistence.entity.Compra;
import com.jlc.market.persistence.mapper.PurchaseMapper;

@Repository
public class CompraRepository implements PurchaseRepository {
    private CompraCrudRepository compraCrudRepository;
    private PurchaseMapper mapper;

    public CompraRepository(CompraCrudRepository compraCrudRepository, PurchaseMapper mapper) {
        this.compraCrudRepository = compraCrudRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Purchase getById(Integer idPurchase) {
        return mapper.toPurchase(compraCrudRepository.findByIdCompra(idPurchase));
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId).map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));

        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}
