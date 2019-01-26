package unitofwork;

public interface IUnitOfWork<T> {

  void registerNew(T entity);

  void registerUpdate(T entity);

  void registerDelete(T entity);

  void commit();

  void rollback();

  void clear();

}