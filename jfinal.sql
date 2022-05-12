/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : jfinal

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 12/05/2022 11:09:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for crm_banner
-- ----------------------------
DROP TABLE IF EXISTS `crm_banner`;
CREATE TABLE `crm_banner`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'ID',
  `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '标题',
  `imageUrl` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '图片地址',
  `linkUrl` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '链接地址',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`title`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '首页banner表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of crm_banner
-- ----------------------------
INSERT INTO `crm_banner` VALUES ('1', '1', 'https://lian-tuchuang.oss-cn-beijing.aliyuncs.com/img/20220420215342.png', '');
INSERT INTO `crm_banner` VALUES ('2', '2', 'https://lian-tuchuang.oss-cn-beijing.aliyuncs.com/img/20220420215230.png', '');
INSERT INTO `crm_banner` VALUES ('3', '3', 'https://lian-tuchuang.oss-cn-beijing.aliyuncs.com/img/20220420215246.png', '');

-- ----------------------------
-- Table structure for edu_chapter
-- ----------------------------
DROP TABLE IF EXISTS `edu_chapter`;
CREATE TABLE `edu_chapter`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '章节ID',
  `courseId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '章节名称',
  `prio` int NULL DEFAULT NULL COMMENT '章节优先级',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`courseId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of edu_chapter
-- ----------------------------
INSERT INTO `edu_chapter` VALUES ('339ab8fd-8c25-410b-b573-fcb013fd5fca', '86c48ccb-3e52-408e-b80a-3f8e170c8220', '第一章', 1);
INSERT INTO `edu_chapter` VALUES ('3dc1a729-9032-473c-9cb6-1bea5b07a9f0', '86c48ccb-3e52-408e-b80a-3f8e170c8220', '第三章', 3);
INSERT INTO `edu_chapter` VALUES ('e9e2b0df-4267-4da5-b763-4cd12f9da635', '0021d528-12d8-474c-a680-722fcf07e8ee', '第一章', 1);
INSERT INTO `edu_chapter` VALUES ('ed3818cd-d73c-4583-aeed-34eeee649f0f', '0021d528-12d8-474c-a680-722fcf07e8ee', '第二章', 2);
INSERT INTO `edu_chapter` VALUES ('f02c32a0-c56a-4f93-af7d-b414344a3f43', '86c48ccb-3e52-408e-b80a-3f8e170c8220', '第二章', 2);

-- ----------------------------
-- Table structure for edu_collect
-- ----------------------------
DROP TABLE IF EXISTS `edu_collect`;
CREATE TABLE `edu_collect`  (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `memberId` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `courseId` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of edu_collect
-- ----------------------------

-- ----------------------------
-- Table structure for edu_course
-- ----------------------------
DROP TABLE IF EXISTS `edu_course`;
CREATE TABLE `edu_course`  (
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程ID',
  `teacherId` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程讲师ID',
  `subjectId` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程专业ID',
  `subjectParentId` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程专业父级ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程标题',
  `price` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '课程销售价格，设置为0则可免费观看',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程封面图片路径',
  `buyCount` bigint UNSIGNED NOT NULL DEFAULT 0 COMMENT '销售数量',
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Draft' COMMENT '课程状态 Draft未发布  Normal已发布',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_title`(`title`) USING BTREE,
  INDEX `idx_subject_id`(`subjectId`) USING BTREE,
  INDEX `idx_teacher_id`(`teacherId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of edu_course
-- ----------------------------
INSERT INTO `edu_course` VALUES ('<p>最详细django3教程(django从入门到实践)</p>', '0021d528-12d8-474c-a680-722fcf07e8ee', 'debc055c-6b95-43e2-9433-a30aa5d319c1', '71c4b2f0-9330-4a0d-bf39-1a310b80715e', 'e645e92f-0f28-4dd2-8565-a83c8c6e1a0b', 'Django全套视频：django框架快速上手', 20.00, 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/e7f48f9d-c606-46b1-a3b7-c945ee1600acOW_6PU46COSBH7LKY4RAMTX.png', 2, 'Normal');
INSERT INTO `edu_course` VALUES ('<p>SpringBoot技术是目前市面上从事JavaEE企业级开发过程中使用量最大的技术。本视频围绕SpringBoot技术由浅入深带领学习者从小白身份入门SpringBoot。</p>', '0fe7f701-e59e-45c5-b19e-fe29f2b757d9', '29b2e5db-e1eb-47a1-beaf-02294e68981b', '39cf20d5-1f4b-44af-9173-b1ce72de5acf', 'e645e92f-0f28-4dd2-8565-a83c8c6e1a0b', '黑马程序员：Springboot零基础到项目实战', 0.00, 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/296844b8-e2d4-44bf-888e-c999f085e04c`ABCNTAJ@E)8ZDP$}6G4$R1.png', 0, 'Normal');
INSERT INTO `edu_course` VALUES ('<p>本课程主要讲解JavaScript基本语法, 包含最新ES6语法，比如let、模板字符串，淘汰老旧的知识。</p>', '15caad3f-6d09-4370-ad1a-427c3db43023', 'debc055c-6b95-43e2-9433-a30aa5d319c1', '2e95d11b-ec23-44a7-91cf-1d25fec7f6f6', '47f8d27d-03c7-4dbd-a5a9-3edb67d70d3e', 'JavaScript：入门到精通', 0.00, 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/992173e8-9a84-49f5-94f3-71ecad6ae779P`5LB({ZPJ1)`Z0GCF{KP3N.png', 0, 'Normal');
INSERT INTO `edu_course` VALUES ('<p>秦疆老师JavaWeb课程之HTML 。从生活到代码，从简单到深入。</p>', '5eadf752-7f9f-4476-98ba-c3a5251b323a', '9588bf5b-1bd5-4f67-9011-53606aba4c2c', '41b1b261-dba2-4f97-b01b-169666b93548', '47f8d27d-03c7-4dbd-a5a9-3edb67d70d3e', 'HTML5完整教学通俗易懂', 0.00, 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/cfd17aa8-6f79-4739-903b-f7b68363ac5a$MXXG}VSS$DH7T~J@W3`4{8.png', 0, 'Normal');
INSERT INTO `edu_course` VALUES ('<p>Python很棒的教程。</p>', '610f6e09-1d98-4468-8447-3645fa34c816', 'debc055c-6b95-43e2-9433-a30aa5d319c1', '225be81c-5f65-403f-89df-67d18612a9a7', 'e645e92f-0f28-4dd2-8565-a83c8c6e1a0b', 'Python从入门到精通教程：懂中文就能学会', 30.00, 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/43a69fbb-36cf-47eb-9783-ce6cd9631a077RK9ZO[D6P7FJ5Q}$}WC8SW.png', 1, 'Normal');
INSERT INTO `edu_course` VALUES ('<p>从入门到精通，从小白到大师。</p>', '63cc4bd5-7dde-489c-8d78-4483d654fd99', 'e78b1b98-4710-49d0-a412-caca40918ec4', 'c23ba5d3-50e4-46d4-898a-950817c3caed', 'e645e92f-0f28-4dd2-8565-a83c8c6e1a0b', 'JFinal：从入门到精通', 0.00, 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/e8373229-cb04-425c-9909-0e5dff9a9addH${@$PCYKNXGP_3K(1PAYNK.png', 0, 'Normal');
INSERT INTO `edu_course` VALUES ('<p>入门深度学习必看吴恩达，对深度学习的理解有很大帮助。</p>', '86c48ccb-3e52-408e-b80a-3f8e170c8220', 'e6dbb82a-64c6-48ef-a365-0a9341ca7659', 'fdfa70c8-68bc-4f33-b438-ab0224b1ea6a', '117c09eb-d17f-4a3e-aa4e-4f202806d00a', '吴恩达深度学习课程：Deeplearning.ai', 0.00, 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/64c3d310-0539-436a-9ad3-811fc4005502]12J)6]FQ~XZP(IPW{AA`RL.png', 0, 'Normal');
INSERT INTO `edu_course` VALUES ('<p>秦疆老师系列课程之Vue课程！</p>', '9ec3bcc6-85e2-413a-9469-32b061602da2', 'debc055c-6b95-43e2-9433-a30aa5d319c1', 'd57821d5-f0ea-4aa2-af17-bf2e9db657dd', '47f8d27d-03c7-4dbd-a5a9-3edb67d70d3e', 'Vue最新快速上手教程通俗易懂', 0.00, 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/d9a577bd-fc14-4dcb-9e2d-ed19ee7e89d60G)NYZVVU1C6I2F`E2A5K0F.png', 0, 'Normal');
INSERT INTO `edu_course` VALUES ('<p>适合初学机器学习、深度学习的人学习。</p>', 'b68412a1-1e64-4952-8c8f-6737d71acb1b', 'e6dbb82a-64c6-48ef-a365-0a9341ca7659', '490124d2-77d0-425f-8f42-6fd059fedf94', '117c09eb-d17f-4a3e-aa4e-4f202806d00a', '吴恩达：机器学习系列课程', 0.00, 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/27717ca6-485a-4d8a-9ba5-efdaade5b1bf9[MN0)F}29L5N1{ISJZFY3Y.png', 0, 'Normal');
INSERT INTO `edu_course` VALUES ('<p>本课程会用Vue全家桶，完成一个市面常见的新闻客户端单页应用程序，带大家掌握vue常规项目的开发流</p>', 'bca26d57-7e4d-467e-87f8-1a48f8e1a9eb', '29b2e5db-e1eb-47a1-beaf-02294e68981b', 'd57821d5-f0ea-4aa2-af17-bf2e9db657dd', '47f8d27d-03c7-4dbd-a5a9-3edb67d70d3e', '玩转Vue全家桶：头条新闻vue客户端开发实战', 50.00, 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/812c16cb-f863-4963-8851-d2c8f0f7cd15_@2D6_J~GF5G_ACC0(CYRSY.png', 0, 'Normal');
INSERT INTO `edu_course` VALUES ('<p>站在初学者的角度，重点帮助大伙完成从早期基于Dom开发，到Vue中基于数据开发</p>', 'd6bd2ba5-12c5-4b7f-ba57-ca467ebdc99c', '9bc7e687-7114-4e9a-82e3-72bc496e7c9a', 'd57821d5-f0ea-4aa2-af17-bf2e9db657dd', '47f8d27d-03c7-4dbd-a5a9-3edb67d70d3e', 'vue前端基础教程：4个小时带你快速入门vue', 30.00, 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/1e1b6ecf-3bbf-44de-83ad-d0eb88074c22)8FD4DHZ`]S1_WIOYRN~@_7.png', 0, 'Normal');

-- ----------------------------
-- Table structure for edu_subject
-- ----------------------------
DROP TABLE IF EXISTS `edu_subject`;
CREATE TABLE `edu_subject`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程类别ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类别名称',
  `parentId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '父ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent_id`(`parentId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程科目' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of edu_subject
-- ----------------------------
INSERT INTO `edu_subject` VALUES ('117c09eb-d17f-4a3e-aa4e-4f202806d00a', '人工智能', '0');
INSERT INTO `edu_subject` VALUES ('225be81c-5f65-403f-89df-67d18612a9a7', 'python', 'e645e92f-0f28-4dd2-8565-a83c8c6e1a0b');
INSERT INTO `edu_subject` VALUES ('2e95d11b-ec23-44a7-91cf-1d25fec7f6f6', 'JavaScript', '47f8d27d-03c7-4dbd-a5a9-3edb67d70d3e');
INSERT INTO `edu_subject` VALUES ('39cf20d5-1f4b-44af-9173-b1ce72de5acf', 'Springboot', 'e645e92f-0f28-4dd2-8565-a83c8c6e1a0b');
INSERT INTO `edu_subject` VALUES ('41b1b261-dba2-4f97-b01b-169666b93548', 'HTML', '47f8d27d-03c7-4dbd-a5a9-3edb67d70d3e');
INSERT INTO `edu_subject` VALUES ('47f8d27d-03c7-4dbd-a5a9-3edb67d70d3e', '前端', '0');
INSERT INTO `edu_subject` VALUES ('490124d2-77d0-425f-8f42-6fd059fedf94', '机器学习', '117c09eb-d17f-4a3e-aa4e-4f202806d00a');
INSERT INTO `edu_subject` VALUES ('71c4b2f0-9330-4a0d-bf39-1a310b80715e', 'Django', 'e645e92f-0f28-4dd2-8565-a83c8c6e1a0b');
INSERT INTO `edu_subject` VALUES ('c23ba5d3-50e4-46d4-898a-950817c3caed', 'java', 'e645e92f-0f28-4dd2-8565-a83c8c6e1a0b');
INSERT INTO `edu_subject` VALUES ('d57821d5-f0ea-4aa2-af17-bf2e9db657dd', 'Vue', '47f8d27d-03c7-4dbd-a5a9-3edb67d70d3e');
INSERT INTO `edu_subject` VALUES ('e645e92f-0f28-4dd2-8565-a83c8c6e1a0b', '后端', '0');
INSERT INTO `edu_subject` VALUES ('fdfa70c8-68bc-4f33-b438-ab0224b1ea6a', '深度学习', '117c09eb-d17f-4a3e-aa4e-4f202806d00a');

-- ----------------------------
-- Table structure for edu_teacher
-- ----------------------------
DROP TABLE IF EXISTS `edu_teacher`;
CREATE TABLE `edu_teacher`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '讲师ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '讲师姓名',
  `intro` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '讲师简介',
  `level` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '讲师资历,一句话说明讲师',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '讲师头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '讲师' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of edu_teacher
-- ----------------------------
INSERT INTO `edu_teacher` VALUES ('29b2e5db-e1eb-47a1-beaf-02294e68981b', '周老师', '多年开发及管理经验，曾先后就职于神华和信、亚信联创、安润金融等大中型互联网公司，任技术经理、项目经理、架构师等职位。历经电信、互联网金融等热门行业的项目历练，对传统JavaEE技术体系、云计算、大数据及当下热门的互联网技术都具有深厚的技术功底。 能够将企业流行、实用的技术带回课堂，引导学生少走弯路。', '2', 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/db1c95e3-8728-41fc-a701-f3f4e5b12c3bfile.png');
INSERT INTO `edu_teacher` VALUES ('40fb8737-7cfe-49c0-bcf1-5750fec54e93', '王老师', '从事Java程序及移动安卓开发及IT培训事业十余年，曾就职于伊利集团作为开发团队主力开发了伊利集团人力资源管理系统、经销商管理系统等项目。后在上海京颐股份参与了公司移动医疗、医疗云、医疗物联网的开发。', '2', 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/65c3002a-76b7-450c-a441-3cd8b8857970file.png');
INSERT INTO `edu_teacher` VALUES ('8cac79e2-7039-4b9d-8d5b-e5431cddf70b', '李老师', '具有多年项目开发和教育培训经验，曾先后任职于致远协创、用友科技等知名企业，历任项目经理、架构师等，精通JavaEE、Android、 C++、C#等开发语言，主持并参与Android SDK开发，熟练掌握JavaEE技术及架构体系、SpringMVC、Spring、 Mybatis、Redis、Struts2、Hibernate等框架。', '1', 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/dd23ed64-7bb0-4c3c-a485-d9d54e0fb02bfile.png');
INSERT INTO `edu_teacher` VALUES ('9588bf5b-1bd5-4f67-9011-53606aba4c2c', '魏老师', '多年软件开发及软件培训经验，曾就职于一路捞互联网科技、智康通达等知名企业，从事Java方向的软件研发及教学工作，曾培训东北大学、武汉大学、兰州大学等近百所高校教师及学生近万名。参与大型在线教育（O2O）、新闻发布、车联网等系统；曾担任高级Java工程师、项目经理等职务。精通C/C++、Java核心技术、JVM、SSH、SSM、数据库、Maven、大数据等技术。', '1', 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/584b3e4c-a472-44ec-9fe7-b511f4ed73d0file.png');
INSERT INTO `edu_teacher` VALUES ('9bc7e687-7114-4e9a-82e3-72bc496e7c9a', '雷老师', '具有多年软件开发经验，熟悉 Java、C/C++等多种开发语言，对主流框架Spring、Struts2、Hibernate、MyBatis、SpringMVC等具有丰富的开发经验。曾参与大型物联网系统（智能物流）、智慧城市系统的开发，并主持开发某社交软件的移动端（Android/iOS）、服务端（openfire）、桌面端（swing）、网页端（webIM），对即时通讯、图像处理、流媒体领域等有深入研究。', '1', 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/1c11c67d-6c50-4ffa-8ef2-21638c6882a0file.png');
INSERT INTO `edu_teacher` VALUES ('bcfa2aee-1a36-4902-8d8b-c37330371604', '宋老师', '东北师大理学学士，北京航空航天大学硕士，曾于北航软件开发环境国家重点实验室研究多项课题，对智能交通—浮动车海量数据挖掘及在线社交网络信息传播和控制问题有深入研究，曾发表论文数篇。先后担任过高级软件开发工程师，系统架构师，高级讲师。具备丰富的软件开发经验和教学经验。', '1', 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/a806c438-45a1-45eb-853a-e6d6757e82bafile.png');
INSERT INTO `edu_teacher` VALUES ('debc055c-6b95-43e2-9433-a30aa5d319c1', '封老师', '具多年软件开发经验，曾主持或参与开发《中国电力轨道交通线路数据交换平台》、《SG186工程宁夏电网营销 稽查管理项目》、《天津市广告监管系统》等项目。对JavaEE 技术体系结构、JavaWeb 原理有深刻理解。', '1', 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/0236b0f5-09ce-47db-8078-597e02bc0912file.png');
INSERT INTO `edu_teacher` VALUES ('e6dbb82a-64c6-48ef-a365-0a9341ca7659', '吴恩达', '吴恩达，华裔美国人，是斯坦福大学计算机科学系和电子工程系副教授，人工智能实验室主任。吴恩达是人工智能和机器学习领域国际上最权威的学者之一。吴恩达也是在线教育平台Coursera的联合创始人（with Daphne Koller）。', '2', 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/636b3023-c2ef-47d8-b2d7-ed56f9e42560file.png');
INSERT INTO `edu_teacher` VALUES ('e78b1b98-4710-49d0-a412-caca40918ec4', '林晓宇', '福建农林大学，副教授 ，硕士研究生导师 。2000年毕业于中南大学，2009获福州大学应用数学专业硕士学位。2013年8月至2014年8月加拿大卡尔加里大学访问学者。主要从事计算智能、深度学习、数据挖掘、信息系统等研究。参与主持省自然科学基金项目多项。发表学术论文10多篇。EI收录多篇。', '1', 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/43c00373-a1d8-41cd-bd8e-4b7ec3eecd74file.png');

-- ----------------------------
-- Table structure for edu_video
-- ----------------------------
DROP TABLE IF EXISTS `edu_video`;
CREATE TABLE `edu_video`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '视频ID',
  `courseId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程ID',
  `chapterId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '章节ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '节点名称',
  `videoSourceId` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '云端视频资源',
  `videoOriginalName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '原始文件名称',
  `isFree` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否可以试听：0收费 1免费',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Empty' COMMENT 'Empty未上传 Transcoding转码中  Normal正常',
  `prio` int NULL DEFAULT NULL COMMENT '优先级显示',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`courseId`) USING BTREE,
  INDEX `idx_chapter_id`(`chapterId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程视频' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of edu_video
-- ----------------------------
INSERT INTO `edu_video` VALUES ('0a094bbe-99d1-4c5b-8860-e5806b1161c4', '86c48ccb-3e52-408e-b80a-3f8e170c8220', '339ab8fd-8c25-410b-b573-fcb013fd5fca', '监督学习与神经网络', '838b4cbfcd984c999c7c774d9300903e', '487539914_nb2-1-64 (1).flv', 0, 'Empty', 2);
INSERT INTO `edu_video` VALUES ('1aab4cb1-0cd4-4bc2-a627-50cda950579b', '0021d528-12d8-474c-a680-722fcf07e8ee', 'ed3818cd-d73c-4583-aeed-34eeee649f0f', '数据库连接', 'f2e27d1f1ecc4a57a1ed0e92b2d471c5', '481314861-1-64.flv', 0, 'Empty', 1);
INSERT INTO `edu_video` VALUES ('1c77e1f4-1de3-4e6b-84e9-2967f5be3048', '86c48ccb-3e52-408e-b80a-3f8e170c8220', '339ab8fd-8c25-410b-b573-fcb013fd5fca', '课程资源', '6498bb372ead4d2485ac095e8be12c08', '487540361-1-64.flv', 0, 'Empty', 3);
INSERT INTO `edu_video` VALUES ('2f3b59b7-0202-465a-a403-4bfc0d0e2e90', '86c48ccb-3e52-408e-b80a-3f8e170c8220', '3dc1a729-9032-473c-9cb6-1bea5b07a9f0', '神经网络的表现形式', 'ed235b29afd745b3948fb01e61fb3464', '487545150_nb2-1-64 (1).flv', 0, 'Empty', 2);
INSERT INTO `edu_video` VALUES ('aa484523-6f63-42e4-b4ac-aed11edf9f83', '86c48ccb-3e52-408e-b80a-3f8e170c8220', 'f02c32a0-c56a-4f93-af7d-b414344a3f43', '二元分类', '4d7951e0870246b2bf9ce194272cb80b', '487540823_nb2-1-64 (1).flv', 0, 'Empty', 1);
INSERT INTO `edu_video` VALUES ('b4ce2c4c-1e95-434c-912f-897ec7ca1dad', '0021d528-12d8-474c-a680-722fcf07e8ee', 'e9e2b0df-4267-4da5-b763-4cd12f9da635', '课程大纲', '6cca25b60773495390ddea83d3126259', '481239299-1-64.flv', 0, 'Empty', 2);
INSERT INTO `edu_video` VALUES ('c06a73c8-dd19-4736-9533-34a6c8c8cce5', '86c48ccb-3e52-408e-b80a-3f8e170c8220', '3dc1a729-9032-473c-9cb6-1bea5b07a9f0', '神经网络概览', 'eab21ec4090e4175a9a5d76ad5506173', '487545089_nb2-1-64 (1).flv', 0, 'Empty', 1);
INSERT INTO `edu_video` VALUES ('d6a583c3-3553-4ac6-862c-ade5892b1114', '86c48ccb-3e52-408e-b80a-3f8e170c8220', '339ab8fd-8c25-410b-b573-fcb013fd5fca', '什么是神经网络', '00431e718f424d209c1924dcec5a5639', '487539776-1-64 (1).flv', 0, 'Empty', 1);
INSERT INTO `edu_video` VALUES ('da63e71d-ab93-45f8-a8c3-b87fa1540f9e', '0021d528-12d8-474c-a680-722fcf07e8ee', 'e9e2b0df-4267-4da5-b763-4cd12f9da635', '安装教程', 'dd4fd067187c44c9a7e91b12b75b7090', '481314861-1-64.flv', 0, 'Empty', 1);
INSERT INTO `edu_video` VALUES ('f6817e38-de3c-4d02-902d-edc5b5441c90', '86c48ccb-3e52-408e-b80a-3f8e170c8220', 'f02c32a0-c56a-4f93-af7d-b414344a3f43', '逻辑回归', 'acbe6a411807498398c2b28176058f4e', '487541344-1-64 (1).flv', 0, 'Empty', 2);

-- ----------------------------
-- Table structure for plc_member
-- ----------------------------
DROP TABLE IF EXISTS `plc_member`;
CREATE TABLE `plc_member`  (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `auto` int NULL DEFAULT NULL COMMENT '后台管理员权限',
  `avatar` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of plc_member
-- ----------------------------
INSERT INTO `plc_member` VALUES ('1', '123456', '123456', 0, 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fblog%2F201306%2F27%2F20130627111258_3zCiH.jpeg&refer=http%3A%2F%2Fimg4.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1648549469&t=a8fd057df3725554b2a5db407f3e2579');
INSERT INTO `plc_member` VALUES ('500e124f-fa65-4f8d-baef-91d11b212213', 'fenlei', '123456', 2, 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/f76a0b6a-a384-46ba-becc-69c626b14d31file.png');
INSERT INTO `plc_member` VALUES ('9d5f8502-6c61-4741-a01a-6d2e89137374', 'jiangshi', '123456', 1, 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/dcc37b80-a08b-4330-8b01-1af261046e2efile.png');
INSERT INTO `plc_member` VALUES ('9f86aada-71d6-4983-9ee0-1ea496c7536d', 'kecheng', '123456', 2, 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/d362b757-0d78-42a2-a812-b6027b8434bffile.png');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `orderNo` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '订单号',
  `courseId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '课程id',
  `courseTitle` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `courseCover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程封面',
  `teacherName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '讲师名称',
  `memberId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '会员id',
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员昵称',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员手机',
  `totalFee` decimal(10, 2) NULL DEFAULT 0.01 COMMENT '订单金额（分）',
  `status` tinyint NULL DEFAULT NULL COMMENT '订单状态（0：未支付 1：已支付）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_order_no`(`orderNo`) USING BTREE,
  INDEX `idx_course_id`(`courseId`) USING BTREE,
  INDEX `idx_member_id`(`memberId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('86e33650-75a3-48b3-bdea-5f7392dab2b4', '20220512100949162', '0021d528-12d8-474c-a680-722fcf07e8ee', 'Django全套视频：django框架快速上手', 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/e7f48f9d-c606-46b1-a3b7-c945ee1600acOW_6PU46COSBH7LKY4RAMTX.png', '封老师', '819600d4-c6bf-466a-b7b5-5440cf2fe8dc', '123456', '13459781', 20.00, 1);
INSERT INTO `t_order` VALUES ('89265211-a2e4-4723-afdd-ae702a09ecf0', '20220512101926039', '610f6e09-1d98-4468-8447-3645fa34c816', 'Python从入门到精通教程：懂中文就能学会', 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/43a69fbb-36cf-47eb-9783-ce6cd9631a077RK9ZO[D6P7FJ5Q}$}WC8SW.png', '封老师', '819600d4-c6bf-466a-b7b5-5440cf2fe8dc', '123456', '13459781', 30.00, 1);
INSERT INTO `t_order` VALUES ('971f94aa-0069-4afa-ac94-97e6e25b9b3e', '20220512104039795', '0021d528-12d8-474c-a680-722fcf07e8ee', 'Django全套视频：django框架快速上手', 'https://guli-edu-teacher1001.oss-cn-beijing.aliyuncs.com/e7f48f9d-c606-46b1-a3b7-c945ee1600acOW_6PU46COSBH7LKY4RAMTX.png', '封老师', 'c6e9070c-0a18-49d3-b966-7187a4479b64', '111111', '123456', 20.00, 1);

-- ----------------------------
-- Table structure for t_pay_log
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_log`;
CREATE TABLE `t_pay_log`  (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `orderNo` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '订单号',
  `payTime` datetime NULL DEFAULT NULL COMMENT '支付完成时间',
  `totalFee` decimal(10, 2) NULL DEFAULT 0.01 COMMENT '支付金额（分）',
  `tradeState` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_order_no`(`orderNo`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_pay_log
-- ----------------------------

-- ----------------------------
-- Table structure for ucenter_member
-- ----------------------------
DROP TABLE IF EXISTS `ucenter_member`;
CREATE TABLE `ucenter_member`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '会员id',
  `openid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '微信openid',
  `mobile` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '手机号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称，账户名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `sex` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '会员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ucenter_member
-- ----------------------------
INSERT INTO `ucenter_member` VALUES ('819600d4-c6bf-466a-b7b5-5440cf2fe8dc', NULL, '13459781', '123456', '123456', '', '1');
INSERT INTO `ucenter_member` VALUES ('c6e9070c-0a18-49d3-b966-7187a4479b64', NULL, '123456', '111111', '111111', '', '1');

SET FOREIGN_KEY_CHECKS = 1;
