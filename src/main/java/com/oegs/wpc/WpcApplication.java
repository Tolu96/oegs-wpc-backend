package com.oegs.wpc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
@Slf4j
public class WpcApplication {
    public static void main(String[] args) {
        SpringApplication.run(WpcApplication.class, args);
    }
}
