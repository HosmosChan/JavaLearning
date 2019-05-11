SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_logs
-- ----------------------------
DROP TABLE IF EXISTS `sys_logs`;
CREATE TABLE `sys_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userId` int(11) NOT NULL COMMENT '用户id',
  `module` varchar(50) DEFAULT NULL COMMENT '模块名',
  `flag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '成功/失败',
  `remark` text COMMENT '备注',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `index_userId` (`userId`),
  KEY `index_createTime` (`createTime`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COMMENT='系统日志';

CREATE TRIGGER `auto_insert_reportform_management_logs` AFTER INSERT ON `sys_logs` FOR EACH ROW begin
insert into reportform.management_logs select * from management.sys_logs where id=new.id;
end;