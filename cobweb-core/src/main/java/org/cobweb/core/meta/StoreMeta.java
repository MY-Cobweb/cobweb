package org.cobweb.core.meta;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.cobweb.core.constant.StoreTag;

@Getter
@Setter
public class StoreMeta {
  private StoreTag tag;
  private String dataSource;
  private String catalog;
  private String schema;
  private String table;
  private List<ColMeta> colMetaList;
  private ColMeta dateColMeta;
}
