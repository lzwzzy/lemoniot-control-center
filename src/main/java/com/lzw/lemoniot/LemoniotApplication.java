package com.lzw.lemoniot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 *
 * @author  lzw
 */
@SpringBootApplication
@EnableAsync
@EnableSwagger2
public class LemoniotApplication {

    public static void main(String[] args) {
        SpringApplication.run(LemoniotApplication.class, args);
    }
}
