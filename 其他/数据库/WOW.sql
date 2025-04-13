/*
 Navicat Premium Data Transfer

 Source Server         : 145
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 192.168.199.145:3306
 Source Schema         : WOW

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 13/04/2025 22:29:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for HasC
-- ----------------------------
DROP TABLE IF EXISTS `HasC`;
CREATE TABLE `HasC`  (
  `ver` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '控制类型',
  `checktt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测内容',
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 563 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for ItemC
-- ----------------------------
DROP TABLE IF EXISTS `ItemC`;
CREATE TABLE `ItemC`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `itemNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '道具名称',
  `itemNeed` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `itemNeedCount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Q` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Ctype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业',
  `itemString` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ic`(`itemNum` ASC, `itemNeed` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37184 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ItemC_ALL
-- ----------------------------
DROP TABLE IF EXISTS `ItemC_ALL`;
CREATE TABLE `ItemC_ALL`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `itemNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '道具名称',
  `itemNeed` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `itemNeedCount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Q` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Ctype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业',
  `itemString` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IN`(`itemNum` ASC) USING BTREE,
  INDEX `INN`(`itemNeed` ASC) USING BTREE,
  INDEX `q`(`Q` ASC) USING BTREE,
  INDEX `c`(`Ctype` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45373 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QBuy
-- ----------------------------
DROP TABLE IF EXISTS `QBuy`;
CREATE TABLE `QBuy`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `itemNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stackSize` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `quantity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `otherPlayer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买方',
  `player` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卖方',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '售卖地域',
  `Q` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区',
  `hashCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保证唯一',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51827 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for QSale
-- ----------------------------
DROP TABLE IF EXISTS `QSale`;
CREATE TABLE `QSale`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `itemNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stackSize` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `quantity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '价格',
  `otherPlayer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卖方',
  `player` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买方',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Q` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区',
  `hashCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保证唯一',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57038 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for TsmScan
-- ----------------------------
DROP TABLE IF EXISTS `TsmScan`;
CREATE TABLE `TsmScan`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `itemNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物品名称',
  `minBuyout` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最少购买\r\n',
  `marketValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'AH价格',
  `numAuctions` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量',
  `quantity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lastScan` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '插入时间',
  `isLast` int NULL DEFAULT NULL COMMENT '最后插入',
  `Q` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `itemString`(`itemNum` ASC) USING BTREE,
  INDEX `SSS`(`lastScan` ASC, `isLast` ASC, `Q` ASC) USING BTREE,
  INDEX `lastScan`(`lastScan` ASC) USING BTREE,
  INDEX `isLast`(`isLast` ASC) USING BTREE,
  INDEX `minBuyout`(`minBuyout` ASC) USING BTREE,
  INDEX `marketValue`(`marketValue` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2317485 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for bagHave
-- ----------------------------
DROP TABLE IF EXISTS `bagHave`;
CREATE TABLE `bagHave`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `itemNum` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '物品ID',
  `itemCount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '物品数量',
  `Q` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '区',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色',
  `comeType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '来源',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 622916 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for destroyingH
-- ----------------------------
DROP TABLE IF EXISTS `destroyingH`;
CREATE TABLE `destroyingH`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `destroyType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '摧毁类型',
  `backItemNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回报物品',
  `backItemCount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分解物品数量',
  `destroyTargetItemNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分解物品',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hashCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '唯一性',
  `batchNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '批次号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 428967 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dirnew
-- ----------------------------
DROP TABLE IF EXISTS `dirnew`;
CREATE TABLE `dirnew`  (
  `pathurl` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '路径地址',
  `lastfix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最近一次修改',
  `id` int NOT NULL AUTO_INCREMENT,
  `pathhash` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'has值',
  `lastfixhash` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 790 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for foceItem
-- ----------------------------
DROP TABLE IF EXISTS `foceItem`;
CREATE TABLE `foceItem`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `itemNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `profile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配置名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1299676 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for itemStr
-- ----------------------------
DROP TABLE IF EXISTS `itemStr`;
CREATE TABLE `itemStr`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `itemNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物品编号',
  `itemString` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 538179 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for itemStr_ALL
-- ----------------------------
DROP TABLE IF EXISTS `itemStr_ALL`;
CREATE TABLE `itemStr_ALL`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `itemNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物品编号',
  `itemString` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 524177 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for itemStr_back2
-- ----------------------------
DROP TABLE IF EXISTS `itemStr_back2`;
CREATE TABLE `itemStr_back2`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `itemNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物品编号',
  `itemString` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 524177 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- View structure for Timt2out
-- ----------------------------
DROP VIEW IF EXISTS `Timt2out`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `Timt2out` AS select `lp`.`Q` AS `Q`,`lp`.`id` AS `id`,`lp`.`itemNum` AS `itemNum`,`lp`.`sumda` AS `sumda`,`lp`.`itemString` AS `itemString`,`lp`.`buyavgprice` AS `buyavgprice`,`lp`.`nowprice` AS `nowprice`,`lp`.`lr` AS `lr`,`lp`.`lrl` AS `lrl`,(select max(`bh`.`role`) from `bagHave` `bh` where ((`bh`.`itemNum` = convert(`lp`.`itemNum` using utf8mb4)) and (`bh`.`Q` = convert(`lp`.`Q` using utf8mb4)))) AS `who` from (select `eq`.`Q` AS `Q`,`eq`.`id` AS `id`,`eq`.`itemNum` AS `itemNum`,`eq`.`sumda` AS `sumda`,`eq`.`itemString` AS `itemString`,`eq`.`buyavgprice` AS `buyavgprice`,`eq`.`nowprice` AS `nowprice`,round((`eq`.`nowprice` - `eq`.`buyavgprice`),2) AS `lr`,round((`eq`.`nowprice` / `eq`.`buyavgprice`),2) AS `lrl` from (select `q`.`Q` AS `Q`,`k`.`id` AS `id`,`z`.`itemNum` AS `itemNum`,`z`.`sumda` AS `sumda`,`z`.`itemString` AS `itemString`,`q`.`buyavgprice` AS `buyavgprice`,round((`k`.`minBuyout` / 10000),2) AS `nowprice` from (((select `a`.`itemNum` AS `itemNum`,`a`.`itemString` AS `itemString`,`b`.`Q` AS `Q`,sum(`b`.`itemCount`) AS `sumda` from (`bagHave` `b` join `itemStr` `a`) where (convert(`a`.`itemNum` using utf8mb4) = `b`.`itemNum`) group by `a`.`itemString`,`a`.`itemNum`,`b`.`Q`) `z` join `TsmScan` `k` on(((`k`.`isLast` = 1) and (`z`.`Q` = convert(`k`.`Q` using utf8mb4)) and (`z`.`sumda` > 2) and (`z`.`itemNum` = `k`.`itemNum`)))) left join (select `a`.`itemNum` AS `itemNum`,`a`.`Q` AS `Q`,round((avg(`a`.`price`) / 10000),2) AS `buyavgprice` from `QBuy` `a` where (1 = 1) group by `a`.`itemNum`,`a`.`Q`) `q` on(((`q`.`itemNum` = `z`.`itemNum`) and (convert(`q`.`Q` using utf8mb4) = `z`.`Q`))))) `eq`) `lp` where ((1 = 1) and (`lp`.`lr` > 10) and (`lp`.`lrl` > 1.1) and (`lp`.`buyavgprice` > 1) and (`lp`.`itemString` not in ('无尽口袋','恒金线')) and (`lp`.`itemNum` > 10000) and (not((`lp`.`itemString` like '%图样%')))) order by `lp`.`Q` desc,(select max(`bh`.`role`) from `bagHave` `bh` where ((`bh`.`itemNum` = convert(`lp`.`itemNum` using utf8mb4)) and (`bh`.`Q` = convert(`lp`.`Q` using utf8mb4)))) desc,`lp`.`sumda` desc,`lp`.`lr` desc,`lp`.`lrl` desc;

-- ----------------------------
-- View structure for 全制造所有区
-- ----------------------------
DROP VIEW IF EXISTS `全制造所有区`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `全制造所有区` AS select `h`.`itemNum` AS `物品编号`,`h`.`itemstring` AS `物品名称`,`h`.`q` AS `服务器`,`h`.`minCost` AS `最小成本`,`h`.`maxCost` AS `最大成本`,`h2`.`marketValue` AS `最大售价`,`h2`.`minBuyout` AS `最小售价`,`h2`.`lastScan` AS `扫描时间`,`h`.`Ctype` AS `专业` from ((select `z`.`itemNum` AS `itemNum`,`z`.`itemstring` AS `itemstring`,sum((`z`.`itemNeedCount` * `z`.`minBuyout`)) AS `minCost`,sum((`z`.`itemNeedCount` * `z`.`marketValue`)) AS `maxCost`,`z`.`q` AS `q`,`z`.`Ctype` AS `Ctype` from (select `a`.`itemNeedCount` AS `itemNeedCount`,`a`.`itemNum` AS `itemNum`,`a`.`Ctype` AS `Ctype`,`a`.`itemString` AS `itemstring`,`b`.`minBuyout` AS `minBuyout`,`b`.`marketValue` AS `marketValue`,`b`.`Q` AS `q` from (`ItemC_ALL` `a` join `TsmScan` `b`) where ((1 = 1) and (`b`.`itemNum` = `a`.`itemNeed`) and (`b`.`isLast` = '1'))) `z` group by `z`.`itemstring`,`z`.`itemNum`,`z`.`q`,`z`.`Ctype`) `h` join `TsmScan` `h2`) where ((1 = 1) and (`h`.`q` = `h2`.`Q`) and (`h`.`itemNum` = `h2`.`itemNum`) and (`h2`.`isLast` = '1'));

-- ----------------------------
-- View structure for 可制造归属区
-- ----------------------------
DROP VIEW IF EXISTS `可制造归属区`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `可制造归属区` AS select `h`.`itemNum` AS `物品编号`,`h`.`itemstring` AS `物品名称`,`h`.`q` AS `服务器`,`h`.`minCost` AS `最小成本`,`h`.`maxCost` AS `最大成本`,`h2`.`marketValue` AS `最大售价`,`h2`.`minBuyout` AS `最小售价`,`h2`.`lastScan` AS `扫描时间`,`h`.`Ctype` AS `专业` from ((select `z`.`itemNum` AS `itemNum`,`z`.`itemstring` AS `itemstring`,sum((`z`.`itemNeedCount` * `z`.`minBuyout`)) AS `minCost`,sum((`z`.`itemNeedCount` * `z`.`marketValue`)) AS `maxCost`,`z`.`q` AS `q`,`z`.`Ctype` AS `Ctype` from (select `a`.`itemNeedCount` AS `itemNeedCount`,`a`.`itemNum` AS `itemNum`,`a`.`Ctype` AS `Ctype`,`a`.`itemString` AS `itemstring`,`b`.`minBuyout` AS `minBuyout`,`b`.`marketValue` AS `marketValue`,`b`.`Q` AS `q` from (`ItemC` `a` join `TsmScan` `b`) where ((1 = 1) and (`b`.`itemNum` = `a`.`itemNeed`) and (`b`.`Q` = `a`.`Q`) and (`b`.`isLast` = '1'))) `z` group by `z`.`itemstring`,`z`.`itemNum`,`z`.`q`,`z`.`Ctype`) `h` join `TsmScan` `h2`) where ((1 = 1) and (`h`.`q` = `h2`.`Q`) and (`h`.`itemNum` = `h2`.`itemNum`) and (`h2`.`isLast` = '1'));

SET FOREIGN_KEY_CHECKS = 1;
