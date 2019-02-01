package com.spring.monitor.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Data;


@Data
public class ClientDto {

  private String firstName;

  private String lastName;

  private Address address;

  private String phoneNumber;

  @NotEmpty
  private String email;

  public ClientDto(String firstName, String lastName, String phoneNumber, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }
}