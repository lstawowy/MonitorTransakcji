package com.spring.monitor;

import com.spring.monitor.repository.AccountRepository;
import com.spring.monitor.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = {"com.spring.monitor.configuration", "com.spring.monitor.resource", "com.spring.monitor.repository"})
public class MainApplication {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private ClientRepository clientRepository;

  public static void main(String[] args) {
    SpringApplication.run(MainApplication.class, args);
  }

}
