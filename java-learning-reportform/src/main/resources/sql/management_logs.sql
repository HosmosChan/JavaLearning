SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for management_logs
-- ----------------------------
DROP TABLE IF EXISTS `management_logs`;
CREATE TABLE `management_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userId` int(11) NOT NULL COMMENT '用户id',
  `module` varchar(50) DEFAULT NULL COMMENT '模块名',
  `flag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '成功/失败',
  `remark` text COMMENT '备注',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `index_userId` (`userId`),
  KEY `index_createTime` (`createTime`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8 COMMENT='后台管理系统日志';