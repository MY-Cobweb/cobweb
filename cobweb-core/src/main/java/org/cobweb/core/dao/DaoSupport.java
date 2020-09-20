package org.cobweb.core.dao;

/**
 * @author meijie
 * @since 0.0.1
 */
public class DaoSupport {

  public static String wrapValueQuota(Object value) {
    return "'" + value.toString() + "'";
  }
}
