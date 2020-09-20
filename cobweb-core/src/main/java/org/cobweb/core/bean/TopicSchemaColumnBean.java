package org.cobweb.core.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TopicSchemaColumnBean {

  private Long id;
  private Long topicSchemaId;
  private String columnName;
  private String typeName;
  private Integer type;
  private String expr;
  private String description;
}
