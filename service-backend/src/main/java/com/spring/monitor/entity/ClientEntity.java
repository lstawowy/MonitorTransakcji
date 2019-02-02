package com.spring.monitor.entity;

import com.spring.monitor.dto.Address;
import com.spring.monitor.dto.ClientDto;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Entity
@Data
public class ClientEntity {

  @Id
  @GeneratedValue
  private String id;

  private Address address;
  @NotEmpty
  private String email;

  private String firstName;

  private String lastName;

  private String phoneNumber;


  public ClientEntity() {
  }

  public ClientEntity(String id, String firstName, String lastName,
      Address address, String phoneNumber, String email) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  public ClientEntity(ClientDto dto) {
    this.firstName = dto.getFirstName();
    this.lastName = dto.getLastName();
    this.address = dto.getAddress();
    this.phoneNumber = dto.getPhoneNumber();
    this.email = dto.getEmail();
  }
}