package com.yulcom.inoutfolderapp.infrastructure.configs.security.services;

import com.yulcom.inoutfolderapp.domain.exceptions.AccessDeniedException;
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
        var userDetails = corporateUserRepository.findByUsername(username);
        if (userDetails.isEmpty()) {
            throw new AccessDeniedException();
        }
        return userDetails.get();
    }
}
