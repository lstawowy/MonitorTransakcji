package com.spring.monitor.entity;

import com.spring.monitor.enums.PaymentType;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;


@lombok.Data
public class AccountEntity {

  @Id
  private ObjectId id;

  private ObjectId clientId;

  private PaymentType paymentType;

  private String cardNumber;

  private double accountBalance;

  private double rate;

}