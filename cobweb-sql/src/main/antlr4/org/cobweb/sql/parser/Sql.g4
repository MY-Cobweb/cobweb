grammar Sql;

@header {
package org.cobweb.sql.parser;
}

statement
  : queryStatement EOF ;

queryStatement
  : selectClause fromClause groupClause?;

selectClause
  : SELECT (selectQualifiedName)+
  ;
fromClause
  : FROM identifier
  ;

groupClause
  : GROUP BY (identifier)+
  ;

selectQualifiedName
  : ASTERISK
  | identifier '.' ASTERISK
  | qualifiedName
  ;

qualifiedName
  : identifier ('.' identifier)*
  ;

identifier
  : (LEFFER | DIGIT | '_') +;

fragment DIGIT
  : [0-9];

fragment LEFFER
  : [A-Z];

// cobweb keywords list
AS : 'AS';
ASTERISK: '*';
BY : 'BY';
FROM : 'FROM';
GROUP : 'GROUP';
SELECT : 'SELECT';
WHERE : 'WHERE';

WS : [ \t\r\n]+ -> skip;