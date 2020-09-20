package org.cobweb.core.dao;

import com.google.common.base.CaseFormat;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.Setter;
import org.cobweb.core.annotation.Id;
import org.cobweb.core.annotation.Table;
import org.reflections.Reflections;

/**
 * Map information from table to entity;
 *
 * @author meijie
 * @since 0.0.1
 */
@Setter
public class EntityMapping {

  private static final Map<Class, EntityMapping> orm = new HashMap<>();
  private String table;
  private Field id;
  private final Map<String, Field> colFieldMap = new HashMap<>();
  private final Map<Field, String> fieldColMap = new HashMap<>();

  public static void scanEntities() {
    Reflections reflections = new Reflections("org.cobweb.core.dao");
    Set<Class<?>> entitySet = reflections.getTypesAnnotatedWith(Table.class);
    for (Class<?> entity : entitySet) {
      EntityMapping entityMapping = new EntityMapping();
      Table table = entity.getAnnotation(Table.class);
      entityMapping.setTable(table.value());

      for (Field field : entity.getDeclaredFields()) {
        Id id = field.getAnnotation(Id.class);
        if (id != null) {
          entityMapping.setId(field);
        } else {
          String colName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field.getName());
          entityMapping.colFieldMap.putIfAbsent(colName, field);
          entityMapping.fieldColMap.putIfAbsent(field, colName);
        }
      }
      orm.putIfAbsent(entity, entityMapping);
    }
  }

  public static String getTableName(Class clazz) {
    EntityMapping entityMapping = orm.get(clazz);
    return entityMapping.table;
  }

  public static Object getIdValue(Class clazz, Object entity) throws IllegalAccessException {
    EntityMapping entityMapping = orm.get(clazz);
    entityMapping.id.setAccessible(true);
    return entityMapping.id.get(entity);
  }


  public static String buildIdEqCondition(Class clazz, Object idValue) {
    EntityMapping entityMapping = orm.get(clazz);
    String colName = CaseFormat.LOWER_CAMEL
        .to(CaseFormat.UPPER_UNDERSCORE, entityMapping.id.getName());
    return colName + "=" + wrapValueQuota(idValue);
  }

  public static String wrapValueQuota(Object value) {
    return "'" + value.toString() + "'";
  }

  public static void setColValue(Object entity, String col, Object value) {

  }
}
