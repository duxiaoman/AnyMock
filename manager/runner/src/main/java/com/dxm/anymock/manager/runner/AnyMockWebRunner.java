package com.dxm.anymock.manager.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.dxm")
public class AnyMockWebRunner {
    public static void main(String[] args) {
        System.setProperty("spring.config.name", "anymock-manager-config");
        SpringApplication.run(AnyMockWebRunner.class, args);
    }
}
