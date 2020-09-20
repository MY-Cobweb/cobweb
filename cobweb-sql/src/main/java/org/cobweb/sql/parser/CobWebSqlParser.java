package org.cobweb.sql.parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.cobweb.sql.statement.Statement;

/**
 * Sql Parser
 *
 * @author meijie
 */
public class CobWebSqlParser {

  public <T extends Statement> T parseStatement(String sql) {
    SqlLexer sqlLexer = new SqlLexer(CharStreams.fromString(sql));
    CommonTokenStream tokens = new CommonTokenStream(sqlLexer);
    SqlParser parser = new SqlParser(tokens);
    ParseTree tree = parser.statement();
    CobWebSqlVisitor visitor = new CobWebSqlVisitor();
    return (T) visitor.visit(tree);
  }
}
