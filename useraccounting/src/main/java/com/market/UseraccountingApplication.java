package com.market;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.market"})
public class UseraccountingApplication {

  public static void main(String[] args) {
    SpringApplication.run(UseraccountingApplication.class, args);
  }
}

