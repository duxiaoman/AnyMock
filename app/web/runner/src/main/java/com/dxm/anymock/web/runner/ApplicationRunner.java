package com.dxm.anymock.web.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication(scanBasePackages = "com.dxm")
public class ApplicationRunner {
    public static void main(String[] args) {
        System.setProperty("server.port", "8330");
        SpringApplication.run(ApplicationRunner.class, args);
    }
}
