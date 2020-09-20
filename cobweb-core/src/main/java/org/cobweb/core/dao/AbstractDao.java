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

  public T findById(Long id) throws CobwebException {
    checkNotNull(id);
    String sql = "SELECT * FROM " + getTableName() + "WHERE " + buildIdEq(id);
    try (Connection conn = DataSourceManager.getConnection(POOL_NAME);
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {
      List<T> result = ResultSetExtractor.extractValues(rs, getGenericClass());
      if (!result.isEmpty()) {
        return null;
      } else {
        return result.get(0);
      }
    } catch (SQLException e) {
      throw new CobwebException(e);
    }
  }

  public List<T> findAll() throws CobwebException {
    String sql = "SELECT * FROM " + getTableName();
    try (Connection conn = DataSourceManager.getConnection(POOL_NAME);
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {
      return ResultSetExtractor.extractValues(rs, getGenericClass());
    } catch (SQLException e) {
      throw new CobwebException(e);
    }
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

  public void add(T t) {
    // TODO finish add function
    checkNotNull(t);
  }

  private Class<T> getGenericClass() {
    ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
    return (Class<T>) parameterizedType.getActualTypeArguments()[0];
  }

  private String getTableName() {
    return EntityMapping.getTableName(this.getClass());
  }

  private String buildIdEq(Object idVal) {
    return EntityMapping.buildIdEqCondition(this.getClass(), idVal);
  }

  private Object getIdValue(T entity) throws IllegalAccessException {
    return EntityMapping.getIdValue(this.getClass(), entity);
  }
}
