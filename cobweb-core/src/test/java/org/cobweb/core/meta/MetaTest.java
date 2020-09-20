package org.cobweb.core.meta;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.cobweb.core.DaoTestIniter;
import org.cobweb.core.conn.DataSourceManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MetaTest extends DaoTestIniter {

  @Test
  public void loadCatalog() throws SQLException {
    testFetchMetas("cobweb");
    testFetchMetas("clickhouse");
  }

  private void testFetchMetas(String dataSource) throws SQLException {
    List<SchemaMeta> schemaMetaList = MetaManager.listSchemaMeta(dataSource);
    for (SchemaMeta schemaMeta : schemaMetaList) {
      assertNotNull(schemaMeta.getSchema());
      List<StoreMeta> storeMetas = MetaManager.listStoreMeta(schemaMeta);
      for (StoreMeta storeMeta : storeMetas) {
        assertNotNull(storeMeta.getTable());
        storeMeta = MetaManager.fetchStoreMeta(storeMeta);
        for (ColMeta colMeta : storeMeta.getColMetaList()) {
          assertNotNull(colMeta.getColName());
          assertNotNull(colMeta.getType());
          assertNotNull(colMeta.getTypeName());
        }
      }
    }
  }
}
