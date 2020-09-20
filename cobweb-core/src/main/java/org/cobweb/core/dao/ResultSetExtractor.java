package org.cobweb.core.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Extract Entity Values from ResultSet
 *
 * @author meijie
 * @since 0.0.1
 */
public class ResultSetExtractor {

  public static <T> List<T> extractValues(ResultSet rs, Class<T> clazz) throws SQLException {
    List<T> resultList = new ArrayList<>();
    while (rs.next()) {
      T value = extractValue(rs, clazz);
      resultList.add(value);
    }
    return resultList;
  }

  private static <T> T extractValue(ResultSet rs, Class<T> clazz) {
    return null;
  }

}
