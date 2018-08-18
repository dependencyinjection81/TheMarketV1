package com.market;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@SpringBootApplication(scanBasePackages = { "com.market" })
public class UseraccountingApplication implements WebMvcConfigurer {

  public static void main(String[] args) {
    SpringApplication.run(UseraccountingApplication.class, args);
  }

}
