package com.spring.monitor.services;

import com.spring.monitor.entity.TransactionEntity;
import com.spring.monitor.repository.TransactionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.repository.MongoRepository;

public class TransactionManager {

  @Autowired
  private TransactionRepository transactionsRepository;

  private MongoRepository<Object, Object> currentRepository;

  private DefaultMongoTypeMapper defaultMapper;

  private List<TransactionEntity> transactions;

  private Object entity;

  public TransactionManager(MongoRepository<Object, Object> currentRepository) {
    this.currentRepository = currentRepository;
    this.transactions = transactionsRepository.findAll();
  }

  public void handleEntities() {
    for (TransactionEntity transaction : transactions) {
      switch (transaction.getTransactionType()) {
        case INSERT:
//          entity = defaultMapper.map(transaction.getUpdatingEntity());
          currentRepository.insert(entity);
        case UPDATE:
          entity = currentRepository.findById(transaction.getEntityId());
//          entity = defaultMapper.map(transaction.getUpdatingEntity());
          currentRepository.insert(entity);
        case DELETE:
          entity = currentRepository.findById(transaction.getEntityId());
          currentRepository.delete(entity);

      }
    }
  }

}






