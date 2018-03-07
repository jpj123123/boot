/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50638
Source Host           : localhost:3306
Source Database       : qxlght

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2018-03-07 15:49:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_customer`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '客户名',
  `password` varchar(40) DEFAULT NULL COMMENT '密码：暂时没有',
  `phone` varchar(15) NOT NULL COMMENT '手机号',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `money` int(15) NOT NULL COMMENT '账户余额',
  `user_id` int(15) DEFAULT NULL COMMENT '所属用户',
  `isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否有效用户0 有效1 无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户表';


-- ----------------------------
-- Table structure for `t_enum`
-- ----------------------------
DROP TABLE IF EXISTS `t_enum`;
CREATE TABLE `t_enum` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` int(15) unsigned NOT NULL DEFAULT '0' COMMENT '父id',
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `icons` varchar(50) DEFAULT NULL,
  `type` tinyint(2) NOT NULL COMMENT '菜单类型 1：菜单 2：链接 3：按钮',
  `url` varchar(255) NOT NULL,
  `desc` int(5) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of t_enum
-- ----------------------------
INSERT INTO `t_enum` VALUES ('1', '0', 'sysroot', '系统管理', '', '1', '#', '1');
INSERT INTO `t_enum` VALUES ('2', '1', 'listEnum', '菜单管理', null, '2', 'enum/listEnumTreegrid', '1');
INSERT INTO `t_enum` VALUES ('3', '2', 'enum_add', '添加', 'icon-add', '3', '/enum/addEnum', '2');
INSERT INTO `t_enum` VALUES ('4', '2', 'enum_edit', '修改', 'icon-edit', '3', '/enum/editEnum', '3');
INSERT INTO `t_enum` VALUES ('5', '2', 'enum_del', '删除', 'icon-remove', '3', '/enum/deleteEnum', '4');
INSERT INTO `t_enum` VALUES ('6', '1', 'roleroot', '角色管理', '', '2', '/role/list', '2');
INSERT INTO `t_enum` VALUES ('8', '6', 'role_add', '添加', 'icon-add', '3', '/role/add', '2');
INSERT INTO `t_enum` VALUES ('9', '6', 'role_del', '删除', 'icon-remove', '3', '/role/delete', '3');
INSERT INTO `t_enum` VALUES ('10', '6', 'add_role_enum', '添加关联菜单', 'icon-add', '3', '/role/addRoleEnum', '4');
INSERT INTO `t_enum` VALUES ('11', '1', 'userroot', '用户管理', '', '2', '/user/list', '3');
INSERT INTO `t_enum` VALUES ('12', '11', 'user_add', '添加', 'icon-add', '3', '/user/add', '2');
INSERT INTO `t_enum` VALUES ('13', '11', 'user_edit', '修改', 'icon-edit', '3', '/user/edit', '3');
INSERT INTO `t_enum` VALUES ('14', '11', 'user_del', '删除', 'icon-remove', '3', '/user/delete', '4');
INSERT INTO `t_enum` VALUES ('15', '11', 'add_user_role', '添加用户角色关联', 'icon-add', '3', '/user/addUserRole', '5');
INSERT INTO `t_enum` VALUES ('16', '0', 'buisnessroot', '业务管理', '', '1', '#', '2');
INSERT INTO `t_enum` VALUES ('17', '16', 'customerroot', '客户管理', '', '2', '/customer/list', '2');
INSERT INTO `t_enum` VALUES ('18', '2', 'enum_list_all', '查询', 'icon-search', '3', '/enum/listEnumAll', '1');
INSERT INTO `t_enum` VALUES ('19', '6', 'role_list_all', '查询', 'icon-search', '3', '/role/listAll', '1');
INSERT INTO `t_enum` VALUES ('20', '11', 'user_list_all', '查询', 'icon-search', '3', '/user/listUser', '1');
INSERT INTO `t_enum` VALUES ('21', '17', 'customer_list_all', '查找', 'icon-search', '3', '/customer/list', '1');
INSERT INTO `t_enum` VALUES ('22', '17', 'customer_add', '添加', 'icon-add', '3', '/customer/add', '2');
INSERT INTO `t_enum` VALUES ('23', '17', 'customer_edit', '修改', 'icon-edit', '3', '/customer/edit', '3');
INSERT INTO `t_enum` VALUES ('24', '17', 'customer_delete', '删除', 'icon-remove', '3', '/customer/delete', '4');

-- ----------------------------
-- Table structure for `t_goods`
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL COMMENT '商品编码',
  `name` varchar(255) NOT NULL COMMENT '商品名称',
  `cost` int(15) DEFAULT NULL COMMENT '进价：单位（分）',
  `price` int(15) DEFAULT NULL,
  `islist` tinyint(1) DEFAULT '1' COMMENT '是否上架 1上架 0 下架',
  `isused` tinyint(1) DEFAULT '1' COMMENT '是否有效 1有效 0无效',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of t_goods
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `order_no` varchar(40) NOT NULL COMMENT '订单号',
  `goods_id` int(15) NOT NULL,
  `user_id` int(15) NOT NULL,
  `customer_id` int(15) NOT NULL,
  `count` int(15) NOT NULL COMMENT '购买数量',
  `price` int(15) NOT NULL COMMENT '价格',
  `total_price` int(15) NOT NULL COMMENT '商品总价应收款',
  `pay_money` int(15) NOT NULL DEFAULT '0' COMMENT '支付金额',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '订单状态0未付款1部分付款2全部付款',
  `create_time` timestamp NOT NULL,
  `update_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of t_order
-- ----------------------------

-- ----------------------------
-- Table structure for `t_receipt`
-- ----------------------------
DROP TABLE IF EXISTS `t_receipt`;
CREATE TABLE `t_receipt` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(15) NOT NULL,
  `money` int(15) DEFAULT NULL COMMENT '收款金额',
  `user_id` int(15) DEFAULT NULL COMMENT '收款人id',
  `remark` varchar(100) DEFAULT NULL COMMENT '收款备注',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收款表';

-- ----------------------------
-- Records of t_receipt
-- ----------------------------

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '上级id',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `un_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '0', 'root');
INSERT INTO `t_role` VALUES ('2', '1', '总经理');

-- ----------------------------
-- Table structure for `t_role_enum`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_enum`;
CREATE TABLE `t_role_enum` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(15) NOT NULL COMMENT '角色id',
  `enum_id` int(15) NOT NULL COMMENT '菜单id',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- ----------------------------
-- Records of t_role_enum
-- ----------------------------
INSERT INTO `t_role_enum` VALUES ('40', '3', '1', '2018-03-06 16:04:47');
INSERT INTO `t_role_enum` VALUES ('41', '3', '6', '2018-03-06 16:04:47');
INSERT INTO `t_role_enum` VALUES ('42', '3', '8', '2018-03-06 16:04:47');
INSERT INTO `t_role_enum` VALUES ('43', '3', '9', '2018-03-06 16:04:47');
INSERT INTO `t_role_enum` VALUES ('44', '3', '10', '2018-03-06 16:04:47');
INSERT INTO `t_role_enum` VALUES ('89', '1', '1', '2018-03-07 09:44:46');
INSERT INTO `t_role_enum` VALUES ('90', '1', '2', '2018-03-07 09:44:46');
INSERT INTO `t_role_enum` VALUES ('91', '1', '18', '2018-03-07 09:44:46');
INSERT INTO `t_role_enum` VALUES ('92', '1', '3', '2018-03-07 09:44:46');
INSERT INTO `t_role_enum` VALUES ('93', '1', '4', '2018-03-07 09:44:46');
INSERT INTO `t_role_enum` VALUES ('94', '1', '5', '2018-03-07 09:44:46');
INSERT INTO `t_role_enum` VALUES ('95', '1', '6', '2018-03-07 09:44:46');
INSERT INTO `t_role_enum` VALUES ('96', '1', '19', '2018-03-07 09:44:46');
INSERT INTO `t_role_enum` VALUES ('97', '1', '8', '2018-03-07 09:44:46');
INSERT INTO `t_role_enum` VALUES ('98', '1', '9', '2018-03-07 09:44:46');
INSERT INTO `t_role_enum` VALUES ('99', '1', '10', '2018-03-07 09:44:46');
INSERT INTO `t_role_enum` VALUES ('100', '1', '11', '2018-03-07 09:44:46');
INSERT INTO `t_role_enum` VALUES ('101', '1', '20', '2018-03-07 09:44:46');
INSERT INTO `t_role_enum` VALUES ('102', '1', '12', '2018-03-07 09:44:46');
INSERT INTO `t_role_enum` VALUES ('103', '1', '13', '2018-03-07 09:44:46');
INSERT INTO `t_role_enum` VALUES ('104', '1', '14', '2018-03-07 09:44:46');
INSERT INTO `t_role_enum` VALUES ('105', '1', '15', '2018-03-07 09:44:46');
INSERT INTO `t_role_enum` VALUES ('106', '1', '16', '2018-03-07 09:44:46');
INSERT INTO `t_role_enum` VALUES ('107', '1', '17', '2018-03-07 09:44:46');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `pid` int(15) NOT NULL DEFAULT '0' COMMENT '上级id',
  `name` varchar(40) NOT NULL COMMENT '用户名',
  `password` varchar(30) NOT NULL COMMENT '密码',
  `real_name` varchar(40) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL COMMENT '电话',
  `address` varchar(255) DEFAULT NULL COMMENT '住址',
  `isused` tinyint(1) DEFAULT '1' COMMENT '0删除1有效用户',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '0', 'admin', '123456', null, null, '鹤壁', '1', '2018-02-28 15:46:14', '2018-02-28 15:46:20');

-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int(15) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(15) unsigned NOT NULL COMMENT '用户id',
  `role_id` int(15) unsigned NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1', '1');

