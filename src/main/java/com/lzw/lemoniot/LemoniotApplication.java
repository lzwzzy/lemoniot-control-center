package com.lzw.lemoniot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 *
 * @author  lzw
 */
@SpringBootApplication
@EnableAsync
public class LemoniotApplication {

    public static void main(String[] args) {
        SpringApplication.run(LemoniotApplication.class, args);
    }
}
