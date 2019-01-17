package com.spring.monitor.entity;

import com.spring.monitor.dto.AddressDto;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;


@Entity
@lombok.Data
public class ClientEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private ObjectId id;

  private String firstName;

  private String lastName;

  private AddressDto address;

  private String phoneNumber;

  private String email;

}