package org.cobweb.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cobweb.core.annotation.Id;
import org.cobweb.core.annotation.Table;

@Getter
@Setter
@NoArgsConstructor
@Table("TABLE_SCHEMA_COLUMN")
public class TopicSchemaColumnEntity {

  @Id
  private Long id;
  private Long topicSchemaId;
  private String columnName;
  private String typeName;
  private Integer type;
  private String expr;
  private String description;
}
