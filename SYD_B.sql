-- MySQL dump 10.13  Distrib 5.5.46, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: SYD
-- ------------------------------------------------------
-- Server version	5.5.46-0ubuntu0.14.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `API_predata`
--

DROP TABLE IF EXISTS `API_predata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `API_predata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `latitude` varchar(20) NOT NULL,
  `longitude` varchar(20) NOT NULL,
  `remark` varchar(50) NOT NULL,
  `time` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `API_predata_6340c63c` (`user_id`),
  CONSTRAINT `user_id_refs_id_ab9e2aaf` FOREIGN KEY (`user_id`) REFERENCES `API_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `API_predata`
--

LOCK TABLES `API_predata` WRITE;
/*!40000 ALTER TABLE `API_predata` DISABLE KEYS */;
INSERT INTO `API_predata` VALUES (1,1,'28.47533627','77.31453647','User Installed App','20-Dec-2015 02:43:24'),(2,1,'28.47559157','77.31414976','User Started travelling','Sun Dec 20 03:03:31 GMT+05:30 2015'),(3,1,'28.47556942','77.31417054','User travelling','Sun Dec 20 03:06:01 GMT+05:30 2015'),(4,1,'28.47553285','77.31420655','User travelling','Sun Dec 20 03:16:32 GMT+05:30 2015'),(5,1,'28.47533627','77.31453647','User Installed App','20-Dec-2015 02:43:24'),(6,1,'28.47559157','77.31414976','User Started travelling','Sun Dec 20 03:03:31 GMT+05:30 2015'),(7,1,'28.47556942','77.31417054','User travelling','Sun Dec 20 03:06:01 GMT+05:30 2015'),(8,1,'28.47553285','77.31420655','User travelling','Sun Dec 20 03:16:32 GMT+05:30 2015'),(9,1,'28.47533627','77.31453647','User Installed App','20-Dec-2015 02:43:24'),(10,1,'28.47559157','77.31414976','User Started travelling','Sun Dec 20 03:03:31 GMT+05:30 2015'),(11,1,'28.47556942','77.31417054','User travelling','Sun Dec 20 03:06:01 GMT+05:30 2015'),(12,1,'28.47553285','77.31420655','User travelling','Sun Dec 20 03:16:32 GMT+05:30 2015'),(13,1,'28.47529731','77.31406867','User Installed App','Sun Dec 20 05:16:55 GMT+05:30 2015'),(14,1,'28.47529731','77.31406867','User Installed App','Sun Dec 20 05:16:55 GMT+05:30 2015'),(15,1,'28.47529731','77.31406867','User Installed App','Sun Dec 20 05:16:55 GMT+05:30 2015'),(16,1,'28.47529731','77.31406867','User Installed App','Sun Dec 20 05:16:55 GMT+05:30 2015'),(17,1,'28.47529731','77.31406867','User Installed App','Sun Dec 20 05:16:55 GMT+05:30 2015'),(18,1,'28.47529731','77.31406867','User Installed App','Sun Dec 20 05:16:55 GMT+05:30 2015'),(19,1,'28.47537409','77.31393377','User Installed App','Sat Dec 19 16:20:17 GMT+05:30 2015'),(20,1,'28.47915787','77.31204991','User Started travelling','Sat Dec 19 16:50:45 GMT+05:30 2015'),(21,1,'28.502658','77.299161','User travelling','Sat Dec 19 17:07:28 GMT+05:30 2015'),(22,1,'28.537462','77.283432','User travelling','Sat Dec 19 17:25:31 GMT+05:30 2015'),(23,1,'28.562370','77.266223','User travelling','Sat Dec 19 17:42:44 GMT+05:30 2015'),(24,1,'28.568750','77.220647','User Stopped travelling','Sat Dec 19 17:59:57 GMT+05:30 2015'),(25,1,'28.47531989','77.31479148','User Installed App','Sun Dec 20 17:21:41 GMT+05:30 2015'),(26,1,'28.47915787','77.31204991','User Started travelling','Sun Dec 20 17:45:42 GMT+05:30 2015'),(27,1,'28.44141496','77.3086683','User travelling','Sun Dec 20 18:02:17 GMT+05:30 2015'),(28,1,'28.43784803','77.30858964','User travelling','Sun Dec 20 18:33:50 GMT+05:30 2015'),(29,1,'28.47507214','77.31421639','User Stopped travelling','Sun Dec 20 18:48:51 GMT+05:30 2015'),(30,2,'28.47537409','77.31393377','User Installed App','15-Dec-2015 7:13:16 pm'),(31,2,'28.47915787','77.31204991','User Started travelling','15-Dec-2015 7:29:28 pm'),(32,2,'28.448765','77.308162','User travelling','15-Dec-2015 7:44:10 pm'),(33,2,'28.412240','77.31136','User travelling','15-Dec-2015 8:01:40 pm'),(34,2,'28.366225','77.315313','User travelling','15-Dec-2015 8:21:23 pm'),(35,2,'28.369633','77.279409','User travelling','15-Dec-2015 8:40:23 pm'),(36,3,'28.582061','77.310903','User Installed App','04-Jan-2016 10:01:23 am'),(37,3,'28.582187','77.311507','User travelling','04-Jan-2016 10:12:23 am'),(38,3,'28.582486','77.313318','User travelling','04-Jan-2016 10:25:23 am'),(39,3,'28.580529','77.315278','User travelling','04-Jan-2016 10:34:23 am'),(40,3,'28.580529','77.315278','User Stopped travelling','04-Jan-2016 10:45:23 am'),(41,2,'28.478361','77.311769','User Started travelling','10-Jan-2016 08:16:50 am'),(42,2,'28.494218','77.304225','User travelling','10-Jan-2016 08:31:00 am'),(43,3,'28.519184','77.294741','User travelling','10-Jan-2016 08:43:02 am'),(44,2,'28.561483','77.266288','User travelling','10-Jan-2016 08:56:22 am'),(45,2,'28.583004','77.262940','User travelling','10-Jan-2016 09:15:23 am'),(46,2,'28.611490','77.281523','User travelling','10-Jan-2016 09:25:30 am'),(47,2,'28.627500','77.317743','User travelling','10-Jan-2016 09:40:34 am'),(48,2,'28.622001','77.364779','User Stopped travelling','10-Jan-2016 09:45:15 am'),(49,1,'28.630627','77.374220','User Started travelling','09-Apr-2016 04:20:23 pm'),(50,1,'28.627613','77.317700','User travelling','09-Apr-2016 04:40:13 pm'),(51,1,'28.610774','77.280664','User travelling','09-Apr-2016 04:55:14 pm'),(52,1,'28.585001','77.260666','User travelling','09-Apr-2016 05:12:14 pm'),(53,1,'28.572188','77.258263','User travelling','09-Apr-2016 05:25:26 pm'),(54,1,'28.538173','77.283046','User travelling','09-Apr-2016 05:41:52 pm'),(55,1,'28.510591','77.298346','User travelling','09-Apr-2016 05:55:13 pm'),(56,1,'28.475175','77.313538','User Stopped travelling','09-Apr-2016 06:01:00 pm'),(57,2,'28.476495','77.305255','User Started travelling','11-Apr-2016 10:55:06 pm'),(58,2,'28.495298','77.302895','User travelling','11-Apr-2016 11:15:14 pm'),(59,2,'28.465970','77.306199','User travelling','11-Apr-2016 11:25:16 pm'),(60,2,'28.437350','77.308774','User travelling','11-Apr-2016 11:38:16 pm'),(61,2,'28.411063','77.319674','User Stopped travelling','11-Apr-2016 11:52:36 pm'),(62,3,'28.577375','77.316434','User Started travelling','12-Apr-2016 10:43:26 am'),(63,3,'28.603578','77.289634','User travelling','12-Apr-2016 10:59:06 am'),(64,3,'28.629308','77.324567','User travelling','12-Apr-2016 11:16:16 am'),(65,3,'28.630890','77.370057','User travelling','12-Apr-2016 11:35:16 am'),(66,3,'28.631041','77.364092','User Stopped travelling','12-Apr-2016 11:56:45 am');
/*!40000 ALTER TABLE `API_predata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `API_user`
--

DROP TABLE IF EXISTS `API_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `API_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `DateTimeCreated` datetime NOT NULL,
  `isLocationTracking` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `API_user`
--

LOCK TABLES `API_user` WRITE;
/*!40000 ALTER TABLE `API_user` DISABLE KEYS */;
INSERT INTO `API_user` VALUES (1,'sarthak','sarthakmeh03@gmail.com','pbkdf2_sha256$12000$POhUa2gSIZXR$LG9r+TAWojZNc4sV4zsTcIGLFzc+1n2xsVUhzEyWBjE=','2015-12-19 22:54:07',1),(2,'shrey','shrey.mehrish@yahoo.co.in','pbkdf2_sha256$12000$cSX4zcucLoLF$UHHK6Zi+aemDyZpiwxVI8b8f/ACnk7Ly0Zh6Bjqskz4=','2015-12-21 00:16:01',1),(3,'vaibhav_pande','vaibhav.pande15@gmail.com','vaibhav','2016-04-11 18:54:14',1);
/*!40000 ALTER TABLE `API_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_group`
--

DROP TABLE IF EXISTS `auth_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_group`
--

LOCK TABLES `auth_group` WRITE;
/*!40000 ALTER TABLE `auth_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_group_permissions`
--

DROP TABLE IF EXISTS `auth_group_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_group_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `group_id` (`group_id`,`permission_id`),
  KEY `auth_group_permissions_5f412f9a` (`group_id`),
  KEY `auth_group_permissions_83d7f98b` (`permission_id`),
  CONSTRAINT `group_id_refs_id_f4b32aac` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  CONSTRAINT `permission_id_refs_id_6ba0f519` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_group_permissions`
--

LOCK TABLES `auth_group_permissions` WRITE;
/*!40000 ALTER TABLE `auth_group_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_group_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_permission`
--

DROP TABLE IF EXISTS `auth_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `content_type_id` int(11) NOT NULL,
  `codename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `content_type_id` (`content_type_id`,`codename`),
  KEY `auth_permission_37ef4eb4` (`content_type_id`),
  CONSTRAINT `content_type_id_refs_id_d043b34a` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_permission`
--

LOCK TABLES `auth_permission` WRITE;
/*!40000 ALTER TABLE `auth_permission` DISABLE KEYS */;
INSERT INTO `auth_permission` VALUES (1,'Can add log entry',1,'add_logentry'),(2,'Can change log entry',1,'change_logentry'),(3,'Can delete log entry',1,'delete_logentry'),(4,'Can add permission',2,'add_permission'),(5,'Can change permission',2,'change_permission'),(6,'Can delete permission',2,'delete_permission'),(7,'Can add group',3,'add_group'),(8,'Can change group',3,'change_group'),(9,'Can delete group',3,'delete_group'),(10,'Can add user',4,'add_user'),(11,'Can change user',4,'change_user'),(12,'Can delete user',4,'delete_user'),(13,'Can add content type',5,'add_contenttype'),(14,'Can change content type',5,'change_contenttype'),(15,'Can delete content type',5,'delete_contenttype'),(16,'Can add session',6,'add_session'),(17,'Can change session',6,'change_session'),(18,'Can delete session',6,'delete_session'),(19,'Can add user',7,'add_user'),(20,'Can change user',7,'change_user'),(21,'Can delete user',7,'delete_user'),(22,'Can add pre data',8,'add_predata'),(23,'Can change pre data',8,'change_predata'),(24,'Can delete pre data',8,'delete_predata'),(25,'Can add user friends',9,'add_userfriends'),(26,'Can change user friends',9,'change_userfriends'),(27,'Can delete user friends',9,'delete_userfriends');
/*!40000 ALTER TABLE `auth_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user`
--

DROP TABLE IF EXISTS `auth_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(128) NOT NULL,
  `last_login` datetime NOT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `username` varchar(30) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `email` varchar(75) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `date_joined` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user`
--

LOCK TABLES `auth_user` WRITE;
/*!40000 ALTER TABLE `auth_user` DISABLE KEYS */;
INSERT INTO `auth_user` VALUES (1,'pbkdf2_sha256$24000$R5NjTFlw5GG7$PwuJ6w2/DjQTOPCri9bm0ObwIEarjYj0frb8r4nvmyo=','2016-06-06 17:17:58',1,'admin','','','admin@admin.com',1,1,'2015-12-19 22:53:36');
/*!40000 ALTER TABLE `auth_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user_groups`
--

DROP TABLE IF EXISTS `auth_user_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user_groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`group_id`),
  KEY `auth_user_groups_6340c63c` (`user_id`),
  KEY `auth_user_groups_5f412f9a` (`group_id`),
  CONSTRAINT `group_id_refs_id_274b862c` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  CONSTRAINT `user_id_refs_id_40c41112` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user_groups`
--

LOCK TABLES `auth_user_groups` WRITE;
/*!40000 ALTER TABLE `auth_user_groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_user_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user_user_permissions`
--

DROP TABLE IF EXISTS `auth_user_user_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user_user_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`permission_id`),
  KEY `auth_user_user_permissions_6340c63c` (`user_id`),
  KEY `auth_user_user_permissions_83d7f98b` (`permission_id`),
  CONSTRAINT `permission_id_refs_id_35d9ac25` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `user_id_refs_id_4dc23c39` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user_user_permissions`
--

LOCK TABLES `auth_user_user_permissions` WRITE;
/*!40000 ALTER TABLE `auth_user_user_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_user_user_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_admin_log`
--

DROP TABLE IF EXISTS `django_admin_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `django_admin_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action_time` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  `content_type_id` int(11) DEFAULT NULL,
  `object_id` longtext,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint(5) unsigned NOT NULL,
  `change_message` longtext NOT NULL,
  PRIMARY KEY (`id`),
  KEY `django_admin_log_6340c63c` (`user_id`),
  KEY `django_admin_log_37ef4eb4` (`content_type_id`),
  CONSTRAINT `content_type_id_refs_id_93d2d1f8` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`),
  CONSTRAINT `user_id_refs_id_c0d12874` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_admin_log`
--

LOCK TABLES `django_admin_log` WRITE;
/*!40000 ALTER TABLE `django_admin_log` DISABLE KEYS */;
INSERT INTO `django_admin_log` VALUES (1,'2016-04-11 18:54:14',1,7,'3','vaibhav.pande15@gmail.com',1,''),(2,'2016-04-11 18:54:41',1,7,'3','vaibhav.pande15@gmail.com',2,'Changed username.'),(3,'2016-04-11 18:54:49',1,7,'3','vaibhav.pande15@gmail.com',2,'Changed isLocationTracking.'),(4,'2016-04-11 18:56:28',1,8,'36','preData object',1,''),(5,'2016-04-11 18:56:50',1,8,'36','preData object',2,'Changed remark.'),(6,'2016-04-11 18:58:03',1,8,'37','preData object',1,''),(7,'2016-04-11 18:59:03',1,8,'38','preData object',1,''),(8,'2016-04-11 18:59:43',1,8,'39','preData object',1,''),(9,'2016-04-11 19:00:18',1,8,'40','preData object',1,''),(10,'2016-04-11 19:01:41',1,8,'41','preData object',1,''),(11,'2016-04-11 19:02:47',1,8,'42','preData object',1,''),(12,'2016-04-11 19:03:46',1,8,'43','preData object',1,''),(13,'2016-04-11 19:03:55',1,8,'42','preData object',2,'Changed time.'),(14,'2016-04-11 19:04:39',1,8,'44','preData object',1,''),(15,'2016-04-11 19:05:30',1,8,'45','preData object',1,''),(16,'2016-04-11 19:06:19',1,8,'46','preData object',1,''),(17,'2016-04-11 19:07:15',1,8,'47','preData object',1,''),(18,'2016-04-11 19:08:49',1,8,'48','preData object',1,''),(19,'2016-04-11 19:10:01',1,8,'49','preData object',1,''),(20,'2016-04-11 19:10:57',1,8,'50','preData object',1,''),(21,'2016-04-11 19:11:21',1,8,'49','preData object',2,'Changed time.'),(22,'2016-04-11 19:11:30',1,8,'49','preData object',2,'Changed time.'),(23,'2016-04-11 19:11:38',1,8,'49','preData object',2,'Changed time.'),(24,'2016-04-11 19:12:10',1,8,'50','preData object',2,'Changed time.'),(25,'2016-04-11 19:12:51',1,8,'51','preData object',1,''),(26,'2016-04-11 19:13:30',1,8,'52','preData object',1,''),(27,'2016-04-11 19:14:23',1,8,'53','preData object',1,''),(28,'2016-04-11 19:15:13',1,8,'54','preData object',1,''),(29,'2016-04-11 19:15:51',1,8,'55','preData object',1,''),(30,'2016-04-11 19:16:01',1,8,'55','preData object',2,'Changed time.'),(31,'2016-04-11 19:17:01',1,8,'56','preData object',1,''),(32,'2016-04-11 19:17:09',1,8,'56','preData object',2,'Changed time.'),(33,'2016-04-11 19:18:16',1,8,'57','preData object',1,''),(34,'2016-04-11 19:18:50',1,8,'58','preData object',1,''),(35,'2016-04-11 19:19:46',1,8,'59','preData object',1,''),(36,'2016-04-11 19:20:29',1,8,'60','preData object',1,''),(37,'2016-04-11 19:21:35',1,8,'61','preData object',1,''),(38,'2016-04-11 19:22:51',1,8,'62','preData object',1,''),(39,'2016-04-11 19:23:53',1,8,'63','preData object',1,''),(40,'2016-04-11 19:24:32',1,8,'64','preData object',1,''),(41,'2016-04-11 19:25:05',1,8,'65','preData object',1,''),(42,'2016-04-11 19:25:43',1,8,'66','preData object',1,'');
/*!40000 ALTER TABLE `django_admin_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_content_type`
--

DROP TABLE IF EXISTS `django_content_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `django_content_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `app_label` (`app_label`,`model`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_content_type`
--

LOCK TABLES `django_content_type` WRITE;
/*!40000 ALTER TABLE `django_content_type` DISABLE KEYS */;
INSERT INTO `django_content_type` VALUES (1,'log entry','admin','logentry'),(2,'permission','auth','permission'),(3,'group','auth','group'),(4,'user','auth','user'),(5,'content type','contenttypes','contenttype'),(6,'session','sessions','session'),(7,'user','API','user'),(8,'pre data','API','predata'),(9,'','API','userfriends');
/*!40000 ALTER TABLE `django_content_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_migrations`
--

DROP TABLE IF EXISTS `django_migrations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `django_migrations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_migrations`
--

LOCK TABLES `django_migrations` WRITE;
/*!40000 ALTER TABLE `django_migrations` DISABLE KEYS */;
INSERT INTO `django_migrations` VALUES (1,'API','0001_initial','2016-04-11 19:38:31');
/*!40000 ALTER TABLE `django_migrations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_session`
--

DROP TABLE IF EXISTS `django_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime NOT NULL,
  PRIMARY KEY (`session_key`),
  KEY `django_session_b7b81f0c` (`expire_date`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_session`
--

LOCK TABLES `django_session` WRITE;
/*!40000 ALTER TABLE `django_session` DISABLE KEYS */;
INSERT INTO `django_session` VALUES ('22dlhagdbyzz5z7j9dnuf2fa7tj730e3','ZGU0MDU5YTRiYjc1MmVkNzUzMGQzYzUwOTUyYTI1N2M4YmNhNjA4Njp7Il9hdXRoX3VzZXJfYmFja2VuZCI6ImRqYW5nby5jb250cmliLmF1dGguYmFja2VuZHMuTW9kZWxCYWNrZW5kIiwiX2F1dGhfdXNlcl9pZCI6MX0=','2016-01-04 09:08:30'),('8ur5kyxbac5yqfd80logd3885e4gl18s','ZGU0MDU5YTRiYjc1MmVkNzUzMGQzYzUwOTUyYTI1N2M4YmNhNjA4Njp7Il9hdXRoX3VzZXJfYmFja2VuZCI6ImRqYW5nby5jb250cmliLmF1dGguYmFja2VuZHMuTW9kZWxCYWNrZW5kIiwiX2F1dGhfdXNlcl9pZCI6MX0=','2016-01-02 23:02:27'),('gigph7eeefpiuj3xlo5gh39swlv2kvlb','ZGU0MDU5YTRiYjc1MmVkNzUzMGQzYzUwOTUyYTI1N2M4YmNhNjA4Njp7Il9hdXRoX3VzZXJfYmFja2VuZCI6ImRqYW5nby5jb250cmliLmF1dGguYmFja2VuZHMuTW9kZWxCYWNrZW5kIiwiX2F1dGhfdXNlcl9pZCI6MX0=','2016-04-25 18:53:18'),('o59ndfhnitg0ibzancifyxobhlh60mbt','MmI2MzkzYWMwNTUzNTlhYjQ5OWVhN2EzNWFiYmI5NjJlZGZmNDExZTp7Il9hdXRoX3VzZXJfaGFzaCI6ImViMDUwMTJkMjllZjc2ZmQ1NzJkNDI1NjU4MTgxYjgyM2E0YWE1MjIiLCJfYXV0aF91c2VyX2JhY2tlbmQiOiJkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZCIsIl9hdXRoX3VzZXJfaWQiOiIxIn0=','2016-06-20 17:17:58'),('z4jjywoamyecxc8ocfog2jx8pi492m1j','ZWQ3ZDAzYzM2ZDg3MGNiNTg3NDZlNmZmNDlmM2ExOWM1NzMzNGFjMzp7Il9hdXRoX3VzZXJfaGFzaCI6IjAzZDRiNjZmYzc5MWM2NzI3YjI4NGI2MDFkNjI3ZGIyZTgwOGM0MDIiLCJfYXV0aF91c2VyX2JhY2tlbmQiOiJkamFuZ28uY29udHJpYi5hdXRoLmJhY2tlbmRzLk1vZGVsQmFja2VuZCIsIl9hdXRoX3VzZXJfaWQiOiIxIn0=','2016-06-20 17:17:58');
/*!40000 ALTER TABLE `django_session` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-06 23:26:55
