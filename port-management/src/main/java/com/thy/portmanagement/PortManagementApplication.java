package com.thy.portmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@EnableScheduling
public class PortManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortManagementApplication.class, args);
    }

}
