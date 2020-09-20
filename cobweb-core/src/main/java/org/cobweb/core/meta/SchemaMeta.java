package org.cobweb.core.meta;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SchemaMeta {
  private String dataSource;
  private String schema;
  private String catalog;
  private String description;
  private List<StoreMeta> storeMetaList;
}
