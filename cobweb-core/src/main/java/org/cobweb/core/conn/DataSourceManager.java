package org.cobweb.core.conn;

import com.zaxxer.hikari.HikariDataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import javax.sql.DataSource;
import org.cobweb.core.constant.StoreType;
import ru.yandex.clickhouse.BalancedClickhouseDataSource;

public class DataSourceManager {

  private static final Map<String, DataSource> dataSourceMap = new ConcurrentHashMap<>();

  private static DataSource getDatasource(String name) {
    return dataSourceMap.get(name);
  }

  public static void loadDataSource(File dataSourceFile) throws IOException {
    if (dataSourceFile.exists() && dataSourceFile.canRead()) {
      if (dataSourceFile.isDirectory()) {
        // list all properties file.
        File[] dataSourceFiles = dataSourceFile.listFiles(theFile -> theFile.isFile()
            && theFile.getName().endsWith(".properties"));
        if (dataSourceFiles == null) {
          return;
        }

        for (File file : dataSourceFiles) {
          loadDataSource(file);
        }
      } else {
        Properties properties = new Properties();
        properties.load(new FileInputStream(dataSourceFile));
        loadDataSource(properties);
      }
    }
  }

  public static void loadDataSource(Properties properties) {
    if (properties != null) {
      dataSourceMap.computeIfAbsent(properties.getProperty("name"), key -> {
        StoreType type = StoreType.valueOf(properties.getProperty("type"));
        switch (type) {
          case MYSQL: {
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setJdbcUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("user"));
            dataSource.setPassword(properties.getProperty("password"));
            dataSource.setDataSourceProperties(properties);
            return dataSource;
          }
          case CLICKHOUSE: {
            return new BalancedClickhouseDataSource(
                properties.getProperty("url"), properties);
          }
          default:
            throw new UnsupportedOperationException();
        }
      });
    }

  }

  public static Connection getConnection(String poolName) throws SQLException {
    DataSource dataSource = getDatasource(poolName);
    return dataSource.getConnection();
  }
}
