/*
Navicat MySQL Data Transfer

Source Server         : firstMySql
Source Server Version : 50546
Source Host           : localhost:3306
Source Database       : yayao_wxs_db

Target Server Type    : MYSQL
Target Server Version : 50546
File Encoding         : 65001

Date: 2018-05-02 10:53:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account_tb`
-- ----------------------------
DROP TABLE IF EXISTS `account_tb`;
CREATE TABLE `account_tb` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '账号Id',
  `name` varchar(100) NOT NULL COMMENT '账号名称',
  `phone` varchar(50) NOT NULL COMMENT '登录账号',
  `password` varchar(255) NOT NULL COMMENT '登录密码',
  `status` int(11) NOT NULL COMMENT '账号状态0 正常   1  异常    2  锁定',
  `role_id` int(50) NOT NULL COMMENT '角色Id(外键)',
  `role_name` varchar(100) NOT NULL COMMENT '角色名称',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`account_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `account_tb_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role_tb` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_tb
-- ----------------------------
INSERT INTO `account_tb` VALUES ('1', '邓黎光', '18774814879', '00445930eaaf54c15037d8ba9b561880', '0', '1000', '管理员', '2018-04-28 17:25:05');

-- ----------------------------
-- Table structure for `app_tb`
-- ----------------------------
DROP TABLE IF EXISTS `app_tb`;
CREATE TABLE `app_tb` (
  `pub_app_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公众号Id',
  `app_id` varchar(255) NOT NULL COMMENT '开发者账号',
  `secret` varchar(255) NOT NULL COMMENT '开发者密码',
  `app_name` varchar(100) NOT NULL COMMENT '公众号名称',
  `status` int(11) NOT NULL COMMENT '公众号状态0  正常   1  异常',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`pub_app_id`),
  UNIQUE KEY `app_id` (`app_id`),
  UNIQUE KEY `app_name` (`app_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_tb
-- ----------------------------
INSERT INTO `app_tb` VALUES ('1', 'wx4d2cb896c1256cbe', '5f3a5483468d2eec01e8fd4a8ce2b8cf', '财财是道', '0', '2018-05-02 10:01:07');


-- ----------------------------
-- Table structure for `channel_tb`
-- ----------------------------
DROP TABLE IF EXISTS `channel_tb`;
CREATE TABLE `channel_tb` (
  `channel_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '渠道Id',
  `name` varchar(100) NOT NULL COMMENT '渠道名称',
  `phone` varchar(100) NOT NULL COMMENT '渠道账号',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`channel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of channel_tb
-- ----------------------------

-- ----------------------------
-- Table structure for `role_tb`
-- ----------------------------
DROP TABLE IF EXISTS `role_tb`;
CREATE TABLE `role_tb` (
  `role_id` int(25) NOT NULL COMMENT '角色Id',
  `role_name` varchar(100) NOT NULL COMMENT '角色名称',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_tb
-- ----------------------------
INSERT INTO `role_tb` VALUES ('1000', '管理员', '2018-04-28 10:11:48');
INSERT INTO `role_tb` VALUES ('1001', '渠道主', '2018-04-28 10:12:06');

-- ----------------------------
-- Table structure for `s_info_tb`
-- ----------------------------
DROP TABLE IF EXISTS `s_info_tb`;
CREATE TABLE `s_info_tb` (
  `s_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '发送信息Id',
  `type` int(11) NOT NULL COMMENT '信息类型 0,图文类型 1文本消息',
  `title` varchar(255) NOT NULL COMMENT '信息标题',
  `description` varchar(21000) DEFAULT NULL COMMENT '信息描述',
  `s_url` varchar(255) NOT NULL COMMENT '跳转路径',
  `p_url` varchar(255) DEFAULT NULL COMMENT '封面图片路径',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_info_tb
-- ----------------------------

-- ----------------------------
-- Table structure for `s_time_tb`
-- ----------------------------
DROP TABLE IF EXISTS `s_time_tb`;
CREATE TABLE `s_time_tb` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '发送时间Id',
  `type` int(11) NOT NULL COMMENT '发送类型 0 固定时间发送  1 自定义时间发送',
  `su_time` datetime DEFAULT NULL COMMENT '固定发送时间',
  `sl_time` varchar(255) DEFAULT NULL COMMENT '自定义发送时间',
  `status` int(11) NOT NULL COMMENT '发送状态 0 未发送  1已发送',
  `s_id` int(11) NOT NULL COMMENT '发送信息Id',
  `account_id` int(11) NOT NULL COMMENT '账号Id',
  `acount_name` varchar(100) NOT NULL COMMENT '账号名称',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`t_id`),
  KEY `s_id` (`s_id`),
  KEY `account_id` (`account_id`),
  CONSTRAINT `s_time_tb_ibfk_1` FOREIGN KEY (`s_id`) REFERENCES `s_info_tb` (`s_id`),
  CONSTRAINT `s_time_tb_ibfk_2` FOREIGN KEY (`account_id`) REFERENCES `account_tb` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_time_tb
-- ----------------------------

-- ----------------------------
-- Table structure for `temp_user_tb`
-- ----------------------------
DROP TABLE IF EXISTS `temp_user_tb`;
CREATE TABLE `temp_user_tb` (
  `open_id` varchar(255) NOT NULL COMMENT '用户Id',
  `app_id` varchar(255) NOT NULL COMMENT '公众号开发者Id',
  `channel_id` int(11) NOT NULL COMMENT '渠道Id',
  `phone` varchar(50) NOT NULL COMMENT '渠道账号',
  `app_name` varchar(100) NOT NULL COMMENT '公众号名称',
  `create_date` datetime NOT NULL COMMENT '创建时间(用户点击推广链接的时间)',
  PRIMARY KEY (`open_id`,`app_id`),
  KEY `channel_id` (`channel_id`),
  KEY `app_id` (`app_id`),
  CONSTRAINT `temp_user_tb_ibfk_1` FOREIGN KEY (`channel_id`) REFERENCES `channel_tb` (`channel_id`),
  CONSTRAINT `temp_user_tb_ibfk_2` FOREIGN KEY (`app_id`) REFERENCES `app_tb` (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of temp_user_tb
-- ----------------------------

-- ----------------------------
-- Table structure for `user_details`
-- ----------------------------
DROP TABLE IF EXISTS `user_details`;
CREATE TABLE `user_details` (
  `ud_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户详情Id',
  `open_id` varchar(255) NOT NULL COMMENT '用户Id',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '用户昵称',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `language` varchar(50) DEFAULT NULL COMMENT '语言',
  `city` varchar(100) DEFAULT NULL COMMENT '城市',
  `province` varchar(100) DEFAULT NULL COMMENT '省份',
  `country` varchar(100) DEFAULT NULL COMMENT '国家',
  `head_img_url` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `subscribe_time` datetime NOT NULL COMMENT '关注时间(用户关注公众号的时间)',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`ud_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_details
-- ----------------------------

-- ----------------------------
-- Table structure for `user_tb`
-- ----------------------------
DROP TABLE IF EXISTS `user_tb`;
CREATE TABLE `user_tb` (
  `open_id` varchar(255) NOT NULL COMMENT '用户Id',
  `app_id` varchar(255) NOT NULL COMMENT '公众号开发者Id',
  `channel_id` int(11) NOT NULL COMMENT '渠道Id(0  自然量渠道Id)',
  `phone` varchar(50) NOT NULL COMMENT '渠道账号',
  `app_name` varchar(100) NOT NULL COMMENT '公众号名称',
  `status` int(11) NOT NULL COMMENT '用户状态(0 关注  1  取消关注   2  关注不活跃)',
  `show_date` datetime NOT NULL COMMENT '关注时间(用户关注公众号的时间)',
  `update_date` datetime NOT NULL COMMENT '更新时间(更新数据的时间)',
  PRIMARY KEY (`open_id`,`app_id`),
  KEY `channel_id` (`channel_id`),
  KEY `app_id` (`app_id`),
  CONSTRAINT `user_tb_ibfk_1` FOREIGN KEY (`channel_id`) REFERENCES `channel_tb` (`channel_id`),
  CONSTRAINT `user_tb_ibfk_2` FOREIGN KEY (`app_id`) REFERENCES `app_tb` (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_tb
-- ----------------------------
