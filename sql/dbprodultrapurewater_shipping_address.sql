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
-- Table structure for table `shipping_address`
--

DROP TABLE IF EXISTS `shipping_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipping_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `shipping_address_city` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `shipping_address_country` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `shipping_address_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `shipping_address_state` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `shipping_address_street1` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `shipping_address_street2` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `shipping_address_zipcode` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `shipping_address_vat` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `shipping_address_phone` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKatbgaqk1hhhhkyyuebylpeh7q` (`order_id`),
  CONSTRAINT `FKatbgaqk1hhhhkyyuebylpeh7q` FOREIGN KEY (`order_id`) REFERENCES `user_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping_address`
--

LOCK TABLES `shipping_address` WRITE;
/*!40000 ALTER TABLE `shipping_address` DISABLE KEYS */;
INSERT INTO `shipping_address` VALUES (1,'aaaaa',NULL,'dfdsfdf','CZ','fsdfs','sdfdsf','12322',1,NULL,NULL),(2,'Praha',NULL,'Ivo Vošahlíkxxycyxcyxcy','A','Renoirova 623, Hlubočepydsfdsfdsfsd','','15200',2,NULL,NULL),(3,'Praha',NULL,'Ivo Vošahlíkxxycyxcyxcy','A','Renoirova 623, Hlubočepydsfdsfdsfsd','','15200',3,NULL,NULL),(4,'Praha',NULL,'2ěščřžýáíé aknjfiosjf','A','Renoirova 623, Hlubočepydsfdsfdsfsd','','15200',4,NULL,NULL),(5,'Praha',NULL,'Ivo Vošahlíkxxycyxcyxcy','A','Renoirova 623, Hlubočepydsfdsfdsfsd','','15200',5,NULL,NULL),(6,'Praha',NULL,'DC Solutions s.r.o.','CZ','Voskovcova 1075/51','','15200',6,'05263247','7777778989'),(7,'Praha',NULL,'DC Solutions s.r.o.','CZ','Voskovcova 1075/51','','15200',7,'05263247','+420777070815'),(8,'Praha',NULL,'DC Solutions s.r.o.','CZ','Voskovcova 1075/51','','15200',8,'05263247','+420777070815'),(9,'Praha',NULL,'DC Solutions s.r.o.','CZ','Voskovcova 1075/51','','15200',9,'05263247','+420777070815'),(10,'Praha',NULL,'DC Solutions s.r.o.','CZ','Voskovcova 1075/51','','15200',10,'05263247','+420777070815'),(11,'Praha',NULL,'TEST199','DE','Renoirova 623, Hlubočepy',NULL,'15200',11,'AAVVVV123','+420 731632054'),(12,'Praha',NULL,'TEST1 Ivo Vošahlík','Czech Republic','Renoirova 623, Hlubočepy',NULL,'15200',12,'AAVVVV123','+420732427710'),(13,'Praha',NULL,'TEST1 Ivo Vošahlík','Czech Republic','Renoirova 623, Hlubočepy',NULL,'15200',13,'AAVVVV123','+420732427710'),(14,'Praha',NULL,'TEST199','Czech Republic','Renoirova 623, Hlubočepy',NULL,'15200',14,'AAVVVV123','+420 731632054'),(15,'Praha',NULL,'TEST1 Ivo Vošahlík','Czech Republic','Renoirova 623, Hlubočepy',NULL,'15200',15,'AAVVVV123','+420732427710'),(16,'OSLO',NULL,'Membrania s.ro.','Norway','Trondheimsveien 64',NULL,'0565',16,'54432399','+420 777044432'),(17,'Praha',NULL,'TEST1 Ivo Vošahlík','Sweden','Renoirova 623, Hlubočepy',NULL,'15200',17,'AAVVVV123','+420732427710'),(18,'Praha',NULL,'TEST1 Ivo Vošahlík','Chile','Renoirova 623, Hlubočepy',NULL,'15200',18,'AAVVVV123','+420732427710'),(19,'OSLO',NULL,'Membrania s.ro.','Niger','Trondheimsveien 64',NULL,'0565',19,'54432399','+420 777044432'),(20,'OSLO',NULL,'Membrania s.ro.','Norway','Trondheimsveien 64',NULL,'0565',20,'54432399','+420 777044432'),(21,'Praha',NULL,'TEST1 Ivo Vošahlík','Czech Republic','Renoirova 623, Hlubočepy',NULL,'15200',21,'AAVVVV123','+420732427710'),(22,'Praha',NULL,'TEST1 Ivo Vošahlík','Norway','Renoirova 623, Hlubočepy',NULL,'15200',22,'AAVVVV123','+420732427710'),(23,'Praha',NULL,'DC Solutions s.r.o.','Czech Republic','Voskovcova 1075/51',NULL,'15200',23,'05263247','+420777070815'),(24,'Praha',NULL,'TEST1 Ivo Vošahlík','Czech Republic','Renoirova 623, Hlubočepy',NULL,'15200',24,'dfgh','731632054'),(25,'Praha',NULL,'TEST1 Ivo Vošahlík','Norway','Renoirova 623, Hlubočepy',NULL,'15200',25,'AAVVVV123','+420732427710'),(26,'OSLO',NULL,'Membrania s.ro.','Norway','Trondheimsveien 64',NULL,'0565',26,'54432399','+420 777044432'),(27,'Praha',NULL,'TEST1 Ivo Vošahlík','Norway','Renoirova 623, Hlubočepy',NULL,'15200',27,'AAVVVV123','+420732427710'),(28,'Praha',NULL,'TEST1 Ivo Vošahlík','Austria','Renoirova 623, Hlubočepy',NULL,'15200',28,'','731632054'),(29,'eadaed',NULL,'testmembrania','Belgium','adasd',NULL,'33333',29,'','+420777070707'),(30,'OSLO',NULL,'Membrania s.ro.','Norway','Trondheimsveien 64',NULL,'0565',30,'54432399','+420 777044432'),(31,'OSLO',NULL,'Membrania s.ro.','Norway','Trondheimsveien 64',NULL,'0565',31,'54432399','+420 777044432'),(32,'OSLO',NULL,'Membrania s.ro.','Norway','Trondheimsveien 64',NULL,'0565',32,'54432399','+420 777044432'),(33,'OSLO',NULL,'Membrania s.ro.','Norway','Trondheimsveien 64',NULL,'0565',33,'54432399','+420 777044432'),(34,'Stockholm',NULL,'Sven Svensson','Sweden','Kungsbron 2',NULL,'111 22',34,'','6502530000'),(35,'OSLO',NULL,'Membrania s.ro.','Norway','Trondheimsveien 64',NULL,'0565',35,'','+420 777044432'),(36,'Praha',NULL,'Ivo Vošahlíkxxycyxcyxcy','Austria','Renoirova 623, Hlubočepydsfdsfdsfsd',NULL,'15200',36,'sdfdfs','+420732427710'),(37,'fdsfdsfdsf',NULL,'fsdfdsfdsf','Belgium','sdfdsfdsfdsf',NULL,'15200',37,'','+420732427710'),(38,'OSLO',NULL,'Membrania s.ro.','New Zealand','Trondheimsveien 64',NULL,'0565',38,'','+420 777044432'),(39,'OSLO',NULL,'Membrania s.ro.','Austria','Trondheimsveien 64',NULL,'0565',39,'2234324234','+420 777044432'),(40,'asasa',NULL,'sales','Austria','asasad',NULL,'3443',40,'3232323','43434355'),(41,'Warszawa',NULL,'pl','Poland','Warsaw Financial Center',NULL,'00-113',41,'','6502530000'),(42,'Nicosia',NULL,'SMART MIST PROJECTS LTD','Cyprus','Kyriakou Kolokassi 11A',NULL,'1040',42,'CY10322123X','+35796743635'),(43,'Pipera (Voluntari)',NULL,'Khaled Zantout ','Romania','GSP ,97 Pipera-Tunari',NULL,'077190',43,'','0733080298'),(44,'Courcelles',NULL,'eytelia','Belgium','rue de Liège n°2',NULL,'6180',44,'be0456542178','+32488477444'),(45,'Praha',NULL,'Marketa s.r.o.','Czech Republic,Norway','Miloše Havla 1245/5',NULL,'15200',45,'CZ0563482','+773623943'),(46,'Praha',NULL,'Marketa s.r.o.','Czech Republic','Miloše Havla 1245/5',NULL,'15200',46,'CZ0563482','+773623943'),(47,'Milan',NULL,'IDROPAN','Italy','Via Valassina 19',NULL,'20159',47,'IT09529190150','+3902666800267'),(48,'Milan',NULL,'IDROPAN','Italy','Via Valassina 19',NULL,'20159',48,'IT09529190150','+3902666800267');
/*!40000 ALTER TABLE `shipping_address` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-11 22:53:38
