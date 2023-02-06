package com.idstar.apps.designpattern.creational;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SingleCreateBeans {

    @Bean
    public SingleCreateBeans singleBean(){
        System.out.println("create beans signleton: penerapan pada koneksi");
        return new SingleCreateBeans();
    }
}
