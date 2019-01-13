package com.spring.template.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;


@lombok.Data
public class TransactionEntity {

  @Id
  private ObjectId id;

  private String firstName;

  private String lastName;

  public TransactionEntity() {
  }

  public TransactionEntity(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

}