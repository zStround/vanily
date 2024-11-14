package com.github.vanily.currencies.cache.user;

import com.github.vanily.currencies.model.user.CurrencyUser;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class UserCache {

    private final Map<String, CurrencyUser> currencyUserMap = Maps.newHashMap();

    public void create(CurrencyUser currencyUser) {
        this.currencyUserMap.put(currencyUser.getName(), currencyUser);
    }

    public void delete(String name) {
        this.currencyUserMap.remove(name);
    }

    public CurrencyUser get(String name) {
       return this.currencyUserMap.get(name);
    }
}
