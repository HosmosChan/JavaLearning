SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(64) NOT NULL,
  `INSTANCE_NAME` varchar(128) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('adminQuartzScheduler', 'localhost.localdomain1544173386533', '1545877561753', '15000');
INSERT INTO `qrtz_scheduler_state` VALUES ('DefaultQuartzScheduler', 'UA-chenhuayang1555570864247', '1555640740339', '15000');
