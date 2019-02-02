package com.spring.monitor.dao;

import com.spring.monitor.entity.ClientEntity;
import com.spring.monitor.repository.MongoClientRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import manager.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import strategy.MongoStrategy;

@Slf4j
@Component
public class MongoClientDao extends TransactionManager<ClientEntity> {

  @Autowired
  private MongoClientRepository repository;

  @Autowired
  public MongoClientDao(
      MongoRepository<ClientEntity, String> repository) {
    super(new HashMap<>(), new MongoStrategy<>(repository));
    this.repository = (MongoClientRepository) repository;
  }

  public List<ClientEntity> findAll() {
    log.info("Finding all clients");
    return repository.findAll();
  }

  public Optional<ClientEntity> findById(String id) {
    log.info("Finding client by id");
    return repository.findById(id);
  }

  public ClientEntity findByEmail(String email) {
    return repository.findByEmail(email);
  }
}
