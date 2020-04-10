/*
 Navicat Premium Data Transfer

 Source Server         : localhost-3306
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : anymock_opensource

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 27/03/2019 20:36:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for anymock_http_interface
-- ----------------------------
DROP TABLE IF EXISTS `anymock_http_interface`;
CREATE TABLE `anymock_http_interface`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `request_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `request_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `need_async_callback` tinyint(1) NOT NULL,
  `config_mode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `response_body` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `callback_request_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `callback_request_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `callback_request_body` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `branch_jump_script` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sync_script` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `async_script` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sync_delay` int(11) NOT NULL,
  `async_delay` int(11) NOT NULL,
  `is_start` tinyint(1) NOT NULL,
  `space_id` bigint(20) NOT NULL,
  `last_update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `access_authority` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_request_mapping`(`request_uri`, `request_method`) USING BTREE,
  INDEX `idx_space_id`(`space_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 98 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for anymock_interface_branch
-- ----------------------------
DROP TABLE IF EXISTS `anymock_interface_branch`;
CREATE TABLE `anymock_interface_branch`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `interface_id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sync_script` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `async_script` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`http_interface_id`, `name`) USING BTREE,
  INDEX `idx_http_interface_id`(`http_interface_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 159 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for anymock_http_interface_header
-- ----------------------------
DROP TABLE IF EXISTS `anymock_http_interface_header`;
CREATE TABLE `anymock_http_interface_header`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `http_interface_id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`http_interface_id`, `name`, `type`) USING BTREE,
  INDEX `idx_http_interface_id`(`http_interface_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 180 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for anymock_http_interface_snapshot
-- ----------------------------
DROP TABLE IF EXISTS `anymock_http_interface_snapshot`;
CREATE TABLE `anymock_http_interface_snapshot`  (
  `http_interface_id` bigint(20) NOT NULL,
  `http_interface_request_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `http_interface_request_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `http_interface_space_id` bigint(20) NOT NULL,
  `http_interface` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `op_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_id`(`http_interface_id`) USING BTREE,
  INDEX `idx_request_mapping`(`http_interface_request_uri`, `http_interface_request_method`) USING BTREE,
  INDEX `idx_space_id`(`http_interface_space_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 290 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for anymock_space
-- ----------------------------
DROP TABLE IF EXISTS `anymock_space`;
CREATE TABLE `anymock_space`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parent_id` bigint(20) NOT NULL,
  `access_authority` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `level` int(11) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_label`(`label`, `parent_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;


CREATE TABLE `anymock_tcp_interface` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `request_uri` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `need_async_callback` tinyint(1) NOT NULL,
  `config_mode` varchar(255) NOT NULL,
  `response_body` mediumtext NOT NULL,
  `callback_request_url` varchar(255) NOT NULL,
  `callback_request_body` mediumtext NOT NULL,
  `branch_jump_script` mediumtext NOT NULL,
  `sync_script` mediumtext NOT NULL,
  `async_script` mediumtext NOT NULL,
  `sync_delay` int(11) NOT NULL,
  `async_delay` int(11) NOT NULL,
  `is_start` tinyint(1) NOT NULL,
  `space_id` bigint(20) NOT NULL,
  `last_update_user` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `access_authority` varchar(255) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_request_mapping` (`request_uri`) USING BTREE,
  KEY `idx_space_id` (`space_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
 
 
CREATE TABLE `anymock_tcp_interface_snapshot` (
  `tcp_interface_id` bigint(20) NOT NULL,
  `tcp_interface_request_uri` varchar(255) NOT NULL,
  `tcp_interface_space_id` bigint(20) NOT NULL,
  `tcp_interface` mediumtext NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `op_type` varchar(255) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_id` (`tcp_interface_id`) USING BTREE,
  KEY `idx_request_mapping` (`tcp_interface_request_uri`) USING BTREE,
  KEY `idx_space_id` (`tcp_interface_space_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=290 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
