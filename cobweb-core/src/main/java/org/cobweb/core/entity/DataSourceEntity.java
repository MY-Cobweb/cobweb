package org.cobweb.core.entity;

import java.util.Properties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cobweb.core.annotation.Id;
import org.cobweb.core.annotation.Table;
import org.cobweb.core.constant.StoreType;

@Getter
@Setter
@NoArgsConstructor
@Table("DATA_SOURCE")
public class DataSourceEntity {

  @Id
  private Long id;
  private String name;
  private StoreType type;
  private String host;
  private Integer port;
  private String user;
  private String password;
  private String properties;
}
