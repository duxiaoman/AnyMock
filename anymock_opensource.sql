/*
 Navicat Premium Data Transfer

 Source Server         : cp01-ocean-2480.epc.baidu.com-8306
 Source Server Type    : MySQL
 Source Server Version : 50530
 Source Host           : cp01-ocean-2480.epc.baidu.com:8306
 Source Schema         : anymock_opensource

 Target Server Type    : MySQL
 Target Server Version : 50530
 File Encoding         : 65001

 Date: 11/03/2019 21:35:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for am_http_interface
-- ----------------------------
DROP TABLE IF EXISTS `am_http_interface`;
CREATE TABLE `am_http_interface`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `request_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `request_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `need_async_callback` tinyint(1) NOT NULL,
  `config_mode` int(11) NOT NULL,
  `response_body` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `callback_request_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `callback_request_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `callback_request_body` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `branch_jump_script` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sync_script` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `async_script` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sync_delay` int(11) NOT NULL,
  `async_delay` int(11) NOT NULL,
  `start` tinyint(1) NOT NULL,
  `space_id` bigint(20) NOT NULL,
  `last_update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_update_time` datetime NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_request_mapping`(`request_uri`, `request_method`) USING BTREE,
  INDEX `idx_sub_space_id`(`space_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 78 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for am_http_interface_branch_script
-- ----------------------------
DROP TABLE IF EXISTS `am_http_interface_branch_script`;
CREATE TABLE `am_http_interface_branch_script`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `http_interface_id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sync_script` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `async_script` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`http_interface_id`, `name`) USING BTREE,
  INDEX `idx_http_interface_id`(`http_interface_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 152 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for am_http_interface_callback_request_header
-- ----------------------------
DROP TABLE IF EXISTS `am_http_interface_callback_request_header`;
CREATE TABLE `am_http_interface_callback_request_header`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `http_interface_id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`http_interface_id`, `name`) USING BTREE,
  INDEX `idx_http_interface_id`(`http_interface_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 150 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for am_http_interface_response_header
-- ----------------------------
DROP TABLE IF EXISTS `am_http_interface_response_header`;
CREATE TABLE `am_http_interface_response_header`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `http_interface_id` bigint(20) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`http_interface_id`, `name`) USING BTREE,
  INDEX `idx_http_interface_id`(`http_interface_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 174 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for am_http_interface_snapshot
-- ----------------------------
DROP TABLE IF EXISTS `am_http_interface_snapshot`;
CREATE TABLE `am_http_interface_snapshot`  (
  `http_interface_id` bigint(20) NOT NULL,
  `http_interface_request_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `http_interface_request_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `http_interface_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `http_interface_need_async_callback` tinyint(1) NOT NULL,
  `http_interface_config_mode` int(11) NOT NULL,
  `http_interface_response_body` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `http_interface_callback_request_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `http_interface_callback_request_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `http_interface_callback_request_body` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `http_interface_branch_jump_script` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `http_interface_sync_script` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `http_interface_async_script` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `http_interface_sync_delay` int(11) NOT NULL,
  `http_interface_async_delay` int(11) NOT NULL,
  `http_interface_start` tinyint(1) NOT NULL,
  `http_interface_space_id` bigint(20) NOT NULL,
  `http_interface_response_header_list` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `http_interface_callback_request_header_list` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `http_interface_branch_script_list` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `http_interface_last_update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `http_interface_last_update_time` datetime NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `op_type` int(11) NOT NULL,
  `op_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `snapshot_time` datetime NOT NULL,
  `http_interface_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_id`(`http_interface_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 237 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for am_space
-- ----------------------------
DROP TABLE IF EXISTS `am_space`;
CREATE TABLE `am_space`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parent_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_label`(`label`, `parent_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
