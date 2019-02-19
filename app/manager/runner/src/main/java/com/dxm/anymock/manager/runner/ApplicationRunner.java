package com.dxm.anymock.manager.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.dxm")
public class ApplicationRunner {
    public static void main(String[] args) {
        System.setProperty("server.port", "8330");
        SpringApplication.run(ApplicationRunner.class, args);
    }
}
