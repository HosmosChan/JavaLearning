SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(64) NOT NULL,
  `ENTRY_ID` varchar(128) NOT NULL,
  `TRIGGER_NAME` varchar(64) NOT NULL,
  `TRIGGER_GROUP` varchar(64) NOT NULL,
  `INSTANCE_NAME` varchar(64) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(64) DEFAULT NULL,
  `JOB_GROUP` varchar(64) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;