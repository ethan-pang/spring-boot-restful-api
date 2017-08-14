/*
Navicat MySQL Data Transfer

Source Server         : 192.168.2.249
Source Server Version : 50545
Source Host           : 192.168.2.249:3306
Source Database       : cps_system

Target Server Type    : MYSQL
Target Server Version : 50545
File Encoding         : 65001

Date: 2017-08-01 15:07:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for groups
-- ----------------------------
DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of groups
-- ----------------------------
INSERT INTO `groups` VALUES ('1', 'admin', '2017-07-27 20:59:30', '2017-07-27 20:56:43');
INSERT INTO `groups` VALUES ('2', 'guest', '2017-07-27 20:59:41', '2017-07-27 20:56:55');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '53922743a1b784159e4778cff1648cbce2feb833', 'admin', '2017-07-27 20:58:48', '2017-07-27 20:58:53', '2017-07-27 20:56:08');
INSERT INTO `user` VALUES ('2', 'guest', '4a983c468c7c422eba65b535110a8aa68454c7a9', 'guest', '2017-07-27 20:59:16', '2017-07-27 20:59:18', '2017-07-27 20:56:32');

-- ----------------------------
-- Table structure for user_groups
-- ----------------------------
DROP TABLE IF EXISTS `user_groups`;
CREATE TABLE `user_groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_id_group_id` (`user_id`,`group_id`) USING BTREE,
  KEY `idx_group_id` (`group_id`) USING BTREE,
  CONSTRAINT `fk_group_id` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_groups
-- ----------------------------
INSERT INTO `user_groups` VALUES ('1', '1', '1', '2017-07-27 20:59:52', '2017-07-27 20:57:04');
INSERT INTO `user_groups` VALUES ('2', '2', '2', '2017-07-27 20:59:59', '2017-07-27 20:57:11');
