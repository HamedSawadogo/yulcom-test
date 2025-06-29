package com.yulcom.inoutfolderapp.infrastructure.configs.security;

import com.yulcom.inoutfolderapp.domain.repositories.CorporateUserRepository;
import com.yulcom.inoutfolderapp.infrastructure.configs.security.services.CorporateUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AuthenticateUserConfig {


  @Bean
  public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder, CorporateUserDetailsService userDetails) {
    var authProvider = new DaoAuthenticationProvider();
    authProvider.setPasswordEncoder(passwordEncoder);
    authProvider.setUserDetailsService(userDetails);
    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }
}
