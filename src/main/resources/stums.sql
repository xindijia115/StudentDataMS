/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : stums

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 18/12/2023 22:12:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `cid` int(0) NOT NULL AUTO_INCREMENT,
  `major` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `course_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `start_term` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `period` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `credit` double NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '计算机科学与技术', '数据结构', '必修', '大二（上）', '64', 5);
INSERT INTO `course` VALUES (2, '计算机科学与技术', '离散数学', '必修', '大二（上）', '48', 4);
INSERT INTO `course` VALUES (3, '计算机科学与技术', '高等数学', '必修', '大一（下）', '64', 5);
INSERT INTO `course` VALUES (4, '计算机科学与技术', '网页制作', '必修', '大一（下）', '48', 3);
INSERT INTO `course` VALUES (5, '计算机科学与技术', '机器学习', '专修', '大三（上）', '48', 3.5);
INSERT INTO `course` VALUES (6, '计算机科学与技术', '深度学习', '专修', '大三（上）', '48', 3.5);
INSERT INTO `course` VALUES (7, '计算机科学与技术', '线性代数', '必修', '大一（上）', '64', 4);
INSERT INTO `course` VALUES (8, '计算机科学与技术', '网络安全', '选修', '大二（下）', '48', 3);
INSERT INTO `course` VALUES (9, '计算机科学与技术', 'Java', '选修', '大二（下）', '48', 3);
INSERT INTO `course` VALUES (10, '计算机科学与技术', '算法', '必修', '大二（上）', '64', 5);
INSERT INTO `course` VALUES (11, '计算机科学与技术', '操作系统', '必修', '大二（上）', '64', 5);

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `sid` int(0) NULL DEFAULT NULL,
  `course_id` int(0) NULL DEFAULT NULL,
  `score` double NULL DEFAULT NULL,
  `credit` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES (1, 17210201, 1, 70, 3);
INSERT INTO `score` VALUES (2, 17210201, 2, 70, 5);
INSERT INTO `score` VALUES (3, 17210201, 3, 80, 4);
INSERT INTO `score` VALUES (4, 17210201, 4, 60, 3);
INSERT INTO `score` VALUES (5, 17210202, 1, 94, 4.6);
INSERT INTO `score` VALUES (6, 17210202, 2, 85, 2.5);
INSERT INTO `score` VALUES (7, 17210202, 1, 40, 1.2);
INSERT INTO `score` VALUES (8, 17210202, 1, 50, 1.5);
INSERT INTO `score` VALUES (9, 17210203, 1, 80, 4);
INSERT INTO `score` VALUES (10, 17210203, 1, 100, 4);
INSERT INTO `score` VALUES (11, 17210203, 1, 60, 3);
INSERT INTO `score` VALUES (12, 17210203, 1, 80, 2.4);
INSERT INTO `score` VALUES (13, 17210204, 1, 90, 4.5);
INSERT INTO `score` VALUES (14, 17210205, 1, 100, 5);
INSERT INTO `score` VALUES (15, 17210206, 1, 0, 0);
INSERT INTO `score` VALUES (16, 17210207, 1, 90, 4.5);
INSERT INTO `score` VALUES (17, 17210208, 1, 84, 4.2);
INSERT INTO `score` VALUES (18, 17210209, 1, 90, 4.5);
INSERT INTO `score` VALUES (22, 17210213, 1, 90, 4.5);
INSERT INTO `score` VALUES (23, 17210214, 1, 100, 5);
INSERT INTO `score` VALUES (24, 17210215, 1, 90, 4.5);
INSERT INTO `score` VALUES (25, 17210216, 1, 80, 4);
INSERT INTO `score` VALUES (26, 17210217, 1, 70, 3.5);
INSERT INTO `score` VALUES (27, 17210218, 1, 65, 3.25);
INSERT INTO `score` VALUES (28, 17210219, 1, 50, 2.5);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `sid` int(0) NOT NULL AUTO_INCREMENT,
  `s_class` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `birth` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `major` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17210224 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (17210201, '172102', '任盈盈', '女', '无', '计算机科学与技术');
INSERT INTO `student` VALUES (17210202, '172102', '阿青', '女', '1999.8.8', '计算机科学与技术');
INSERT INTO `student` VALUES (17210203, '172102', '阿朱', '女', '1999.8.15', '计算机科学与技术');
INSERT INTO `student` VALUES (17210204, '172102', '阿紫', '女', '1998.5.5', '计算机科学与技术');
INSERT INTO `student` VALUES (17210205, '172102', '小龙女', '女', '2000.1.1', '计算机科学与技术');
INSERT INTO `student` VALUES (17210206, '172102', '黄蓉', '女', '2000.8.5', '计算机科学与技术');
INSERT INTO `student` VALUES (17210207, '172102', '岳灵珊', '女', '2222.2.2', '计算机科学与技术');
INSERT INTO `student` VALUES (17210208, '172102', '公孙绿萼', '女', '1998.5.5', '计算机科学与技术');
INSERT INTO `student` VALUES (17210209, '172102', '东方不败', '无性', '1998.5.5', '计算机科学与技术');
INSERT INTO `student` VALUES (17210210, '172102', '乔峰', '男', '1998.5.5', '计算机科学与技术');
INSERT INTO `student` VALUES (17210211, '172102', '段誉', '男', '2000.8.5', '计算机科学与技术');
INSERT INTO `student` VALUES (17210212, '172102', '虚竹', '男', '2000.8.5', '计算机科学与技术');
INSERT INTO `student` VALUES (17210213, '172102', '田伯光', '男', '1998.5.5', '计算机科学与技术');
INSERT INTO `student` VALUES (17210214, '172102', '胡斐', '男', '1997.6.5', '计算机科学与技术');
INSERT INTO `student` VALUES (17210215, '172102', '郭靖', '男', '1998.5.5', '计算机科学与技术');
INSERT INTO `student` VALUES (17210216, '172102', '张无忌', '男', '1998.5.5', '计算机科学与技术');
INSERT INTO `student` VALUES (17210217, '172102', '陈家洛', '男', '1998.8.4', '计算机科学与技术');
INSERT INTO `student` VALUES (17210218, '172102', '杨过', '男', '2000.8.5', '计算机科学与技术');
INSERT INTO `student` VALUES (17210219, '172102', '令狐冲', '男', '2000.8.5', '计算机科学与技术');
INSERT INTO `student` VALUES (17210220, '172102', '韦小宝', '男', '2000.8.5', '计算机科学与技术');
INSERT INTO `student` VALUES (17210221, '172102', '辛迪加', '男', '2003.1.15', '计算机科学与技术');
INSERT INTO `student` VALUES (17210222, '软件工程2202', 'DJ', '男', '2004.1.1', '计算机科学与技术');
INSERT INTO `student` VALUES (17210223, '软件工程2202', 'xindijia', '男', '2003.1.1', '计算机科学与技术');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '20220201', '123456');

SET FOREIGN_KEY_CHECKS = 1;
