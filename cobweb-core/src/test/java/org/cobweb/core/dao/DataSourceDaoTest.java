package org.cobweb.core.dao;

import org.cobweb.core.DaoTestIniter;
import org.cobweb.core.constant.StoreType;
import org.cobweb.core.entity.DataSourceEntity;
import org.cobweb.core.exception.CobwebException;
import org.junit.jupiter.api.Test;

/**
 * @author meijie
 * @since 0.0.1
 */
public class DataSourceDaoTest extends DaoTestIniter {

  private static final DataSourceDao dataSourceDao = new DataSourceDao();

  @Test
  public void testCRUD() throws CobwebException {
    DataSourceEntity entity = new DataSourceEntity();
    entity.setName("test_data_source");
    entity.setHost("127.0.0.1");
    entity.setPort(3307);
    entity.setPassword("test_password");
    entity.setType(StoreType.CLICKHOUSE);
    entity.setUser("test");
    dataSourceDao.insert(entity);

    DataSourceEntity searchEntity = dataSourceDao.findByName("test_data_source");
    dataSourceDao.delete(searchEntity.getId());
  }
}
