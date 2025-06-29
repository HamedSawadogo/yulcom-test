package com.yulcom.inoutfolderapp.infrastructure.security;


import com.yulcom.inoutfolderapp.domain.repositories.CorporateUserRepository;
import com.yulcom.inoutfolderapp.infrastructure.security.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final CorporateUserRepository userRepository;

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
            var userOptional = userRepository.findByUsername(userName);
            if (userOptional.isEmpty()) {
                filterChain.doFilter(request, response);
                return;
            }
            var user = userOptional.get();
            UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user, null, user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name())).toList());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception ex) {

        }
        filterChain.doFilter(request, response);
    }
}

