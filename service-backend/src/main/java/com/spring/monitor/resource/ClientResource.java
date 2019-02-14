package com.spring.monitor.resource;

import com.spring.monitor.dao.MongoClientDao;
import com.spring.monitor.dto.ClientDto;
import com.spring.monitor.entity.ClientEntity;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<Object> getAllClients() {
    List<ClientEntity> entities;
    try {
      entities = dao.findAll();
    } catch (Exception e){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(entities,HttpStatus.OK);
  }

  @ApiOperation(value = "Insert client", nickname = "Insert client")
  @PostMapping
  public ResponseEntity<ClientEntity> insertClient(@Valid @RequestBody ClientEntity entity) {
    try {
      dao.registerNew(entity);
    } catch (Exception e){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(entity,HttpStatus.CREATED);
  }

  @ApiOperation(value = "Modify client", nickname = "Modify client")
  @PutMapping
  public ResponseEntity<ClientEntity> modifyClient(@Valid @RequestBody ClientEntity entity) {
    if (checkIfIdIsNull(entity)) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    try {
      dao.registerUpdate(entity);
    } catch (Exception e){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(entity,HttpStatus.OK);
  }

  @ApiOperation(value = "Delete client", nickname = "Delete client")
  @DeleteMapping
  public ResponseEntity<ClientEntity> deleteClient(@Valid @RequestBody ClientEntity entity) {
    if (checkIfIdIsNull(entity)) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    try {
      dao.registerDelete(entity);
    } catch (Exception e){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(entity,HttpStatus.OK);
  }

  @PostMapping(value = "/dto")
  public ResponseEntity<ClientEntity> insertClientFromDto(@Valid @RequestBody ClientDto dto) {
    ClientEntity entity = new ClientEntity(dto);
    try {
      dao.registerNew(entity);
    } catch (Exception e){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(entity,HttpStatus.CREATED);
  }

  @PutMapping(value = "/dto")
  public ResponseEntity<ClientEntity> modifyClientFromDto(@Valid @RequestBody ClientDto dto) {
    ClientEntity entity = new ClientEntity(dto);
    if (checkIfIdIsNull(entity)) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    try {
      dao.registerUpdate(entity);
    } catch (Exception e){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(entity,HttpStatus.OK);
  }

  @DeleteMapping(value = "/dto")
  public ResponseEntity<ClientEntity> deleteClientFromDto(@Valid @RequestBody ClientDto dto) {
    ClientEntity entity = new ClientEntity(dto);
    if (checkIfIdIsNull(entity)) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    try {
      dao.registerDelete(entity);
    } catch (Exception e){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(entity,HttpStatus.OK);
  }

  private boolean checkIfIdIsNull(ClientEntity entity) {
    if (entity.getId() == null) {
      return true;
    }
    return false;
  }

  @PostMapping(value = "/commit")
  public ResponseEntity<Object> commit() {
    try {
      dao.commit();
    } catch (Exception e){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping(value = "/rollback")
  public ResponseEntity<Object> rollback() {
    try {
      dao.rollback();
    } catch (Exception e){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }
}



