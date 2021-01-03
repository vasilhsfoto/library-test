package com.vassilis.library.service;

import java.time.Instant;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.vassilis.library.configuration.properties.CoinCapProperties;
import com.vassilis.library.representation.CurrencyRateRep;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CoinCapService {

    private final RestTemplate restTemplate;
    private final CoinCapProperties coinCapProperties;

    public CurrencyRateRep getUsdRate(String symbol) {
        UriComponents uriComponents = UriComponentsBuilder
                .fromUriString(coinCapProperties.getUrl())
                .path("/rates")
                .pathSegment(symbol)
                .build();

        CurrencyRateDto currencyRateDto = restTemplate.getForObject(uriComponents.toUri(), CurrencyRateDto.class);
        return toCurrencyRateRep(currencyRateDto);
    }

    private CurrencyRateRep toCurrencyRateRep(CurrencyRateDto currencyRateDto) {
        CurrencyRateRep currencyRateRep = new CurrencyRateRep();
        currencyRateRep.setUsdRate(currencyRateDto.getData().rateUsd);
        currencyRateRep.setSymbol(currencyRateDto.getData().getId());
        currencyRateRep.setTime(currencyRateDto.getTimestamp());
        return currencyRateRep;
    }

    @Data
    public static class CurrencyRateDto {
        private CurrencyDataDto data;
        private Instant timestamp;
    }

    @Data
    public static class CurrencyDataDto {
        private String rateUsd;
        private String id;
    }

}
