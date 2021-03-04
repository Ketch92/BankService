package com.core.service.currency;

import static java.time.temporal.ChronoUnit.SECONDS;

import com.core.lib.ExternalApiRequestException;
import com.core.model.Currency;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConversionServiceImpl implements CurrencyConversionService {
    public static final String CONNECTION_EXCEPTION_MESSAGE =
            "Couldn't proceed due to error, "
            + "please check of URL you're requesting is valid!";
    @Value("${currency-exchange-api_url-template}")
    private String apiUrl;
    
    @Override
    public BigDecimal convert(Currency fromCurrency, Currency toCurrency, BigDecimal amount) {
        String uri = String.format(apiUrl, fromCurrency.name(), toCurrency.name(), amount.doubleValue());
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .timeout(Duration.of(10, SECONDS))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build().send(request, HttpResponse.BodyHandlers.ofString());
            String s = response.body();
    
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new ExternalApiRequestException(CONNECTION_EXCEPTION_MESSAGE);
        }
    
        return null;
    }
}
