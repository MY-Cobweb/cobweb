package org.cobweb.core;

import java.io.File;
import java.io.IOException;
import org.cobweb.core.conn.DataSourceManager;
import org.cobweb.core.dao.EntityMapping;
import org.junit.jupiter.api.BeforeAll;

/**
 * @author meijie
 * @since 0.0.1
 */
public class DaoTestIniter {
  @BeforeAll
  public static void beforeAll() throws IOException {
    // load all datasource
    DataSourceManager.loadDataSource(new File("src/test/resources/datasource"));
    EntityMapping.scanEntities();
  }

}
