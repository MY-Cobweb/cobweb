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
    T t = clazz.newInstance();
    EntityMapping entityMapping = EntityMapping.getEntityMapping(clazz);
    Map<String, FieldAccessor> colFieldMap = entityMapping.getColFieldMap();

    for (Entry<String, FieldAccessor> entry : colFieldMap.entrySet()) {
      Class valueType = EntityMapping.getFieldType(clazz, entry.getValue());
      setFieldValue(rs, valueType, t, entry.getKey(), entry.getValue());
    }
    setFieldValue(rs, entityMapping.getIdType(), t, entityMapping.getIdColumnName(),
        entityMapping.getId());
    // set id value
    return t;
  }

  private static <T> void setFieldValue(ResultSet rs, Class valueType, T t,
      String columnName, FieldAccessor field) throws IllegalAccessException, SQLException {
    if (valueType.isEnum()) {
      field.set(t, Enum.valueOf(valueType, rs.getString(columnName)));
    } else {
      field.set(t, rs.getObject(columnName, valueType));
    }
  }

}
