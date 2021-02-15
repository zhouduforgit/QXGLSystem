-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: qxgl05
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_fn`
--

DROP TABLE IF EXISTS `t_fn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_fn` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(16) DEFAULT NULL,
  `fhref` varchar(32) DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `del` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `yl1` varchar(32) DEFAULT NULL,
  `yl2` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_fn`
--

LOCK TABLES `t_fn` WRITE;
/*!40000 ALTER TABLE `t_fn` DISABLE KEYS */;
INSERT INTO `t_fn` VALUES (1,'权限管理','',1,-1,1,'2020-11-16 19:41:55',NULL,NULL),(2,'用户管理','UserAction.do?method=list',1,1,1,'2020-11-16 19:42:06',NULL,NULL),(3,'角色管理','role.jsp',1,1,1,'2020-11-16 19:42:17',NULL,NULL),(4,'功能管理','fn.jsp',1,1,1,'2020-11-16 19:42:27',NULL,NULL),(5,'基本信息管理','',1,-1,1,'2020-11-16 19:42:49',NULL,NULL),(6,'商品管理','',1,5,1,'2020-11-16 19:43:04',NULL,NULL),(7,'供应商管理','',1,5,1,'2020-11-16 19:43:15',NULL,NULL),(8,'商品-添加','',2,5,1,'2020-11-16 19:43:31',NULL,NULL),(9,'商品-删除','',2,5,1,'2020-11-16 19:43:41',NULL,NULL),(10,'经营管理','',1,-1,1,'2020-11-23 21:58:55',NULL,NULL),(11,'财务管理','',1,-1,1,'2020-11-23 22:05:30',NULL,NULL),(12,'系统管理','',1,-1,1,'2020-11-23 22:06:42',NULL,NULL),(13,'组织管理','',1,1,1,'2020-11-24 21:27:25',NULL,NULL),(14,'商品-编辑','goods/edit.do',2,6,1,'2020-11-24 21:28:15',NULL,NULL),(15,'商品-过滤查询','goods/query.do',2,6,1,'2020-11-24 21:30:49',NULL,NULL),(16,'进货','',1,10,1,'2020-11-24 21:35:06',NULL,NULL),(17,'用户-新建','usreAdd.jsp',2,2,1,'2020-11-26 10:19:36',NULL,NULL),(18,'用户-删除','UserAction.do?method=delete',2,2,1,'2020-11-26 10:20:06',NULL,NULL);
/*!40000 ALTER TABLE `t_fn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(16) DEFAULT NULL,
  `del` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `yl1` varchar(32) DEFAULT NULL,
  `yl2` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES (1,'系统管理员',1,'2020-11-16 19:40:48',NULL,NULL),(2,'人资经理',1,'2020-11-16 19:41:05',NULL,NULL),(3,'销售经理',1,'2020-11-16 19:41:13',NULL,NULL);
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_fn`
--

DROP TABLE IF EXISTS `t_role_fn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role_fn` (
  `rid` int(11) NOT NULL,
  `fid` int(11) NOT NULL,
  PRIMARY KEY (`rid`,`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_fn`
--

LOCK TABLES `t_role_fn` WRITE;
/*!40000 ALTER TABLE `t_role_fn` DISABLE KEYS */;
INSERT INTO `t_role_fn` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,13),(1,14),(1,15),(1,17),(1,18),(2,1),(2,2),(2,3),(2,4),(2,13);
/*!40000 ALTER TABLE `t_role_fn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(16) DEFAULT NULL,
  `upass` varchar(16) DEFAULT NULL,
  `truename` varchar(16) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `del` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `yl1` varchar(32) DEFAULT NULL,
  `yl2` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'zs','123','张三','男',28,1,'2020-11-10 21:14:01',NULL,NULL),(2,'ls','123','李四','女',17,1,'2020-11-10 21:14:22',NULL,NULL),(3,'zs1','123','张三1','男',19,2,'2020-11-11 20:06:35',NULL,NULL),(4,'zs2','123','张三2','男',20,1,'2020-11-11 20:06:45',NULL,NULL),(5,'zs3','123','张三3','男',21,2,'2020-11-11 20:06:52',NULL,NULL),(6,'zs4','123','张三4','男',22,1,'2020-11-11 20:06:59',NULL,NULL),(7,'zs5','123','张三5','男',23,1,'2020-11-11 20:07:07',NULL,NULL),(8,'ls1','123','李四1','女',18,1,'2020-11-11 20:07:30',NULL,NULL),(9,'ls2','123','李四2','女',19,1,'2020-11-11 20:07:37',NULL,NULL),(10,'ls3','123','李四3','女',20,1,'2020-11-11 20:07:45',NULL,NULL),(11,'ls4','123','李四4','女',21,1,'2020-11-11 20:08:39',NULL,NULL),(12,'ls5','123','李四5','女',22,1,'2020-11-11 20:08:45',NULL,NULL),(13,'ww1','123','王五1','男',18,2,'2020-11-16 22:12:17',NULL,NULL),(14,'ww1','123','王五1','男',19,1,'2020-11-18 21:00:53',NULL,NULL),(15,'zl1','123','赵六1','男',18,1,'2020-11-19 21:44:59',NULL,NULL),(16,'zl2','123','赵六2','女',19,1,'2020-11-19 21:44:59',NULL,NULL),(17,'zl3','123','赵六3','男',20,1,'2020-11-19 21:44:59',NULL,NULL),(18,'zl4','123','赵六4','女',21,1,'2020-11-19 21:44:59',NULL,NULL),(19,'zl5','123','赵六5','男',22,1,'2020-11-19 21:44:59',NULL,NULL),(20,'zl6','123','赵六6','女',23,1,'2020-11-19 21:44:59',NULL,NULL),(21,'zl7','123','赵六7','男',24,1,'2020-11-19 21:44:59',NULL,NULL),(22,'zl8','123','赵六8','女',25,1,'2020-11-19 21:44:59',NULL,NULL),(23,'zl9','123','赵六9','男',26,1,'2020-11-19 21:44:59',NULL,NULL),(24,'zl10','123','赵六10','女',27,1,'2020-11-19 21:44:59',NULL,NULL),(25,'zl11','123','赵六11','男',28,1,'2020-11-19 21:44:59',NULL,NULL),(26,'zl12','123','赵六12','女',29,1,'2020-11-19 21:44:59',NULL,NULL),(27,'zl13','123','赵六13','男',30,1,'2020-11-19 21:44:59',NULL,NULL),(28,'zl14','123','赵六14','女',31,1,'2020-11-19 21:44:59',NULL,NULL),(29,'zl15','123','赵六15','男',32,1,'2020-11-19 21:44:59',NULL,NULL),(30,'zl16','123','赵六16','女',33,1,'2020-11-19 21:44:59',NULL,NULL),(31,'zl17','123','赵六17','男',34,1,'2020-11-19 21:44:59',NULL,NULL),(32,'zl18','123','赵六18','女',35,1,'2020-11-19 21:44:59',NULL,NULL),(33,'zl19','123','赵六19','男',36,1,'2020-11-19 21:44:59',NULL,NULL),(34,'zl20','123','赵六20','女',37,1,'2020-11-19 21:44:59',NULL,NULL),(35,'zl21','123','赵六21','男',38,1,'2020-11-19 21:44:59',NULL,NULL),(36,'zl22','123','赵六22','女',39,1,'2020-11-19 21:44:59',NULL,NULL),(37,'zl23','123','赵六23','男',40,1,'2020-11-19 21:44:59',NULL,NULL),(38,'zl24','123','赵六24','女',41,1,'2020-11-19 21:44:59',NULL,NULL),(39,'zl25','123','赵六25','男',42,1,'2020-11-19 21:44:59',NULL,NULL),(40,'zl26','123','赵六26','女',43,1,'2020-11-19 21:44:59',NULL,NULL),(41,'zl27','123','赵六27','男',44,1,'2020-11-19 21:44:59',NULL,NULL),(42,'zl28','123','赵六28','女',45,1,'2020-11-19 21:44:59',NULL,NULL),(43,'zl29','123','赵六29','男',46,1,'2020-11-19 21:44:59',NULL,NULL),(44,'zl30','123','赵六30','女',47,1,'2020-11-19 21:44:59',NULL,NULL),(45,'zl31','123','赵六31','男',48,1,'2020-11-19 21:44:59',NULL,NULL),(46,'zl32','123','赵六32','女',49,1,'2020-11-19 21:44:59',NULL,NULL),(47,'zl33','123','赵六33','男',50,1,'2020-11-19 21:44:59',NULL,NULL),(48,'zl34','123','赵六34','女',51,1,'2020-11-19 21:44:59',NULL,NULL),(49,'zl35','123','赵六35','男',52,1,'2020-11-19 21:44:59',NULL,NULL),(50,'zl36','123','赵六36','女',53,1,'2020-11-19 21:44:59',NULL,NULL),(51,'zl37','123','赵六37','男',54,1,'2020-11-19 21:44:59',NULL,NULL),(52,'zl38','123','赵六38','女',55,1,'2020-11-19 21:44:59',NULL,NULL),(53,'zl39','123','赵六39','男',56,1,'2020-11-19 21:44:59',NULL,NULL),(54,'zl40','123','赵六40','女',57,1,'2020-11-19 21:44:59',NULL,NULL),(55,'zl41','123','赵六41','男',58,1,'2020-11-19 21:44:59',NULL,NULL),(56,'zl42','123','赵六42','女',59,1,'2020-11-19 21:44:59',NULL,NULL),(57,'zl43','123','赵六43','男',60,1,'2020-11-19 21:44:59',NULL,NULL),(58,'zl44','123','赵六44','女',61,1,'2020-11-19 21:44:59',NULL,NULL);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_role`
--

DROP TABLE IF EXISTS `t_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_role` (
  `uid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  PRIMARY KEY (`uid`,`rid`),
  KEY `fk_role_rid` (`rid`),
  CONSTRAINT `fk_role_rid` FOREIGN KEY (`rid`) REFERENCES `t_role` (`rid`),
  CONSTRAINT `fk_user_uid` FOREIGN KEY (`uid`) REFERENCES `t_user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role`
--

LOCK TABLES `t_user_role` WRITE;
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
INSERT INTO `t_user_role` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-26 22:30:39
