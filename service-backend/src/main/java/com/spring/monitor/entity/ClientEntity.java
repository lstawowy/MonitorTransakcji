package com.spring.monitor.entity;

import com.spring.monitor.dto.AddressDto;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;


@lombok.Data
public class ClientEntity {

  @Id
  private ObjectId id;

  private String firstName;

  private String lastName;

  private AddressDto address;

  private String phoneNumber;

  private String email;

}