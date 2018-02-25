/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.7.20-0ubuntu0.16.04.1 : Database - examination_system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`examination_system` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `examination_system`;

/*Table structure for table `college` */

DROP TABLE IF EXISTS `college`;

CREATE TABLE `college` (
  `collegeID` int(11) NOT NULL,
  `collegeName` varchar(200) NOT NULL COMMENT '课程名',
  PRIMARY KEY (`collegeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `college` */

insert  into `college`(`collegeID`,`collegeName`) values (1,'信息学院'),(2,'林学院'),(3,'水土保持学院'),(4,'生物科学与技术学院'),(5,'园林学院'),(6,'经济管理学院'),(7,'工学院'),(8,'材料科学与技术学院'),(9,'人文社会科学学院 '),(10,'外语学院'),(11,'理学院'),(12,'自然保护区学院'),(13,'环境科学与工程学院 '),(14,'艺术设计学院 '),(15,'马克思主义学院');

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `courseID` int(11) NOT NULL,
  `courseName` varchar(200) NOT NULL COMMENT '课程名称',
  `teacherID` int(11) NOT NULL,
  `courseTime` varchar(200) DEFAULT NULL COMMENT '开课时间',
  `classRoom` varchar(200) DEFAULT NULL COMMENT '开课地点',
  `courseWeek` int(200) DEFAULT NULL COMMENT '学时',
  `courseType` varchar(20) DEFAULT NULL COMMENT '课程类型',
  `collegeID` int(11) NOT NULL COMMENT '所属院系',
  `score` int(11) NOT NULL COMMENT '学分',
  PRIMARY KEY (`courseID`),
  KEY `collegeID` (`collegeID`),
  KEY `teacherID` (`teacherID`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`collegeID`) REFERENCES `college` (`collegeID`),
  CONSTRAINT `course_ibfk_2` FOREIGN KEY (`teacherID`) REFERENCES `teacher` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`courseID`,`courseName`,`teacherID`,`courseTime`,`classRoom`,`courseWeek`,`courseType`,`collegeID`,`score`) values (1,'C语言程序设计',1001,'周二','科401',18,'必修课',1,3),(2,'Python爬虫技巧',1001,'周四','X402',18,'必修课',1,3),(3,'数据结构',1001,'周四','科401',18,'必修课',1,2),(4,'Java程序设计',1002,'周五','科401',18,'必修课',1,2),(5,'英语',1002,'周四','X302',18,'必修课',2,2),(6,'服装设计',1003,'周一','科401',18,'选修课',2,2);

/*Table structure for table `firchoose` */

DROP TABLE IF EXISTS `firchoose`;

CREATE TABLE `firchoose` (
  `topic_id` int(10) DEFAULT NULL,
  `stu_id` int(10) DEFAULT NULL,
  `fc_time` date DEFAULT NULL,
  KEY `topic_id` (`topic_id`),
  KEY `stu_id` (`stu_id`),
  CONSTRAINT `firchoose_ibfk_1` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`topic_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `firchoose_ibfk_2` FOREIGN KEY (`stu_id`) REFERENCES `student` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `firchoose` */

insert  into `firchoose`(`topic_id`,`stu_id`,`fc_time`) values (6,151303120,'2018-01-08'),(4,151303120,'2018-01-08'),(5,100021,'2018-01-09'),(33,100021,'2018-01-09');

/*Table structure for table `manager` */

DROP TABLE IF EXISTS `manager`;

CREATE TABLE `manager` (
  `mag_id` char(10) NOT NULL,
  `mag_password` varchar(20) NOT NULL,
  PRIMARY KEY (`mag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `manager` */

insert  into `manager`(`mag_id`,`mag_password`) values ('molinli','123456');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `roleID` int(11) NOT NULL,
  `roleName` varchar(20) NOT NULL,
  `permissions` varchar(255) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`roleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`roleID`,`roleName`,`permissions`) values (0,'admin',NULL),(1,'teacher',NULL),(2,'student',NULL);

/*Table structure for table `score` */

DROP TABLE IF EXISTS `score`;

CREATE TABLE `score` (
  `stu_id` int(10) NOT NULL,
  `score` int(10) DEFAULT NULL,
  PRIMARY KEY (`stu_id`),
  CONSTRAINT `score_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `student` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `score` */

insert  into `score`(`stu_id`,`score`) values (100021,98);

/*Table structure for table `selectedcourse` */

DROP TABLE IF EXISTS `selectedcourse`;

CREATE TABLE `selectedcourse` (
  `courseID` int(11) NOT NULL,
  `studentID` int(11) NOT NULL,
  `mark` int(11) DEFAULT NULL COMMENT '成绩',
  KEY `courseID` (`courseID`),
  KEY `studentID` (`studentID`),
  CONSTRAINT `selectedcourse_ibfk_1` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`) ON DELETE CASCADE,
  CONSTRAINT `selectedcourse_ibfk_2` FOREIGN KEY (`studentID`) REFERENCES `student` (`userID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `selectedcourse` */

insert  into `selectedcourse`(`courseID`,`studentID`,`mark`) values (5,12,NULL);

/*Table structure for table `statusInfo` */

DROP TABLE IF EXISTS `statusInfo`;

CREATE TABLE `statusInfo` (
  `status` int(1) NOT NULL,
  `content` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `flag` int(1) DEFAULT NULL,
  PRIMARY KEY (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `statusInfo` */

insert  into `statusInfo`(`status`,`content`,`flag`) values (0,'现在是老师出题阶段，请童鞋们耐心等待~',NULL);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(200) NOT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `birthYear` date DEFAULT NULL COMMENT '出生日期',
  `grade` date DEFAULT NULL COMMENT '入学时间',
  `collegeID` int(11) NOT NULL COMMENT '院系id',
  PRIMARY KEY (`userID`),
  KEY `collegeID` (`collegeID`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`collegeID`) REFERENCES `college` (`collegeID`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=151002404 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`userID`,`userName`,`sex`,`birthYear`,`grade`,`collegeID`) values (12,'莫林立','男','1995-11-30','2019-09-17',14),(100013,'开普勒','男','1996-09-02','2015-09-02',15),(100021,'愣头青','男','2018-01-10','2017-12-21',2),(150302223,'黄自华','男','2018-01-07','2018-01-07',1),(150702107,'马浩云','女','1996-09-02','2015-09-02',1),(151002103,'应咏志','男','1996-09-02','2015-09-02',1),(151002110,'刘天禹','男','1996-09-02','2015-09-02',1),(151002124,'纪芳','女','2018-03-03','2017-12-27',1),(151002207,'丁可','男','2018-01-10','2018-01-02',1),(151002308,'张宇奇','男','2018-01-09','2018-01-17',1),(151002312,'张盛福','男','2018-01-23','2018-01-19',1),(151002314,'孙蕴哲','男','2018-01-25','2018-01-16',1),(151002320,'王雅卉','女','2018-01-26','2018-01-08',1),(151002403,'张子良','男','2018-01-12','2018-01-16',1),(151303120,'林哲宇','男','1996-09-21','2015-09-02',1);

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(200) NOT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `birthYear` date NOT NULL,
  `degree` varchar(20) DEFAULT NULL COMMENT '学历',
  `title` varchar(255) DEFAULT NULL COMMENT '职称',
  `grade` date DEFAULT NULL COMMENT '入职时间',
  `collegeID` int(11) NOT NULL COMMENT '院系',
  PRIMARY KEY (`userID`),
  KEY `collegeID` (`collegeID`),
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`collegeID`) REFERENCES `college` (`collegeID`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1015 DEFAULT CHARSET=utf8;

/*Data for the table `teacher` */

insert  into `teacher`(`userID`,`userName`,`sex`,`birthYear`,`degree`,`title`,`grade`,`collegeID`) values (1001,'李冬梅','女','1980-01-01','博士','副教授','2015-09-02',1),(1002,'王建新','男','1972-09-01','博士','教授','2015-09-02',1),(1003,'付红萍','女','1996-09-02','硕士','助教','2017-07-07',1),(1004,'杨刚','男','1996-09-02','硕士','教授','2015-09-02',1),(1006,'柏荣刚','男','1985-05-15','博士','讲师','2017-03-01',1),(1007,'杨猛','男','1990-01-28','硕士','副教授','2009-12-28',1),(1008,'任忠诚','男','1975-11-12','硕士','讲师','2011-07-14',1),(1009,'蓝海洋','男','1984-02-08','博士','副教授','2018-01-10',1),(1010,'罗柳红','女','2018-02-16','博士','副教授','2018-01-05',11),(1011,'张晓宇','女','2018-01-18','博士','副教授','2018-01-25',11),(1012,'安友丰','男','2018-01-01','博士','教授','2018-01-08',5),(1013,'曹新','男','2018-03-23','博士','教授','2018-01-17',5),(1014,'','男','1996-09-02','本科','普通教师','2015-09-02',1);

/*Table structure for table `topic` */

DROP TABLE IF EXISTS `topic`;

CREATE TABLE `topic` (
  `topic_id` int(10) NOT NULL,
  `tea_id` int(10) DEFAULT NULL,
  `stu_id` int(10) DEFAULT NULL,
  `topic_type` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `topic_title` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `topic_major` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '课题面向的专业',
  `topic_content` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `topic_request` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`topic_id`),
  KEY `stu_id` (`stu_id`),
  KEY `tea_id` (`tea_id`),
  CONSTRAINT `topic_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `student` (`userID`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `topic_ibfk_2` FOREIGN KEY (`tea_id`) REFERENCES `teacher` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `topic` */

insert  into `topic`(`topic_id`,`tea_id`,`stu_id`,`topic_type`,`topic_title`,`topic_major`,`topic_content`,`topic_request`) values (4,1002,NULL,'运筹学领域','基于程序设计语言的单纯形表的换基迭代系统','信息学院','为了减少人工出错，单纯形表的换基迭代使用程序开发','线性代数，概率逻辑，运筹学线性规划'),(5,1007,NULL,'电算化','Financial business integrated ERP risk control','经济管理学院','ERP企业资源的财务管理','会计信息专业或者会计电算化专业'),(6,1008,151303120,'语境语义','Ontext and Meaning','外语学院','分析英语语义与语境的结合','英语相关专业'),(7,1006,NULL,'写作领域','入世商务英语写作的措施研究','经济管理学院 ','减少商务英语与现实脱节，具体的做法','英语相关专业'),(9,1002,NULL,'商业银行','商业银行应对利率市场化的策略研究','经济管理学院 ','商业银行与现行市场进行比对与分析','修读金融经济学，证券投资学，公司理财'),(11,1008,NULL,'供应链','产品设计与物流成本关系研究','信息学院','设计产品后与现代物流供应链的联系','产品艺术设计，故事创作，交互设计'),(12,1003,NULL,'网页开发','基于html5的水果忍者网页游戏开发','艺术设计学院 ','使用网页设计语言实现小型游戏开发','修读网站开发技术等课程，具有游戏创作鉴赏能力'),(13,1001,NULL,'林业产业','Research on the development of forestry industrialization in China','林学院','基于林业及其产业化的经济分析 修改','林业经济管理或林业信息专业'),(14,1002,NULL,'水土流失','Sabina vulgaris, Artemisia ordosica root distribution and root dynamic','水土保持学院','就近年来美国水土流失现象做出简要分析并提出对策','修读水土沙漠学等相关课程'),(15,1004,NULL,'民营企业','民营企业人力资源管理问题研究毕业论文','经济管理学院','人力研究方法在民营企业的应用','人力资源或工商管理相关专业'),(17,1006,NULL,'生态林业','低碳经济下林业重点工程资金稽查工作浅探','林学院','林业与森林保护在资金稽查方面的应用','修读林业生态学，现代林学进展'),(18,1004,NULL,'生态系统','伊犁绢蒿荒漠退化草地植物生态适应对策的研究','林学院','草业科学在新疆荒漠地带的应用','草业科学或草业科学管理方向的专业'),(19,1006,NULL,'皇家园林','A comparative study of private gardens in the south of China and the style of natural landscape architecture in the UK','园林学院','研究西方园林艺术与中国艺术','园艺园林风景园林相关专业'),(20,1008,NULL,'控制系统','基于PLC的电梯控制系统软件设计','工学院','控制系统的设计与完善','具有较强的数学推理，编程能力'),(21,1003,NULL,'智能机器','Design and Simulation of machine teaching robot (graduation design)','工学院','智能机器人完成机械设计制造及其自动化','机械设计制造，电气工程，及其自动化专业'),(22,1001,NULL,'引擎制动','the detection and maintenance of the transmission system','工学院','汽车引擎与传动装置的设计','拥有车辆工程传动控制理论'),(23,1002,NULL,'廉政建设','渎职罪与廉政建设','人文社会科学学院 ','就法律相关的某一领域进行分析','法律文学相关专业，修读法理学'),(24,1007,NULL,'高等代数','解析函数展开成幂级数的方法研究','理学院','研究高等代数中有关解析函数的应用','修读傅里叶及其变换函数课程'),(25,1004,NULL,'数理逻辑','概率统计在教学管理中的应用','理学院','研究数理统计在教学方面的进展','修读编程语言及概率论（数学专业）'),(27,1003,NULL,'温煮系统','Stromboli麦汁煮沸系统的研究与设计','生物科学与技术学院','基于生物细胞工程实验分析','生物工程，生物技术，生物科学相关专业'),(28,1007,NULL,'相色谱法','高效液相色谱法优化黄酮类物质的分离分析','生物科学与技术学院','同位素标记法的广泛应用','生物工程，生物技术，生物科学相关专业'),(29,1002,NULL,'环境经济','循环经济学的研究与探讨','环境科学与工程学院 ','环境工程与污染防治等领域的应用','环境科学，环境工程，排水工程相关专业'),(31,1001,NULL,'花椰菜种植技术','花椰菜种植','马克思主义学院','会种韭菜','会园林艺术基础'),(33,1001,100021,'宏观经济学','经济学人','经济管理学院','看个人喜好','要求写12000字论文');

/*Table structure for table `userlogin` */

DROP TABLE IF EXISTS `userlogin`;

CREATE TABLE `userlogin` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `role` int(11) NOT NULL DEFAULT '2' COMMENT '角色权限',
  PRIMARY KEY (`userID`),
  KEY `role` (`role`),
  CONSTRAINT `userlogin_ibfk_1` FOREIGN KEY (`role`) REFERENCES `role` (`roleID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Data for the table `userlogin` */

insert  into `userlogin`(`userID`,`userName`,`password`,`role`) values (1,'admin','13161613522',0),(14,'1001','1234',1),(15,'1002','123',1),(16,'1003','123',1),(17,'12','123',2),(22,'100013','123',2),(23,'151303120','123',2),(25,'12345','123',1),(27,'100021','123',2),(28,'1008','123',1),(30,'150702107','123',2),(31,'151002103','123',2),(32,'151002110','123',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
