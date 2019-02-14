package unitofwork;

public interface IUnitOfWork<T> {

  T registerNew(T entity);

  T registerUpdate(T entity);

  T registerDelete(T entity);

  void commit();

  void rollback();

  void clear();

}