package com.trading.stockevent.repository;

import com.trading.stockevent.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findBySymbolIn(List<String> skuCode);
}
