package strategy;

import java.util.List;

public interface IStrategy<T> {

  List<T> findAll();

  void saveAll(List<T> previousState);

  void insertEntities(List<T> entitiesInserted);

  void modifyEntities(List<T> entitiesModified);

  void deleteEntities(List<T> clientsDeleted);

}