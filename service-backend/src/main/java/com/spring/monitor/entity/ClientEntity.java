package com.spring.monitor.entity;

import com.spring.monitor.dto.Address;
import com.spring.monitor.dto.ClientDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name="clients")
public class ClientEntity {

  @Id @GeneratedValue(generator="system-uuid")
  @GenericGenerator(name="system-uuid", strategy = "uuid")
  @Column(name="id", nullable=false)
  private String id;

  @Column(name="email", nullable=false)
  private String email;

  @Column(name="firstName", nullable=false)
  private String firstName;

  @Column(name="lastName", nullable=false)
  private String lastName;

  @Column(name="phoneNumber")
  private String phoneNumber;


  public ClientEntity() {
  }

  public ClientEntity(String id, String firstName, String lastName,
      Address address, String phoneNumber, String email) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  public ClientEntity(ClientDto dto) {
    this.firstName = dto.getFirstName();
    this.lastName = dto.getLastName();
    this.phoneNumber = dto.getPhoneNumber();
    this.email = dto.getEmail();
  }
}