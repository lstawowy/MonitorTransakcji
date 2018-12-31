package com.spring.template.repository;

import com.spring.template.entity.TransactionEntity;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<TransactionEntity, String> {

  TransactionEntity findByFirstName(String firstName);

  List<TransactionEntity> findByLastName(String lastName);

  TransactionEntity findById(ObjectId id);
}