-- ----------------------------
-- Table structure for currency_set
-- ----------------------------
CREATE TABLE IF NOT EXISTS `currency_set` (
  `currency_code` varchar(10) NOT NULL,
  `currency_name` varchar(100) NOT NULL,
  `creation_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`currency_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for exchange rate
-- ----------------------------
CREATE TABLE IF NOT EXISTS `exchange_rate` (
  `rate_id` int NOT NULL AUTO_INCREMENT,
  `chart_name` varchar(100) NOT NULL,
  `disclaimer` varchar(1000) DEFAULT NULL,
  `rate_time` datetime DEFAULT NULL,
  `updated` varchar(100) DEFAULT NULL,
  `updated_iso` varchar(100) DEFAULT NULL,
  `updateduk` varchar(100) DEFAULT NULL,
  `currency_code` varchar(10) NOT NULL,
  `currency_name` varchar(20) DEFAULT NULL,
  `currency_rate` double NOT NULL,
  `creation_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`rate_id`),
  UNIQUE KEY `exchange_rate_idx1` (`chart_name`),
  UNIQUE KEY `exchange_rate_idx2` (`currency_code`),
  UNIQUE KEY `exchange_rate_idx3` (`currency_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;