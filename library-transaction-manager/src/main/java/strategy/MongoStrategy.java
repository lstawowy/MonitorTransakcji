package strategy;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.repository.MongoRepository;

@Slf4j
public class MongoStrategy<T> implements IStrategy<T> {

  private MongoRepository<T, String> repository;

  public MongoStrategy(MongoRepository<T, String> repository) {
    this.repository = repository;
  }

  public List<T> findAll() {
    return repository.findAll();
  }

  public void saveAll(List<T> previousState) {
    repository.saveAll(previousState);
  }

  public void insertEntities(List<T> entitiesInserted) {
    for (T entity : entitiesInserted) {
      log.debug("Saving entity:" + entity.toString());
      repository.insert(entity);
    }
  }

  public void modifyEntities(List<T> entitiesModified) {
    for (T entity : entitiesModified) {
      log.debug("Modifying entity:" + entity.toString());
      repository.save(entity);
    }
  }

  public void deleteEntities(List<T> clientsDeleted) {
    for (T entity : clientsDeleted) {
      log.debug("Deleting entity:" + entity.toString());
      repository.delete(entity);
    }
  }
}
