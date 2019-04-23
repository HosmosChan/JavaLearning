/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : management

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2019-04-19 14:10:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `roleId` int(11) NOT NULL COMMENT '角色id',
  `permissionId` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`roleId`,`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';
