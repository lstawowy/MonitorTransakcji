package com.spring.monitor.repository;

import com.spring.monitor.entity.TransactionEntity;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<TransactionEntity, String> {

  TransactionEntity findByFirstName(String firstName);

  List<TransactionEntity> findByLastName(String lastName);

  TransactionEntity findById(ObjectId id);
}