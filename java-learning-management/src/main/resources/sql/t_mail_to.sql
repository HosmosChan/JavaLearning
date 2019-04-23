/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : management

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2019-04-19 15:57:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_mail_to
-- ----------------------------
DROP TABLE IF EXISTS `t_mail_to`;
CREATE TABLE `t_mail_to` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mailId` int(11) NOT NULL COMMENT '邮件编码',
  `toUser` varchar(128) NOT NULL COMMENT '接收人',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1成功，0失败',
  `createTime` datetime NOT NULL COMMENT '创建时间'
  PRIMARY KEY (`id`),
  KEY `index_mailId` (`mailId`),
  KEY `index_toUser` (`toUser`)
  KEY `index_createTime` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮件接收表';
