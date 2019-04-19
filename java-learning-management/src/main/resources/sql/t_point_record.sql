/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : management

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2019-04-19 14:10:04
*/

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分增减记录表';

-- ----------------------------
-- Records of t_point_record
-- ----------------------------
