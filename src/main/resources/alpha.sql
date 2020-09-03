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

 Date: 17/08/2020 19:53:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu`;
CREATE TABLE `admin_menu`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '菜单主键',
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `name_zh` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称-中文',
  `path` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单显示路径，唯一',
  `icon_cls` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `component` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父ID',
  `operator` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_menu
-- ----------------------------
INSERT INTO `admin_menu` VALUES (1, 'Admin', '系统用户', '/admin', 'el-icon-s-home', 'AdminIndex', 0, NULL, '2020-08-17 10:59:02');
INSERT INTO `admin_menu` VALUES (2, 'Role', '角色信息', '/api/admin/role/list', NULL, 'user/Role', 1, NULL, '2020-08-17 10:59:02');
INSERT INTO `admin_menu` VALUES (3, 'Profile', '用户信息', '/admin/user/profile', NULL, 'user/UserProfile', 1, NULL, '2020-08-17 10:59:02');
INSERT INTO `admin_menu` VALUES (9, 'Admin', '管理用户', '/admin', 'el-icon-s-home', 'AdminIndex', 0, NULL, '2020-08-17 10:59:02');
INSERT INTO `admin_menu` VALUES (11, 'Profile', '用户信息', '/admin/user/profile', NULL, 'user/UserProfile', 9, NULL, '2020-08-17 10:59:02');
INSERT INTO `admin_menu` VALUES (25, 'Admin', '采购业务功能', '/admin', 'el-icon-s-home', 'AdminIndex', 0, NULL, '2020-08-17 10:59:02');
INSERT INTO `admin_menu` VALUES (28, 'CustomerOrderManagement', '客户单上传', '/admin/content/customerorder', NULL, 'content/CustomerOrderManagement', 25, NULL, '2020-08-17 10:59:02');
INSERT INTO `admin_menu` VALUES (29, 'CustomerServiceManagement', '服务评价', '/admin/content/customerservice', NULL, 'content/CustomerServiceManagement', 25, NULL, '2020-08-17 10:59:02');
INSERT INTO `admin_menu` VALUES (33, 'Admin', '服务业务功能', '/admin', 'el-icon-s-home', 'AdminIndex', 0, NULL, '2020-08-17 10:59:02');
INSERT INTO `admin_menu` VALUES (37, 'PurchaseOrderManagement', '采购单审核', '/admin/content/purchaseorder', NULL, 'content/PurchaseOrderManagement', 33, NULL, '2020-08-17 10:59:02');
INSERT INTO `admin_menu` VALUES (38, 'CustomerServiceManagement', '服务评价', '/admin/content/customerservice', NULL, 'content/CustomerServiceManagement', 33, NULL, '2020-08-17 10:59:02');
INSERT INTO `admin_menu` VALUES (39, 'ServiceManagement', '服务信息', '/admin/content/service', NULL, 'content/ServiceManagement', 33, NULL, '2020-08-17 10:59:02');
INSERT INTO `admin_menu` VALUES (41, 'Admin', '采购财务功能', '/admin', 'el-icon-s-home', 'AdminIndex', 0, NULL, '2020-08-17 10:59:02');
INSERT INTO `admin_menu` VALUES (47, 'PurchaseOrderPayManagement', '采购单付费', '/admin/content/purchaseorderpay', NULL, 'content/PurchaseOrderPayManagement', 41, NULL, '2020-08-17 10:59:02');
INSERT INTO `admin_menu` VALUES (49, 'Admin', '服务财务功能', '/admin', 'el-icon-s-home', 'AdminIndex', 0, NULL, '2020-08-17 10:59:02');
INSERT INTO `admin_menu` VALUES (56, 'PurchaseOrderConfirmManagement', '收款确认', '/admin/content/purchaseorderpayconfirm', NULL, 'content/PurchaseOrderPayConfirmManagement', 49, NULL, '2020-08-17 10:59:02');

-- ----------------------------
-- Table structure for admin_permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_permission`;
CREATE TABLE `admin_permission`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '许可主键',
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '许可名称',
  `desc_` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '许可名称-中文',
  `url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '许可url前缀',
  `operator` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '许可' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_permission
-- ----------------------------
INSERT INTO `admin_permission` VALUES (1, 'admin', '系统许可', '/api', NULL, '2020-08-17 10:59:03');
INSERT INTO `admin_permission` VALUES (2, 'Enterprise', '管理许可', '/api/admin', NULL, '2020-08-17 10:59:03');
INSERT INTO `admin_permission` VALUES (3, 'BuyBusiness', '业务许可', '/api/admin/v1/pri', NULL, '2020-08-17 10:59:03');

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色主键',
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `name_zh` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称-中文，不可修改',
  `enabled` int(11) NULL DEFAULT 1 COMMENT '是否可用，0不可用，1可用',
  `operator` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES (1, 'Admin', '系统-管理岗', 1, NULL, '2020-08-17 10:59:02');
INSERT INTO `admin_role` VALUES (2, 'BuyEnterprise', '采购-管理岗', 1, NULL, '2020-08-17 10:59:02');
INSERT INTO `admin_role` VALUES (3, 'ServiceEnterprise', '服务-管理岗', 1, NULL, '2020-08-17 10:59:02');
INSERT INTO `admin_role` VALUES (4, 'BuyBusiness', '采购-业务岗', 1, NULL, '2020-08-17 10:59:02');
INSERT INTO `admin_role` VALUES (5, 'ServiceBusiness', '服务-业务岗', 1, NULL, '2020-08-17 10:59:02');
INSERT INTO `admin_role` VALUES (6, 'BuyFinance', '采购-财务岗', 1, NULL, '2020-08-17 10:59:02');
INSERT INTO `admin_role` VALUES (7, 'ServiceFinance', '服务-财务岗', 1, NULL, '2020-08-17 10:59:02');

-- ----------------------------
-- Table structure for admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_menu`;
CREATE TABLE `admin_role_menu`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色-菜单主键',
  `rid` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  `mid` int(11) NULL DEFAULT NULL COMMENT '一级菜单ID，二级菜单显示全部',
  `operator` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色-菜单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_role_menu
-- ----------------------------
INSERT INTO `admin_role_menu` VALUES (1, 1, 1, NULL, '2020-08-17 10:59:02');
INSERT INTO `admin_role_menu` VALUES (4, 1, 25, NULL, '2020-08-17 10:59:02');
INSERT INTO `admin_role_menu` VALUES (5, 1, 33, NULL, '2020-08-17 10:59:03');
INSERT INTO `admin_role_menu` VALUES (6, 1, 41, NULL, '2020-08-17 10:59:03');
INSERT INTO `admin_role_menu` VALUES (7, 1, 49, NULL, '2020-08-17 10:59:03');
INSERT INTO `admin_role_menu` VALUES (8, 2, 9, NULL, '2020-08-17 10:59:03');
INSERT INTO `admin_role_menu` VALUES (9, 2, 25, NULL, '2020-08-17 10:59:03');
INSERT INTO `admin_role_menu` VALUES (10, 2, 41, NULL, '2020-08-17 10:59:03');
INSERT INTO `admin_role_menu` VALUES (11, 3, 9, NULL, '2020-08-17 10:59:03');
INSERT INTO `admin_role_menu` VALUES (12, 3, 33, NULL, '2020-08-17 10:59:03');
INSERT INTO `admin_role_menu` VALUES (13, 3, 49, NULL, '2020-08-17 10:59:03');
INSERT INTO `admin_role_menu` VALUES (14, 4, 25, NULL, '2020-08-17 10:59:03');
INSERT INTO `admin_role_menu` VALUES (15, 5, 33, NULL, '2020-08-17 10:59:03');
INSERT INTO `admin_role_menu` VALUES (16, 6, 41, NULL, '2020-08-17 10:59:03');
INSERT INTO `admin_role_menu` VALUES (17, 7, 49, NULL, '2020-08-17 10:59:03');

-- ----------------------------
-- Table structure for admin_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_permission`;
CREATE TABLE `admin_role_permission`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色-许可主键',
  `rid` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  `pid` int(11) NULL DEFAULT NULL COMMENT '许可ID，许可url前缀',
  `operator` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色-许可' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_role_permission
-- ----------------------------
INSERT INTO `admin_role_permission` VALUES (1, 1, 1, NULL, '2020-08-17 10:59:03');
INSERT INTO `admin_role_permission` VALUES (2, 2, 2, NULL, '2020-08-17 10:59:03');
INSERT INTO `admin_role_permission` VALUES (3, 3, 2, NULL, '2020-08-17 10:59:03');
INSERT INTO `admin_role_permission` VALUES (4, 4, 3, NULL, '2020-08-17 10:59:03');
INSERT INTO `admin_role_permission` VALUES (5, 5, 3, NULL, '2020-08-17 10:59:03');
INSERT INTO `admin_role_permission` VALUES (6, 6, 3, NULL, '2020-08-17 10:59:03');
INSERT INTO `admin_role_permission` VALUES (7, 7, 3, NULL, '2020-08-17 10:59:03');

-- ----------------------------
-- Table structure for admin_user_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_role`;
CREATE TABLE `admin_user_role`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户-角色主键',
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `rid` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  `operator` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户-角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_user_role
-- ----------------------------
INSERT INTO `admin_user_role` VALUES (1, 1, 1, NULL, '2020-08-17 10:59:03');
INSERT INTO `admin_user_role` VALUES (2, 2, 0, NULL, '2020-08-17 12:03:32');
INSERT INTO `admin_user_role` VALUES (4, 4, 3, NULL, '2020-08-17 12:11:02');
INSERT INTO `admin_user_role` VALUES (5, 3, 2, NULL, '2020-08-17 12:11:32');

-- ----------------------------
-- Table structure for alpha_subject
-- ----------------------------
DROP TABLE IF EXISTS `alpha_subject`;
CREATE TABLE `alpha_subject`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主体主键',
  `subject_type` int(11) NULL DEFAULT NULL COMMENT '1、客户，2、保险企业，3、服务企业',
  `record_type` int(11) NULL DEFAULT NULL COMMENT '证件类型,1、身份证，2、军官证,驾驶证，3、新生儿出生证明，4、社会保险卡号，,10企业机构代码，',
  `record_number` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织机构代码，验证，唯一，或者身份证或其他证件好',
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业名称，或客户姓名',
  `phone` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `source_type` int(11) NULL DEFAULT NULL COMMENT '申请类型，1.申请（付费）企业excel，2.系统维护, 3.API上传',
  `source_id` int(11) NULL DEFAULT NULL COMMENT '触发id，1、cp_excel_mst_id，2null',
  `source_detail_id` int(11) NULL DEFAULT NULL COMMENT '触发id，1、cp_excel_detail_id，2null',
  `enabled` int(1) NULL DEFAULT 1 COMMENT '是否可用，0不可用，1可用',
  `operator` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `alpha_subject_UNIQUE`(`subject_type`, `record_type`, `record_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '主体' ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Table structure for batch_fee_detail
-- ----------------------------
DROP TABLE IF EXISTS `batch_fee_detail`;
CREATE TABLE `batch_fee_detail`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `batch_fee_mst_id` int(11) NULL DEFAULT NULL COMMENT '批次付费主表id',
  `customer_subject_id` int(11) NULL DEFAULT NULL COMMENT '客户主体ID，这里是客户id',
  `source_type` int(11) NULL DEFAULT NULL COMMENT '申请类型，1.申请（付费）企业excel，2.系统维护, 3.API上传',
  `source_detail_id` int(11) NULL DEFAULT NULL COMMENT '触发id，客户-产品excle明细ID, 之一',
  `effective_number` int(11) NULL DEFAULT NULL COMMENT '有效数，一般是1，有多次投诉可能是多个，按客户_企业计费时取一个客户_产品ID为1，其他为0',
  `operator` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '批次付费明细' ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Table structure for batch_fee_mst
-- ----------------------------
DROP TABLE IF EXISTS `batch_fee_mst`;
CREATE TABLE `batch_fee_mst`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `batch_number` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务批号，P_组织机构代码_YYYYMMDD_序号2位',
  `pay_subject_id` int(11) NULL DEFAULT NULL COMMENT '付费主体ID，Excel上传企业，这里是保险企业',
  `charge_subject_id` int(11) NULL DEFAULT NULL COMMENT '提供主体ID，选择的企业，这里是服务企业，冗余',
  `effective_date` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `closing_date` datetime(0) NULL DEFAULT NULL COMMENT '结束时间，默认为开始时间后一月',
  `pay_type` int(11) NULL DEFAULT NULL COMMENT '1、按客户付费、2按客户产品',
  `charge_type` int(11) NULL DEFAULT NULL COMMENT '1收款，-1退款',
  `effective_number` int(11) NULL DEFAULT NULL COMMENT '有效数',
  `price` int(11) NULL DEFAULT NULL COMMENT '单价',
  `prepayment` int(11) NULL DEFAULT NULL COMMENT '预付款',
  `receivable` int(11) NULL DEFAULT NULL COMMENT '应收款',
  `img` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付费凭证图片',
  `url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片存储url',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '付费时间',
  `charge_time` datetime(0) NULL DEFAULT NULL COMMENT '收款时间',
  `remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `confirm_remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '确认备注',
  `state` int(1) NULL DEFAULT NULL COMMENT '状态',
  `operator` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员',
  `cashier` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款员',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '批次付费主表' ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Table structure for complaint
-- ----------------------------
DROP TABLE IF EXISTS `complaint`;
CREATE TABLE `complaint`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customer_product_id` int(11) NULL DEFAULT NULL COMMENT '客户-企业-产品订单ID',
  `complaint_type` int(1) NULL DEFAULT NULL COMMENT '类型,1、不减扣服务费、2、减扣服务费',
  `remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `operator` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '投诉' ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Table structure for cp_excel_detail
-- ----------------------------
DROP TABLE IF EXISTS `cp_excel_detail`;
CREATE TABLE `cp_excel_detail`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '客户-产品excle明细主键',
  `cp_excel_mst_id` int(11) NULL DEFAULT NULL COMMENT '客户-产品excle主表ID',
  `customer_subject_id` int(11) NULL DEFAULT NULL COMMENT '客户主体ID，这里是客户id',
  `product_id` int(11) NULL DEFAULT NULL COMMENT '收费产品ID，保险产品',
  `effective_date` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `closing_date` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `out_trade_no` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单标识,唯一，可能是保单号，服务单据号等，对应客户+产品+时间范围',
  `ordered` int(11) NULL DEFAULT NULL COMMENT '行号，excel从第2行记录开始,排序',
  `seq_number` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '序号，文件内容',
  `customer_type` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件类型名称,1、身份证，2、军官证,驾驶证，3、新生儿出生证明，4、社会保险卡号，',
  `customer_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户姓名，冗余信息',
  `customer_phone` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话，冗余信息',
  `product_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称，目前录入，验证生成产品表',
  `location` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在地,可以录入或来源于身份证号',
  `age` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年龄,可以录入或来源于身份证号',
  `sex` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别,1男，2女，可以录入或来源于身份证号',
  `remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `confirm_remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核备注',
  `state` int(1) NULL DEFAULT NULL COMMENT '状态：',
  `operator` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `excel_out_trade_no_UNIQUE`(`out_trade_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户-产品excle明细' ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Table structure for cp_excel_mst
-- ----------------------------
DROP TABLE IF EXISTS `cp_excel_mst`;
CREATE TABLE `cp_excel_mst`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '客户-产品Excel主表主键',
  `pay_subject_id` int(11) NULL DEFAULT NULL COMMENT '付费主体ID，Excel上传企业，这里是保险企业',
  `charge_subject_id` int(11) NULL DEFAULT NULL COMMENT '收费主体ID，选择的企业，这里是服务企业',
  `file_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名，唯一',
  `url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件存储url',
   `ip` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上传客户端ip',
  `source_type` int(11) NULL DEFAULT NULL COMMENT '申请类型，1.申请（付费）企业excel，2.系统维护, 3.API上传',   
  `remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `operator` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `filename_UNIQUE`(`file_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户-产品Excel主表' ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Table structure for customer_product
-- ----------------------------
DROP TABLE IF EXISTS `customer_product`;
CREATE TABLE `customer_product`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '客户-企业-产品订单主键',
  `customer_subject_id` int(11) NULL DEFAULT NULL COMMENT '客户主体ID，这里是客户id',
  `product_id` int(11) NULL DEFAULT NULL COMMENT '产品ID',
  `effective_date` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `closing_date` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `out_trade_no` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单标识,唯一，目前没有',
  `remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `confirm_remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请备注',
  `source_type` int(11) NULL DEFAULT NULL COMMENT '申请类型，1.申请（付费）企业excel，2.系统维护, 3.API上传',
  `source_id` int(11) NULL DEFAULT NULL COMMENT '触发ID，客户-产品excle主表ID，冗余',
  `source_detail_id` int(11) NULL DEFAULT NULL COMMENT '触发id，客户-产品excle明细ID, 之一',
  `state` int(1) NULL DEFAULT NULL COMMENT '状态',
  `pay_times` int(11) NULL DEFAULT NULL COMMENT '付费次数',
  `operator` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '客户-企业-产品订单' ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '产品主键',
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品全称',
  `alpha_subject_id` int(11) NULL DEFAULT NULL COMMENT '企业主体id',
  `product_type` int(1) NULL DEFAULT NULL COMMENT '1、普通，2、保险，3、保险配套服务',
  `record_number` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备案编号，唯一',
  `ordered` int(11) NULL DEFAULT NULL COMMENT '顺序号，排序用',
  `source_type` int(11) NULL DEFAULT NULL COMMENT '申请类型，1.申请（付费）企业excel，2.系统维护, 3.API上传',
  `source_id` int(11) NULL DEFAULT NULL COMMENT '触发id，1、cp_excel_mst_id，2null',
  `source_detail_id` int(11) NULL DEFAULT NULL COMMENT '触发id，1、cp_excel_detail_id，2null',
  `enabled` int(11) NULL DEFAULT 1 COMMENT '是否可用，0不可用，1可用',
  `operator` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `product_record_number_UNIQUE`(`record_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品' ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `username` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户名，验证，唯一',
  `password` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码，md5加密',
  `salt` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐值',
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `alpha_subject_id` int(11) NULL DEFAULT NULL COMMENT '企业主体ID',
  `enabled` int(1) NULL DEFAULT 1 COMMENT '是否可用，0不可用，1可用',
  `operator` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作员',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username_UNIQUE`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '6052580ac665ae5e9747ab45f7fd23c0', 'txu+vm4Z0oC2bXmqwOrItQ==', '系统管理员', '123', '1@1.com', NULL, 1, NULL, '2020-08-17 10:59:03');

SET FOREIGN_KEY_CHECKS = 1;
