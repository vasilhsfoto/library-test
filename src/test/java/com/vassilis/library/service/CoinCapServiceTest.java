package com.vassilis.library.service;

import java.time.Instant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vassilis.library.representation.CurrencyRateRep;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

@RestClientTest(components = CoinCapService.class)
public class CoinCapServiceTest {

    @Autowired
    private CoinCapService coinCapService;

    @Autowired
    private MockRestServiceServer mockServer;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getUsdRateForEuroSuccessful() throws JsonProcessingException {
        var data = new CoinCapService.CurrencyDataDto();
        data.setId("euro");
        data.setRateUsd("1.5");

        var responseReturned = new CoinCapService.CurrencyRateDto();
        Instant instant = Instant.now();
        responseReturned.setTimestamp(instant);
        responseReturned.setData(data);

        mockServer.expect(ExpectedCount.once(), requestTo("https://api.coincap.io:443/v2/rates/euro"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withSuccess(
                        objectMapper.writeValueAsString(responseReturned), MediaType.APPLICATION_JSON));

        CurrencyRateRep currencyRateRepActual = coinCapService.getUsdRate("euro");
        CurrencyRateRep currencyRateRepExpected = new CurrencyRateRep();
        currencyRateRepExpected.setTime(instant);
        currencyRateRepExpected.setUsdRate("1.5");
        currencyRateRepExpected.setSymbol("euro");

        Assertions.assertThat(currencyRateRepActual).isEqualTo(currencyRateRepExpected);

        mockServer.verify();
    }
}