package com.yulcom.inoutfolderapp.application.auth.login.services;

import com.yulcom.inoutfolderapp.application.auth.dtos.in.AuthRequest;
import com.yulcom.inoutfolderapp.application.auth.dtos.out.AuthResponse;
import com.yulcom.inoutfolderapp.domain.entities.CorporateUser;
import com.yulcom.inoutfolderapp.infrastructure.configs.security.services.CorporateUserDetailsService;
import com.yulcom.inoutfolderapp.infrastructure.configs.security.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService
{
    private final CorporateUserDetailsService  userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthResponse handle(AuthRequest authRequest)  {
       var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
           authRequest.username(),
           authRequest.password()
       ));
       var principal = (CorporateUser) authentication.getPrincipal();
       var token = jwtUtils.generateToken(principal);
       return new AuthResponse(token, null);
    }
}
