/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-09-17 11:13:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for studentinfotbl
-- ----------------------------
DROP TABLE IF EXISTS `studentinfotbl`;
CREATE TABLE `studentinfotbl` (
  `stuId` int(11) NOT NULL AUTO_INCREMENT,
  `stuNo` varchar(20) DEFAULT NULL,
  `stuName` varchar(10) DEFAULT NULL,
  `sex` varchar(5) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `gradeId` int(11) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `stuDesc` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`stuId`),
  KEY `gradeId` (`gradeId`),
  CONSTRAINT `gradeId` FOREIGN KEY (`gradeId`) REFERENCES `classtbl` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of studentinfotbl
-- ----------------------------
INSERT INTO `studentinfotbl` VALUES ('1', '120610101', '张三', '男', '1993-01-01', '1', '120610101@qq.com', 'good student');
INSERT INTO `studentinfotbl` VALUES ('2', '120610102', '张四', '男', '1993-01-03', '1', '120610102@qq.com', 'good student');
INSERT INTO `studentinfotbl` VALUES ('3', '120610201', '李四', '女', '1993-01-01', '2', '120610103@qq.com', 'good student');
INSERT INTO `studentinfotbl` VALUES ('4', '120610202', '王五', '男', '1993-02-04', '2', '120610104@qq.com', 'good student');
INSERT INTO `studentinfotbl` VALUES ('5', '120610203', '小米', '女', '1993-01-01', '2', '120610105@qq.com', 'good student');
INSERT INTO `studentinfotbl` VALUES ('6', '120610106', '小明', '男', '1993-01-01', '1', '120610106@qq.com', 'good student');
INSERT INTO `studentinfotbl` VALUES ('7', '120610107', '小马', '女', '1993-01-01', '1', '120610107@qq.com', 'good student');
INSERT INTO `studentinfotbl` VALUES ('8', '120610108', '小康', '男', '1993-01-01', '1', '120610108@qq.com', 'good student');
INSERT INTO `studentinfotbl` VALUES ('9', '120610109', '小鸟', '女', '1993-01-01', '1', '120610109@qq.com', 'good student');
INSERT INTO `studentinfotbl` VALUES ('10', '120610110', '许嵩', '男', '1993-01-01', '1', '120610110@qq.com', 'good student');
INSERT INTO `studentinfotbl` VALUES ('11', '120610111', '周杰伦', '男', '1993-01-01', '1', '120610111@qq.com', 'good student');
INSERT INTO `studentinfotbl` VALUES ('12', '120610112', '周润发', '男', '1993-01-01', '1', '120610112@qq.com', 'good student');
INSERT INTO `studentinfotbl` VALUES ('13', '120610113', '赵本山', '男', '1993-01-01', '1', '120610113@qq.com', 'good student');
