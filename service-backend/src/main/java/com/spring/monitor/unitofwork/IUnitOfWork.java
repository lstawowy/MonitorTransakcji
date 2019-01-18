package com.spring.monitor.unitofwork;

public interface IUnitOfWork<T> {

  void registerNew(T entity);

  void registerUpdated(T entity);

  void registerDeleted(T entity);

  void commit();

  void rollback();

  void clear();

}