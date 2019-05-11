SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '角色名',
  `description` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `gmtTime` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='角色列表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ADMIN', '超级管理员', '2018-12-25 16:01:19', '2019-04-22 16:25:14');
INSERT INTO `sys_role` VALUES ('2', 'USER', '用户', '2018-12-27 15:47:17', '2018-12-27 15:47:27');
INSERT INTO `sys_role` VALUES ('3', 'MANAGER', '管理员', '2019-04-22 16:27:04', '2019-04-22 16:34:04');
