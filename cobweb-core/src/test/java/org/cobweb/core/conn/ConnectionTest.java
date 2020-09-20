package org.cobweb.core.conn;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import org.junit.jupiter.api.Test;

public class ConnectionTest {

  @Test
  public void testClickHouseConnect() throws IOException, SQLException {
    InputStream inputStream = getClass().getClassLoader()
        .getResourceAsStream("datasource/clickhouse.properties");
    Properties properties = new Properties();
    properties.load(inputStream);

    DataSourceManager.loadDataSource(properties);
    try (Connection conn = DataSourceManager.getConnection(properties.getProperty("name"));
        PreparedStatement pstmt = conn.prepareStatement("show tables");
        ResultSet rs = pstmt.executeQuery()) {
      while (rs.next()) {
        System.out.println(rs.getString(1));
      }
    }
  }
}
