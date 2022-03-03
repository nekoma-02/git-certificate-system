package com.epam.esm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com"})
@SpringBootApplication
public class GiftCertificateSystemApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GiftCertificateSystemApplication.class, args);
    }

}
