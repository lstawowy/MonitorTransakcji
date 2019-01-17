package com.spring.monitor.entity;

import com.spring.monitor.enums.EntityType;
import com.spring.monitor.enums.TransactionStatus;
import com.spring.monitor.enums.TransactionType;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Entity
@lombok.Data
public class TransactionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private ObjectId id;

  private Date transactionDate;

  private TransactionStatus status;

  private ObjectId entityId;

  private TransactionType transactionType;

  private EntityType entityType;

  private Entity updatingEntity;

}