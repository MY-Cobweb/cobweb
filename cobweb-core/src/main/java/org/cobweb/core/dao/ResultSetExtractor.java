package org.cobweb.core.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import sun.reflect.FieldAccessor;

/**
 * Extract Entity Values from ResultSet
 *
 * @author meijie
 * @since 0.0.1
 */
public class ResultSetExtractor {

  public static <T> List<T> extractValues(ResultSet rs, Class<T> clazz)
      throws SQLException, InstantiationException, IllegalAccessException {
    List<T> resultList = new ArrayList<>();
    while (rs.next()) {
      T value = extractValue(rs, clazz);
      resultList.add(value);
    }
    return resultList;
  }

  private static <T> T extractValue(ResultSet rs, Class<T> clazz)
      throws SQLException, IllegalAccessException, InstantiationException {
    Map<String, FieldAccessor> colFieldMap = EntityMapping.getColFieldMap(clazz);
    T t = clazz.newInstance();
    for (Entry<String, FieldAccessor> entry : colFieldMap.entrySet()) {
      Class<?> valueType = EntityMapping.getFieldType(clazz, entry.getValue());
      // TODO MAPPING ENTITY VALUE
      if (valueType.isEnum()) {

      }
      entry.getValue().set(t, rs.getObject(entry.getKey(), valueType));
    }
    return t;
  }

}
