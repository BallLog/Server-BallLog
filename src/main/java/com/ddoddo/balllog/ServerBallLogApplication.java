package com.ddoddo.balllog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ServerBallLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerBallLogApplication.class, args);
    }

}
