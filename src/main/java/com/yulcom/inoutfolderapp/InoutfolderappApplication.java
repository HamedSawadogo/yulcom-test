package com.yulcom.inoutfolderapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class InoutfolderappApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(InoutfolderappApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BCryptPasswordEncoder encoder) {
        return args -> {
            System.err.println(encoder.encode("test"));
        };
    }

}
