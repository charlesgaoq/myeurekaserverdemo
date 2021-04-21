package com.flowable.myflowable;

import lombok.extern.slf4j.Slf4j;
import com.github.frtu.logs.config.LogConfigAll;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootApplication
@Import({LogConfigAll.class})
//@EnableR2dbcRepositories
public class Application {
    private static void runApp(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public static void main(String[] args) {
        try {
            runApp(args);
        } catch (Exception e) {
            if(e.getClass().getName().contains("SilentExitException")) {
                // https://docs.spring.io/spring-boot/docs/current/reference/html/using-spring-boot.html#using-boot-devtools
                LOGGER.debug("Spring is restarting the main thread - See spring-boot-devtools");
            } else {
                LOGGER.error("Application crashed!", e);
            }
        }
    }
}
