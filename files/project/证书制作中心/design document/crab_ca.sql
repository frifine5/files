/*
Navicat MySQL Data Transfer

Source Server         : 192.168.6.218
Source Server Version : 50722
Source Host           : 192.168.6.218:3306
Source Database       : crab_ca

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-03-25 14:35:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for agcrls
-- ----------------------------
DROP TABLE IF EXISTS agcrls;
CREATE TABLE agcrls (
  id bigint(20) NOT NULL,
  data longblob NOT NULL,
  rdtime datetime NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of agcrls
-- ----------------------------

-- ----------------------------
-- Table structure for certs
-- ----------------------------
DROP TABLE IF EXISTS certs;
CREATE TABLE certs (
  id bigint(20) NOT NULL,
  type tinyint(4) NOT NULL DEFAULT '0' COMMENT '0: sign, 1: enc; no else',
  dn text NOT NULL,
  prtk longblob,
  ft bigint(20) DEFAULT NULL,
  cert longblob,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of certs
-- ----------------------------

-- ----------------------------
-- Table structure for feerd
-- ----------------------------
DROP TABLE IF EXISTS feerd;
CREATE TABLE feerd (
  id bigint(20) NOT NULL,
  sn bigint(20) DEFAULT NULL,
  coe tinyint(4) NOT NULL DEFAULT '1' COMMENT 'coefficient',
  rdtime datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of feerd
-- ----------------------------

-- ----------------------------
-- Table structure for sys_info
-- ----------------------------
DROP TABLE IF EXISTS sys_info;
CREATE TABLE sys_info (
  code varchar(50) NOT NULL,
  value varchar(255) NOT NULL,
  ent varchar(255) DEFAULT NULL,
  md varchar(50) DEFAULT NULL,
   PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_info
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user (
  id int(11) NOT NULL,
  name varchar(50) NOT NULL,
  md varchar(50) NOT NULL,
  type tinyint(4) DEFAULT NULL,
  auth tinyint(4) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
