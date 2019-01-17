package com.spring.monitor.entity;

import com.spring.monitor.enums.PaymentType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Entity
@lombok.Data
public class AccountEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private ObjectId id;

  private ObjectId clientId;

  private PaymentType paymentType;

  private String cardNumber;

  private double accountBalance;

  private double rate;

}