package com.trading.stockevent.service;

import com.trading.stockevent.event.StockResponse;
import com.trading.stockevent.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockService {

    private final StockRepository stockRepository;

    @Transactional
    @SneakyThrows
    public List<StockResponse> isInStock(List<String> symbols) {
        log.info("Checking Inventory");
        return stockRepository.findBySymbolIn(symbols).stream()
                .map(symbol ->
                        StockResponse.builder()
                                .symbol(symbol.getSymbol())
                                .price(symbol.getPrice().toString())
                                .build()
                ).collect(Collectors.toList());
    }
}
