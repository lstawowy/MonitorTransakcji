package com.spring.monitor.resource;

import com.spring.monitor.dao.ClientDao;
import com.spring.monitor.dto.ClientDto;
import com.spring.monitor.entity.ClientEntity;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

  private ClientDao dao;

  @ApiOperation(value = "Get all clients", nickname = "Get all clients")
  @GetMapping(value = "/")
  public List<ClientEntity> getAllClients() {
    return dao.findAll();
  }

  @ApiOperation(value = "Insert client", nickname = "Insert client")
  @PostMapping(value = "/")
  public void insertClient(@Valid @RequestBody ClientEntity entity) {
    dao.registerNew(entity);
  }

  @ApiOperation(value = "Modify client", nickname = "Modify client")
  @PutMapping(value = "/")
  public void modifyClient(@Valid @RequestBody ClientEntity entity) {
    dao.registerUpdated(entity);
  }

  @ApiOperation(value = "Delete client", nickname = "Delete client")
  @PostMapping(value = "/")
  public void deleteClient(@Valid @RequestBody ClientEntity entity) {
    dao.registerDeleted(entity);
  }

  @ApiOperation(value = "Insert client", nickname = "Insert client")
  @PostMapping(value = "/")
  public void insertClient(@Valid @RequestBody ClientDto client) {
    ClientEntity entity = new ClientEntity(client);
    dao.registerNew(entity);
  }

  @ApiOperation(value = "Modify client", nickname = "Modify client")
  @PutMapping(value = "/")
  public void modifyClient(@Valid @RequestBody ClientDto client) {
    ClientEntity entity = new ClientEntity(client);
    dao.registerUpdated(entity);
  }

  @ApiOperation(value = "Delete client", nickname = "Delete client")
  @PostMapping(value = "/")
  public void deleteClient(@Valid @RequestBody ClientDto client) {
    ClientEntity entity = new ClientEntity(client);
    dao.registerDeleted(entity);
  }

  @ApiOperation(value = "Commit changes", nickname = "Commit changes")
  @PostMapping(value = "/commit")
  public void commit() {
    dao.commit();
  }

  @ApiOperation(value = "Rollback changes", nickname = "Rollback changes")
  @PostMapping(value = "/rollback")
  public void rollback() {
    dao.rollback();
  }
}



