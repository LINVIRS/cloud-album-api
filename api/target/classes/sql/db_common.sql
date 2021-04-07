/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50709
 Source Host           : localhost:3306
 Source Schema         : db_common

 Target Server Type    : MySQL
 Target Server Version : 50709
 File Encoding         : 65001

 Date: 23/10/2018 16:06:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `pk_id` int(11) NOT NULL DEFAULT '' AUTO_INCREMENT,
  `account` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `state` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `delete_flag` int(11) NOT NULL DEFAULT 0,
  `create_time` timestamp(3) NULL DEFAULT NULL,
  `update_time` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`pk_id`) USING BTREE,
  INDEX `uni_account`(`account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '13704825983', '', '0', 0, '2018-10-17 10:23:20.609', '2018-10-17 10:23:20.609');
INSERT INTO `t_user` VALUES (2, '13704821302', '', '0', 0, '2018-10-17 09:48:18.913', '2018-10-17 09:48:18.913');
INSERT INTO `t_user` VALUES (4, '13704823697', '', '0', 0, '2018-10-17 10:05:33.077', '2018-10-17 10:05:33.077');
INSERT INTO `t_user` VALUES (5, '13704820585', '', '0', 0, '2018-10-17 09:43:44.583', '2018-10-17 09:43:44.583');
INSERT INTO `t_user` VALUES (6, '13704822831', '', '0', 0, '2018-10-17 09:59:00.656', '2018-10-17 09:59:00.656');
INSERT INTO `t_user` VALUES (7, '13704821166', '', '0', 0, '2018-10-17 09:47:28.223', '2018-10-17 09:47:28.223');
INSERT INTO `t_user` VALUES (8, '13704820920', '', '0', 0, '2018-10-17 09:45:51.332', '2018-10-17 09:45:51.332');
INSERT INTO `t_user` VALUES (9, '13704825354', '', '0', 0, '2018-10-17 10:18:23.106', '2018-10-17 10:18:23.106');
INSERT INTO `t_user` VALUES (10, '13704822968', '', '0', 0, '2018-10-17 10:00:06.305', '2018-10-17 10:00:06.305');
INSERT INTO `t_user` VALUES (11, '13704823225', '', '0', 0, '2018-10-17 10:02:02.983', '2018-10-17 10:02:02.983');
INSERT INTO `t_user` VALUES (12, '18851190054', '', '0', 0, '2018-10-17 10:06:57.938', '2018-10-17 10:06:57.938');
INSERT INTO `t_user` VALUES (16, '18851193089', '', '0', 0, '2018-10-23 15:23:47.222', '2018-10-23 15:23:47.222');

-- ----------------------------
-- Table structure for t_user_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info`  (
  `pk_id` int(11) NOT NULL DEFAULT '' AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `nickname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `industry` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `sexual` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `delete_flag` int(11) NOT NULL DEFAULT 0,
  `create_time` timestamp(3) NULL DEFAULT NULL,
  `update_time` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`pk_id`) USING BTREE,
  INDEX `uni_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_info
-- ----------------------------
INSERT INTO `t_user_info` VALUES (1, 1, '26d8344f65e74775813acc7017d452ff', '新用户_137****0265', '', 'male', 0, '2018-10-17 09:41:50.011', '2018-10-17 09:41:50.011');
INSERT INTO `t_user_info` VALUES (2, 2, '19c623b0dd484714ad76c63a91fc47b0', '新用户_137****0036', '', 'male', 0, '2018-10-17 09:40:34.506', '2018-10-17 09:40:34.506');
INSERT INTO `t_user_info` VALUES (3, 3, '09cd65d1b6714c2da93a9628d2b40b0d', '新用户_137****0542', '', 'male', 0, '2018-10-17 09:43:29.197', '2018-10-17 09:43:29.197');
INSERT INTO `t_user_info` VALUES (4, 4, '3da5a06c55b1493ebfd68b1fb8cd7e87', '新用户_137****4604', '', 'male', 0, '2018-10-17 10:12:17.197', '2018-10-17 10:12:17.197');
INSERT INTO `t_user_info` VALUES (6, 6, 'd445ba20a58e44ee96621aca49f18d47', '新用户_137****0349', '', 'male', 0, '2018-10-17 09:42:16.495', '2018-10-17 09:42:16.495');
INSERT INTO `t_user_info` VALUES (7, 7, '0488558225b14052820e36c9cb1389a1', '新用户_137****3946', '', 'male', 0, '2018-10-17 10:07:19.268', '2018-10-17 10:07:19.268');
INSERT INTO `t_user_info` VALUES (8, 8, '5210869ab194479badf2c2245e3eb033', '新用户_137****3167', '', 'male', 0, '2018-10-17 10:01:34.904', '2018-10-17 10:01:34.904');
INSERT INTO `t_user_info` VALUES (9, 9, '235cbddcbf7e4d06a7449a3def255531', '新用户_137****4317', '', 'male', 0, '2018-10-17 10:10:10.256', '2018-10-17 10:10:10.256');
INSERT INTO `t_user_info` VALUES (10, 10, '925e1420138740a8bc146ef249a4d266', '新用户_137****6380', '', 'male', 0, '2018-10-17 10:26:23.105', '2018-10-17 10:26:23.105');
INSERT INTO `t_user_info` VALUES (11, 11, '09cd65d1b6714c2da93a9628d2b40b0d', '新用户_137****4386', '', 'male', 0, '2018-10-17 10:10:40.272', '2018-10-17 10:10:40.272');
INSERT INTO `t_user_info` VALUES (12, 12, 'd445ba20a58e44ee96621aca49f18d47', '新用户_137****9895', '', 'male', 0, '2018-10-17 09:39:51.824', '2018-10-17 09:39:51.824');
INSERT INTO `t_user_info` VALUES (15, 16, 'd445ba20a58e44ee96621aca49f18d47', '新用户_XXX', NULL, 'male', 0, '2018-10-23 15:23:47.222', '2018-10-23 15:23:47.222');

SET FOREIGN_KEY_CHECKS = 1;
