package org.cobweb.sql.statement.select;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cobweb.sql.Table;
import org.cobweb.sql.statement.Statement;

/**
 * Sql Select Statement
 *
 * @author meijie
 * @since 0.0.1
 */
@NoArgsConstructor
@Setter
@Getter
public class SelectStatement implements Statement {

  private List<SelectItem> selectItems;
  private Table table;

}
