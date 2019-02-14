package com.spring.monitor.dao;

import com.spring.monitor.entity.ClientEntity;
import com.spring.monitor.repository.JpaClientRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import manager.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import strategy.JpaStrategy;

@Slf4j
@Component
public class JpaClientDao extends TransactionManager<ClientEntity> {

  @Autowired
  private JpaClientRepository repository;

  @Autowired
  public JpaClientDao(JpaRepository<ClientEntity, String> repository) {
    super(new HashMap<>(), new JpaStrategy(repository));
    this.repository = (JpaClientRepository) repository;
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
