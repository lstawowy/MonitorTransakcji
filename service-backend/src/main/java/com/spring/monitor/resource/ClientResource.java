package com.spring.monitor.resource;

import com.spring.monitor.entity.ClientEntity;
import com.spring.monitor.repository.ClientRepository;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

  @Autowired
  private ClientRepository repository;

  @ApiOperation(value = "Get all clients", nickname = "Get all clients")
  @GetMapping(value = "/")
  public List<ClientEntity> getAllClients() {
    return repository.findAll();
  }

  @ApiOperation(value = "Insert client", nickname = "Insert client")
  @PostMapping(value = "/")
  public void insertClient(@Valid @RequestBody ClientEntity entity) {
    repository.insert(entity);
  }

}



