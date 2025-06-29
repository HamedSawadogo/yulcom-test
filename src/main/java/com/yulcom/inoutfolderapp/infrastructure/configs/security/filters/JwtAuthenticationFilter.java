package com.yulcom.inoutfolderapp.infrastructure.configs.security.filters;

import com.yulcom.inoutfolderapp.infrastructure.configs.security.services.CorporateUserDetailsService;
import com.yulcom.inoutfolderapp.infrastructure.configs.security.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final CorporateUserDetailsService corporateUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = header.substring(7);
        try {
            String userName = jwtUtils.getUserNameFromJwtToken(token);
            System.err.println("Usernamme" + userName);
            var user  = corporateUserDetailsService.loadUserByUsername(userName);
            System.err.println("USER" + user);
            UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception ex) {
        }
        filterChain.doFilter(request, response);
    }
}

