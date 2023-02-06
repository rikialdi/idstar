package com.idstar.apps.testing;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestingLogging {

    private static Logger logger = LoggerFactory.getLogger(TestingLogging.class);

    @Test
    public  void testLogging(){
        try {
            logger.info("info logging level");
            logger.warn("warning logging level");
            logger.debug("debug logging level");
            logger.trace("trace logging level");
            Integer data = null;
            int dataTampung = data;
        }catch (Exception e){
            logger.error("eror logging leve: {}"+e);
        }

    }
}
