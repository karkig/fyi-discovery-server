package com.ps.attendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer

public class PsGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PsGatewayApplication.class, args);
    }

}
