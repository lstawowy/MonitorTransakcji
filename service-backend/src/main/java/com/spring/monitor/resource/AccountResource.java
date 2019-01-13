package com.spring.monitor.resource;

import com.spring.monitor.entity.AccountEntity;
import com.spring.monitor.repository.AccountRepository;
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
@RequestMapping(value = "/accounts")
public class AccountResource {

  @Autowired
  private AccountRepository repository;

  @ApiOperation(value = "Get all accounts", nickname = "Get all accounts")
  @GetMapping(value = "/")
  public List<AccountEntity> getAllAccounts() {
    return repository.findAll();
  }

  @ApiOperation(value = "Insert account", nickname = "Insert account")
  @PostMapping(value = "/")
  public void insertAccount(@Valid @RequestBody AccountEntity entity) {
    repository.insert(entity);
  }

}


