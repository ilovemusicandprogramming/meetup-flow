package com.example.meetupflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MeetupFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeetupFlowApplication.class, args);
    }

}
