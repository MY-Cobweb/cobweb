CREATE TABLE IF NOT EXISTS cobweb.`TOPIC_SCHEMA_COLUMN` (
  `ID` bigint unsigned NOT NULL AUTO_INCREMENT,
  `TOPIC_SCHEMA_ID` bigint unsigned NOT NULL,
  `COLUMN_NAME` varchar(100) NOT NULL,
  `TAG` VARCHAR(20) NOT NULL,
  `TYPE_NAME` VARCHAR(100) NOT NULL,
  `TYPE` INT NOT NULL,
  `EXPR` varchar(500),
  `DESCRIPTION` VARCHAR(500),
  PRIMARY KEY (`ID`),
  UNIQUE KEY `TOPIC_SCHEMA_COLUMN_UN` (`TOPIC_SCHEMA_ID`, `COLUMN_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='TOPIC SCHEMA';
