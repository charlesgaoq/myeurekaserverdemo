package com.example.myspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyspringbootApplication {

    public static void main(String[] args) {
        System.out.println("开始运行springboot demo");
        SpringApplication.run(MyspringbootApplication.class, args);
        System.out.println("springboot demo 运行成功");
    }

}
