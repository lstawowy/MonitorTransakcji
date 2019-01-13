package com.spring.monitor.resource;

import com.spring.monitor.entity.ClientEntity;
import com.spring.monitor.entity.TransactionEntity;
import com.spring.monitor.repository.TransactionRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionResource {

  @Autowired
  private TransactionRepository repository;

  @ApiOperation(value = "Get all transactions", nickname = "Get all transactions")
  @GetMapping(value = "/")
  public List<TransactionEntity> findAllTransactions() {
    return repository.findAll();
  }


  @ApiOperation(value = "Get transaction by id", nickname = "Get transaction by id")
  @GetMapping(value = "/{id}")
  public TransactionEntity getTransactionById(@PathVariable("id") ObjectId id) {
    return repository.findById(id);
  }

  @ApiOperation(value = "Modify transaction", nickname = "Modify transaction")
  @PutMapping(value = "/{id}")
  public void modifyTransactionById(@PathVariable("id") ObjectId id,
      @Valid @RequestBody TransactionEntity transactionEntity) {
    transactionEntity.setId(id);
    repository.save(transactionEntity);
  }

  @ApiOperation(value = "Insert transaction", nickname = "Insert transaction")
  @PostMapping(value = "/")
  public void insertTransaction(@Valid @RequestBody TransactionEntity entity) {
    repository.insert(entity);
  }

}


