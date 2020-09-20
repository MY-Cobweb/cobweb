package org.cobweb.core.meta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColMeta {
  private String colName;
  private String typeName;
  private Integer type;
  private String expr;
  private String description;
}
