CREATE TABLE IF NOT EXISTS cobweb.`DATA_SOURCE` (
  `ID` bigint unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL COMMENT 'Data Source Name',
  `TYPE` varchar(30) NOT NULL COMMENT 'Data Source Type',
  `HOST` varchar(50) NOT NULL,
  `PORT` int unsigned DEFAULT NULL,
  `USER` varchar(50),
  `PASSWORD` VARCHAR(100),
  `PROPERTIES` varchar(500) DEFAULT NULL COMMENT 'other configurations',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `DATA_SOURCE_UN` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Data Source';