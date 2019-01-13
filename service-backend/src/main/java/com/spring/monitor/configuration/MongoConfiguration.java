package com.spring.monitor.configuration;

import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Component;

@Component
public class MongoConfiguration {

  private final MongoDbFactory mongo;

  @Autowired
  public MongoConfiguration(MongoDbFactory mongo) {
    this.mongo = mongo;
  }

  // ...

  public void example() {
    MongoDatabase db = mongo.getDb();
    // ...
  }

}