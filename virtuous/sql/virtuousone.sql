/*
Navicat MySQL Data Transfer

Source Server         : 127
Source Server Version : 50715
Source Host           : 127.0.0.1:3306
Source Database       : nanhaidetianzhi

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2017-03-21 17:32:12
*/

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
CREATE TABLE `user_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `qq` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'virtuousOne','2812927266@qq.com','2812927266','2019-01-05 09:12:13','2019-01-05 09:12:13');

