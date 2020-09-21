package org.cobweb.core.dao;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author meijie
 * @since 0.0.1
 */
public class DaoSupport {

  public static String wrapValueQuota(Object value) {
    checkNotNull(value);
    if (value instanceof Integer
        || value instanceof Short
        || value instanceof Long
        || value instanceof Double
        || value instanceof Float) {
      return String.valueOf(value);
    } else {
      return "'" + value.toString() + "'";
    }
  }

}
