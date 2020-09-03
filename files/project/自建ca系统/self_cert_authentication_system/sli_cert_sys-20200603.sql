/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : sli_cert_sys

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2020-06-03 14:27:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cert_db_rel`
-- ----------------------------
DROP TABLE IF EXISTS `cert_db_rel`;
CREATE TABLE `cert_db_rel` (
  `ID` varchar(255) NOT NULL,
  `UUID` varchar(50) DEFAULT NULL,
  `SIGN_SERIAL` varchar(100) DEFAULT NULL,
  `ENC_SERIAL` varchar(100) DEFAULT NULL,
  `PRT_ALG` varchar(10) DEFAULT NULL,
  `EPK_PRT_DATA` mediumblob,
  `RD_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of cert_db_rel
-- ----------------------------

-- ----------------------------
-- Table structure for `cert_revoke_rnd`
-- ----------------------------
DROP TABLE IF EXISTS `cert_revoke_rnd`;
CREATE TABLE `cert_revoke_rnd` (
  `ID` bigint(20) NOT NULL,
  `SERIAL_NUMBER` varchar(100) DEFAULT NULL,
  `PRE_STS` tinyint(2) DEFAULT NULL,
  `REV_MSG` varchar(100) DEFAULT NULL,
  `REV_DATE` date DEFAULT NULL,
  `RD_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of cert_revoke_rnd
-- ----------------------------

-- ----------------------------
-- Table structure for `cert_rnd`
-- ----------------------------
DROP TABLE IF EXISTS `cert_rnd`;
CREATE TABLE `cert_rnd` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `SERIAL_NUMBER` varchar(100) DEFAULT NULL,
  `CERT_TYPE` tinyint(2) DEFAULT NULL,
  `ALG_TYPE` varchar(10) DEFAULT NULL,
  `DN` longtext,
  `VALID_ST` datetime DEFAULT NULL,
  `VALID_END` datetime DEFAULT NULL,
  `CER_DATA` mediumblob,
  `RD_TIME` datetime DEFAULT NULL,
  `STATUS` tinyint(2) DEFAULT NULL,
  `PRE_SERIAL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

 
-- ----------------------------
-- Table structure for `rent_info`
-- ----------------------------
DROP TABLE IF EXISTS `rent_info`;
CREATE TABLE `rent_info` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `UID` varchar(50) DEFAULT NULL,
  `UNAME` varchar(50) DEFAULT NULL,
  `PIN` varchar(20) DEFAULT NULL,
  `RD_TIME` datetime DEFAULT NULL,
  `LIM_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of rent_info
-- ----------------------------
INSERT INTO `rent_info` VALUES ('1', '1', '测试GM', '88998899', '2020-05-31 21:34:03', '2025-12-31 21:34:08');

-- ----------------------------
-- Table structure for `rent_proj_rd`
-- ----------------------------
DROP TABLE IF EXISTS `rent_proj_rd`;
CREATE TABLE `rent_proj_rd` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `UID` varchar(50) DEFAULT NULL,
  `PRJ_ID` varchar(50) DEFAULT NULL,
  `ALG_TYPE` varchar(10) DEFAULT NULL,
  `AUTH_TYPE` varchar(10) DEFAULT NULL,
  `AUTH_PUK` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `RD_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of rent_proj_rd
-- ----------------------------
INSERT INTO `rent_proj_rd` VALUES ('1', '1', '1', 'sm2', 'sm2_sign', 'MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEo16YR8dyI3GopR+Pr4fy4WWJx5kjHUgK7wpxJ7niDOPRxEK+kFm5Divs5IXedaAV0lJc1xNHVvKqI3RzhJxmGw==', '2020-05-31 21:35:19');

-- ----------------------------
-- Table structure for `req_data_rnd`
-- ----------------------------
DROP TABLE IF EXISTS `req_data_rnd`;
CREATE TABLE `req_data_rnd` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `UID` varchar(50) DEFAULT NULL,
  `REQ_DATA` mediumblob,
  `BIZ_TYPE` tinyint(2) DEFAULT NULL,
  `CERT_TYPE` tinyint(2) DEFAULT NULL,
  `REQ_ID` varchar(50) DEFAULT NULL,
  `CODE` varchar(2) DEFAULT NULL,
  `ERR_MSG` varchar(255) DEFAULT NULL,
  `RD_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


