package com.github.vanily.usersservice.controller;

import com.github.vanily.usersservice.dto.LoginRequestDto;
import com.github.vanily.usersservice.dto.RegisterRequestDto;
import com.github.vanily.usersservice.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDto dto) {
        return authenticationService.login(dto.username(), dto.password());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequestDto dto) {
        return authenticationService.register(dto.username(), dto.password());
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        return authenticationService.exists(username);
    }

}
