SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_point_record
-- ----------------------------
DROP TABLE IF EXISTS `t_point_record`;
CREATE TABLE `t_point_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userId` int(11) NOT NULL COMMENT '用户id',
  `pointChange` bigint(20) NOT NULL COMMENT '积分变更数值',
  `status` tinyint(255) NOT NULL COMMENT '变更状态(-1为减，1为增，0为无效)',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `index_userId` (`userId`),
  KEY `index_status` (`status`),
  KEY `index_createTime` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='积分增减记录表';
