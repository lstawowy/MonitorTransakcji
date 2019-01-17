package com.spring.monitor.dto;

import com.spring.monitor.enums.TransactionStatus;
import com.spring.monitor.enums.TransactionType;
import java.util.Date;
import java.util.Map;
import org.bson.types.ObjectId;


@lombok.Data
public class TransactionDto {

  private Date transactionDate;

  private TransactionStatus status;

  private ObjectId entityId;

  private TransactionType transactionType;

  private Map<String, String> updatingEntity;

}