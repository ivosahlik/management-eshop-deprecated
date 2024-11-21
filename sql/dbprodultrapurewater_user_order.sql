-- MySQL dump 10.13  Distrib 8.0.21, for macos10.15 (x86_64)
--
-- Host: localhost    Database: dbprodultrapurewater
-- ------------------------------------------------------
-- Server version	5.7.33-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user_order`
--

DROP TABLE IF EXISTS `user_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_date` datetime DEFAULT NULL,
  `order_status` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `order_total` decimal(19,2) DEFAULT NULL,
  `shipping_date` datetime DEFAULT NULL,
  `shipping_method` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `billing_address_id` bigint(20) DEFAULT NULL,
  `payment_id` bigint(20) DEFAULT NULL,
  `shipping_address_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `order_grand_total_vat` decimal(19,2) DEFAULT NULL,
  `order_grand_width_total` decimal(19,2) DEFAULT NULL,
  `order_shipping` decimal(19,2) DEFAULT NULL,
  `order_sum_total_order_with_shipping_and_include_vat` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbaytj4l2p74kc5dp2dcrhucjo` (`billing_address_id`),
  KEY `FKqjg5jrh5qwnhl2f9lk7n77454` (`payment_id`),
  KEY `FKo2lj94xaujs1se8whlhc37nj7` (`shipping_address_id`),
  KEY `FKj86u1x7csa8yd68ql2y1ibrou` (`user_id`),
  CONSTRAINT `FKbaytj4l2p74kc5dp2dcrhucjo` FOREIGN KEY (`billing_address_id`) REFERENCES `billing_address` (`id`),
  CONSTRAINT `FKj86u1x7csa8yd68ql2y1ibrou` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKo2lj94xaujs1se8whlhc37nj7` FOREIGN KEY (`shipping_address_id`) REFERENCES `shipping_address` (`id`),
  CONSTRAINT `FKqjg5jrh5qwnhl2f9lk7n77454` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_order`
--

LOCK TABLES `user_order` WRITE;
/*!40000 ALTER TABLE `user_order` DISABLE KEYS */;
INSERT INTO `user_order` VALUES (1,'2019-02-05 00:09:10','created',23.00,NULL,'groundShipping',1,1,1,1,NULL,NULL,NULL,NULL),(2,'2019-03-13 18:20:53','created',605.00,NULL,'groundShipping',2,2,2,1,NULL,NULL,NULL,NULL),(3,'2019-03-24 19:50:45','created',157.00,NULL,'groundShipping',3,3,3,1,NULL,NULL,NULL,NULL),(4,'2019-03-24 20:15:13','created',157.00,NULL,'groundShipping',4,4,4,1,NULL,NULL,NULL,NULL),(5,'2019-03-24 20:27:29','created',628.00,NULL,'groundShipping',5,5,5,1,NULL,NULL,NULL,NULL),(6,'2019-03-27 21:03:16','created',2945.00,NULL,'groundShipping',6,6,6,9,NULL,NULL,NULL,NULL),(7,'2019-04-13 00:39:08','created',628.00,NULL,'groundShipping',7,7,7,9,NULL,NULL,NULL,NULL),(8,'2019-04-15 19:08:07','created',628.00,NULL,'groundShipping',8,8,8,9,NULL,NULL,NULL,NULL),(9,'2019-04-15 19:08:08','created',628.00,NULL,'groundShipping',9,9,9,9,NULL,NULL,NULL,NULL),(10,'2019-04-15 19:08:11','created',628.00,NULL,'groundShipping',10,10,10,9,NULL,NULL,NULL,NULL),(11,'2019-04-23 01:44:03','created',942.00,NULL,'groundShipping',11,11,11,1,NULL,NULL,NULL,NULL),(12,'2019-05-05 01:24:49','created',318.00,NULL,'groundShipping',12,12,12,8,NULL,NULL,NULL,NULL),(13,'2019-05-05 01:29:57','created',159.00,NULL,'groundShipping',13,13,13,8,NULL,NULL,NULL,NULL),(14,'2019-05-05 01:54:12','created',795.00,NULL,'groundShipping',14,14,14,1,NULL,NULL,NULL,NULL),(15,'2019-05-05 01:54:14','created',159.00,NULL,'groundShipping',15,15,15,8,NULL,NULL,NULL,NULL),(16,'2019-05-05 14:21:17','created',1272.00,NULL,'premiumShipping',16,16,16,12,NULL,NULL,NULL,NULL),(17,'2019-05-06 22:38:41','created',159.00,NULL,'groundShipping',17,17,17,13,NULL,NULL,NULL,NULL),(18,'2019-05-08 16:58:24','created',159.00,NULL,'groundShipping',18,18,18,13,NULL,NULL,NULL,NULL),(19,'2019-05-08 16:59:53','created',954.00,NULL,'groundShipping',19,19,19,12,NULL,NULL,NULL,NULL),(20,'2019-05-09 13:24:10','created',1246.00,NULL,'',20,20,20,12,NULL,NULL,NULL,NULL),(21,'2019-05-11 23:41:32','created',6589.00,NULL,'',21,21,21,13,0.00,199.00,0.00,6589.00),(22,'2019-05-12 18:58:46','created',942.00,NULL,'',22,22,22,13,0.00,30.00,140.00,1082.00),(23,'2019-05-12 19:40:58','created',956.00,NULL,'',23,23,23,9,0.00,28.00,0.00,956.00),(24,'2019-05-19 23:04:32','created',5646.00,NULL,'',24,24,24,13,1186.00,170.00,0.00,5346.00),(25,'2019-05-19 23:24:00','created',4588.00,NULL,'',25,25,25,13,0.00,110.00,164.00,4588.00),(26,'2019-05-20 19:47:53','created',3168.00,NULL,'',26,26,26,12,0.00,90.00,0.00,3168.00),(27,'2019-05-20 21:29:28','created',2680.00,NULL,'',27,27,27,13,0.00,50.00,0.00,2680.00),(28,'2019-05-21 22:27:56','created',776.00,NULL,'',28,28,28,13,163.00,10.00,68.00,707.00),(29,'2019-05-21 22:30:11','created',3408.00,NULL,'',29,29,29,19,716.00,90.00,0.00,4124.00),(30,'2019-05-29 19:24:10','created',1118.00,NULL,'',30,30,30,12,0.00,15.00,0.00,1118.00),(31,'2019-05-30 07:17:46','created',788.00,NULL,'',31,31,31,12,0.00,15.00,0.00,788.00),(32,'2019-05-30 07:42:46','created',2107.00,NULL,'',32,32,32,12,0.00,43.00,137.00,2107.00),(33,'2019-05-30 07:47:14','created',1830.00,NULL,'',33,33,33,12,0.00,45.00,0.00,1830.00),(34,'2019-06-03 10:06:33','created',481.00,NULL,'',34,34,34,21,101.00,5.00,81.00,363.00),(35,'2019-06-03 10:16:59','created',481.00,NULL,'',35,35,35,12,101.00,5.00,0.00,582.00),(36,'2019-06-08 18:52:34','created',215.00,NULL,'',36,36,36,1,0.00,5.00,56.00,215.00),(37,'2019-06-08 18:55:47','created',1346.00,NULL,'',37,37,37,13,283.00,30.00,0.00,1629.00),(38,'2019-06-08 20:28:45','created',486.00,NULL,'',38,38,38,12,102.00,5.00,0.00,588.00),(39,'2019-06-08 20:40:51','created',1078.00,NULL,'',39,39,39,12,226.00,15.00,0.00,1304.00),(40,'2019-06-08 21:11:45','created',778.00,NULL,'',40,40,40,22,0.00,15.00,0.00,778.00),(41,'2019-06-11 10:57:38','created',538.00,NULL,'',41,41,41,23,113.00,5.00,0.00,651.00),(42,'2020-05-06 07:25:49','created',636.00,NULL,'',42,42,42,32,0.00,20.00,0.00,636.00),(43,'2021-06-29 10:53:41','created',240.00,NULL,'',43,43,43,50,33.00,5.00,81.00,273.00),(44,'2021-09-08 05:34:48','created',1046.00,NULL,'',44,44,44,61,0.00,30.00,92.00,1046.00),(45,'2021-10-22 21:13:02','created',396.00,NULL,'',45,45,45,17,69.00,10.00,68.00,465.00),(46,'2021-10-22 21:13:04','created',396.00,NULL,'',46,46,46,17,69.00,10.00,68.00,465.00),(47,'2021-11-03 14:47:36','created',3492.00,NULL,'',47,47,47,66,0.00,115.00,0.00,3492.00),(48,'2021-11-03 14:47:46','created',3492.00,NULL,'',48,48,48,66,0.00,115.00,0.00,3492.00);
/*!40000 ALTER TABLE `user_order` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-11 22:53:20
