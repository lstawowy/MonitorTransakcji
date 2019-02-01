package com.spring.monitor.dao;

import com.spring.monitor.entity.ClientEntity;
import com.spring.monitor.repository.ClientRepository;
import java.util.HashMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import manager.TransactionManager;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClientDao extends TransactionManager<ClientEntity> {

  @Autowired
  private ClientRepository repository;

  @Autowired
  public ClientDao(
      MongoRepository<ClientEntity, String> repository) {
    super(new HashMap<>(), repository);
    this.repository = (ClientRepository) repository;
  }

  public List<ClientEntity> findAll() {
    log.info("Finding all clients");
    return repository.findAll();
  }

  public ClientEntity findById(ObjectId id) {
    log.info("Finding client by id");
    return repository.findById(id);
  }

  public ClientEntity findByEmail(String email) {
    return repository.findByEmail(email);
  }
}
