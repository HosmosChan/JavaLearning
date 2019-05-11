SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_mail
-- ----------------------------
DROP TABLE IF EXISTS `t_mail`;
CREATE TABLE `t_mail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userId` int(11) NOT NULL COMMENT '发送人',
  `subject` varchar(255) CHARACTER SET utf8mb4mb4 COLLATE utf8mb4mb4_general_ci NOT NULL COMMENT '标题',
  `content` longtext CHARACTER SET utf8mb4mb4 COLLATE utf8mb4mb4_general_ci NOT NULL COMMENT '正文',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `gmtTime` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `index_userId` (`userId`),
  KEY `index_createTime` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='邮件发送信息表';
