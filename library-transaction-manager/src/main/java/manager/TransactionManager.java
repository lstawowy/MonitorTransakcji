package manager;

import enums.OperationType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.repository.MongoRepository;
import unitofwork.IUnitOfWork;

@Slf4j
public class TransactionManager<E> implements IUnitOfWork<E> {

  private Map<OperationType, List<E>> context;

  private MongoRepository<E, String> repository;
  private List<E> previousState;

  public TransactionManager(Map<OperationType, List<E>> context,
      MongoRepository<E, String> repository) {
    this.repository = repository;
    this.context = context;

  }

  public void registerNew(E entity) {
    log.info("Registering new entity: ", entity);
    register(OperationType.INSERT, entity);
  }

  public void registerUpdate(E entity) {
    log.info("Registering updated entity: ", entity);
    register(OperationType.UPDATE, entity);
  }

  public void registerDelete(E entity) {
    log.info("Registering deleted entity: ", entity);
    register(OperationType.DELETE, entity);
  }

  private void register(OperationType operation, E entity) {
    List<E> entities = context.get(operation);
    if (entities == null) {
      entities = new ArrayList<E>();
    }
    entities.add(entity);
    context.put(operation, entities);
  }

  public void commit() {
    log.info("Commiting changes");
    if (context == null || context.size() == 0) {
      return;
    }
    previousState = repository.findAll();
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
    log.info("Commit finished.");
  }

  private void commitInsert() {
    List<E> entitiesInserted = context.get(OperationType.INSERT);
    for (E entity : entitiesInserted) {
      log.info("Saving entity.", entity.toString());
      repository.insert(entity);
    }
  }

  private void commitModify() {
    List<E> entitiesModified = context.get(OperationType.UPDATE);
    for (E entity : entitiesModified) {
      log.info("Modifying entity.", entity.toString());
      repository.save(entity);
    }
  }

  private void commitDelete() {
    List<E> clientsDeleted = context.get(OperationType.DELETE);
    for (E entity : clientsDeleted) {
      log.info("Deleting entity.", entity.toString());
      repository.delete(entity);
    }
  }

  public void rollback() {
    log.info("Rollback changes");
    repository.saveAll(previousState);
    log.info("Rollback finished.");
  }

  public void clear() {
    context = null;
    previousState = repository.findAll();
  }
}
