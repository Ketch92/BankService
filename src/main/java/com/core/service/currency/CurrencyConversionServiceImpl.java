package com.core.service.currency;

import static java.time.temporal.ChronoUnit.SECONDS;

import com.core.lib.ExternalApiRequestException;
import com.core.model.Currency;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConversionServiceImpl implements CurrencyConversionService {
    private static final String CONNECTION_EXCEPTION_MESSAGE =
            "Couldn't proceed due to error, "
            + "please check of URL you're requesting is valid!";
    private static final String EXCHANGE_RESULT_VALUE = "result";
    
    @Value("${currency-exchange-api-url-template}")
    private String apiUrl;
    
    @Override
    public double convert(Currency fromCurrency, Currency toCurrency, double amount) {
        String uri = String.format(apiUrl, fromCurrency.name(),
                toCurrency.name(), amount);
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .timeout(Duration.of(10, SECONDS))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build().send(request, HttpResponse.BodyHandlers.ofString());
            HashMap<String, Object> responseValues
                    = new ObjectMapper().readValue(response.body(), HashMap.class);
            return (double) responseValues.get(EXCHANGE_RESULT_VALUE);
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new ExternalApiRequestException(CONNECTION_EXCEPTION_MESSAGE);
        }
    }
}
