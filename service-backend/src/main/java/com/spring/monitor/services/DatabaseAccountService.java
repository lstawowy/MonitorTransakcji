package com.spring.monitor.services;

import com.spring.monitor.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseAccountService {

  @Autowired
  public DatabaseAccountService(AccountRepository repository) {
  }

}