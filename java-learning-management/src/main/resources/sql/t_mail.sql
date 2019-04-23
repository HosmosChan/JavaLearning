/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : management

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2019-04-19 15:57:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_mail
-- ----------------------------
DROP TABLE IF EXISTS `t_mail`;
CREATE TABLE `t_mail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userId` int(11) NOT NULL COMMENT '发送人',
  `subject` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '正文',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `gmtTime` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `index_userId` (`userId`),
  KEY `index_createTime` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮件发送信息表';
