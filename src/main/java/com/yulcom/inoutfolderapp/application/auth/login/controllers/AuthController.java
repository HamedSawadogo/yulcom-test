package com.yulcom.inoutfolderapp.application.auth.login.controllers;

import com.yulcom.inoutfolderapp.application.auth.dtos.in.AuthRequest;
import com.yulcom.inoutfolderapp.application.auth.dtos.out.AuthResponse;
import com.yulcom.inoutfolderapp.application.auth.login.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController
{

    private final AuthService authService;

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> login(@Validated @RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.handle(authRequest));
    }
}
