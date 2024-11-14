package com.github.vanily.currenciesservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controller")
public class Controller {

    @GetMapping("/free")
    public String free() {
        return "free";
    }

    @GetMapping("/secure")
    public String secure() {
        return "secure";
    }

}
