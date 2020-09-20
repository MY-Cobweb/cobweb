package org.cobweb.sql.parser;

import org.cobweb.sql.parser.SqlParser.QueryStatementContext;
import org.cobweb.sql.statement.Statement;
import org.cobweb.sql.statement.select.SelectStatement;

/**
 * Anltr4 Visitor to parse statement
 * TODO extend it to support other statements
 */
public class CobWebSqlVisitor<T extends Statement> extends SqlBaseVisitor<T> {

  @Override
  public T visitQueryStatement(QueryStatementContext ctx) {
    SelectStatement statement = new SelectStatement();
    return (T) statement;
  }
}
