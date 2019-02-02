package com.spring.monitor.resource;

import com.spring.monitor.dao.MongoClientDao;
import com.spring.monitor.dto.ClientDto;
import com.spring.monitor.entity.ClientEntity;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/clients")
@RequestMapping(value = "/clients")
public class ClientResource {

  @Autowired
  private MongoClientDao dao;

  @ApiOperation(value = "Get all clients", nickname = "Get all clients")
  @GetMapping
  public List<ClientEntity> getAllClients() {
    return dao.findAll();
  }

  @ApiOperation(value = "Insert client", nickname = "Insert client")
  @PostMapping
  public void insertClient(@Valid @RequestBody ClientEntity entity) {
    dao.registerNew(entity);
  }

  @ApiOperation(value = "Modify client", nickname = "Modify client")
  @PutMapping
  public void modifyClient(@Valid @RequestBody ClientEntity entity) {
    dao.registerUpdate(entity);
  }

  @ApiOperation(value = "Delete client", nickname = "Delete client")
  @DeleteMapping
  public void deleteClient(@Valid @RequestBody ClientEntity entity) {
    dao.registerDelete(entity);
  }

  @PostMapping(value = "/dto")
  public void insertClientFromDto(@Valid @RequestBody ClientDto dto) {
    dao.registerNew(new ClientEntity(dto));
  }

  @PutMapping(value = "/dto")
  public void modifyClientFromDto(@Valid @RequestBody ClientDto dto) {
    dao.registerUpdate(new ClientEntity(dto));
  }

  @DeleteMapping(value = "/dto")
  public void deleteClientFromDto(@Valid @RequestBody ClientDto dto) {
    dao.registerDelete(new ClientEntity(dto));
  }

  @PostMapping(value = "/commit")
  public void commit() {
    dao.commit();
  }

  @PostMapping(value = "/rollback")
  public void rollback() {
    dao.rollback();
  }
}



