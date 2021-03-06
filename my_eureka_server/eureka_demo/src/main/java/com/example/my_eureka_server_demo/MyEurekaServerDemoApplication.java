package com.example.my_eureka_server_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MyEurekaServerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyEurekaServerDemoApplication.class, args);
        System.out.println("eureka server 启动完成");
    }

}
