package com.spring.monitor;

import com.spring.monitor.repository.TransactionRepository;
import com.spring.monitor.resource.TransactionResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackageClasses = TransactionResource.class)
public class MainApplication {

  @Autowired
  private TransactionRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(MainApplication.class, args);
  }


}
