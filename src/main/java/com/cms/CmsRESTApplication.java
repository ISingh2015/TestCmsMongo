package com.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : Inderjit SS
 * @version: v1.0.0
 * @github: https://github.com/ISingh2015/TestMongo
 * @since : 06.06.2019
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.cms"})
public class CmsRESTApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(CmsRESTApplication.class, args);
    }

}
