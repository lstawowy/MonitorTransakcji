package manager;

import enums.OperationType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import strategy.IStrategy;
import unitofwork.IUnitOfWork;

@Slf4j
public class TransactionManager<T> implements IUnitOfWork<T> {

  private Map<OperationType, List<T>> context;
  private IStrategy<T> strategy;
  private List<T> previousState;

  public TransactionManager(Map<OperationType, List<T>> context,
      IStrategy<T> strategy) {
    this.strategy = strategy;
    this.context = context.isEmpty() ? new HashMap<>() : context;
  }

  public T registerNew(T entity) {
    log.info("Registering new entity: " + entity.toString());
    register(OperationType.INSERT, entity);
    return entity;
  }

  public T registerUpdate(T entity) {
    log.info("Registering updated entity: " + entity.toString());
    register(OperationType.UPDATE, entity);
    return entity;
  }

  public T registerDelete(T entity) {
    log.info("Registering deleted entity: " + entity.toString());
    register(OperationType.DELETE, entity);
    return entity;
  }

  private void register(OperationType operation, T entity) {
    List<T> entities = context.get(operation);
    if (entities==null) {
      entities = new ArrayList<>();
    }
    entities.add(entity);
    context.put(operation, entities);
  }

  public void commit() {
    log.info("Commiting changes");
    previousState = strategy.findAll();
    log.info("Commit started");
    try {
      handleInsert();
      handleModify();
      handleDelete();
    } catch (Exception e) {
      log.error("Exception found during commit, rolling back", e);
      this.rollback();
    }
    log.info("Commit finished.");
  }

  private void handleInsert() {
    if (context.containsKey(OperationType.INSERT)) {
      List<T> entitiesInserted = context.get(OperationType.INSERT);
      strategy.insertEntities(entitiesInserted);
    }
  }

  private void handleModify() {
    if (context.containsKey(OperationType.UPDATE)) {
      List<T> entitiesModified = context.get(OperationType.UPDATE);
      strategy.modifyEntities(entitiesModified);
    }
  }

  private void handleDelete() {
    if (context.containsKey(OperationType.DELETE)) {
      List<T> clientsDeleted = context.get(OperationType.DELETE);
      strategy.deleteEntities(clientsDeleted);
    }
  }

  public void rollback() {
    log.info("Rollback changes");
    try {
      strategy.saveAll(previousState);
      context = new HashMap<>();
    } catch (Exception e) {
      log.error("Error occured during rollback!", e);
    }
    log.info("Rollback finished.");
  }

  public void clear() {
    log.info("Clear transaction manager");
    try {
      context = new HashMap<>();
      previousState = strategy.findAll();
    } catch (Exception e) {
      log.error("Error occured during clear! Is this even possible?", e);
    }
    log.info("Clear transaction manager");
  }
}
