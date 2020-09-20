package org.cobweb.core.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cobweb.core.constant.StoreTag;

@Getter
@Setter
@NoArgsConstructor
public class TopicSchemaBean {

  private Long id;
  private String name;
  private StoreTag tag;
  private String dataSource;
  private String catalog;
  private String table;
}
