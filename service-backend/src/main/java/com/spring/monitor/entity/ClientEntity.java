package com.spring.monitor.entity;

import com.spring.monitor.dto.Address;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotEmpty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;


@Entity
@lombok.Data
public class ClientEntity {

  @Id
  @NotEmpty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private ObjectId id;
  private Address address;
  @NotEmpty
  private String email;

  private String firstName;

  private String lastName;

  private String phoneNumber;


  public ClientEntity(ObjectId id, String firstName, String lastName,
      Address address, String phoneNumber, String email) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  public ClientEntity(String firstName, String lastName, Address address,
      String phoneNumber, String email) {
    this.id = ObjectId.get();
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  public ClientEntity() {
  }

}