package org.cobweb.core.meta;

import lombok.Getter;
import lombok.Setter;
import org.cobweb.core.constant.ColumnTag;

@Getter
@Setter
public class ColMeta {
  private String colName;
  private ColumnTag tag;
  private String typeName;
  private Integer type;
  private String expr;
  private String description;
}
