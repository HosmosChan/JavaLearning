SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `roleId` int(11) NOT NULL COMMENT '角色id',
  `permissionId` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`roleId`,`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';
