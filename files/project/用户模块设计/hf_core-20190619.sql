/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : hf_core

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-06-19 15:14:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `hf_equ_chlso`
-- ----------------------------
DROP TABLE IF EXISTS `hf_equ_chlso`;
CREATE TABLE `hf_equ_chlso` (
  `chl_id` bigint(20) unsigned NOT NULL,
  `file_dir` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `chl_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `chl_symc` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`chl_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hf_equ_chlso
-- ----------------------------
INSERT INTO `hf_equ_chlso` VALUES ('2187614052417536', '/app/usr/package/2019-02', 'MTIzNDU2', null);
INSERT INTO `hf_equ_chlso` VALUES ('2190713936674816', '/app/usr/package/2019-02', 'MTIzNDU2', null);

-- ----------------------------
-- Table structure for `hf_equ_info`
-- ----------------------------
DROP TABLE IF EXISTS `hf_equ_info`;
CREATE TABLE `hf_equ_info` (
  `geid` bigint(20) NOT NULL,
  `equ_nick` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `guid` bigint(20) DEFAULT NULL,
  `udid` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `imei` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `imsi` varchar(16) COLLATE utf8_bin DEFAULT NULL,
  `equ_mfac_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `equ_model` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `equ_os_version` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `equ_os_ver_num` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `recent_time` datetime DEFAULT NULL,
  `recent_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `chl_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`geid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hf_equ_info
-- ----------------------------
INSERT INTO `hf_equ_info` VALUES ('2242028062638080', '测试', '1234567890', null, null, null, '制造商', 'HUAWEI Mate20', 'Android', '6.0', '2019-02-18 13:56:38', null, '0');
INSERT INTO `hf_equ_info` VALUES ('2242952126529536', '测试', '1234567890', null, null, null, '制造商', 'HUAWEI Mate20', 'Android', '6.0', '2019-02-18 00:00:00', null, '0');
INSERT INTO `hf_equ_info` VALUES ('2243636733411328', '测试', '1234567890', null, null, null, '制造商', 'HUAWEI Mate20', 'Android', '6.0', '2019-02-18 14:03:02', null, '0');
INSERT INTO `hf_equ_info` VALUES ('2243984856449024', '测试', '1234567890', null, null, null, '制造商', 'HUAWEI Mate20', 'Android', '6.0', '2019-02-18 14:04:25', null, '0');
INSERT INTO `hf_equ_info` VALUES ('2244231447969792', '测试', '1234567890', null, null, null, '制造商', 'HUAWEI Mate20', 'Android', '6.0', '2019-02-18 14:05:24', null, '0');

-- ----------------------------
-- Table structure for `hf_usr_alg_sm2`
-- ----------------------------
DROP TABLE IF EXISTS `hf_usr_alg_sm2`;
CREATE TABLE `hf_usr_alg_sm2` (
  `guid` bigint(20) NOT NULL,
  `geid` bigint(20) NOT NULL,
  `key_type` tinyint(1) NOT NULL,
  `key_prvk` varchar(120) COLLATE utf8_bin DEFAULT NULL,
  `key_symc` varchar(240) COLLATE utf8_bin DEFAULT NULL,
  `key_puk` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `cert` text COLLATE utf8_bin,
  PRIMARY KEY (`guid`,`geid`,`key_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hf_usr_alg_sm2
-- ----------------------------

-- ----------------------------
-- Table structure for `hf_usr_alg_sm9`
-- ----------------------------
DROP TABLE IF EXISTS `hf_usr_alg_sm9`;
CREATE TABLE `hf_usr_alg_sm9` (
  `guid` bigint(20) NOT NULL,
  `geid` bigint(20) NOT NULL,
  `key_type` tinyint(1) NOT NULL,
  `key_prvk` varchar(120) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `key_symc` varchar(240) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `key_puk` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `key_mpk` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`guid`,`geid`,`key_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hf_usr_alg_sm9
-- ----------------------------

-- ----------------------------
-- Table structure for `hf_usr_info`
-- ----------------------------
DROP TABLE IF EXISTS `hf_usr_info`;
CREATE TABLE `hf_usr_info` (
  `guid` bigint(20) NOT NULL,
  `usr_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `usr_type` tinyint(1) DEFAULT NULL COMMENT '0:else; 1:person; 2:enterprise; 3:org',
  PRIMARY KEY (`guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of hf_usr_info
-- ----------------------------
INSERT INTO `hf_usr_info` VALUES ('1112000935493632', 'test', '1');

-- ----------------------------
-- Table structure for `user_auth`
-- ----------------------------
DROP TABLE IF EXISTS `user_auth`;
CREATE TABLE `user_auth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `identity_type` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '1手机号 2邮箱 3用户名 4qq 5微信 6腾讯微博 7新浪微博',
  `identifier` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '手机号 邮箱 用户名或第三方应用的唯一标识',
  `certificate` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '密码凭证(站内的保存密码，站外的不保存或保存token)',
  `create_time` datetime DEFAULT NULL COMMENT '绑定时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新绑定时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `only` (`uid`,`identity_type`),
  KEY `idx_uid` (`uid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user_auth
-- ----------------------------

-- ----------------------------
-- Table structure for `user_base`
-- ----------------------------
DROP TABLE IF EXISTS `user_base`;
CREATE TABLE `user_base` (
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `user_role` tinyint(2) unsigned NOT NULL DEFAULT '2' COMMENT '2正常用户 3禁言用户 4虚拟用户 5运营',
  `register_source` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '注册来源：1手机号 2邮箱 3用户名 4qq 5微信 6腾讯微博 7新浪微博',
  `user_name` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户账号，必须唯一',
  `nick_name` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户昵称',
  `gender` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '用户性别 0-female 1-male',
  `birthday` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户生日',
  `signature` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户个人签名',
  `mobile` varchar(16) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '手机号码(唯一)',
  `mobile_bind_time` datetime DEFAULT NULL COMMENT '手机号码绑定时间',
  `email` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '邮箱(唯一)',
  `email_bind_time` datetime DEFAULT NULL COMMENT '邮箱绑定时间',
  `face` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '头像',
  `face200` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '头像 200x200x80',
  `srcface` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '原图头像',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `push_token` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '用户设备push_token',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user_base
-- ----------------------------

-- ----------------------------
-- Table structure for `user_extra`
-- ----------------------------
DROP TABLE IF EXISTS `user_extra`;
CREATE TABLE `user_extra` (
  `uid` bigint(20) NOT NULL COMMENT '用户 ID',
  `vendor` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '手机厂商：apple|htc|samsung，很少用',
  `client_name` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '客户端名称，如hjskang',
  `client_version` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '客户端版本号，如7.0.1',
  `os_name` varchar(16) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '设备号:android|ios',
  `os_version` varchar(16) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '系统版本号:2.2|2.3|4.0|5.1',
  `device_name` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '设备型号，如:iphone6s、u880、u8800',
  `device_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '设备ID',
  `idfa` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '苹果设备的IDFA',
  `idfv` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '苹果设备的IDFV',
  `market` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '来源',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `extend1` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '扩展字段1',
  `extend2` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '扩展字段2',
  `extend3` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '扩展字段3',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user_extra
-- ----------------------------

-- ----------------------------
-- Table structure for `user_info_update`
-- ----------------------------
DROP TABLE IF EXISTS `user_info_update`;
CREATE TABLE `user_info_update` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `uid` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `attribute_name` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '属性名',
  `attribute_old_val` varchar(30) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '属性对应旧的值',
  `attribute_new_val` varchar(30) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '属性对应新的值',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user_info_update
-- ----------------------------

-- ----------------------------
-- Table structure for `user_location`
-- ----------------------------
DROP TABLE IF EXISTS `user_location`;
CREATE TABLE `user_location` (
  `uid` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `curr_nation` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '所在地国',
  `curr_province` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '所在地省',
  `curr_city` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '所在地市',
  `curr_district` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '所在地地区',
  `location` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '具体地址',
  `longitude` decimal(10,6) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(10,6) DEFAULT NULL COMMENT '纬度',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user_location
-- ----------------------------

-- ----------------------------
-- Table structure for `user_login_log`
-- ----------------------------
DROP TABLE IF EXISTS `user_login_log`;
CREATE TABLE `user_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户uid',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '登录方式 第三方/邮箱/手机等',
  `command` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '操作类型 1登录成功  2登出成功 3登录失败 4登出失败',
  `version` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '1.0' COMMENT '客户端版本号',
  `client` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT 'dabaozha' COMMENT '客户端',
  `device_id` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '登录时设备号',
  `lastip` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '登录ip',
  `os` varchar(16) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '手机系统',
  `osver` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '系统版本',
  `text` varchar(200) COLLATE utf8_bin NOT NULL DEFAULT '',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`),
  KEY `idx_uid_type_time` (`uid`,`type`,`create_time`) USING BTREE,
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for `user_register_log`
-- ----------------------------
DROP TABLE IF EXISTS `user_register_log`;
CREATE TABLE `user_register_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `uid` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `register_method` tinyint(2) unsigned NOT NULL COMMENT '注册方式1手机号 2邮箱 3用户名 4qq 5微信 6腾讯微博 7新浪微博',
  `register_time` int(11) NOT NULL COMMENT '注册时间',
  `register_ip` varchar(16) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '注册IP',
  `register_client` varchar(16) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '注册客户端',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user_register_log
-- ----------------------------
