package com.springboot.project.learning;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCon {

    @Bean
    public RazorPay razorPay(){
        return new RazorPay();
    }
}
