package com.idstar.apps.designpattern.creational;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
public class Testing {

    @Test
    public  void testSingleton(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SingleCreateBeans.class);

    }
}
