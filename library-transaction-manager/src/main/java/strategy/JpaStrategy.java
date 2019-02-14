package strategy;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

@Slf4j
public class JpaStrategy<T> implements IStrategy<T> {

  private JpaRepository<T, String> repository;

  public JpaStrategy(JpaRepository<T, String> repository) {
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
      repository.save(entity);
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
