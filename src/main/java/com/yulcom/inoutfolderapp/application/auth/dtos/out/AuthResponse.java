package com.yulcom.inoutfolderapp.application.auth.dtos.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse
{
    private String token;
    private String refreshToken;
}
