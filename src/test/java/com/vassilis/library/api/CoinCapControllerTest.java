package com.vassilis.library.api;

import java.time.Instant;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vassilis.library.JunitTags;
import com.vassilis.library.representation.CurrencyRateRep;
import com.vassilis.library.service.CoinCapService;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@Tag(JunitTags.API_TEST)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureMockRestServiceServer
class CoinCapControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockRestServiceServer mockServer;

    @Test
    public void getUsdRate_whenGetWithBitcoinSymbol_shouldReturnUsdRateForBitcoin() throws Exception {
        var coincapReturnedResponse = new CoinCapService.CurrencyRateDto();
        var currencyData = new CoinCapService.CurrencyDataDto();
        currencyData.setId("bitcoin");
        currencyData.setRateUsd("26100");

        Instant instant = Instant.now();
        coincapReturnedResponse.setData(currencyData);
        coincapReturnedResponse.setTimestamp(instant);

        mockServer.expect(ExpectedCount.once(),
                requestTo("https://api.coincap.io:443/v2/rates/bitcoin"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(
                        withStatus(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(objectMapper.writeValueAsString(coincapReturnedResponse)));

        var responseExpected = new CurrencyRateRep();
        responseExpected.setSymbol("bitcoin");
        responseExpected.setUsdRate("26100");
        responseExpected.setTime(instant);

        mockMvc.perform(get("/api/rates/{symbol}", "bitcoin"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(responseExpected)));

        mockServer.verify();
    }

    @Test
    public void getUsdRate_whenGetWithUnknownSymbol_shouldReturn500() throws Exception {
        String errorResponseFromCoinCap = "{\n" +
                "  \"error\": \"not symbol exist\"\n" +
                "}\n" +
                "\n";
        mockServer.expect(ExpectedCount.once(),
                requestTo("https://api.coincap.io:443/v2/rates/not-existing-symbol"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(
                        withStatus(HttpStatus.NOT_FOUND)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(objectMapper.writeValueAsString(errorResponseFromCoinCap)));

        mockMvc.perform(get("/api/rates/{symbol}", "not-existing-symbol"))
                .andExpect(MockMvcResultMatchers.status().is(500));

        mockServer.verify();
    }

}