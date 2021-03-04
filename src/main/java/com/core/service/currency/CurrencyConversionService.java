package com.core.service.currency;

import com.core.model.Currency;
import java.math.BigDecimal;

public interface CurrencyConversionService {
    BigDecimal convert(Currency fromCurrency, Currency toCurrency, BigDecimal amount);
}
