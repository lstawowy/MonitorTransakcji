package com.spring.monitor.repository;

import com.spring.monitor.entity.ClientEntity;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface ClientRepository extends MongoRepository<ClientEntity, String> {

  List<ClientEntity> findByFirstName(String firstName);

  List<ClientEntity> findByLastName(String lastName);

  ClientEntity findById(ObjectId id);

  ClientEntity findByEmail(String email);
}