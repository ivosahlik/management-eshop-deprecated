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
-- Table structure for table `user_shipping`
--

DROP TABLE IF EXISTS `user_shipping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_shipping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_shipping_city` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `user_shipping_country` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `user_shipping_default` bit(1) NOT NULL,
  `user_shipping_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `user_shipping_state` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `user_shipping_street1` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `user_shipping_street2` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `user_shipping_zipcode` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `user_shipping_phone` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `user_shipping_vat` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9hidca5hndj9y0b5jb0xtpn9u` (`user_id`),
  CONSTRAINT `FK9hidca5hndj9y0b5jb0xtpn9u` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_shipping`
--

LOCK TABLES `user_shipping` WRITE;
/*!40000 ALTER TABLE `user_shipping` DISABLE KEYS */;
INSERT INTO `user_shipping` VALUES (2,'Praha',NULL,_binary '\0','Ivo Vošahlíkxxycyxcyxcy','Austria','Renoirova 623, Hlubočepydsfdsfdsfsd',NULL,'15200',1,'+420732427710','sdfdfs'),(3,'Praha',NULL,_binary '\0','ADAM COMPANY s.r.o.','A','Renoirova 623, Hlubočepy',NULL,'15200',1,'732427710','ABCD1234'),(4,'Praha',NULL,_binary '\0','DC Solutions s.r.o.','CZ','Voskovcova 1075/51',NULL,'15200',9,'+420777070815','CZ05263247'),(5,'Praha',NULL,_binary '\0','TEST1 Ivo Vošahlíkdsadsadasdasd','A','Renoirova 623, Hlubočepy',NULL,'15200',1,'731632054','TEST11AAVVVV12311111'),(6,'Praha',NULL,_binary '\0','DC Solutions s.r.o.','Czech Republic','Voskovcova 1075/51',NULL,'15200',9,'+420777070815','05263247'),(7,'Praha',NULL,_binary '\0','TEST199','Czech Republic','Renoirova 623, Hlubočepy',NULL,'15200',1,'+420 731632054','AAVVVV123'),(8,'Praha',NULL,_binary '\0','TEST1 Ivo Vošahlík','Czech Republic','Renoirova 623, Hlubočepy',NULL,'15200',8,'+420732427710','AAVVVV123'),(9,'OSLO',NULL,_binary '','Membrania s.ro.','Norway','Trondheimsveien 64',NULL,'0565',12,'+420 777044432',''),(12,'Praha',NULL,_binary '\0','TEST1 Ivo Vošahlík','Norway','Renoirova 623, Hlubočepy',NULL,'15200',13,'+420732427710','AAVVVV123'),(13,'Praha',NULL,_binary '','Marketa s.r.o.','Czech Republic','Miloše Havla 1245/5',NULL,'15200',17,'+773623943','CZ0563482'),(14,'eadaed',NULL,_binary '','testmembrania','Belgium','adasd',NULL,'33333',19,'+420777070707',''),(15,'MIKLAVZ NA DR. POLJU',NULL,_binary '','HIDROTEHNICNI BIRO, IZTOK BRATOS S.P.','Slovenia','UL. CEBELARJA MOCNIKA 3',NULL,'2204',35,'+38641653890',''),(16,'MIKLAVZ NA DR. POLJU',NULL,_binary '','HIDROTEHNICNI BIRO, IZTOK BRATOS S.P.','Slovenia','UL. CEBELARJA MOCNIKA 3',NULL,'2204',35,'+38641653890','');
/*!40000 ALTER TABLE `user_shipping` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-11 22:53:35
