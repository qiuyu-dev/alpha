/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : alpha

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 15/07/2020 09:27:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu`;
CREATE TABLE `admin_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name_zh` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon_cls` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `component` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_menu
-- ----------------------------
INSERT INTO `admin_menu` VALUES (1, '/admin', 'AdminIndex', '首页', 'el-icon-s-home', 'AdminIndex', 0);
INSERT INTO `admin_menu` VALUES (2, '/admin/dashboard', 'DashboardAdmin', '运行情况', NULL, 'dashboard/admin/index', 1);
INSERT INTO `admin_menu` VALUES (3, '/admin', 'User', '用户管理', 'el-icon-user', 'AdminIndex', 0);
INSERT INTO `admin_menu` VALUES (4, '/admin', 'Content', '内容管理', 'el-icon-tickets', 'AdminIndex', 0);
INSERT INTO `admin_menu` VALUES (5, '/admin', 'System', '系统配置', 'el-icon-s-tools', 'AdminIndex', 0);
INSERT INTO `admin_menu` VALUES (6, '/admin/user/profile', 'Profile', '用户信息', NULL, 'user/UserProfile', 3);
INSERT INTO `admin_menu` VALUES (7, '/admin/user/role', 'Role', '角色配置', NULL, 'user/Role', 3);
INSERT INTO `admin_menu` VALUES (11, '/admin/content/customerorder', 'CustomerOrderManagement', '客户单上传', NULL, 'content/CustomerOrderManagement', 4);

-- ----------------------------
-- Table structure for admin_permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_permission`;
CREATE TABLE `admin_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `desc_` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_permission
-- ----------------------------
INSERT INTO `admin_permission` VALUES (1, 'users_management', '用户管理', '/api/admin/user');
INSERT INTO `admin_permission` VALUES (2, 'roles_management', '角色管理', '/api/admin/role');
INSERT INTO `admin_permission` VALUES (3, 'content_management', '内容管理', '/api/admin/content');

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name_zh` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enabled` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES (1, 'sysAdmin', '系统管理员', 1);
INSERT INTO `admin_role` VALUES (2, 'contentManager', '内容管理员', 1);
INSERT INTO `admin_role` VALUES (3, 'userRoleManager', '用户角色管理员', 1);
INSERT INTO `admin_role` VALUES (9, 'test', '测试角色', 1);
INSERT INTO `admin_role` VALUES (11, 'test2', '测试角色2', 1);

-- ----------------------------
-- Table structure for admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_menu`;
CREATE TABLE `admin_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NULL DEFAULT NULL,
  `mid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 330 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_role_menu
-- ----------------------------
INSERT INTO `admin_role_menu` VALUES (121, 1, 1);
INSERT INTO `admin_role_menu` VALUES (122, 1, 2);
INSERT INTO `admin_role_menu` VALUES (123, 1, 3);
INSERT INTO `admin_role_menu` VALUES (124, 1, 6);
INSERT INTO `admin_role_menu` VALUES (125, 1, 7);
INSERT INTO `admin_role_menu` VALUES (126, 1, 4);
INSERT INTO `admin_role_menu` VALUES (127, 1, 8);
INSERT INTO `admin_role_menu` VALUES (128, 1, 9);
INSERT INTO `admin_role_menu` VALUES (129, 1, 10);
INSERT INTO `admin_role_menu` VALUES (130, 1, 5);
INSERT INTO `admin_role_menu` VALUES (276, 11, 1);
INSERT INTO `admin_role_menu` VALUES (277, 11, 2);
INSERT INTO `admin_role_menu` VALUES (278, 11, 5);
INSERT INTO `admin_role_menu` VALUES (284, 2, 1);
INSERT INTO `admin_role_menu` VALUES (285, 2, 2);
INSERT INTO `admin_role_menu` VALUES (286, 2, 6);
INSERT INTO `admin_role_menu` VALUES (287, 2, 4);
INSERT INTO `admin_role_menu` VALUES (288, 2, 11);
INSERT INTO `admin_role_menu` VALUES (320, 3, 1);
INSERT INTO `admin_role_menu` VALUES (321, 3, 2);
INSERT INTO `admin_role_menu` VALUES (322, 3, 3);
INSERT INTO `admin_role_menu` VALUES (323, 3, 6);
INSERT INTO `admin_role_menu` VALUES (324, 3, 7);
INSERT INTO `admin_role_menu` VALUES (325, 9, 1);
INSERT INTO `admin_role_menu` VALUES (326, 9, 2);
INSERT INTO `admin_role_menu` VALUES (327, 9, 4);
INSERT INTO `admin_role_menu` VALUES (328, 9, 11);
INSERT INTO `admin_role_menu` VALUES (329, 9, 5);

-- ----------------------------
-- Table structure for admin_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_permission`;
CREATE TABLE `admin_role_permission`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `rid` int(20) NULL DEFAULT NULL,
  `pid` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_role_permission_role_1`(`rid`) USING BTREE,
  INDEX `fk_role_permission_permission_1`(`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 183 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_role_permission
-- ----------------------------
INSERT INTO `admin_role_permission` VALUES (108, 1, 1);
INSERT INTO `admin_role_permission` VALUES (109, 1, 2);
INSERT INTO `admin_role_permission` VALUES (110, 1, 3);
INSERT INTO `admin_role_permission` VALUES (166, 11, 3);
INSERT INTO `admin_role_permission` VALUES (168, 2, 3);
INSERT INTO `admin_role_permission` VALUES (180, 3, 1);
INSERT INTO `admin_role_permission` VALUES (181, 3, 2);
INSERT INTO `admin_role_permission` VALUES (182, 9, 3);

-- ----------------------------
-- Table structure for admin_user_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_role`;
CREATE TABLE `admin_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NULL DEFAULT NULL,
  `rid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_operator_role_operator_1`(`uid`) USING BTREE,
  INDEX `fk_operator_role_role_1`(`rid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 112 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_user_role
-- ----------------------------
INSERT INTO `admin_user_role` VALUES (64, 1, 1);
INSERT INTO `admin_user_role` VALUES (68, 3, 2);
INSERT INTO `admin_user_role` VALUES (85, 2, 9);
INSERT INTO `admin_user_role` VALUES (104, 110, 2);
INSERT INTO `admin_user_role` VALUES (105, 110, 3);
INSERT INTO `admin_user_role` VALUES (106, 110, 9);

-- ----------------------------
-- Table structure for customer_order
-- ----------------------------
DROP TABLE IF EXISTS `customer_order`;
CREATE TABLE `customer_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) NULL DEFAULT NULL,
  `certificate_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `closing_date` datetime(0) NULL DEFAULT NULL,
  `effective_date` datetime(0) NULL DEFAULT NULL,
  `insured_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `insured_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phonenum` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `policy_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `product` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `seq_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of customer_order
-- ----------------------------
INSERT INTO `customer_order` VALUES (4, 30, '1', '2020-07-18 08:00:00', '2020-07-11 08:00:00', '110112197901020043', '张三风', '北京', '13790870001', 'BJ00323', '中国平安健康一生', '这是一个测试', '11345', '2', NULL);
INSERT INTO `customer_order` VALUES (10, 22, '1', '2020-07-01 08:00:00', '2020-07-01 08:00:00', '22', '212', '22', '22', '2121', '2121', '22', '1212', '1', NULL);
INSERT INTO `customer_order` VALUES (17, 33, '1', '2020-08-01 00:00:00', '2020-07-01 00:00:00', '110110198001010000', '张三', '北京', '1366667777', '22222', '中国平安幸福久久', '测试1', '12345', '1', '1');
INSERT INTO `customer_order` VALUES (18, 23, '2', '2020-08-01 00:00:00', '2020-07-01 00:00:00', '654321', '李四', '天津', '1388889999', '3333', '太平人寿新安康', '测试2', '123456', '2', '1');
INSERT INTO `customer_order` VALUES (19, 22, '1', '2020-07-15 08:00:00', '2020-07-13 08:00:00', '232131', '32131', '22', '1321', '321312', '32131', '22', '1321', '1', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enabled` tinyint(1) NULL DEFAULT NULL,
  `crop` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 122 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '35b9529f89cfb9b848060ca576237e17', '8O+vDNr2sI3N82BI31fu1A==', '管理员', '12312312312', 'evan_nightly@163.com', 1, NULL, NULL);
INSERT INTO `user` VALUES (2, 'test1', '85087738b6c1e1d212683bfafc163853', 'JBba3j5qRykIPJQYTNNH9A==', '测试1', '12312312312', '123@123.com', 1, NULL, NULL);
INSERT INTO `user` VALUES (3, 'editor', '8583a2d965d6159edbf65c82d871fa3e', 'MZTe7Qwf9QgXBXrZzTIqJQ==', '编辑', '13800001111', 'test@qq.com', 1, NULL, NULL);
INSERT INTO `user` VALUES (110, '123', '0e2df59bdee8274aa78c001799f59e07', 'AGSlxzNc40XbdZjfAOugqg==', '测试', '18699998888', '75387273@qq.com', 1, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
