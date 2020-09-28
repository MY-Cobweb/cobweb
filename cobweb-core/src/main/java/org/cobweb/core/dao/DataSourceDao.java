package org.cobweb.core.dao;

import org.cobweb.core.entity.DataSourceEntity;
import org.cobweb.core.exception.CobwebException;

/**
 * Data Source Dao
 *
 * @author meijie
 * @since 0.0.1
 */
public class DataSourceDao extends AbstractDao<DataSourceEntity> {

  public DataSourceEntity findByName(String name) throws CobwebException {
    return query(Cql.select().column("NAME").is(name));
  }

  public void deleteByName(String name) throws CobwebException {
    executeSql(Cql.delete().column("NAME").is(name));
  }
}
