package com.spring.monitor.entity;

import com.spring.monitor.dao.ClientDao;
import com.spring.monitor.dto.Address;
import com.spring.monitor.dto.ClientDto;
import java.util.Objects;
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

  private String firstName;

  private String lastName;

  public ClientEntity(ClientDto client) {
    ClientDao dao = new ClientDao(null);
    ClientEntity entity = dao.findByEmail(client.getEmail());

    this.id = entity != null ? entity.getId() : ObjectId.get();
    fillEntity(client, entity);
  }

  private String phoneNumber;

  private void fillEntity(ClientDto client, ClientEntity entity) {
    this.address = client.getAddress() != null ? client.getAddress() : Objects
        .requireNonNull(entity).getAddress();
    this.firstName = client.getFirstName() != null ? client.getFirstName() : Objects
        .requireNonNull(entity).getFirstName();
    this.lastName = client.getLastName() != null ? client.getLastName() : Objects
        .requireNonNull(entity).getLastName();
    this.email = client.getEmail() != null ? client.getEmail() : Objects
        .requireNonNull(entity).getEmail();
    this.phoneNumber = client.getPhoneNumber() != null ? client.getPhoneNumber() : Objects
        .requireNonNull(entity).getPhoneNumber();
  }

}