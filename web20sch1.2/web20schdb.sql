DROP DATABASE IF EXISTS web20schdb;

CREATE DATABASE web20schdb CHAR SET utf8;

USE web20schdb;


-- ----------------------------
-- Table structure for w_admin
-- ----------------------------

CREATE TABLE `w_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickName` varchar(20) NOT NULL,
  `loginId` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginId` (`loginId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of w_admin
-- ---------------------------

INSERT INTO `w_admin`(`id`,`nickName`,`loginId`,`password`) VALUES(1,'REN','w001','ok');

-- ----------------------------
-- Table structure for w_author
-- ----------------------------

CREATE TABLE `w_author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickName` varchar(20) NOT NULL,
  `loginId` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginId` (`loginId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of w_author
-- ---------------------------

INSERT INTO `w_author`(`id`,`nickName`,`loginId`,`password`) VALUES(1,'REN','w001','ok'),(2,'HANG','w002','ok'),(3,'JIN','w003','ok');

-- ----------------------------
-- Table structure for w_topic
-- ----------------------------

CREATE TABLE `w_topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `headLine` varchar(200) NOT NULL ,
  `content` varchar(5000) NOT NULL,
  `topicDateTime` datetime NOT NULL,
  `publish` varchar(50) NOT NULL,
  `author` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_w_author` (`author`),
  CONSTRAINT `FK_w_author` FOREIGN KEY (`author`) REFERENCES `w_author` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
