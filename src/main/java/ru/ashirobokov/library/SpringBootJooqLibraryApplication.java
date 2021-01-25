package ru.ashirobokov.library;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringBootJooqLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJooqLibraryApplication.class, args);
        log.info("SpringBootJooqLibraryApplication started ... ");
    }
}
