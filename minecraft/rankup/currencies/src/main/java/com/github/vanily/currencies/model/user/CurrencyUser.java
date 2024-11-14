package com.github.vanily.currencies.model.user;

import com.github.vanily.currencies.model.currency.Currency;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;


@RequiredArgsConstructor @Data
public class CurrencyUser {

    private final String name;
    private final Map<String, Currency> currencyMap;

}
