SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parentId` int(11) NOT NULL COMMENT '父类id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单名',
  `css` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图标编号',
  `href` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
  `type` tinyint(1) NOT NULL COMMENT '类型',
  `permission` varchar(50) CHARACTER SET utf8mb4mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限',
  `sort` int(11) NOT NULL COMMENT '队列顺序',
  PRIMARY KEY (`id`),
  KEY `index_parentId` (`parentId`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COMMENT='菜单管理表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '0', '用户管理', 'fa-users', 'pages/user/userList.html', '1', '', '1');
INSERT INTO `sys_permission` VALUES ('2', '1', '用户查询', 'fa-user', 'pages/user/userList.html', '1', '', '1');
INSERT INTO `sys_permission` VALUES ('3', '2', '查询', '', '', '2', 'sys:user:query', '1');
INSERT INTO `sys_permission` VALUES ('4', '2', '新增', '', '', '2', 'sys:user:add', '2');
INSERT INTO `sys_permission` VALUES ('5', '1', '修改密码', 'fa-pencil-square-o', 'pages/user/changePassword.html', '1', 'sys:user:password', '2');
INSERT INTO `sys_permission` VALUES ('6', '0', '系统设置', 'fa-gears', '', '1', '', '2');
INSERT INTO `sys_permission` VALUES ('7', '6', '菜单', 'fa-cog', 'pages/menu/menuList.html', '1', '', '1');
INSERT INTO `sys_permission` VALUES ('8', '7', '查询', '', '', '2', 'sys:menu:query', '1');
INSERT INTO `sys_permission` VALUES ('9', '7', '新增', '', '', '2', 'sys:menu:add', '2');
INSERT INTO `sys_permission` VALUES ('10', '7', '删除', '', '', '2', 'sys:menu:del', '3');
INSERT INTO `sys_permission` VALUES ('11', '6', '角色', 'fa-user-secret', 'pages/role/roleList.html', '1', '', '2');
INSERT INTO `sys_permission` VALUES ('12', '11', '查询', '', '', '2', 'sys:role:query', '1');
INSERT INTO `sys_permission` VALUES ('13', '11', '新增', '', '', '2', 'sys:role:add', '2');
INSERT INTO `sys_permission` VALUES ('14', '11', '删除', '', '', '2', 'sys:role:del', '3');
INSERT INTO `sys_permission` VALUES ('15', '0', '文件管理', 'fa-folder-open', 'pages/file/fileList.html', '1', '', '3');
INSERT INTO `sys_permission` VALUES ('16', '15', '查询', '', '', '2', 'sys:file:query', '1');
INSERT INTO `sys_permission` VALUES ('17', '15', '删除', '', '', '2', 'sys:file:del', '2');
INSERT INTO `sys_permission` VALUES ('18', '0', '数据源监控', 'fa-eye', 'druid/index.html', '1', '', '4');
INSERT INTO `sys_permission` VALUES ('19', '0', '接口swagger', 'fa-file-pdf-o', 'swagger-ui.html', '1', '', '5');
INSERT INTO `sys_permission` VALUES ('20', '0', '代码生成', 'fa-wrench', 'pages/generate/editGenerate.html', '1', 'generate:edit', '6');
INSERT INTO `sys_permission` VALUES ('21', '0', '公告管理', 'fa-book', 'pages/notice/noticeList.html', '1', '', '7');
INSERT INTO `sys_permission` VALUES ('22', '21', '查询', '', '', '2', 'notice:query', '1');
INSERT INTO `sys_permission` VALUES ('23', '21', '添加', '', '', '2', 'notice:add', '2');
INSERT INTO `sys_permission` VALUES ('24', '21', '删除', '', '', '2', 'notice:del', '3');
INSERT INTO `sys_permission` VALUES ('25', '0', '日志查询', 'fa-reorder', 'pages/log/logList.html', '1', 'sys:log:query', '8');
INSERT INTO `sys_permission` VALUES ('26', '0', '邮件管理', 'fa-envelope', 'pages/mail/mailList.html', '1', '', '9');
INSERT INTO `sys_permission` VALUES ('27', '26', '发送邮件', '', '', '2', 'mail:send', '1');
INSERT INTO `sys_permission` VALUES ('28', '26', '查询', '', '', '2', 'mail:all:query', '2');
INSERT INTO `sys_permission` VALUES ('29', '0', '定时任务管理', 'fa-tasks', 'pages/job/jobList.html', '1', '', '10');
INSERT INTO `sys_permission` VALUES ('30', '29', '查询', '', '', '2', 'job:query', '1');
INSERT INTO `sys_permission` VALUES ('31', '29', '新增', '', '', '2', 'job:add', '2');
INSERT INTO `sys_permission` VALUES ('32', '29', '删除', '', '', '2', 'job:del', '3');
INSERT INTO `sys_permission` VALUES ('33', '0', 'excel导出', 'fa-arrow-circle-down', 'pages/excel/sql.html', '1', '', '11');
INSERT INTO `sys_permission` VALUES ('34', '33', '导出', '', '', '2', 'excel:down', '1');
INSERT INTO `sys_permission` VALUES ('35', '33', '页面显示数据', '', '', '2', 'excel:show:datas', '2');
INSERT INTO `sys_permission` VALUES ('36', '0', '字典管理', 'fa-reddit', 'pages/dict/dictList.html', '1', '', '12');
INSERT INTO `sys_permission` VALUES ('37', '36', '查询', '', '', '2', 'dict:query', '1');
INSERT INTO `sys_permission` VALUES ('38', '36', '新增', '', '', '2', 'dict:add', '2');
INSERT INTO `sys_permission` VALUES ('39', '36', '删除', '', '', '2', 'dict:del', '3');
