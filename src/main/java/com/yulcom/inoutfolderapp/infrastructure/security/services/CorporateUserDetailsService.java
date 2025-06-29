package com.yulcom.inoutfolderapp.infrastructure.security.services;

import com.yulcom.inoutfolderapp.domain.repositories.CorporateUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CorporateUserDetailsService implements UserDetailsService
{
    private final CorporateUserRepository corporateUserRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return corporateUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
