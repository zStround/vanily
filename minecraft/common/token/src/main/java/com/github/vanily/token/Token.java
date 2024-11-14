package com.github.vanily.token;

import lombok.Getter;

public class Token {

    @Getter
    private static final TokenManager tokenManager = new TokenManager();

}
