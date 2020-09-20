package org.cobweb.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cobweb.core.annotation.Id;
import org.cobweb.core.annotation.Table;
import org.cobweb.core.constant.StoreTag;

@Getter
@Setter
@NoArgsConstructor
@Table("TOPIC_SCHEMA")
public class TopicSchemaEntity {

  @Id
  private Long id;
  private String name;
  private StoreTag tag;
  private String dataSource;
  private String catalog;
  private String table;
}
