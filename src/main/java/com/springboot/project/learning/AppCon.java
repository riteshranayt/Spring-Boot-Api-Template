package com.springboot.project.learning;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCon {

    @Bean
    public RazorPay razorPay(){
        return new RazorPay();
    }
    @Bean
    public ModelMapper ModelMapper(){
        return new ModelMapper();
    }
}
