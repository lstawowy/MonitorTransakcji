package com.spring.monitor.repository;

import com.spring.monitor.entity.AccountEntity;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<AccountEntity, String> {

  AccountEntity findById(ObjectId id);

  AccountEntity findByCardNumber(String cardNumber);

  List<AccountEntity> findByClientId(ObjectId clientId);

}