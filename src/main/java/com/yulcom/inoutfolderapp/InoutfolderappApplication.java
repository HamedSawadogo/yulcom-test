package com.yulcom.inoutfolderapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class InoutfolderappApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(InoutfolderappApplication.class, args);
    }

}
