# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: localhost (MySQL 5.6.34)
# Database: anymock
# Generation Time: 2019-12-12 10:35:46 +0000
# ************************************************************


# Dump of table anymock_http_interface
# ------------------------------------------------------------

DROP TABLE IF EXISTS `anymock_http_interface`;

CREATE TABLE `anymock_http_interface` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `request_uri` varchar(255) NOT NULL,
  `request_method` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `need_async_callback` tinyint(1) NOT NULL,
  `config_mode` varchar(255) NOT NULL,
  `response_body` mediumtext NOT NULL,
  `callback_request_url` varchar(255) NOT NULL,
  `callback_request_method` varchar(255) NOT NULL,
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
  UNIQUE KEY `uk_request_mapping` (`request_uri`,`request_method`) USING BTREE,
  KEY `idx_space_id` (`space_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;



# Dump of table anymock_http_interface_header
# ------------------------------------------------------------

DROP TABLE IF EXISTS `anymock_http_interface_header`;

CREATE TABLE `anymock_http_interface_header` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `http_interface_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_name` (`http_interface_id`,`name`,`type`) USING BTREE,
  KEY `idx_http_interface_id` (`http_interface_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;



# Dump of table anymock_http_interface_snapshot
# ------------------------------------------------------------

DROP TABLE IF EXISTS `anymock_http_interface_snapshot`;

CREATE TABLE `anymock_http_interface_snapshot` (
  `http_interface_id` bigint(20) NOT NULL,
  `http_interface_request_uri` varchar(255) NOT NULL,
  `http_interface_request_method` varchar(255) NOT NULL,
  `http_interface_space_id` bigint(20) NOT NULL,
  `http_interface` mediumtext NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `op_type` varchar(255) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_id` (`http_interface_id`) USING BTREE,
  KEY `idx_request_mapping` (`http_interface_request_uri`,`http_interface_request_method`) USING BTREE,
  KEY `idx_space_id` (`http_interface_space_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;



# Dump of table anymock_interface_branch
# ------------------------------------------------------------

DROP TABLE IF EXISTS `anymock_interface_branch`;

CREATE TABLE `anymock_interface_branch` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `interface_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `sync_script` mediumtext NOT NULL,
  `async_script` mediumtext NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_name` (`interface_id`,`name`) USING BTREE,
  KEY `idx_http_interface_id` (`interface_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;



# Dump of table anymock_space
# ------------------------------------------------------------

DROP TABLE IF EXISTS `anymock_space`;

CREATE TABLE `anymock_space` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) NOT NULL,
  `parent_id` bigint(20) NOT NULL,
  `access_authority` varchar(255) NOT NULL,
  `level` int(11) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_label` (`label`,`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;



# Dump of table anymock_tcp_interface
# ------------------------------------------------------------

DROP TABLE IF EXISTS `anymock_tcp_interface`;

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



# Dump of table anymock_tcp_interface_snapshot
# ------------------------------------------------------------

DROP TABLE IF EXISTS `anymock_tcp_interface_snapshot`;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

