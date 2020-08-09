package com.whyzaa.vspringblog;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VspringblogApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testLogOutPut(){
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
    }
}
