/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 100110
 Source Host           : localhost
 Source Database       : cheanxin

 Target Server Type    : MySQL
 Target Server Version : 100110
 File Encoding         : utf-8

 Date: 01/03/2017 16:31:24 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `dept`
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `dept_code` varchar(10) NOT NULL COMMENT '部门编码',
  `is_diabled` tinyint(1) unsigned NOT NULL COMMENT '是否禁用',
  `name` varchar(50) NOT NULL COMMENT '部门名称',
  `parent_dept_id` int(10) unsigned NOT NULL COMMENT '上级部门',
  PRIMARY KEY (`id`),
  KEY `idx_parent_dept_id` (`parent_dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `loan`
-- ----------------------------
DROP TABLE IF EXISTS `loan`;
CREATE TABLE `loan` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `application_pic_url` varchar(1000) DEFAULT NULL COMMENT '申请图片列表',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '贷款创建时间',
  `ext_applicant_id` int(10) unsigned DEFAULT NULL COMMENT '贷款申请人id',
  `ext_co_applicant_id` int(10) unsigned DEFAULT NULL COMMENT '贷款共同申请人id',
  `ext_guarantor_id` int(10) unsigned DEFAULT NULL COMMENT '贷款担保人id',
  `ext_source_id` int(10) unsigned DEFAULT NULL COMMENT '贷款来源id',
  `ext_vehicle_id` int(10) unsigned DEFAULT NULL COMMENT '贷款车辆id',
  `load_terms` smallint(3) unsigned DEFAULT NULL COMMENT '贷款期数',
  `loan_monthly_interest_rate` decimal(6,4) unsigned DEFAULT NULL COMMENT '贷款月利率',
  `loan_rate` tinyint(2) unsigned DEFAULT NULL COMMENT '贷款比率',
  `product_id` int(10) unsigned DEFAULT NULL COMMENT '产品id，如果为空表示为该贷款为意向贷款',
  `product_name` varchar(50) DEFAULT NULL COMMENT '产品名称',
  `product_type` tinyint(2) unsigned DEFAULT NULL COMMENT '产品类型',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` tinyint(2) unsigned NOT NULL COMMENT '贷款状态',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `loan_applicant`
-- ----------------------------
DROP TABLE IF EXISTS `loan_applicant`;
CREATE TABLE `loan_applicant` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `applicant_name` int(10) unsigned NOT NULL COMMENT '自增id',
  `applicant_tel` varchar(20) NOT NULL COMMENT '申请人电话',
  `certificate_file_ids` varchar(100) NOT NULL COMMENT '证件图片',
  `certificate_number` varchar(20) NOT NULL COMMENT '证件号码',
  `certificate_type` tinyint(2) unsigned NOT NULL COMMENT '证件类型',
  `estate_file_ids` varchar(200) DEFAULT NULL COMMENT '房产图片',
  `income_file_ids` varchar(500) DEFAULT NULL COMMENT '收入证明图片',
  `income_per_month` int(10) NOT NULL COMMENT '月收入',
  `marriage` tinyint(1) unsigned NOT NULL COMMENT '婚姻状态',
  `other_file_ids` varchar(500) DEFAULT NULL COMMENT '其他图片',
  `qualification_file_ids` varchar(200) DEFAULT NULL COMMENT '学历证明图片',
  `vehicle_file_ids` varchar(200) DEFAULT NULL COMMENT '车辆图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `loan_co_applicant`
-- ----------------------------
DROP TABLE IF EXISTS `loan_co_applicant`;
CREATE TABLE `loan_co_applicant` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `certificate_file_ids` varchar(100) NOT NULL COMMENT '证件图片',
  `estate_file_ids` varchar(200) DEFAULT NULL COMMENT '房产图片',
  `income_file_ids` varchar(500) DEFAULT NULL COMMENT '收入证明图片',
  `other_file_ids` varchar(500) DEFAULT NULL COMMENT '其他图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `loan_guarantor`
-- ----------------------------
DROP TABLE IF EXISTS `loan_guarantor`;
CREATE TABLE `loan_guarantor` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `estate_file_ids` varchar(200) DEFAULT NULL COMMENT '房产图片',
  `income_file_ids` varchar(500) DEFAULT NULL COMMENT '收入证明图片',
  `other_file_ids` varchar(500) DEFAULT NULL COMMENT '其他图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `loan_log`
-- ----------------------------
DROP TABLE IF EXISTS `loan_log`;
CREATE TABLE `loan_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `loan_id` int(10) unsigned NOT NULL COMMENT '贷款id',
  `operator_type` tinyint(2) unsigned NOT NULL COMMENT '操作类型',
  `operator_uid` int(10) unsigned NOT NULL COMMENT '操作人uid',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_loan_id` (`loan_id`),
  KEY `idx_operator_uid` (`operator_uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `loan_source`
-- ----------------------------
DROP TABLE IF EXISTS `loan_source`;
CREATE TABLE `loan_source` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `application_source` tinyint(2) unsigned NOT NULL COMMENT '来源渠道',
  `city_id` int(10) unsigned NOT NULL COMMENT '城市id',
  `source_person_name` varchar(30) NOT NULL COMMENT '来源联系人姓名',
  `source_person_tel` varchar(20) NOT NULL COMMENT '来源联系人电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `loan_vehicle`
-- ----------------------------
DROP TABLE IF EXISTS `loan_vehicle`;
CREATE TABLE `loan_vehicle` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `brand` varchar(20) NOT NULL COMMENT '品牌',
  `kilometers` decimal(7,4) unsigned NOT NULL COMMENT '公里数',
  `license_file_ids` varchar(100) NOT NULL COMMENT '机动车行驶证图片',
  `manufacturers` varchar(20) NOT NULL COMMENT '厂家',
  `registration_certificate_file_ids` varchar(100) NOT NULL COMMENT '车辆登记证书图片',
  `series` varchar(50) NOT NULL COMMENT '车系',
  `vehicle_file_ids` varchar(2000) NOT NULL COMMENT '车辆图片',
  `vin` char(17) NOT NULL COMMENT '车辆vin码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `post`
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `is_diabled` tinyint(1) unsigned NOT NULL COMMENT '是否禁用',
  `name` varchar(20) NOT NULL COMMENT '岗位名称',
  `serial_number` char(3) NOT NULL COMMENT '岗位编号',
  `type` tinyint(1) unsigned NOT NULL COMMENT '岗位类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `post_authority`
-- ----------------------------
DROP TABLE IF EXISTS `post_authority`;
CREATE TABLE `post_authority` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `authority` varchar(32) NOT NULL COMMENT '权限(等同于角色)',
  `post_id` int(10) unsigned NOT NULL COMMENT '岗位id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_post_id_authority` (`post_id`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `available_terms` smallint(4) unsigned NOT NULL COMMENT '可贷期数',
  `city_id` int(10) unsigned NOT NULL COMMENT '城市id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `creator_uid` int(10) unsigned DEFAULT NULL COMMENT '创建人',
  `is_diabled` tinyint(1) unsigned NOT NULL COMMENT '是否禁用',
  `loan_benefit_per_month` decimal(6,4) unsigned NOT NULL COMMENT '贷款月利率',
  `loan_policy` tinyint(2) unsigned NOT NULL COMMENT '贷款政策',
  `max_available_rate` tinyint(1) unsigned NOT NULL COMMENT '最高可贷成数',
  `min_available_rate` tinyint(1) unsigned NOT NULL COMMENT '最低可贷成数',
  `modified_time` int(10) unsigned DEFAULT NULL COMMENT '修改时间',
  `name` varchar(50) NOT NULL COMMENT '产品名称',
  `payback_type` tinyint(2) unsigned NOT NULL COMMENT '还款类型',
  `product_template_id` int(10) unsigned NOT NULL COMMENT '产品模板id，如果该id为0表示为产品模板',
  `product_type` tinyint(2) unsigned NOT NULL COMMENT '产品类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `product_log`
-- ----------------------------
DROP TABLE IF EXISTS `product_log`;
CREATE TABLE `product_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `operator_type` tinyint(1) unsigned NOT NULL COMMENT '操作类型',
  `operator_uid` int(10) unsigned NOT NULL COMMENT '操作人uid',
  `product_id` int(10) unsigned NOT NULL COMMENT '产品id',
  `remark` int(10) unsigned DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_operator_uid` (`operator_uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `address` varchar(300) NOT NULL COMMENT '地址',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `dept_id` int(10) unsigned NOT NULL COMMENT '部门id',
  `email` varchar(64) NOT NULL COMMENT '邮箱',
  `emergency_contact` varchar(30) NOT NULL COMMENT '紧急联系人',
  `emergency_contact_mobile_number` char(11) NOT NULL COMMENT '紧急联系人手机号码',
  `identity_number` char(18) NOT NULL COMMENT '身份证号码',
  `identity_photo` varchar(100) NOT NULL COMMENT '身份证照片',
  `mobile_number` char(11) NOT NULL COMMENT '手机号',
  `password` char(80) NOT NULL COMMENT '用户密码（登录用）,使用Spring Security的BaseEncoder加密',
  `photo` varchar(50) NOT NULL COMMENT '用户照片',
  `real_name` varchar(30) NOT NULL COMMENT '用户姓名',
  `region` varchar(20) NOT NULL COMMENT '地区',
  `username` varchar(20) NOT NULL COMMENT '用户账号（登录用）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user_post`
-- ----------------------------
DROP TABLE IF EXISTS `user_post`;
CREATE TABLE `user_post` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `post_id` int(10) unsigned NOT NULL COMMENT '岗位id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username_post_id` (`username`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
