package com.gkoo.open.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * open api gkoo
 * 
 * @author sanghuncho
 *
 */
@SpringBootApplication(scanBasePackages = {"com.gkoo.open.controller","com.gkoo.open.data", "com.gkoo.open.service", "com.gkoo.open.serviceImpl", "com.gkoo.open.configuration" })
public class Application extends SpringBootServletInitializer  {
    private static final Logger LOGGER = LogManager.getLogger(Application.class);
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    
    public static void main(String[] args) throws Exception {
        LOGGER.info("create gkoo open api App");
        SpringApplication.run(Application.class, args);
    }
}
