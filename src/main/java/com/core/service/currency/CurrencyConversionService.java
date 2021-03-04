package com.core.service.currency;

import com.core.model.Currency;

public interface CurrencyConversionService {
    double convert(Currency fromCurrency, Currency toCurrency, double amount);
}
