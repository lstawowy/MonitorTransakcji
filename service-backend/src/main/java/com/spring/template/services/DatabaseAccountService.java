package com.spring.template.services;

import com.spring.template.resource.TransactionResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseAccountService {

  private final TransactionResource transactionResource;

  @Autowired
  public DatabaseAccountService(TransactionResource transactionResource) {
    this.transactionResource = transactionResource;
  }

}