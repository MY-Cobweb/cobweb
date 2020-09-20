package org.cobweb.core.bean;

import java.util.Properties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cobweb.core.constant.StoreType;

@Getter
@Setter
@NoArgsConstructor
public class DataSourceBean {
  private Long id;
  private String name;
  private StoreType type;
  private String host;
  private Integer port;
  private String user;
  private String password;
  private Properties properties;
}
