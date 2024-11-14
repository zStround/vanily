package com.github.vanily.currencies.model.currency;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Currency {

    private final String id, singular, plural, command, icon, color;
    private final String[] aliases;

}
