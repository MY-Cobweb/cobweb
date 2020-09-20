package org.cobweb.core.meta;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cobweb.core.conn.DataSourceManager;

public class MetaManager {

  public static List<SchemaMeta> listSchemaMeta(String dataSource) throws SQLException {
    try (Connection conn = DataSourceManager.getConnection(dataSource)) {
      DatabaseMetaData metaData = conn.getMetaData();
      List<SchemaMeta> schemaMetaList = new ArrayList<>();
      try (ResultSet rs = metaData.getSchemas()) {
        while (rs.next()) {
          SchemaMeta schemaMeta = new SchemaMeta();
          schemaMeta.setDataSource(dataSource);
          schemaMeta.setSchema(rs.getString(1));
          schemaMeta.setCatalog(rs.getString(2));
          schemaMetaList.add(schemaMeta);
        }
      }
      return schemaMetaList;
    }
  }

  public static List<StoreMeta> listStoreMeta(SchemaMeta schemaMeta) throws SQLException {
    try (Connection connection = DataSourceManager.getConnection(schemaMeta.getDataSource());
        ResultSet rs = connection.getMetaData()
            .getTables(schemaMeta.getCatalog(), schemaMeta.getSchema(), null, null)) {
      List<StoreMeta> storeMetas = new ArrayList<>();
      while (rs.next()) {
        StoreMeta storeMeta = new StoreMeta();
        storeMeta.setDataSource(schemaMeta.getDataSource());
        storeMeta.setSchema(schemaMeta.getSchema());
        storeMeta.setTable(rs.getString(3));
        storeMetas.add(storeMeta);
      }
      return storeMetas;
    }
  }

  public static StoreMeta fetchStoreMeta(StoreMeta storeMeta) throws SQLException {
    try (Connection connection = DataSourceManager.getConnection(storeMeta.getDataSource());
        ResultSet rs = connection.getMetaData().getColumns(
            storeMeta.getCatalog(), storeMeta.getSchema(),
            storeMeta.getTable(), null)) {
      List<ColMeta> colMetas = new ArrayList<>();
      while (rs.next()) {
        ColMeta colMeta = new ColMeta();
        colMeta.setColName(rs.getString(4));
        colMeta.setType(rs.getInt(5));
        colMeta.setTypeName(rs.getString(6));
        colMeta.setDescription(rs.getString(12));
        colMetas.add(colMeta);
      }
      storeMeta.setColMetaList(colMetas);
      return storeMeta;
    }
  }
}
