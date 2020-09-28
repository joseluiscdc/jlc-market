package com.jlc.market.domain.repository;

import java.util.List;
import java.util.Optional;

import com.jlc.market.domain.Purchase;

public interface PurchaseRepository {
    List<Purchase> getAll();

    Optional<List<Purchase>> getByClient(String clientId);

    Purchase save(Purchase purchase);
}
