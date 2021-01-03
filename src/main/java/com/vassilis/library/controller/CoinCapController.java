package com.vassilis.library.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vassilis.library.representation.CurrencyRateRep;
import com.vassilis.library.service.CoinCapService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CoinCapController {
    private final CoinCapService coinCapService;

    @GetMapping("/api/rates/{symbol}")
    public CurrencyRateRep getUsdRate(@PathVariable String symbol) {
        return coinCapService.getUsdRate(symbol);
    }
}
