DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (

  `id`  int(11) NOT NULL AUTO_INCREMENT ,
  `name`  varchar(255) NOT NULL COMMENT  '角色名称' ,
  `name_zh`  varchar(255) NOT NULL COMMENT  '角色名称-中文，不可修改' ,
  `enabled`  int(1) NOT NULL COMMENT  '是否可用，0不可用，1可用' ,
  `operator`  varchar(255) NULL COMMENT  '操作员' ,
  `create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT  '创建时间' ,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


/*
-- Query: SELECT id,name,name_zh,enabled FROM alpha.admin_role
-- Date: 2020-07-28 20:59
*/
INSERT INTO `admin_role` (`id`,`name`,`name_zh`,`enabled`) VALUES (1,'Admin','系统-管理岗',1);
INSERT INTO `admin_role` (`id`,`name`,`name_zh`,`enabled`) VALUES (2,'BuyEnterprise','采购-管理岗',1);
INSERT INTO `admin_role` (`id`,`name`,`name_zh`,`enabled`) VALUES (3,'ServiceEnterprise','服务-管理岗',1);
INSERT INTO `admin_role` (`id`,`name`,`name_zh`,`enabled`) VALUES (4,'BuyBusiness','采购-业务岗',1);
INSERT INTO `admin_role` (`id`,`name`,`name_zh`,`enabled`) VALUES (5,'ServiceBusiness','服务-业务岗',1);
INSERT INTO `admin_role` (`id`,`name`,`name_zh`,`enabled`) VALUES (6,'BuyFinance','采购-财务岗',1);
INSERT INTO `admin_role` (`id`,`name`,`name_zh`,`enabled`) VALUES (7,'ServiceFinance','服务-财务岗',1);



DROP TABLE IF EXISTS `admin_menu`;
CREATE TABLE `admin_menu` (

  `id`  int(11) NOT NULL AUTO_INCREMENT ,
  `name`  varchar(255) NOT NULL COMMENT  '菜单名称' ,
  `name_zh`  varchar(255) NOT NULL COMMENT  '菜单名称-中文' ,
  `path`  varchar(255) NOT NULL COMMENT  '菜单显示路径' ,
  `icon_cls`  varchar(255) NULL COMMENT  '图标' ,
  `component`  varchar(255) NOT NULL COMMENT  '组件' ,
  `parent_id`  int(11) NOT NULL COMMENT  '父ID' ,
  `operator`  varchar(255) NULL COMMENT  '操作员' ,
  `create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT  '创建时间' ,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1  CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;
/*
-- Query: SELECT `id`,`name`,`name_zh`,`path`,`icon_cls`,`component`,`parent_id` FROM alpha.admin_menu
-- Date: 2020-07-28 21:11
*/
INSERT INTO `admin_menu` (`id`,`name`,`name_zh`,`path`,`icon_cls`,`component`,`parent_id`) VALUES (1,'Admin','系统用户','/admin','el-icon-s-home','AdminIndex',0);
INSERT INTO `admin_menu` (`id`,`name`,`name_zh`,`path`,`icon_cls`,`component`,`parent_id`) VALUES (2,'Role','角色信息','/api/admin/role/list',NULL,'user/Role',1);
INSERT INTO `admin_menu` (`id`,`name`,`name_zh`,`path`,`icon_cls`,`component`,`parent_id`) VALUES (3,'Profile','用户信息','/admin/user/profile',NULL,'user/UserProfile',1);
INSERT INTO `admin_menu` (`id`,`name`,`name_zh`,`path`,`icon_cls`,`component`,`parent_id`) VALUES (9,'Admin','管理用户','/admin','el-icon-s-home','AdminIndex',0);
INSERT INTO `admin_menu` (`id`,`name`,`name_zh`,`path`,`icon_cls`,`component`,`parent_id`) VALUES (11,'Profile','用户信息','/admin/user/profile',NULL,'user/UserProfile',9);
INSERT INTO `admin_menu` (`id`,`name`,`name_zh`,`path`,`icon_cls`,`component`,`parent_id`) VALUES (25,'Admin','采购业务功能','/admin','el-icon-s-home','AdminIndex',0);
INSERT INTO `admin_menu` (`id`,`name`,`name_zh`,`path`,`icon_cls`,`component`,`parent_id`) VALUES (28,'CustomerOrderManagement','客户单上传','/admin/content/customerorder',NULL,'content/CustomerOrderManagement',25);
INSERT INTO `admin_menu` (`id`,`name`,`name_zh`,`path`,`icon_cls`,`component`,`parent_id`) VALUES (29,'CustomerServiceManagement','服务评价','/admin/content/customerservice',NULL,'content/CustomerServiceManagement',25);
INSERT INTO `admin_menu` (`id`,`name`,`name_zh`,`path`,`icon_cls`,`component`,`parent_id`) VALUES (33,'Admin','服务业务功能','/admin','el-icon-s-home','AdminIndex',0);
INSERT INTO `admin_menu` (`id`,`name`,`name_zh`,`path`,`icon_cls`,`component`,`parent_id`) VALUES (37,'PurchaseOrderManagement','采购单审核','/admin/content/purchaseorder',NULL,'content/PurchaseOrderManagement',33);
INSERT INTO `admin_menu` (`id`,`name`,`name_zh`,`path`,`icon_cls`,`component`,`parent_id`) VALUES (38,'CustomerServiceManagement','服务评价','/admin/content/customerservice',NULL,'content/CustomerServiceManagement',33);
INSERT INTO `admin_menu` (`id`,`name`,`name_zh`,`path`,`icon_cls`,`component`,`parent_id`) VALUES (39,'ServiceManagement','服务信息','/admin/content/service',NULL,'content/ServiceManagement',33);
INSERT INTO `admin_menu` (`id`,`name`,`name_zh`,`path`,`icon_cls`,`component`,`parent_id`) VALUES (41,'Admin','采购财务功能','/admin','el-icon-s-home','AdminIndex',0);
INSERT INTO `admin_menu` (`id`,`name`,`name_zh`,`path`,`icon_cls`,`component`,`parent_id`) VALUES (47,'PurchaseOrderPayManagement','采购单付费','/admin/content/purchaseorderpay',NULL,'content/PurchaseOrderPayManagement',41);
INSERT INTO `admin_menu` (`id`,`name`,`name_zh`,`path`,`icon_cls`,`component`,`parent_id`) VALUES (49,'Admin','服务财务功能','/admin','el-icon-s-home','AdminIndex',0);
INSERT INTO `admin_menu` (`id`,`name`,`name_zh`,`path`,`icon_cls`,`component`,`parent_id`) VALUES (56,'PurchaseOrderConfirmManagement','收款确认','/admin/content/purchaseorderpayconfirm',NULL,'content/PurchaseOrderPayConfirmManagement',49);


DROP TABLE IF EXISTS `admin_role_menu`;
CREATE TABLE `admin_role_menu` (

  `id`  int(11) NOT NULL AUTO_INCREMENT ,
  `rid`  int(1) NOT NULL COMMENT  '角色ID' ,
  `mid`  int(11) NOT NULL COMMENT  '一级菜单ID，二级菜单显示全部' ,
  `operator`  varchar(255) NULL COMMENT  '操作员' ,
  `create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT  '创建时间' ,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1  CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

/*
-- Query: SELECT * FROM alpha.admin_role_menu
-- Date: 2020-07-28 21:18
*/
INSERT INTO `admin_role_menu` (`id`,`mid`,`rid`) VALUES (1,1,1);
INSERT INTO `admin_role_menu` (`id`,`mid`,`rid`) VALUES (4,25,1);
INSERT INTO `admin_role_menu` (`id`,`mid`,`rid`) VALUES (5,33,1);
INSERT INTO `admin_role_menu` (`id`,`mid`,`rid`) VALUES (6,41,1);
INSERT INTO `admin_role_menu` (`id`,`mid`,`rid`) VALUES (7,49,1);
INSERT INTO `admin_role_menu` (`id`,`mid`,`rid`) VALUES (8,9,2);
INSERT INTO `admin_role_menu` (`id`,`mid`,`rid`) VALUES (9,25,2);
INSERT INTO `admin_role_menu` (`id`,`mid`,`rid`) VALUES (10,41,2);
INSERT INTO `admin_role_menu` (`id`,`mid`,`rid`) VALUES (11,9,3);
INSERT INTO `admin_role_menu` (`id`,`mid`,`rid`) VALUES (12,33,3);
INSERT INTO `admin_role_menu` (`id`,`mid`,`rid`) VALUES (13,49,3);
INSERT INTO `admin_role_menu` (`id`,`mid`,`rid`) VALUES (14,25,4);
INSERT INTO `admin_role_menu` (`id`,`mid`,`rid`) VALUES (15,33,5);
INSERT INTO `admin_role_menu` (`id`,`mid`,`rid`) VALUES (16,41,6);
INSERT INTO `admin_role_menu` (`id`,`mid`,`rid`) VALUES (17,49,7);

DROP TABLE IF EXISTS `admin_permission`;
CREATE TABLE `admin_permission` (

  `id`  int(11) NOT NULL AUTO_INCREMENT ,
  `name`  varchar(255) NOT NULL COMMENT  '许可名称' ,
  `desc_`  varchar(255) NOT NULL COMMENT  '许可名称-中文' ,
  `url`  varchar(255) NOT NULL COMMENT  '许可url前缀' ,
  `operator`  varchar(255) NULL COMMENT  '操作员' ,
  `create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT  '创建时间' ,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1  CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


INSERT INTO `admin_permission` (`id`,`name`,`desc_`,`url`) VALUES (1,'admin','系统许可','/api');
INSERT INTO `admin_permission` (`id`,`name`,`desc_`,`url`) VALUES (2,'Enterprise','管理许可','/api/admin');
INSERT INTO `admin_permission` (`id`,`name`,`desc_`,`url`) VALUES (3,'BuyBusiness','业务许可','/api/admin/v1/pri');


DROP TABLE IF EXISTS `admin_role_permission`;
CREATE TABLE `admin_role_permission` (

  `id`  int(11) NOT NULL AUTO_INCREMENT ,
  `rid`  int(11) NOT NULL COMMENT  '角色ID' ,
  `pid`  int(11) NOT NULL COMMENT  '许可ID，许可url前缀' ,
  `operator`  varchar(255)  NULL COMMENT  '操作员' ,
  `create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT  '创建时间' ,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1  CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

INSERT INTO `admin_role_permission` (`id`,`pid`,`rid`) VALUES (1,1,1);
INSERT INTO `admin_role_permission` (`id`,`pid`,`rid`) VALUES (2,2,2);
INSERT INTO `admin_role_permission` (`id`,`pid`,`rid`) VALUES (3,2,3);
INSERT INTO `admin_role_permission` (`id`,`pid`,`rid`) VALUES (4,3,4);
INSERT INTO `admin_role_permission` (`id`,`pid`,`rid`) VALUES (5,3,5);
INSERT INTO `admin_role_permission` (`id`,`pid`,`rid`) VALUES (6,3,6);
INSERT INTO `admin_role_permission` (`id`,`pid`,`rid`) VALUES (7,3,7);

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (

  `id`  int(11) NOT NULL AUTO_INCREMENT ,
  `username`  varchar(255) NOT NULL COMMENT  '登录名，验证，不能重复' ,
  `password`  varchar(255) NOT NULL COMMENT  '密码，md5加密' ,
  `name`  varchar(255) NOT NULL COMMENT  '姓名' ,
  `salt`  varchar(255) NULL COMMENT  '盐值' ,
  `phone`  varchar(255) NOT NULL COMMENT  '联系电话' ,
  `email`  varchar(255) NOT NULL COMMENT  '邮箱' ,
  `enabled`  int(1) NOT NULL COMMENT  '是否可用，0不可用，1可用' ,
  `company_id`  int(11) NULL COMMENT  '企业ID' ,
  `operator`  varchar(255) NULL COMMENT  '操作员' ,
  `create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT  '创建时间' ,
  PRIMARY KEY (`id`) USING BTREE,
 UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE = InnoDB AUTO_INCREMENT = 1  CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

INSERT INTO `user` (`id`,`username`,`password`,`name`,`salt`,`phone`,`email`,`enabled`,`company_id`) VALUES (1,'admin','6052580ac665ae5e9747ab45f7fd23c0','系统管理员','txu+vm4Z0oC2bXmqwOrItQ==','123','1@1.com',1,NULL);

 DROP TABLE IF EXISTS `admin_user_role`;
CREATE TABLE `admin_user_role` (

  `id`  int(11) NOT NULL AUTO_INCREMENT ,
  `uid`  int(11) NOT NULL COMMENT  '用户ID' ,
  `rid`  int(11) NOT NULL COMMENT  '角色ID' ,
  `operator`  varchar(255) NULL COMMENT  '操作员' ,
  `create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT  '创建时间' ,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1  CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

INSERT INTO `admin_user_role` (`id`,`rid`,`uid`) VALUES (1,1,1);


DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (

  `id`  int(11) NOT NULL AUTO_INCREMENT ,
  `company_id`  int(11) NOT NULL COMMENT  '企业ID' ,
  `record_number`  varchar(255) NOT NULL COMMENT  '备案编号，应该唯一' ,
  `product`  varchar(255) NOT NULL COMMENT  '产品全称' ,
  `ptype`  int(1) NOT NULL COMMENT  '1、普通，2、保险，3、保险配套服务' ,
  `seq_number`  int(11) NULL COMMENT  '顺序号，排序用' ,
  `enabled`  int(1) NOT NULL DEFAULT '1' COMMENT  '是否可用，0不可用，1可用' ,
  `operator`  varchar(255) NULL COMMENT  '操作员' ,
  `create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT  '创建时间' ,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1  CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (

  `id`  int(11) NOT NULL AUTO_INCREMENT ,
  `code`  varchar(255) NOT NULL COMMENT  '组织机构代码，验证，不能重复' ,
  `name`  varchar(255) NOT NULL COMMENT  '企业名称' ,
  `ctype`  int(1) NOT NULL COMMENT  '企业类型，1、保险商（产品企业），2、服务商（服务企业）' ,
  `phone`  varchar(255) NOT NULL COMMENT  '联系电话' ,
  `enabled`  int(1) NOT NULL DEFAULT '1' COMMENT  '是否可用，0不可用，1可用' ,
  `operator`  varchar(255) NULL COMMENT  '操作员' ,
  `create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT  '创建时间' ,
  PRIMARY KEY (`id`) USING BTREE,
 UNIQUE KEY `company_code_UNIQUE` (`code`)
) ENGINE = InnoDB AUTO_INCREMENT = 1  CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


DROP TABLE IF EXISTS `cp_excel_detail`;
CREATE TABLE `cp_excel_detail` (

  `id`  int(11) NOT NULL AUTO_INCREMENT ,
  `cp_excel_mst_id`  int(11) NOT NULL COMMENT  '客户_产品 _excel_主表ID' ,
  `row_num`  int(11) NOT NULL COMMENT  '行号，excel从第2行记录开始' ,
  `certificate_type`  varchar(255) NOT NULL COMMENT  '证件类型,1、身份证，2、军官证,驾驶证，3、新生儿出生证明，4、社会保险卡号，目前只有1' ,
  `insured_id`  varchar(255) NOT NULL COMMENT  '证件号,需要验证、脱敏、形成所在地、年龄、性别' ,
  `insured_name`  varchar(255) NOT NULL COMMENT  '客户姓名' ,
  `phone`  varchar(255) NULL COMMENT  '联系电话' ,
  `location`  varchar(255) NULL COMMENT  '所在地,可以录入或来源于身份证号' ,
  `age`  varchar(255) NULL COMMENT  '年龄,可以录入或来源于身份证号' ,
  `sex`  varchar(255) NULL COMMENT  '性别,1男，2女，可以录入或来源于身份证号' ,
  `company_id`  int(11) NULL COMMENT  '企业ID，产品的企业ID，同主表from_id' ,
  `customer_id`  int(11) NULL COMMENT  '客户ID,目前未使用' ,
  `product_id`  int(11) NULL COMMENT  '产品ID，目前未使用' ,
  `product`  varchar(255) NOT NULL COMMENT  '产品名称，目前录入，需要确认是否验证' ,
  `policy_number`  varchar(255) NOT NULL COMMENT  '单据号，可能是保单号，服务单据号等，对应客户+产品' ,
  `effective_date`  datetime  NULL COMMENT  '开始时间' ,
  `closing_date`  datetime NULL COMMENT  '结束时间' ,
  `status`  varchar(255) NOT NULL COMMENT  '状态，1 = 已触发待申请;导入cp_excel2 = 重新触发待申请;未通过后修改3 = 申请未通过;cp_excel导入客户-企业表中违反限制不能导入3 = 已申请待审核;excel导入customer_enterprise表4 = 重新申请待审核;服务方审核未通过然后采购方修改后状态5 = 审核未通过;服务方审核未通过5 = 审核通过可付费;服务方审核通过可多次用于付费6 = 付费完成待收款;批量付费完成取customer_enterprise表数据进入batch_fee7 = 未收款;批量付费完成服务方确认未收款7 = 确认收款服务中;已经收款进入customer_product8 = 服务完成;服务方-客户或采购方 9 = 服务完成且评价;评价就认为完成' ,
  `seq_number`  int(11) NOT NULL COMMENT  '顺序号' ,
  `remark`  varchar(256) NULL COMMENT  '备注' ,
  `confirm_remark`  varchar(256) NULL COMMENT  '申请备注' ,
  `operator`  varchar(255) NULL COMMENT  '操作员' ,
  `create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT  '创建时间' ,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1  CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


DROP TABLE IF EXISTS `cp_excel_mst`;
CREATE TABLE `cp_excel_mst` (

  `id`  int(11) NOT NULL AUTO_INCREMENT ,
  `from_type`  int(1) NOT NULL COMMENT  '来源类型，1:（客户）、2:企业' ,
  `from_id`  int(11) NOT NULL COMMENT  '来源ID' ,
  `to_type`  int(1) NOT NULL COMMENT  '服务类型，1:（客户）、2:企业' ,
  `to_id`  int(11) NOT NULL COMMENT  '服务商企业ID' ,
  `file_name`  varchar(255) NULL COMMENT  '文件名' ,
  `status`  varchar(255) NOT NULL COMMENT  '状态，1 = 已触发待申请;导入cp_excel2 = 重新触发待申请;未通过后修改3 = 申请未通过;cp_excel导入客户-企业表中违反限制不能导入3 = 已申请待审核;excel导入customer_enterprise表4 = 重新申请待审核;服务方审核未通过然后采购方修改后状态5 = 审核未通过;服务方审核未通过5 = 审核通过可付费;服务方审核通过可多次用于付费6 = 付费完成待收款;批量付费完成取customer_enterprise表数据进入batch_fee7 = 未收款;批量付费完成服务方确认未收款7 = 确认收款服务中;已经收款进入customer_product8 = 服务完成;服务方-客户或采购方 9 = 服务完成且评价;评价就认为完成' ,
  `seq_number`  int(11) NULL COMMENT  '顺序号' ,
  `remark`  varchar(256) NULL COMMENT  '备注' ,
  `operator`  varchar(255) NULL COMMENT  '操作员' ,
  `create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT  '创建时间' ,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1  CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `customer_enterprise`;
CREATE TABLE `customer_enterprise` (

  `id`  int(11) NOT NULL AUTO_INCREMENT ,
  `certificate_type`  varchar(255) NOT NULL COMMENT  '证件类型,1、身份证，2、军官证,驾驶证，3、新生儿出生证明，4、社会保险卡号，目前只有1' ,
  `insured_id`  varchar(255) NOT NULL COMMENT  '证件号,需要验证、脱敏、形成所在地、年龄、性别' ,
  `cname`  varchar(255) NOT NULL COMMENT  '客户姓名' ,
  `phone`  varchar(255) NOT NULL COMMENT  '联系电话' ,
  `eid`  int(11) NOT NULL COMMENT  '企业ID，服务的企业ID，同to_id' ,



  `effective_date`  datetime NULL COMMENT  '开始时间' ,
  `closing_date`  datetime NULL COMMENT  '结束时间，默认为开始时间后一年' ,
  `status`  varchar(255) NOT NULL COMMENT  '状态，1 = 已触发待申请;导入cp_excel2 = 重新触发待申请;未通过后修改3 = 申请未通过;cp_excel导入客户-企业表中违反限制不能导入3 = 已申请待审核;excel导入customer_enterprise表4 = 重新申请待审核;服务方审核未通过然后采购方修改后状态5 = 审核未通过;服务方审核未通过5 = 审核通过可付费;服务方审核通过可多次用于付费6 = 付费完成待收款;批量付费完成取customer_enterprise表数据进入batch_fee7 = 未收款;批量付费完成服务方确认未收款7 = 确认收款服务中;已经收款进入customer_product8 = 服务完成;服务方-客户或采购方 9 = 服务完成且评价;评价就认为完成' ,
  `from_type`  int(1) NOT NULL COMMENT  '来源类型, 冗余，可以从excel主表取' ,
  `from_id`  int(11) NOT NULL COMMENT  '来源ID, 冗余，可以从excel主表取' ,
  `cpem_id`  int(11) NULL COMMENT  '客户_产品 _excel_主表_ID' ,
  `cped_id`  int(11) NULL COMMENT  '客户_产品 _excel_明细_ID' ,
  `remark`  varchar(255) NULL COMMENT  '备注' ,
  `confirm_remark`  varchar(255) NULL COMMENT  '审核备注' ,
  `operator`  varchar(255) NULL COMMENT  '操作员' ,
  `create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT  '创建时间' ,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1  CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `batch_fee_detail`;
CREATE TABLE `batch_fee_detail` (

  `id`  int(11) NOT NULL AUTO_INCREMENT ,
  `batch_fee_mst_id`  int(11) NOT NULL COMMENT  '批次付费主表id' ,
  `ce_id`  int(11) NULL COMMENT  '客户_企业表ID' ,
  `cp_id`  int(11) NULL COMMENT  '客户_产品ID，目前不使用' ,
  `effective_number`  int(11) NOT NULL COMMENT  '有效数，一般是1，有多次投诉可能是多个，按客户_企业计费时取一个客户_产品ID为1，其他为0' ,
  `operator`  varchar(255) NULL COMMENT  '操作员' ,
  `create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT  '创建时间' ,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1  CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;


DROP TABLE IF EXISTS `batch_fee_mst`;
CREATE TABLE `batch_fee_mst` (

  `id`  int(11) NOT NULL AUTO_INCREMENT ,
  `batch_number`  varchar(255) NOT NULL COMMENT  '服务批号，P_组织机构代码_YYYYMMDD_序号2位' ,
  `ftype`  int(1) NOT NULL COMMENT  '类型' ,
  `from_type`  int(1) NOT NULL COMMENT  '来源类型，1:（客户）、2:企业' ,
  `from_id`  int(11) NOT NULL COMMENT  '付费来源ID，customer_enterprise' ,
  `to_type`  int(1) NOT NULL COMMENT  '来源类型，1:（客户）、2:企业' ,
  `to_id`  int(11) NOT NULL COMMENT  '收费来源ID，customer_enterprise' ,
  `effective_date`  datetime NOT NULL COMMENT  '开始时间' ,
  `closing_date`  datetime NOT NULL COMMENT  '结束时间，默认为开始时间后一月' ,
  `effective_number`  int(11) NOT NULL COMMENT  '有效数' ,
  `btype`  int(1) NOT NULL COMMENT  '付费类型1，客户-企业，2客户-产品' ,
  `price`  int(11) NOT NULL COMMENT  '单价' ,
  `prepayment`  int(11) NOT NULL COMMENT  '预付款' ,
  `receivable`  int(11) NOT NULL COMMENT  '应收款' ,
  `pay_img`  varchar(255) NULL COMMENT  '付费凭证图片' ,
  `pay_time`  datetime NOT NULL COMMENT  '付费时间' ,
  `remark`  varchar(256) NULL COMMENT  '备注' ,
  `confirm_remark`  varchar(256) NULL COMMENT  '确认备注' ,
  `status`  varchar(255) NOT NULL COMMENT  '状态，1 = 已触发待申请;导入cp_excel2 = 重新触发待申请;未通过后修改3 = 申请未通过;cp_excel导入客户-企业表中违反限制不能导入3 = 已申请待审核;excel导入customer_enterprise表4 = 重新申请待审核;服务方审核未通过然后采购方修改后状态5 = 审核未通过;服务方审核未通过5 = 审核通过可付费;服务方审核通过可多次用于付费6 = 付费完成待收款;批量付费完成取customer_enterprise表数据进入batch_fee7 = 未收款;批量付费完成服务方确认未收款7 = 确认收款服务中;已经收款进入customer_product8 = 服务完成;服务方-客户或采购方 9 = 服务完成且评价;评价就认为完成' ,
  `seq_number`  int(11) NULL COMMENT  '顺序号' ,
  `operator`  varchar(255) NULL COMMENT  '操作员' ,
  `create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT  '创建时间' ,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1  CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;




DROP TABLE IF EXISTS `customer_product`;
CREATE TABLE `customer_product` (

  `id`  int(11) NOT NULL AUTO_INCREMENT ,
  `from_type`  int(1) NOT NULL COMMENT  '来源类型，1:（客户）、2:企业' ,
  `from_id`  int(11) NOT NULL COMMENT  '付费来源ID，customer_enterprise' ,
  `to_type`  int(1) NOT NULL COMMENT  '来源类型，1:（客户）、2:企业' ,
  `to_id`  int(11) NOT NULL COMMENT  '收费来源ID，customer_enterprise' ,
  `company_id`  int(11) NOT NULL COMMENT  '客户_企业ID' ,
  `certificate_type`  varchar(255) NOT NULL COMMENT  '证件类型,1、身份证，2、军官证,驾驶证，3、新生儿出生证明，4、社会保险卡号，目前只有1' ,
  `insured_id`  varchar(255) NOT NULL COMMENT  '证件号,需要验证、脱敏、形成所在地、年龄、性别' ,
  `name`  varchar(255) NOT NULL COMMENT  '客户姓名' ,
  `phone`  varchar(255) NOT NULL COMMENT  '联系电话' ,
  `product_id`  int(11) NOT NULL COMMENT  '产品ID' ,
  `policy_number`  varchar(255) NOT NULL COMMENT  '单据号，可能是保单号，服务单据号等，对应客户+产品' ,
  `effective_date`  datetime NOT NULL COMMENT  '开始时间' ,
  `closing_date`  datetime NOT NULL COMMENT  '结束时间，默认为开始时间后一月，评价后就完成了，修改结束日期为当天' ,
  `status`  int(1) NOT NULL COMMENT  '状态，1 = 已触发待申请;导入cp_excel2 = 重新触发待申请;未通过后修改3 = 申请未通过;cp_excel导入客户-企业表中违反限制不能导入3 = 已申请待审核;excel导入customer_enterprise表4 = 重新申请待审核;服务方审核未通过然后采购方修改后状态5 = 审核未通过;服务方审核未通过5 = 审核通过可付费;服务方审核通过可多次用于付费6 = 付费完成待收款;批量付费完成取customer_enterprise表数据进入batch_fee7 = 未收款;批量付费完成服务方确认未收款7 = 确认收款服务中;已经收款进入customer_product8 = 服务完成;服务方-客户或采购方 9 = 服务完成且评价;评价就认为完成' ,
  `seq_number`  int(11) NULL COMMENT  '顺序号' ,
  `remark`  varchar(256) NULL COMMENT  '备注' ,
  `operator`  varchar(255) NULL COMMENT  '操作员' ,
  `create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT  '创建时间' ,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1  CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `complaint`;
CREATE TABLE `complaint` (

  `id`  int(11) NOT NULL AUTO_INCREMENT ,
  `cp_id`  int(11) NOT NULL COMMENT  '客户_产品ID' ,
  `ctype`  int(1) NOT NULL COMMENT  '类型' ,
  `remark`  varchar(255) NOT NULL COMMENT  '备注' ,
  `seq_number`  int(11)  NULL COMMENT  '顺序号' ,
  `operator`  varchar(255) NULL COMMENT  '操作员' ,
  `create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT  '创建时间' ,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1  CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;



