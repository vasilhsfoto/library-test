package com.vassilis.library.representation;

import java.time.Instant;

import lombok.Data;

@Data
public class CurrencyRateRep {
    private String symbol;
    private String usdRate;
    private Instant time;
}
