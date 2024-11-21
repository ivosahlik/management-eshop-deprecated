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
-- Table structure for table `tariff_zone`
--

DROP TABLE IF EXISTS `tariff_zone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tariff_zone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `country` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `zone` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tariff_zone`
--

LOCK TABLES `tariff_zone` WRITE;
/*!40000 ALTER TABLE `tariff_zone` DISABLE KEYS */;
INSERT INTO `tariff_zone` VALUES (2,_binary '','Belgium',2),(3,_binary '','Bulgaria',3),(4,_binary '','Croatia',3),(5,_binary '','Denmark',1),(7,_binary '','Estonia',3),(8,_binary '','Finland',3),(9,_binary '','France',2),(10,_binary '','Ireland',2),(11,_binary '','Italy',2),(12,_binary '','Liechtenstein',4),(13,_binary '','Lithuania',3),(14,_binary '','Latvia',3),(15,_binary '','Luxembourg',2),(16,_binary '','Hungary',1),(17,_binary '','Vatican City',2),(18,_binary '','Monaco',2),(19,_binary '','Germany',1),(20,_binary '','Netherlands',2),(21,_binary '','Norway',4),(22,_binary '','Poland',1),(23,_binary '','Portugal',3),(24,_binary '','Austria',1),(25,_binary '','Romania',3),(26,_binary '','Greece',3),(27,_binary '','Slovakia',1),(28,_binary '','Slovenia',3),(29,_binary '','Spain',3),(30,_binary '','Sweden',3),(31,_binary '','Switzerland',4),(32,_binary '','United Kingdom',2),(33,_binary '','Czech Republic',1);
/*!40000 ALTER TABLE `tariff_zone` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-11 22:53:40
