package org.cobweb.core.dao;

import com.google.common.base.CaseFormat;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.cobweb.core.annotation.Id;
import org.cobweb.core.annotation.Table;
import org.reflections.Reflections;
import sun.reflect.FieldAccessor;
import sun.reflect.ReflectionFactory;

/**
 * Map information from table to entity;
 *
 * @author meijie
 * @since 0.0.1
 */
@Setter
@Getter
public class EntityMapping {

  private static final Map<Class, EntityMapping> orm = new HashMap<>();
  private String table;
  private FieldAccessor id;
  private String idColumnName;
  private Class idType;

  private final Map<String, FieldAccessor> colFieldMap = new HashMap<>();
  private final Map<FieldAccessor, Class<?>> fieldTypeMap = new HashMap<>();
  private final Map<FieldAccessor, String> fieldColMap = new HashMap<>();

  public static void scanEntities() {
    Reflections reflections = new Reflections("org.cobweb.core.entity");
    Set<Class<?>> entitySet = reflections.getTypesAnnotatedWith(Table.class);
    for (Class<?> entity : entitySet) {
      EntityMapping entityMapping = new EntityMapping();
      Table table = entity.getAnnotation(Table.class);
      entityMapping.setTable(table.value());

      for (Field field : entity.getDeclaredFields()) {
        Id id = field.getAnnotation(Id.class);
        FieldAccessor fieldAccessor = ReflectionFactory.getReflectionFactory()
            .newFieldAccessor(field, false);
        String colName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field.getName());
        if (id != null) {
          entityMapping.setId(fieldAccessor);
          entityMapping.setIdColumnName(colName);
          entityMapping.setIdType(field.getType());
        } else {
          entityMapping.colFieldMap.putIfAbsent(colName, fieldAccessor);
          entityMapping.fieldColMap.putIfAbsent(fieldAccessor, colName);
          entityMapping.fieldTypeMap.putIfAbsent(fieldAccessor, field.getType());
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
    return entityMapping.id.get(entity);
  }

  public static Map<FieldAccessor, String> getFieldColMap(Class clazz) {
    EntityMapping entityMapping = orm.get(clazz);
    return entityMapping.getFieldColMap();
  }

  public static Map<String, FieldAccessor> getColFieldMap(Class clazz) {
    EntityMapping entityMapping = orm.get(clazz);
    return entityMapping.getColFieldMap();
  }

  public static Class<?> getFieldType(Class clazz, FieldAccessor fieldAccessor) {
    EntityMapping entityMapping = orm.get(clazz);
    return entityMapping.getFieldTypeMap().get(fieldAccessor);
  }

  public static String buildIdEqCondition(Class clazz, Object idValue) {
    return getIdColumn(clazz) + "=" + DaoSupport.wrapValueQuota(idValue);
  }

  public static String getIdColumn(Class clazz) {
    EntityMapping entityMapping = orm.get(clazz);
    return entityMapping.getIdColumnName();
  }

  public static FieldAccessor getIdField(Class clazz) {
    EntityMapping entityMapping = orm.get(clazz);
    return entityMapping.getId();
  }

  public static EntityMapping getEntityMapping(Class clazz) {
    return orm.get(clazz);
  }

}
