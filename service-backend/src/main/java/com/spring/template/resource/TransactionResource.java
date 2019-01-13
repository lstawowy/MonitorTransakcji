package com.spring.template.resource;

import com.spring.template.entity.TransactionEntity;
import com.spring.template.repository.TransactionRepository;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionResource {

  @Autowired
  private TransactionRepository repository;


  @GetMapping(value = "/")
  public List<TransactionEntity> getAllPets() {
    return repository.findAll();
  }


  @ApiOperation(value = "Get transaction by id", nickname = "Get transaction by id")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "id", value = "TransactionEntity's id", required = true, dataType = "long", paramType = "path", defaultValue = "1")
  })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = TransactionEntity.class),
      @ApiResponse(code = 401, message = "Unauthorized"),
      @ApiResponse(code = 403, message = "Forbidden"),
      @ApiResponse(code = 404, message = "Not Found"),
      @ApiResponse(code = 500, message = "Failure")}) // Swagger annotation

  @GetMapping(value = "/{id}")
  public TransactionEntity getTransactionById(@PathVariable("id") ObjectId id) {
    return repository.findById(id);
  }


  @PutMapping(value = "/{id}")
  public void modifyTransactionById(@PathVariable("id") ObjectId id,
      @Valid @RequestBody TransactionEntity transactionEntity) {
    transactionEntity.setId(id);
    repository.save(transactionEntity);
  }

}


