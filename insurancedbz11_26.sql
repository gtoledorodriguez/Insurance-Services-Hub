/*
 Navicat Premium Data Transfer

 Source Server         : zjl
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : insurancedbz

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 26/11/2022 15:28:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `AddressID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) NOT NULL,
  `Address` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Apt` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `City` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `State` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ZipCode` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`AddressID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES (1, 1, 'ww', 'ww', 'ww', 'ww', 'ww');
INSERT INTO `address` VALUES (2, 1, 'ss', 'ss', 'ss', 'ss', 'ss');
INSERT INTO `address` VALUES (3, 1, 'ss', 'ss', 'ss', 'ss', 'ss');
INSERT INTO `address` VALUES (4, 1, 'ss', 'ss', 'ss', 'ss', 'ss');
INSERT INTO `address` VALUES (5, 21, '1', '1', '1', '1', '1');
INSERT INTO `address` VALUES (6, 22, '1', '1', '1', '1', '1');
INSERT INTO `address` VALUES (7, 23, '1', '1', '1', '1', '1');
INSERT INTO `address` VALUES (8, 24, '123 Street', '1', 'Chicago', 'IL', '60612');

-- ----------------------------
-- Table structure for arpersonalinfo
-- ----------------------------
DROP TABLE IF EXISTS `arpersonalinfo`;
CREATE TABLE `arpersonalinfo`  (
  `ARPersonalInfoID` int(11) NOT NULL AUTO_INCREMENT,
  `MaritalStatus` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DLNo` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DLState` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ARPersonalInfoID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of arpersonalinfo
-- ----------------------------
INSERT INTO `arpersonalinfo` VALUES (1, 'Si', 'Male', '1111111111', 'IL', '1');

-- ----------------------------
-- Table structure for auto_service
-- ----------------------------
DROP TABLE IF EXISTS `auto_service`;
CREATE TABLE `auto_service`  (
  `auto_service_id` int(11) NOT NULL AUTO_INCREMENT,
  `collision` int(11) NOT NULL,
  `comprehensive` int(11) NOT NULL,
  `create_time` date NOT NULL,
  `liability_bodily` int(11) NOT NULL,
  `liability_property` int(11) NOT NULL,
  `medical` int(11) NOT NULL,
  `rental` int(11) NOT NULL,
  `service_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` int(11) NOT NULL,
  `um_bodily` int(11) NOT NULL,
  `um_property` int(11) NOT NULL,
  PRIMARY KEY (`auto_service_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auto_service
-- ----------------------------
INSERT INTO `auto_service` VALUES (1, 0, 0, '0000-00-00', 0, 0, 0, 0, '', 0, 0, 0);

-- ----------------------------
-- Table structure for autorelative
-- ----------------------------
DROP TABLE IF EXISTS `autorelative`;
CREATE TABLE `autorelative`  (
  `AutoRelativeID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) NOT NULL,
  `ARPersonalInfoID` int(11) NOT NULL,
  `VehicleID` int(11) NOT NULL,
  PRIMARY KEY (`AutoRelativeID`, `UserID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of autorelative
-- ----------------------------

-- ----------------------------
-- Table structure for autoservice
-- ----------------------------
DROP TABLE IF EXISTS `autoservice`;
CREATE TABLE `autoservice`  (
  `AutoServiceID` int(11) NOT NULL AUTO_INCREMENT,
  `ServiceName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LiabilityBodily` int(11) NOT NULL,
  `LiabilityProperty` int(11) NOT NULL,
  `Comprehensive` int(11) NULL DEFAULT NULL,
  `Collision` int(11) NULL DEFAULT NULL,
  `Medical` int(11) NULL DEFAULT NULL,
  `UMBodily` int(11) NULL DEFAULT NULL,
  `UMProperty` int(11) NULL DEFAULT NULL,
  `Rental` int(11) NULL DEFAULT NULL,
  `ServiceType` int(11) NOT NULL DEFAULT 0,
  `CreateTime` date NOT NULL,
  PRIMARY KEY (`AutoServiceID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of autoservice
-- ----------------------------
INSERT INTO `autoservice` VALUES (1, 'Diamond', 50000, 100000, 250, 250, 50000, 50000, 100000, 150, 0, '2022-10-01');
INSERT INTO `autoservice` VALUES (2, 'Gold', 35000, 50000, 300, 300, 35000, 35000, 50000, 100, 0, '2022-10-01');
INSERT INTO `autoservice` VALUES (3, 'Silver', 25000, 30000, 500, 500, 35000, 25000, 30000, 80, 0, '2022-10-01');
INSERT INTO `autoservice` VALUES (4, 'Bronze', 20000, 20000, 550, 550, 20000, 20000, 20000, 60, 0, '2022-10-01');

-- ----------------------------
-- Table structure for claims
-- ----------------------------
DROP TABLE IF EXISTS `claims`;
CREATE TABLE `claims`  (
  `ClaimID` int(11) NOT NULL AUTO_INCREMENT,
  `CreateDate` date NOT NULL,
  `UserID` int(11) NULL DEFAULT NULL,
  `FirstName` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LastName` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PhoneNo` bigint(20) NULL DEFAULT NULL,
  `Email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `AddressID` int(11) NOT NULL,
  `VehicleID1` int(11) NOT NULL,
  `VehicleID2` int(11) NULL DEFAULT NULL,
  `VehicleID3` int(11) NULL DEFAULT NULL,
  `ClaimDate` date NOT NULL,
  `Details` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `AgentID` int(11) NULL DEFAULT NULL,
  `Status` int(11) NOT NULL DEFAULT 0,
  `Reimbursement` float NULL DEFAULT NULL,
  PRIMARY KEY (`ClaimID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of claims
-- ----------------------------

-- ----------------------------
-- Table structure for creditcard
-- ----------------------------
DROP TABLE IF EXISTS `creditcard`;
CREATE TABLE `creditcard`  (
  `CreditCardID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) NOT NULL,
  `CreditCardNumber` bigint(20) NOT NULL,
  `NameOnCard` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ExpireDate` date NOT NULL,
  `ZipCode` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`CreditCardID`, `UserID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of creditcard
-- ----------------------------

-- ----------------------------
-- Table structure for quote
-- ----------------------------
DROP TABLE IF EXISTS `quote`;
CREATE TABLE `quote`  (
  `QuoteID` int(11) NOT NULL AUTO_INCREMENT,
  `CreateDate` date NOT NULL,
  `UserID` int(11) NULL DEFAULT NULL,
  `FirstName` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LastName` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PhoneNo` bigint(20) NULL DEFAULT NULL,
  `Email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `AddressID` int(11) NOT NULL,
  `VehicleID` int(11) NOT NULL,
  PRIMARY KEY (`QuoteID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of quote
-- ----------------------------

-- ----------------------------
-- Table structure for serviceorder
-- ----------------------------
DROP TABLE IF EXISTS `serviceorder`;
CREATE TABLE `serviceorder`  (
  `ServiceOrderID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Description` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Discount` float NOT NULL DEFAULT 0,
  `Total` float NOT NULL,
  `AgentID` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  `AutoServiceID` int(34) NULL DEFAULT NULL,
  PRIMARY KEY (`ServiceOrderID`, `UserID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of serviceorder
-- ----------------------------
INSERT INTO `serviceorder` VALUES (1, '1', 'text', 0, 1, '1', '0', 1);
INSERT INTO `serviceorder` VALUES (2, '1', 'test2', 0, 1, '1', '0', 2);
INSERT INTO `serviceorder` VALUES (3, '1', 'test3', 0, 0, '2', '0', 2);
INSERT INTO `serviceorder` VALUES (4, '2', 'test3', 0, 0, '2', '0', 3);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LastName` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PhoneNo` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DateOfBirth` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `AddressID` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CreditCardID` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `UserType` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'User',
  `Password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `UserName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `FullAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`UserID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'junlong', 'zhao', '1233245', 'sjidg@sodgn', '2022-11-16 00:00:00', '2', '123123', 'User', '1', 'junlong', NULL);
INSERT INTO `user` VALUES (2, 'ribin', 'lee', '5432432', 'sjidg@sodgn1', '2022-11-07 00:00:00', '3', '3476566', 'Manager', '2', 'ribin', NULL);
INSERT INTO `user` VALUES (3, 'pony', 'ma', '6546', 'sjidg@sodgn2', '2022-10-12 00:00:00', '4', '765876765', 'Agent', '3', 'pony', NULL);
INSERT INTO `user` VALUES (4, 'jack', 'ma', '98573498', 'sjidg@sodgn3', '2022-09-06 00:00:00', '5', '5364', 'User', '4', 'jack', NULL);
INSERT INTO `user` VALUES (5, '546', '546', '546', '546', '2022-11-07 00:00:00', '546', '546', 'User', '4324234', 'leasdf sdf', NULL);
INSERT INTO `user` VALUES (6, '546', '546', '546', '546', '2022-11-07 00:00:00', '546', '546', 'User', '4324234', 'leasdf sdf', NULL);
INSERT INTO `user` VALUES (7, '546', '546', '546', '546', '2022-11-07 00:00:00', '546', '546', 'User', '4324234', 'leasdf sdf', NULL);
INSERT INTO `user` VALUES (8, 'zhao', 'momer', '123123', 'weri@sdf', '2022-11-03 22:37:48', '12', '123123123', 'Manager', '123123123', 'tom', NULL);
INSERT INTO `user` VALUES (9, 'zhao', 'momer', '123123', 'weri@sdf', '2022-11-04 12:39:27', '12', '123123123', 'Manager', '123123123', 'tom', NULL);
INSERT INTO `user` VALUES (10, '333333333', '3333333333', '33333333', '3333333333', '2022-02-11 02:32:00', '333333333', '33333333333', '33333333', '33333333', '3333333', NULL);
INSERT INTO `user` VALUES (11, '333333333', '3333333333', '33333333', '3333333333', '2022-02-11 02:32:00', '333333333', '33333333333', '33333333', '33333333', '444444444', NULL);
INSERT INTO `user` VALUES (12, 'junlong', 'junlong2', 'junlong', 'junlong', '2020-10-11 01:10:20', 'junlong', 'junlong', 'junlong2', '1', 'junlong2', NULL);
INSERT INTO `user` VALUES (13, '!junlong', '!zhao', '!1233245', '!sjidg@sodgn', '2022-11-16 06:00:00', '2', '!123123', 'User', '1', NULL, NULL);
INSERT INTO `user` VALUES (14, '!junlong', '!zhao', '!1233245', '@sjidg@sodgn', '2022-11-16 06:00:00', '2', '!123123', 'User', '1', NULL, NULL);
INSERT INTO `user` VALUES (15, '@!junlong', '@!zhao', '@!1233245', '@@sjidg@sodgn', '2022-11-16 06:00:00', '2', '!123123', 'User', '1', NULL, NULL);
INSERT INTO `user` VALUES (16, '@!junlong', '@!zhao', '$@%!1233245', '@@s&jidg@sodgn', '2022-11-16 06:00:00', '2', '!123123', 'User', '1', '@!junlong @!zhao', NULL);
INSERT INTO `user` VALUES (17, '@!junlong', '@!zhao', '$@%!1233245', '@@s&js\'idg@sodgn', '2022-11-16 06:00:00', '2', '!123123', 'User', '1', '@!junlong @!zhao', NULL);
INSERT INTO `user` VALUES (18, '@!junlong', '@!zhao', '$@t1233245', '@@s&idg@sodgn', '2022-11-16 06:00:00', '2', '!123123', 'User', '1', '@!junlong @!zhao', NULL);
INSERT INTO `user` VALUES (19, '@!junlong', '@!zhao', '$@t1233245', 'dfg@@s&idg@sodgn', '2022-11-16 06:00:00', '2', '!123123', 'User', '1', '@!junlong @!zhao', NULL);
INSERT INTO `user` VALUES (20, '@!junlong', '@!zhao', '$@tf1233245', 'dfg@@ssdf&idg@sodgn', '2022-11-16 06:00:00', '2', '!123123', 'User', '1', '@!junlong @!zhao', NULL);
INSERT INTO `user` VALUES (21, '@!junlong', '@!zhao', '$@tf1233245', 'dfg@@ssdf&df@son', '2022-11-16 06:00:00', '2', '!123123', 'User', '1', '@!junlong @!zhao', NULL);
INSERT INTO `user` VALUES (22, '@!junlong', '@!zhao', '$@tf1233245', 'eeeeeeeeeeeeee', '2022-11-16 06:00:00', '2', '!123123', 'User', '1', '@!junlong @!zhao', NULL);
INSERT INTO `user` VALUES (23, '@!junlong', '@!zhao', '$@tf1233245', '!eeeeeeeeeeeeee', '2022-11-16 06:00:00', '2', '!123123', 'User', '1', '@!junlong @!zhao', '1, 1, 1, 1');
INSERT INTO `user` VALUES (24, '@!junlong', '@!zhao', '$@tf1233245', '@!eeeeeeeeeeeeee', '2022-11-16 06:00:00', '2', '!123123', 'User', '1', '@!junlong @!zhao', '123 Street, Chicago, IL, 60612');

-- ----------------------------
-- Table structure for vehicleinfo
-- ----------------------------
DROP TABLE IF EXISTS `vehicleinfo`;
CREATE TABLE `vehicleinfo`  (
  `VehicleID` int(11) NOT NULL AUTO_INCREMENT,
  `Maker` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Year` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `VIN` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Mileage` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userId` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`VehicleID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vehicleinfo
-- ----------------------------
INSERT INTO `vehicleinfo` VALUES (1, 'Toyota', '4Runner', '2021', '111111111111111', '15,000', '1');

SET FOREIGN_KEY_CHECKS = 1;
