package com.yulcom.inoutfolderapp.infrastructure.configs.security.services;

import com.yulcom.inoutfolderapp.domain.entities.CorporateUser;
import com.yulcom.inoutfolderapp.domain.exceptions.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUSerService
{
    public CorporateUser getCurrentUser()
    {
        try
        {
            return (CorporateUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e)  {
           throw new AccessDeniedException();
        }
    }
}
