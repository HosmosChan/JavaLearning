SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户登录名',
  `password` varchar(32) NOT NULL COMMENT '用户密码',
  `salt` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码签名密钥',
  `nickName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户昵称',
  `headImgUrl` varchar(255) DEFAULT NULL COMMENT '用户头像Url',
  `phone` varchar(11) DEFAULT NULL COMMENT '用户电话',
  `telephone` varchar(11) DEFAULT NULL COMMENT '用户手机',
  `email` varchar(50) DEFAULT NULL COMMENT '用户Email',
  `birthday` date DEFAULT NULL COMMENT '用户生日',
  `sex` tinyint(1) DEFAULT NULL COMMENT '用户性别',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '用户状态',
  `lastLoginTime` datetime COMMENT '最后登录时间',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `gmtTime` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `index_username` (`username`),
  KEY `index_status` (`status`),
  KEY `index_lastLoginTime` (`lastLoginTime`),
  KEY `index_gmtTime` (`gmtTime`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户列表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'Hosmos', 'd21b17bf5e282557c88e972bc933ca1c', 'd28bdac96883bb09c41034617c9bf0b6', 'Hosmos', '', '', '', '375938415@qq.com', '1996-03-21', '1', '1', '2019-05-08 10:16:27', '2018-12-03 15:19:13', '2019-02-25 16:52:44');
