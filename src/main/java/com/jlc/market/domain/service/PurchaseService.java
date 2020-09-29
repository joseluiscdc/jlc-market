package com.jlc.market.domain.service;

import com.jlc.market.domain.Purchase;
import com.jlc.market.domain.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    private PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public List<Purchase> getAll() {
        return purchaseRepository.getAll();
    }

    public Purchase getById(Integer idPurchase) {
        return purchaseRepository.getById(idPurchase);
    }

    public Optional<List<Purchase>> getByClient(String clientId) {
        return purchaseRepository.getByClient(clientId);
    }

    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }
}
