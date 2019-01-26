package manager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.repository.MongoRepository;

public class TransactionManagerTest {

  @Mock
  private MongoRepository repository;

  private TransactionManager transactionManager;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

  }

  @Test
  public void registerNew() {
  }

  @Test
  public void registerUpdate() {
  }

  @Test
  public void registerDelete() {
  }

  @Test
  public void commit() {
  }

  @Test
  public void rollback() {
  }

  @Test
  public void clear() {
  }
}