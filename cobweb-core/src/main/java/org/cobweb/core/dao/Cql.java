package org.cobweb.core.dao;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

/**
 * @author meijie
 * @since 0.0.1
 */
public class Cql {

  private SqlType sqlType;
  private StringBuilder selectClause = new StringBuilder("SELECT * FROM ");
  private StringBuilder deleteClause = new StringBuilder("DELETE FROM ");
  private StringBuilder whereClause;
  private StringBuilder groupClause;
  private StringBuilder orderClause;
  private StringBuilder limitClause;

  public static Cql select() {
    Cql cql = new Cql();
    cql.sqlType = SqlType.SELECT;
    return cql;
  }

  public static Cql delete() {
    Cql cql = new Cql();
    cql.sqlType = SqlType.DELETE;
    return cql;
  }

  public Cql column(String column) {
    if (whereClause != null) {
      whereClause.append(" AND ");
    } else {
      whereClause = new StringBuilder(" WHERE ");
    }
    whereClause.append(column);
    return this;
  }

  public Cql is(Object val) {
    checkNotNull(whereClause);
    whereClause.append("=").append(DaoSupport.wrapValueQuota(val));
    return this;
  }

  public Cql not(Object val) {
    checkNotNull(whereClause);
    whereClause.append("!=").append(DaoSupport.wrapValueQuota(val));
    return this;
  }

  public Cql in(Collection<Object> col) {
    checkNotNull(whereClause);
    checkNotNull(col);
    col = col.stream().map(item -> item.toString()).collect(Collectors.toSet());
    whereClause.append(" IN (");
    whereClause.append(StringUtils.join(col, ","));
    whereClause.append(")");
    return this;
  }

  public String toSql(String table) {
    StringBuilder sqlBuilder;
    switch (this.sqlType) {
      case SELECT: {
        sqlBuilder = selectClause.append(table);
        break;
      }
      case DELETE: {
        sqlBuilder = deleteClause.append(table);
        break;
      }
      default: {
        throw new UnsupportedOperationException(this.sqlType.name());
      }
    }
    if (whereClause != null) {
      sqlBuilder.append(whereClause);
    }
    if (groupClause != null) {
      sqlBuilder.append(groupClause);
    }
    if (orderClause != null) {
      sqlBuilder.append(orderClause);
    }
    if (limitClause != null) {
      sqlBuilder.append(limitClause);
    }
    return sqlBuilder.toString();
  }

  private enum SqlType {
    SELECT,
    DELETE
  }
}
