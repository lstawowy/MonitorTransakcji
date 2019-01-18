package com.spring.monitor.dao;

import com.spring.monitor.entity.ClientEntity;
import com.spring.monitor.enums.OperationType;
import com.spring.monitor.repository.ClientRepository;
import com.spring.monitor.unitofwork.IUnitOfWork;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ClientDao implements IUnitOfWork<ClientEntity> {

  @Autowired
  private ClientRepository repository;

  private Map<OperationType, List<ClientEntity>> context;

  private Map<OperationType, List<ClientEntity>> rollbackContext;

  public ClientDao(Map<OperationType, List<ClientEntity>> context) {
    this.context = context;
  }

  @Override
  public void registerNew(ClientEntity entity) {
    log.info("Registering new entity: ", entity);
    register(OperationType.INSERT, entity);
  }

  @Override
  public void registerUpdated(ClientEntity entity) {
    log.info("Registering updated entity: ", entity);
    register(OperationType.UPDATE, entity);
  }

  @Override
  public void registerDeleted(ClientEntity entity) {
    log.info("Registering deleted entity: ", entity);
    register(OperationType.DELETE, entity);
  }

  private void register(OperationType operation, ClientEntity entity) {
    List<ClientEntity> clientsToOperate = context.get(operation);
    if (clientsToOperate == null) {
      clientsToOperate = new ArrayList<>();
    }
    clientsToOperate.add(entity);
    context.put(operation, clientsToOperate);
  }

  @Override
  public void commit() {
    log.info("Commiting changes");
    if (context == null || context.size() == 0) {
      return;
    }
    log.info("Commit started");
    try {
      if (context.containsKey(OperationType.INSERT)) {
        commitInsert();
      }

      if (context.containsKey(OperationType.UPDATE)) {
        commitModify();
      }
      if (context.containsKey(OperationType.DELETE)) {
        commitDelete();
      }
    } catch (Exception e) {
      log.error("Exception found, rolling back", e);
      this.rollback();
    }
    rollbackContext = null;
    log.info("Commit finished.");
  }

  private void commitInsert() {
    List<ClientEntity> clientsInserted = context.get(OperationType.INSERT);
    for (ClientEntity client : clientsInserted) {
      log.info("Saving client.", client.getFirstName() + client.getLastName());
      repository.insert(client);
      registerRollback(OperationType.INSERT, client);
    }
  }

  private void commitModify() {
    List<ClientEntity> clientsModified = context.get(OperationType.UPDATE);
    for (ClientEntity client : clientsModified) {
      log.info("Modifying client.", client.getFirstName() + client.getLastName());
      ClientEntity oldClient = repository.findById(client.getId());
      repository.save(client);
      registerRollback(OperationType.UPDATE, oldClient);
    }
  }

  private void commitDelete() {
    List<ClientEntity> clientsDeleted = context.get(OperationType.DELETE);
    for (ClientEntity client : clientsDeleted) {
      log.info("Deleting client.", client.getFirstName() + client.getLastName());
      repository.delete(client);
      registerRollback(OperationType.DELETE, client);
    }
  }

  private void registerRollback(OperationType operation, ClientEntity entity) {
    List<ClientEntity> clientsToOperate = rollbackContext.get(operation);
    if (clientsToOperate == null) {
      clientsToOperate = new ArrayList<>();
    }
    clientsToOperate.add(entity);
    rollbackContext.put(operation, clientsToOperate);
  }

  @Override
  public void rollback() {
    log.info("Rollback changes");
    if (rollbackContext == null || rollbackContext.size() == 0) {
      return;
    }
    log.info("Rollback started");
    if (rollbackContext.containsKey(OperationType.INSERT)) {
      rollbackInsert();
    }

    if (rollbackContext.containsKey(OperationType.UPDATE)) {
      rollbackModify();
    }
    if (rollbackContext.containsKey(OperationType.DELETE)) {
      rollbackDelete();
    }
    log.info("Rollback finished.");
    rollbackContext = null;
  }

  @Override
  public void clear() {

  }

  private void rollbackInsert() {
    List<ClientEntity> clientsInserted = rollbackContext.get(OperationType.INSERT);
    for (ClientEntity client : clientsInserted) {
      log.info("Rolling back inserting client.", client.getFirstName() + client.getLastName());
      repository.delete(client);
    }
  }

  private void rollbackModify() {
    List<ClientEntity> clientsModified = rollbackContext.get(OperationType.UPDATE);
    for (ClientEntity client : clientsModified) {
      log.info("Rolling back client modification.", client.getFirstName() + client.getLastName());
      repository.save(client);
    }
  }

  private void rollbackDelete() {
    List<ClientEntity> clientsDeleted = rollbackContext.get(OperationType.DELETE);
    for (ClientEntity client : clientsDeleted) {
      log.info("Rolling back client deletion.", client.getFirstName() + client.getLastName());
      repository.insert(client);
    }
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
