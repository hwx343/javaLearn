/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-09-17 11:14:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for classtbl
-- ----------------------------
DROP TABLE IF EXISTS `classtbl`;
CREATE TABLE `classtbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `className` varchar(255) DEFAULT NULL,
  `classDesc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classtbl
-- ----------------------------
INSERT INTO `classtbl` VALUES ('1', '1206101', '12级应用数学1班');
INSERT INTO `classtbl` VALUES ('2', '1206102', '12级应用数学2班');
INSERT INTO `classtbl` VALUES ('3', '1206201', '12级信息计算1班');
INSERT INTO `classtbl` VALUES ('4', '1206202', '12级信息计算2班');
INSERT INTO `classtbl` VALUES ('5', '1306101', '13级应用数学1班');
INSERT INTO `classtbl` VALUES ('6', '1306102', '13级应用数学2班');
INSERT INTO `classtbl` VALUES ('7', '1306201', '13级信息计算1班');
INSERT INTO `classtbl` VALUES ('8', '1306202', '13级信息计算2班');
INSERT INTO `classtbl` VALUES ('16', '1406101', '2014数学1班');
INSERT INTO `classtbl` VALUES ('17', '1406102', '2014级数学2班');
