/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : cheanxin

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-02-16 16:27:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `dept`
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `enabled` tinyint(1) unsigned NOT NULL COMMENT '是否启用',
  `level` tinyint(1) unsigned NOT NULL COMMENT '部门层级',
  `name` varchar(50) NOT NULL COMMENT '部门名称',
  `parent_dept_id` int(10) unsigned NOT NULL COMMENT '上级部门',
  PRIMARY KEY (`id`),
  KEY `idx_parent_dept_id` (`parent_dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('1', '1234567890', '1', '1', '车安心', '0');
INSERT INTO `dept` VALUES ('2', '1234567890', '1', '2', '金融中心', '1');
INSERT INTO `dept` VALUES ('3', '1234567890', '1', '3', '华南', '2');

-- ----------------------------
-- Table structure for `dept_city`
-- ----------------------------
DROP TABLE IF EXISTS `dept_city`;
CREATE TABLE `dept_city` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `city_id` int(10) unsigned NOT NULL COMMENT '城市id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `creator_username` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `dept_id` int(10) unsigned NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_dept_id_city_id` (`dept_id`,`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept_city
-- ----------------------------
INSERT INTO `dept_city` VALUES ('1', '1', '1487230032', '273', '1');
INSERT INTO `dept_city` VALUES ('2', '3', '1487230032', '273', '1');
INSERT INTO `dept_city` VALUES ('3', '7', '1487230032', '273', '1');
INSERT INTO `dept_city` VALUES ('4', '90', '1487230032', '273', '1');

-- ----------------------------
-- Table structure for `loan`
-- ----------------------------
DROP TABLE IF EXISTS `loan`;
CREATE TABLE `loan` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `applicant_address` varchar(200) DEFAULT NULL COMMENT '现居住地',
  `applicant_birth_year_month` char(6) DEFAULT NULL COMMENT '出生年月',
  `applicant_census` varchar(20) DEFAULT NULL COMMENT '户籍',
  `applicant_certificate_file_ids` varchar(100) NOT NULL COMMENT '证件图片',
  `applicant_certificate_number` varchar(20) NOT NULL COMMENT '证件号码',
  `applicant_certificate_type` tinyint(2) unsigned NOT NULL COMMENT '证件类型',
  `applicant_employer_address` varchar(200) DEFAULT NULL COMMENT '单位居住地',
  `applicant_employer_industry` varchar(100) DEFAULT NULL COMMENT '工作单位所属行业',
  `applicant_employer_name` varchar(200) DEFAULT NULL COMMENT '工作单位名称',
  `applicant_employer_telephone` varchar(20) DEFAULT NULL COMMENT '单位电话',
  `applicant_employer_type` varchar(50) DEFAULT NULL COMMENT '工作单位性质(国企、私企、外企等等)',
  `applicant_estate_file_ids` varchar(200) DEFAULT NULL COMMENT '房产图片',
  `applicant_first_emergency_contact` varchar(30) DEFAULT NULL COMMENT '紧急联系人1',
  `applicant_first_emergency_contact_address` varchar(200) DEFAULT NULL COMMENT '紧急联系人1地址',
  `applicant_first_emergency_contact_mobile_number` char(11) DEFAULT NULL COMMENT '紧急联系人1手机号码',
  `applicant_first_emergency_contact_relationship` varchar(10) DEFAULT NULL COMMENT '紧急联系人1与申请人的关系',
  `applicant_gender` tinyint(1) unsigned DEFAULT NULL COMMENT '性别',
  `applicant_income_file_ids` varchar(500) DEFAULT NULL COMMENT '收入证明图片',
  `applicant_income_per_month` int(10) NOT NULL COMMENT '月收入',
  `applicant_job_title` varchar(50) DEFAULT NULL COMMENT '申请人职称',
  `applicant_marriage` tinyint(1) unsigned NOT NULL COMMENT '婚姻状态',
  `applicant_mobile_number` char(11) NOT NULL COMMENT '申请人手机号',
  `applicant_name` varchar(30) NOT NULL COMMENT '贷款申请人姓名',
  `applicant_occupation` varchar(50) DEFAULT NULL COMMENT '申请人职业',
  `applicant_other_file_ids` varchar(500) DEFAULT NULL COMMENT '其他图片',
  `applicant_position` varchar(50) DEFAULT NULL COMMENT '申请人职务',
  `applicant_post` varchar(50) DEFAULT NULL COMMENT '申请人工作岗位',
  `applicant_post_address` varchar(200) DEFAULT NULL COMMENT '邮寄地址',
  `applicant_qualification` varchar(30) DEFAULT NULL COMMENT '学历',
  `applicant_qualification_file_ids` varchar(200) DEFAULT NULL COMMENT '学历证明图片',
  `applicant_second_emergency_contact` varchar(30) DEFAULT NULL COMMENT '紧急联系人2',
  `applicant_second_emergency_contact_address` varchar(200) DEFAULT NULL COMMENT '紧急联系人2地址',
  `applicant_second_emergency_contact_mobile_number` char(11) DEFAULT NULL COMMENT '紧急联系人2手机号码',
  `applicant_second_emergency_contact_relationship` varchar(10) DEFAULT NULL COMMENT '紧急联系人1与申请人的关系',
  `applicant_telephone` varchar(20) DEFAULT NULL COMMENT '申请人电话',
  `applicant_vehicle_file_ids` varchar(200) DEFAULT NULL COMMENT '车辆图片',
  `applicant_work_years` tinyint(2) unsigned DEFAULT NULL COMMENT '申请人工作年限',
  `application_pic_url` varchar(1000) DEFAULT NULL COMMENT '申请图片列表',
  `co_applicant_address` varchar(200) DEFAULT NULL COMMENT '现居住地',
  `co_applicant_census` varchar(20) DEFAULT NULL COMMENT '户籍',
  `co_applicant_certificate_file_ids` varchar(100) DEFAULT NULL COMMENT '证件图片',
  `co_applicant_certificate_number` varchar(20) DEFAULT NULL COMMENT '证件号码',
  `co_applicant_certificate_type` tinyint(2) unsigned DEFAULT NULL COMMENT '证件类型',
  `co_applicant_employer_address` varchar(200) DEFAULT NULL COMMENT '单位居住地',
  `co_applicant_employer_name` varchar(200) DEFAULT NULL COMMENT '工作单位名称',
  `co_applicant_employer_telephone` varchar(20) DEFAULT NULL COMMENT '单位电话',
  `co_applicant_estate_file_ids` varchar(200) DEFAULT NULL COMMENT '房产图片',
  `co_applicant_income_file_ids` varchar(500) DEFAULT NULL COMMENT '收入证明图片',
  `co_applicant_income_per_month` int(10) DEFAULT NULL COMMENT '月收入',
  `co_applicant_mobile_number` char(11) DEFAULT NULL COMMENT '共同申请人手机号',
  `co_applicant_name` varchar(200) DEFAULT NULL COMMENT '共同申请人姓名',
  `co_applicant_other_file_ids` varchar(500) DEFAULT NULL COMMENT '其他图片',
  `co_applicant_qualification` varchar(30) DEFAULT NULL COMMENT '学历',
  `co_applicant_telephone` varchar(20) DEFAULT NULL COMMENT '共同申请人电话',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '贷款创建时间',
  `creator_username` varchar(20) DEFAULT NULL COMMENT '创建贷款草稿用户',
  `guarantor_certificate_file_ids` varchar(100) DEFAULT NULL COMMENT '证件图片',
  `guarantor_estate_file_ids` varchar(200) DEFAULT NULL COMMENT '房产图片',
  `guarantor_income_file_ids` varchar(500) DEFAULT NULL COMMENT '收入证明图片',
  `guarantor_other_file_ids` varchar(500) DEFAULT NULL COMMENT '其他图片',
  `loan_draft_id` int(10) unsigned NOT NULL COMMENT '贷款草稿id',
  `loan_monthly_interest_rate` decimal(6,4) unsigned NOT NULL COMMENT '贷款月利率',
  `loan_rate` tinyint(2) unsigned NOT NULL COMMENT '贷款比率',
  `loan_terms` smallint(3) unsigned NOT NULL COMMENT '贷款期数',
  `modified_time` int(10) unsigned DEFAULT NULL COMMENT '修改时间',
  `prepayment_penalty_rate` decimal(6,4) unsigned NOT NULL COMMENT '提前还款违约金比例',
  `product_id` int(10) unsigned NOT NULL COMMENT '产品id',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `source_channel` tinyint(2) unsigned NOT NULL COMMENT '来源渠道',
  `source_city_id` int(10) unsigned NOT NULL COMMENT '城市id',
  `source_financial_commissioner` varchar(30) NOT NULL COMMENT '跟单金融专员',
  `source_person_name` varchar(30) NOT NULL COMMENT '来源联系人姓名',
  `source_person_tel` varchar(20) NOT NULL COMMENT '来源联系人电话',
  `source_receiver` varchar(30) NOT NULL COMMENT '收单员',
  `status` tinyint(3) unsigned NOT NULL COMMENT '贷款状态',
  `vehicle_brand` varchar(20) NOT NULL COMMENT '品牌',
  `vehicle_deal_price` int(10) unsigned NOT NULL COMMENT '车辆成交价格',
  `vehicle_emission` varchar(10) DEFAULT NULL COMMENT '排放标准',
  `vehicle_file_ids` varchar(2000) NOT NULL COMMENT '车辆图片',
  `vehicle_kilometers` decimal(7,4) unsigned NOT NULL COMMENT '公里数',
  `vehicle_license_file_ids` varchar(100) NOT NULL COMMENT '机动车行驶证图片',
  `vehicle_manufacturers` varchar(20) NOT NULL COMMENT '厂家',
  `vehicle_production_year_month` varchar(10) DEFAULT NULL COMMENT '生产年月',
  `vehicle_registration_certificate_file_ids` varchar(100) NOT NULL COMMENT '车辆登记证书图片',
  `vehicle_registration_year_month` varchar(10) DEFAULT NULL COMMENT '首次登记年月',
  `vehicle_series` varchar(50) NOT NULL COMMENT '车系',
  `vehicle_utility_type` varchar(20) DEFAULT NULL COMMENT '使用性质',
  `vehicle_vin` char(17) NOT NULL COMMENT '车辆vin码',
  PRIMARY KEY (`id`),
  KEY `idx_applicantName` (`applicant_name`),
  KEY `idx_applicant_mobile_number` (`applicant_mobile_number`),
  KEY `idx_source_financial_commissioner` (`source_financial_commissioner`),
  KEY `idx_created_time` (`created_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of loan
-- ----------------------------

-- ----------------------------
-- Table structure for `loan_draft`
-- ----------------------------
DROP TABLE IF EXISTS `loan_draft`;
CREATE TABLE `loan_draft` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `applicant_address` varchar(200) DEFAULT NULL COMMENT '现居住地',
  `applicant_birth_year_month` char(6) DEFAULT NULL COMMENT '出生年月',
  `applicant_census` varchar(20) DEFAULT NULL COMMENT '户籍',
  `applicant_certificate_file_ids` varchar(100) DEFAULT NULL COMMENT '证件图片',
  `applicant_certificate_number` varchar(20) DEFAULT NULL COMMENT '证件号码',
  `applicant_certificate_type` tinyint(2) unsigned DEFAULT NULL COMMENT '证件类型',
  `applicant_employer_address` varchar(200) DEFAULT NULL COMMENT '单位居住地',
  `applicant_employer_industry` varchar(100) DEFAULT NULL COMMENT '工作单位所属行业',
  `applicant_employer_name` varchar(200) DEFAULT NULL COMMENT '工作单位名称',
  `applicant_employer_telephone` varchar(20) DEFAULT NULL COMMENT '单位电话',
  `applicant_employer_type` varchar(50) DEFAULT NULL COMMENT '工作单位性质(国企、私企、外企等等)',
  `applicant_estate_file_ids` varchar(200) DEFAULT NULL COMMENT '房产图片',
  `applicant_first_emergency_contact` varchar(30) DEFAULT NULL COMMENT '紧急联系人1',
  `applicant_first_emergency_contact_address` varchar(200) DEFAULT NULL COMMENT '紧急联系人1地址',
  `applicant_first_emergency_contact_mobile_number` char(11) DEFAULT NULL COMMENT '紧急联系人1手机号码',
  `applicant_first_emergency_contact_relationship` varchar(10) DEFAULT NULL COMMENT '紧急联系人1与申请人的关系',
  `applicant_gender` tinyint(1) unsigned DEFAULT NULL COMMENT '性别',
  `applicant_income_file_ids` varchar(500) DEFAULT NULL COMMENT '收入证明图片',
  `applicant_income_per_month` int(10) DEFAULT NULL COMMENT '月收入',
  `applicant_job_title` varchar(50) DEFAULT NULL COMMENT '申请人职称',
  `applicant_marriage` tinyint(1) unsigned DEFAULT NULL COMMENT '婚姻状态',
  `applicant_mobile_number` char(11) DEFAULT NULL COMMENT '申请人手机号',
  `applicant_name` varchar(30) DEFAULT NULL COMMENT '贷款申请人姓名',
  `applicant_occupation` varchar(50) DEFAULT NULL COMMENT '申请人职业',
  `applicant_other_file_ids` varchar(500) DEFAULT NULL COMMENT '其他图片',
  `applicant_position` varchar(50) DEFAULT NULL COMMENT '申请人职务',
  `applicant_post` varchar(50) DEFAULT NULL COMMENT '申请人工作岗位',
  `applicant_post_address` varchar(200) DEFAULT NULL COMMENT '邮寄地址',
  `applicant_qualification` varchar(30) DEFAULT NULL COMMENT '学历',
  `applicant_qualification_file_ids` varchar(200) DEFAULT NULL COMMENT '学历证明图片',
  `applicant_second_emergency_contact` varchar(30) DEFAULT NULL COMMENT '紧急联系人2',
  `applicant_second_emergency_contact_address` varchar(200) DEFAULT NULL COMMENT '紧急联系人2地址',
  `applicant_second_emergency_contact_mobile_number` char(11) DEFAULT NULL COMMENT '紧急联系人2手机号码',
  `applicant_second_emergency_contact_relationship` varchar(10) DEFAULT NULL COMMENT '紧急联系人1与申请人的关系',
  `applicant_telephone` varchar(20) DEFAULT NULL COMMENT '申请人电话',
  `applicant_vehicle_file_ids` varchar(200) DEFAULT NULL COMMENT '车辆图片',
  `applicant_work_years` tinyint(2) unsigned DEFAULT NULL COMMENT '申请人工作年限',
  `application_pic_url` varchar(1000) DEFAULT NULL COMMENT '申请图片列表',
  `co_applicant_address` varchar(200) DEFAULT NULL COMMENT '现居住地',
  `co_applicant_census` varchar(20) DEFAULT NULL COMMENT '户籍',
  `co_applicant_certificate_file_ids` varchar(100) DEFAULT NULL COMMENT '证件图片',
  `co_applicant_certificate_number` varchar(20) DEFAULT NULL COMMENT '证件号码',
  `co_applicant_certificate_type` tinyint(2) unsigned DEFAULT NULL COMMENT '证件类型',
  `co_applicant_employer_address` varchar(200) DEFAULT NULL COMMENT '单位居住地',
  `co_applicant_employer_name` varchar(200) DEFAULT NULL COMMENT '工作单位名称',
  `co_applicant_employer_telephone` varchar(20) DEFAULT NULL COMMENT '单位电话',
  `co_applicant_estate_file_ids` varchar(200) DEFAULT NULL COMMENT '房产图片',
  `co_applicant_income_file_ids` varchar(500) DEFAULT NULL COMMENT '收入证明图片',
  `co_applicant_income_per_month` int(10) DEFAULT NULL COMMENT '月收入',
  `co_applicant_mobile_number` char(11) DEFAULT NULL COMMENT '共同申请人手机号',
  `co_applicant_name` varchar(200) DEFAULT NULL COMMENT '共同申请人姓名',
  `co_applicant_other_file_ids` varchar(500) DEFAULT NULL COMMENT '其他图片',
  `co_applicant_qualification` varchar(30) DEFAULT NULL COMMENT '学历',
  `co_applicant_telephone` varchar(20) DEFAULT NULL COMMENT '共同申请人电话',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `creator_username` varchar(20) DEFAULT NULL COMMENT '创建贷款草稿用户',
  `guarantor_certificate_file_ids` varchar(100) DEFAULT NULL COMMENT '证件图片',
  `guarantor_estate_file_ids` varchar(200) DEFAULT NULL COMMENT '房产图片',
  `guarantor_income_file_ids` varchar(500) DEFAULT NULL COMMENT '收入证明图片',
  `guarantor_other_file_ids` varchar(500) DEFAULT NULL COMMENT '其他图片',
  `loan_monthly_interest_rate` decimal(6,4) unsigned DEFAULT NULL COMMENT '贷款月利率',
  `loan_rate` tinyint(2) unsigned DEFAULT NULL COMMENT '贷款比率',
  `loan_terms` smallint(3) unsigned DEFAULT NULL COMMENT '贷款期数',
  `modified_time` int(10) unsigned DEFAULT NULL COMMENT '修改时间',
  `prepayment_penalty_rate` decimal(6,4) unsigned DEFAULT NULL COMMENT '提前还款违约金比例',
  `product_id` int(10) unsigned DEFAULT NULL COMMENT '产品id',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `source_channel` tinyint(2) unsigned DEFAULT NULL COMMENT '来源渠道',
  `source_city_id` int(10) unsigned DEFAULT NULL COMMENT '城市id',
  `source_financial_commissioner` varchar(30) DEFAULT NULL COMMENT '跟单金融专员',
  `source_person_name` varchar(30) DEFAULT NULL COMMENT '来源联系人姓名',
  `source_person_tel` varchar(20) DEFAULT NULL COMMENT '来源联系人电话',
  `source_receiver` varchar(30) DEFAULT NULL COMMENT '收单员',
  `status` tinyint(2) unsigned DEFAULT NULL COMMENT '贷款草稿状态',
  `vehicle_brand` varchar(20) DEFAULT NULL COMMENT '品牌',
  `vehicle_deal_price` int(10) unsigned DEFAULT NULL COMMENT '车辆成交价格',
  `vehicle_emission` varchar(10) DEFAULT NULL COMMENT '排放标准',
  `vehicle_file_ids` varchar(2000) DEFAULT NULL COMMENT '车辆图片',
  `vehicle_kilometers` decimal(7,4) unsigned DEFAULT NULL COMMENT '公里数',
  `vehicle_license_file_ids` varchar(100) DEFAULT NULL COMMENT '机动车行驶证图片',
  `vehicle_manufacturers` varchar(20) DEFAULT NULL COMMENT '厂家',
  `vehicle_production_year_month` varchar(10) DEFAULT NULL COMMENT '生产年月',
  `vehicle_registration_certificate_file_ids` varchar(100) DEFAULT NULL COMMENT '车辆登记证书图片',
  `vehicle_registration_year_month` varchar(10) DEFAULT NULL COMMENT '首次登记年月',
  `vehicle_series` varchar(50) DEFAULT NULL COMMENT '车系',
  `vehicle_utility_type` varchar(20) DEFAULT NULL COMMENT '使用性质',
  `vehicle_vin` char(17) DEFAULT NULL COMMENT '车辆vin码',
  PRIMARY KEY (`id`),
  KEY `idx_creator_username` (`creator_username`),
  KEY `idx_modified_time` (`modified_time`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of loan_draft
-- ----------------------------
INSERT INTO `loan_draft` VALUES ('1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1487151283', '273', null, null, null, null, null, null, null, '1487151283', null, '2', null, null, null, null, null, null, null, '1', null, '10000', null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `loan_log`
-- ----------------------------
DROP TABLE IF EXISTS `loan_log`;
CREATE TABLE `loan_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `loan_draft_id` int(10) unsigned NOT NULL COMMENT '贷款草稿箱id',
  `loan_id` int(10) unsigned DEFAULT NULL COMMENT '贷款id',
  `operator_type` tinyint(2) unsigned NOT NULL COMMENT '操作类型',
  `operator_username` varchar(20) NOT NULL COMMENT '操作人username',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_loan_id` (`loan_id`),
  KEY `idx_operator_username` (`operator_username`),
  KEY `idx_loan_draft_id` (`loan_draft_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of loan_log
-- ----------------------------

-- ----------------------------
-- Table structure for `post`
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `enabled` tinyint(1) unsigned NOT NULL COMMENT '是否启用',
  `name` varchar(20) NOT NULL COMMENT '岗位名称',
  `post_type_id` int(10) unsigned NOT NULL COMMENT '岗位类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('1', '1234567890', '1', '金融专员', '3');
INSERT INTO `post` VALUES ('2', '1234567890', '1', '意向分配员', '3');
INSERT INTO `post` VALUES ('3', '1234567890', '1', '大区经理', '2');
INSERT INTO `post` VALUES ('4', '1234567890', '1', 'ADMIN', '0');

-- ----------------------------
-- Table structure for `post_authority`
-- ----------------------------
DROP TABLE IF EXISTS `post_authority`;
CREATE TABLE `post_authority` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `authority` varchar(64) NOT NULL COMMENT '权限(等同于角色)',
  `post_id` int(10) unsigned NOT NULL COMMENT '岗位id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_post_id_authority` (`post_id`,`authority`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post_authority
-- ----------------------------
INSERT INTO `post_authority` VALUES ('25', 'ROLE_CONTRACT_REJECTED_TO_LOAN_ABORTED', '4');
INSERT INTO `post_authority` VALUES ('24', 'ROLE_CONTRACT_REJECTED_TO_SECOND_REVIEW_PENDING', '4');
INSERT INTO `post_authority` VALUES ('3', 'ROLE_FIRST_DRAFT_TO_FIRST_DRAFT', '4');
INSERT INTO `post_authority` VALUES ('4', 'ROLE_FIRST_DRAFT_TO_SECOND_DRAFT', '4');
INSERT INTO `post_authority` VALUES ('12', 'ROLE_FIRST_REVIEW_ACCEPTED_TO_FIRST_REVIEW_ACCEPTED', '4');
INSERT INTO `post_authority` VALUES ('15', 'ROLE_FIRST_REVIEW_ACCEPTED_TO_SECOND_REVIEW_PENDING', '4');
INSERT INTO `post_authority` VALUES ('1', 'ROLE_PENDING_REVIEW_TO_ACCEPTED', '4');
INSERT INTO `post_authority` VALUES ('2', 'ROLE_PENDING_REVIEW_TO_REJECTED', '4');
INSERT INTO `post_authority` VALUES ('8', 'ROLE_SECOND_DRAFT_TO_DRAFT_ABORTED', '4');
INSERT INTO `post_authority` VALUES ('6', 'ROLE_SECOND_DRAFT_TO_DRAFT_ACCEPTED', '4');
INSERT INTO `post_authority` VALUES ('7', 'ROLE_SECOND_DRAFT_TO_DRAFT_REJECTED', '4');
INSERT INTO `post_authority` VALUES ('5', 'ROLE_SECOND_DRAFT_TO_SECOND_DRAFT', '4');
INSERT INTO `post_authority` VALUES ('22', 'ROLE_SECOND_REVIEW_ACCEPTED_TO_CONTRACT_ACCEPTED', '4');
INSERT INTO `post_authority` VALUES ('23', 'ROLE_SECOND_REVIEW_ACCEPTED_TO_CONTRACT_REJECTED', '4');
INSERT INTO `post_authority` VALUES ('16', 'ROLE_SECOND_REVIEW_PENDING_TO_SECOND_REVIEW_ACCEPTED', '4');
INSERT INTO `post_authority` VALUES ('19', 'ROLE_SECOND_REVIEW_PENDING_TO_SECOND_REVIEW_REJECTED', '4');
INSERT INTO `post_authority` VALUES ('21', 'ROLE_SECOND_REVIEW_REJECTED_TO_LOAN_ABORTED', '4');
INSERT INTO `post_authority` VALUES ('20', 'ROLE_SECOND_REVIEW_REJECTED_TO_SECOND_REVIEW_PENDING', '4');

-- ----------------------------
-- Table structure for `post_type`
-- ----------------------------
DROP TABLE IF EXISTS `post_type`;
CREATE TABLE `post_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(20) NOT NULL COMMENT '岗位类型名称',
  `sort_index` int(10) unsigned NOT NULL COMMENT '岗位类型排序索引',
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post_type
-- ----------------------------
INSERT INTO `post_type` VALUES ('1', '高层领导岗', '1', '1');
INSERT INTO `post_type` VALUES ('2', '中层领导岗', '2', '1');
INSERT INTO `post_type` VALUES ('3', '执行岗', '3', '1');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `available_terms` varchar(100) NOT NULL COMMENT '可贷期数',
  `city_id` int(10) unsigned NOT NULL COMMENT '城市id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `creator_username` varchar(20) DEFAULT NULL COMMENT '创建人',
  `loan_monthly_interest_rate` decimal(5,4) unsigned NOT NULL COMMENT '贷款月利率',
  `loan_policy` tinyint(2) unsigned NOT NULL COMMENT '贷款政策',
  `max_available_rate` tinyint(1) unsigned NOT NULL COMMENT '最高可贷成数',
  `min_available_rate` tinyint(1) unsigned NOT NULL COMMENT '最低可贷成数',
  `modified_time` int(10) unsigned DEFAULT NULL COMMENT '修改时间',
  `name` varchar(50) NOT NULL COMMENT '产品名称',
  `product_template_id` int(10) unsigned NOT NULL COMMENT '产品模板id，如果productTemplateId为0表示自身为产品模板',
  `product_type` tinyint(2) unsigned NOT NULL COMMENT '产品类型',
  `repayment_method` tinyint(2) unsigned NOT NULL COMMENT '还款类型',
  `status` tinyint(1) unsigned NOT NULL COMMENT '产品状态',
  PRIMARY KEY (`id`),
  KEY `idx_product_template_id` (`product_template_id`),
  KEY `idx_city_id` (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '6,12,24,36,48,60', '0', '1483778458', '273', '0.8000', '1', '9', '1', '1483778458', '车安心1号', '0', '1', '1', '0');
INSERT INTO `product` VALUES ('2', '6,12,24,36', '1', '1483778588', '273', '0.8200', '1', '9', '1', '1483778659', '车安心1号', '1', '1', '1', '1');
INSERT INTO `product` VALUES ('3', '6,12', '1', '1485329343', '273', '0.8100', '1', '8', '3', '1485329343', '车安心2号', '1', '1', '1', '2');

-- ----------------------------
-- Table structure for `product_log`
-- ----------------------------
DROP TABLE IF EXISTS `product_log`;
CREATE TABLE `product_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `operator_type` tinyint(1) unsigned NOT NULL COMMENT '操作类型',
  `operator_username` varchar(20) NOT NULL COMMENT '操作人username',
  `product_id` int(10) unsigned NOT NULL COMMENT '产品id',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_operator_username` (`operator_username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_log
-- ----------------------------
INSERT INTO `product_log` VALUES ('1', '1483780887', '1', '273', '2', 'nice');
INSERT INTO `product_log` VALUES ('2', '1485329395', '2', '273', '3', 'BAD');

-- ----------------------------
-- Table structure for `user`
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
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否启用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '福州gu\'lou\'qu鼓楼区', '1483578389', '1', 'jack163@163.com', '杰克他爸', '13452457689', '24313519381214192X', '/1/jack/identity_0.jpg', '14567884433', '35623d53e176367d6d211f5fe56e7fb79ec90ac674eac3674c43b73198ad7088e0c93b432f54cd14', '/1/jack/avatar.jpg', '杰克', '华南', '273', '1');
INSERT INTO `user` VALUES ('2', '福州gu\'lou\'qu鼓楼区', '1483772833', '1', 'jack163@163.com', '杰克他爸', '13452457689', '24313519381214192X', '/1/jack/identity_0.jpg', '14567884333', '27391db1a77b0a72a76073b461ba81f82d1ba02177e609adee3d92ff4947d2407d58329eb7dd49ef', '/1/jack/avatar.jpg', '杰克', '华南', '2731', '1');

-- ----------------------------
-- Table structure for `user_post`
-- ----------------------------
DROP TABLE IF EXISTS `user_post`;
CREATE TABLE `user_post` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `created_time` int(10) unsigned DEFAULT NULL COMMENT '创建时间',
  `post_id` int(10) unsigned NOT NULL COMMENT '岗位id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username_post_id` (`username`,`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_post
-- ----------------------------
INSERT INTO `user_post` VALUES ('1', '1234567890', '4', '273');
