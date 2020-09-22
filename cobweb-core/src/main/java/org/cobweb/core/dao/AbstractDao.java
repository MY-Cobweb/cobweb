package org.cobweb.core.dao;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.cobweb.core.conn.DataSourceManager;
import org.cobweb.core.exception.CobwebDaoException;
import org.cobweb.core.exception.CobwebException;
import sun.reflect.FieldAccessor;

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
      StringBuilder sqlBuilder = new StringBuilder("UPDATE TABLE ").append(getTableName());
      Map<FieldAccessor, String> fieldColumnMap = EntityMapping.getFieldColMap(getEntityClass());
      checkArgument(Objects.nonNull(fieldColumnMap) && MapUtils.isNotEmpty(fieldColumnMap));
      List<String> setValueList = new ArrayList<>();
      for (Entry<FieldAccessor, String> entry : fieldColumnMap.entrySet()) {
        Object value = entry.getKey().get(t);
        if (value != null) {
          setValueList.add(entry.getValue() + "=" + DaoSupport.wrapValueQuota(value));
        }
      }
      sqlBuilder.append(" ").append(StringUtils.join(setValueList, ","))
          .append(" WHERE ").append(buildIdEqCondition(getIdValue(t)));
      executeSql(sqlBuilder.toString(),
          "UPDATE RECORD " + getIdValue(t) + " FOR TABLE " + getTableName() + " FAILURE");
    } catch (IllegalAccessException e) {
      throw new CobwebDaoException(e);
    }
  }

  public void delete(Long id) throws CobwebException {
    checkNotNull(id);
    String sql = "DELETE FROM " + getTableName() + " WHERE " + buildIdEqCondition(id);
    executeSql(sql, "DELETE FROM " + getTableName() + " FAILURE FOR " + id);
  }

  public void insert(T t) throws CobwebException {
    checkNotNull(t);
    StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ")
        .append(getTableName())
        .append("(");
    Map<FieldAccessor, String> fieldColumnMap = EntityMapping.getFieldColMap(getEntityClass());
    checkArgument(Objects.nonNull(fieldColumnMap) && MapUtils.isNotEmpty(fieldColumnMap));
    List<Object> valueList = new LinkedList<>();
    List<String> columnList = new LinkedList<>();
    for (Entry<FieldAccessor, String> entry : fieldColumnMap.entrySet()) {
      Object value = entry.getKey().get(t);
      if (value != null) {
        valueList.add(value);
        columnList.add(entry.getValue());
      }
    }
    sqlBuilder.append(StringUtils.join(columnList, ","))
        .append(") VALUES (").append(StringUtils.join(
        valueList.stream().map(value -> DaoSupport.wrapValueQuota(value))
            .collect(Collectors.toList()), ","))
        .append(")");
    executeSql(sqlBuilder.toString(), "insert failure");
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
      return ResultSetExtractor.extractValues(rs, getEntityClass());
    } catch (SQLException | InstantiationException | IllegalAccessException e) {
      throw new CobwebDaoException(e);
    }
  }

  public void executeSql(String sql, String failureMsg) throws CobwebException {
    try (Connection conn = DataSourceManager.getConnection(POOL_NAME);
        PreparedStatement stmt = conn.prepareStatement(sql);) {
      int row = stmt.executeUpdate();
      if (row < 1) {
        throw new CobwebDaoException(failureMsg);
      }
    } catch (SQLException | CobwebException e) {
      throw new CobwebDaoException(e);
    }
  }

  private Class<T> getEntityClass() {
    ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
    return (Class<T>) parameterizedType.getActualTypeArguments()[0];
  }

  private String getTableName() {
    return EntityMapping.getTableName(getEntityClass());
  }

  private String getIdCol() {
    return EntityMapping.getIdColumn(getEntityClass());
  }

  private String buildIdEqCondition(Object idVal) {
    return EntityMapping.buildIdEqCondition(getEntityClass(), idVal);
  }

  private Object getIdValue(T entity) throws IllegalAccessException {
    return EntityMapping.getIdValue(getEntityClass(), entity);
  }
}
