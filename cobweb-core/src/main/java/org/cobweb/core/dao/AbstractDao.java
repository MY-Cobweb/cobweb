package org.cobweb.core.dao;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.cobweb.core.conn.DataSourceManager;
import org.cobweb.core.exception.CobwebException;

/**
 * A Abstract Dao to support basic database CURD
 *
 * @param <T>
 * @author meijie
 * @since 0.0.1
 */
public abstract class AbstractDao<T> {

  private static final String POOL_NAME = "cobweb";

  /**
   * Find Entity by Id
   *
   * @param id
   * @return Entity if exists
   * @throws CobwebException
   */
  public T findById(Long id) throws CobwebException {
    checkNotNull(id);
    String sql = Cql.cql().column(getIdCol()).is(id).toSql(getTableName());
    return query(sql);
  }

  public List<T> findAll() throws CobwebException {
    String sql = Cql.cql().toSql(getTableName());
    return queryList(sql);
  }

  public void update(T t) throws CobwebException {
    try {
      checkNotNull(t);
      checkNotNull(getIdValue(t));
      // TODO finish update function
    } catch (IllegalAccessException e) {
      throw new CobwebException(e);
    }
  }

  public void delete(Long id) throws CobwebException {
    checkNotNull(id);
    String sql = "DELETE FROM " + getTableName() + "WHERE " + buildIdEq(id);
    try (Connection conn = DataSourceManager.getConnection(POOL_NAME);
        PreparedStatement stmt = conn.prepareStatement(sql);) {
      int row = stmt.executeUpdate();
      if (row < 1) {
        throw new CobwebException("DELETE BY ID FAILURE");
      }
    } catch (SQLException e) {
      throw new CobwebException(e);
    }
  }

  public void insert(T t) {
    // TODO finish add function
    checkNotNull(t);

  }

  public T query(Cql cql) throws CobwebException {
    String sql = cql.toSql(getTableName());
    return query(sql);
  }

  public T query(String sql) throws CobwebException {
    List<T> resultList = queryList(sql);
    if (resultList != null && !resultList.isEmpty()) {
      return resultList.get(0);
    } else {
      return null;
    }
  }

  public List<T> queryList(Cql cql) throws CobwebException {
    String sql = cql.toSql(getTableName());
    return queryList(sql);
  }

  public List<T> queryList(String sql) throws CobwebException {
    try (Connection conn = DataSourceManager.getConnection(POOL_NAME);
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {
      return ResultSetExtractor.extractValues(rs, getGenericClass());
    } catch (SQLException e) {
      throw new CobwebException(e);
    }
  }

  public void batchExecute(List<String> sqlList) {

  }

  private Class<T> getGenericClass() {
    ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
    return (Class<T>) parameterizedType.getActualTypeArguments()[0];
  }

  private String getTableName() {
    return EntityMapping.getTableName(this.getClass());
  }

  private String getIdCol() {
    return EntityMapping.getIdColumn(this.getClass());
  }

  private String buildIdEq(Object idVal) {
    return EntityMapping.buildIdEqCondition(this.getClass(), idVal);
  }

  private Object getIdValue(T entity) throws IllegalAccessException {
    return EntityMapping.getIdValue(this.getClass(), entity);
  }
}
