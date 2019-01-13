package com.spring.monitor.repository;

import com.spring.monitor.entity.ClientEntity;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<ClientEntity, String> {

  List<ClientEntity> findByFirstName(String firstName);

  List<ClientEntity> findByLastName(String lastName);

  ClientEntity findById(ObjectId id);
}