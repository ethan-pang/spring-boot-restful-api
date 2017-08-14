/*
Navicat MySQL Data Transfer

Source Server         : 192.168.2.249
Source Server Version : 50545
Source Host           : 192.168.2.249:3306
Source Database       : cps_system

Target Server Type    : MYSQL
Target Server Version : 50545
File Encoding         : 65001

Date: 2017-08-14 17:18:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for daily_report
-- ----------------------------
DROP TABLE IF EXISTS `daily_report`;
CREATE TABLE `daily_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stats_date` date DEFAULT NULL,
  `channel` bigint(20) DEFAULT NULL,
  `stats_reg` bigint(20) DEFAULT NULL,
  `stats_dau` bigint(20) DEFAULT NULL,
  `stats_pay` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of daily_report
-- ----------------------------
INSERT INTO `daily_report` VALUES ('1', '2017-08-06', '30010123', '333', '333', '333');
INSERT INTO `daily_report` VALUES ('2', '2017-08-06', '30010126', '666', '666', '666');
INSERT INTO `daily_report` VALUES ('3', '2017-08-07', '30010123', '999', '999', '999');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `resourceType` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', 'test', 'menu', null, 'query:data', '2017-08-02 18:10:35', '2017-08-02 18:08:01');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(80) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `available` tinyint(4) DEFAULT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`role`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ROLE_ADMIN', null, null, '2017-07-27 20:59:30', '2017-08-03 14:56:07');
INSERT INTO `sys_role` VALUES ('2', 'ROLE_GUEST', null, null, '2017-07-27 20:59:41', '2017-08-04 11:32:07');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_id_group_id` (`user_id`,`role_id`) USING BTREE,
  KEY `idx_group_id` (`role_id`) USING BTREE,
  CONSTRAINT `fk_group_id` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '21', '1', '2017-08-08 14:32:39', '2017-08-08 14:30:19');
INSERT INTO `sys_user_role` VALUES ('2', '21', '2', '2017-08-08 14:51:59', '2017-08-08 14:49:38');
INSERT INTO `sys_user_role` VALUES ('3', '23', '1', '2017-08-09 17:31:40', '2017-08-09 17:29:23');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `channel` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('21', 'admin', 'admin', '$2a$10$blzLE1tOgPmu7XbAQRSzL.92MLb.fdMDxB9WMPCl0f2SVyM.uAAue', '30010123', '0', null, '2017-08-08 14:31:47', '2017-08-09 11:08:35');
INSERT INTO `user` VALUES ('23', 'test', 'test', '$2a$10$0hNLbWTmc7rflJe6H.yNq.O15bhVnQZm17qizhL7oweJlHPjeNOjq', '3001312', '0', null, '2017-08-09 17:21:15', '2017-08-09 17:31:56');
INSERT INTO `user` VALUES ('24', 'test1', 'test', '$2a$10$3IkBiEUv4gq.VWgGm8myguqofzUcXPC/54nUdhvrdyN8UAxrjtHCq', '1232132', '0', null, '2017-08-09 17:33:42', '2017-08-09 17:32:04');
