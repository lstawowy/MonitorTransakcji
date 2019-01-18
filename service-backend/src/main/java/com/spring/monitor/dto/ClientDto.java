package com.spring.monitor.dto;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;


@Entity
@lombok.Data
public class ClientDto {

  private String firstName;

  private String lastName;

  private Address address;

  private String phoneNumber;

  @NotEmpty
  private String email;

}