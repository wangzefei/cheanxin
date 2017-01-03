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

 Date: 01/03/2017 15:05:07 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `dept`
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `dept_code` varchar(10) NOT NULL,
  `is_diabled` bit(1) NOT NULL,
  `name` varchar(50) NOT NULL,
  `parent_dept_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_parent_dept_id` (`parent_dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `loan`
-- ----------------------------
DROP TABLE IF EXISTS `loan`;
CREATE TABLE `loan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_pic_url` longtext,
  `created_time` bigint(20) DEFAULT NULL,
  `ext_applicant_id` bigint(20) DEFAULT NULL,
  `ext_co_applicant_id` bigint(20) DEFAULT NULL,
  `ext_guarantor_id` bigint(20) DEFAULT NULL,
  `ext_source_id` bigint(20) DEFAULT NULL,
  `ext_vehicle_id` bigint(20) DEFAULT NULL,
  `load_terms` int(11) DEFAULT NULL,
  `loan_monthly_interest_rate` float DEFAULT NULL,
  `loan_rate` int(11) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `product_name` varchar(50) DEFAULT NULL,
  `product_type` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `loan_applicant`
-- ----------------------------
DROP TABLE IF EXISTS `loan_applicant`;
CREATE TABLE `loan_applicant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `applicant_name` varchar(20) NOT NULL,
  `applicant_tel` varchar(20) NOT NULL,
  `certificate_file_ids` varchar(100) NOT NULL,
  `certificate_num` varchar(20) NOT NULL,
  `certificate_type` int(11) NOT NULL,
  `estate_file_ids` varchar(200) DEFAULT NULL,
  `income_file_ids` longtext,
  `income_per_month` bigint(20) NOT NULL,
  `marriage` int(11) NOT NULL,
  `other_file_ids` longtext,
  `qualification_file_ids` varchar(200) DEFAULT NULL,
  `vehicle_file_ids` varchar(200) DEFAULT NULL,
  `certificate_number` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `loan_co_applicant`
-- ----------------------------
DROP TABLE IF EXISTS `loan_co_applicant`;
CREATE TABLE `loan_co_applicant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `certificate_file_ids` varchar(200) NOT NULL,
  `estate_file_ids` varchar(200) DEFAULT NULL,
  `income_file_ids` longtext,
  `other_file_ids` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `loan_guarantor`
-- ----------------------------
DROP TABLE IF EXISTS `loan_guarantor`;
CREATE TABLE `loan_guarantor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `estate_file_ids` varchar(200) DEFAULT NULL,
  `income_file_ids` longtext,
  `other_file_ids` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `loan_log`
-- ----------------------------
DROP TABLE IF EXISTS `loan_log`;
CREATE TABLE `loan_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `loan_id` bigint(20) NOT NULL,
  `operator_type` int(11) NOT NULL,
  `operator_uid` bigint(20) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_loan_id` (`loan_id`),
  KEY `idx_operator_uid` (`operator_uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `loan_source`
-- ----------------------------
DROP TABLE IF EXISTS `loan_source`;
CREATE TABLE `loan_source` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_source` int(11) NOT NULL,
  `city_id` bigint(20) NOT NULL,
  `source_person_name` varchar(30) NOT NULL,
  `source_person_tel` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `loan_vehicle`
-- ----------------------------
DROP TABLE IF EXISTS `loan_vehicle`;
CREATE TABLE `loan_vehicle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand` varchar(20) NOT NULL,
  `kilometers` float NOT NULL,
  `license_file_ids` varchar(100) NOT NULL,
  `manufacturers` varchar(20) NOT NULL,
  `registration_certificate_file_ids` varchar(100) NOT NULL,
  `series` varchar(50) NOT NULL,
  `vehicle_file_ids` longtext NOT NULL,
  `vin` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `post`
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `is_diabled` bit(1) NOT NULL,
  `name` varchar(20) NOT NULL,
  `serial_number` varchar(3) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `post_authority`
-- ----------------------------
DROP TABLE IF EXISTS `post_authority`;
CREATE TABLE `post_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(32) NOT NULL,
  `post_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_post_id_authority` (`post_id`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available_terms` varchar(50) NOT NULL,
  `city_id` bigint(20) NOT NULL,
  `created_time` bigint(20) DEFAULT NULL,
  `creator_uid` bigint(20) DEFAULT NULL,
  `is_diabled` bit(1) NOT NULL,
  `loan_benifit_per_month` varchar(50) NOT NULL,
  `loan_policy` int(11) NOT NULL,
  `max_available_rate` int(11) NOT NULL,
  `min_available_rate` int(11) NOT NULL,
  `modified_time` bigint(20) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `payback_type` int(11) NOT NULL,
  `product_template_id` bigint(20) NOT NULL,
  `product_type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `product_log`
-- ----------------------------
DROP TABLE IF EXISTS `product_log`;
CREATE TABLE `product_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `operator_type` int(11) NOT NULL,
  `operator_uid` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_operator_uid` (`operator_uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` longtext NOT NULL,
  `created_time` bigint(20) DEFAULT NULL,
  `dept_id` bigint(20) NOT NULL,
  `email` varchar(64) NOT NULL,
  `emergency_contact` varchar(30) NOT NULL,
  `emergency_contact_mobile_number` varchar(11) NOT NULL,
  `identity_number` varchar(18) NOT NULL,
  `identity_photo` varchar(100) NOT NULL,
  `mobile_number` varchar(11) NOT NULL,
  `password` varchar(128) NOT NULL,
  `photo` varchar(50) NOT NULL,
  `real_name` varchar(30) NOT NULL,
  `region` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user_post`
-- ----------------------------
DROP TABLE IF EXISTS `user_post`;
CREATE TABLE `user_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_time` bigint(20) DEFAULT NULL,
  `post_id` bigint(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username_post_id` (`username`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
