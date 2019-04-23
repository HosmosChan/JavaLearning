/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : management

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2019-04-19 14:10:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_point
-- ----------------------------
DROP TABLE IF EXISTS `t_point`;
CREATE TABLE `t_point` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userId` int(11) NOT NULL COMMENT '用户id',
  `pointAmount` bigint(255) NOT NULL DEFAULT '0' COMMENT '用户积分',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `gmtTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `index_userId` (`userId`),
  KEY `index_createTime` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分表';
