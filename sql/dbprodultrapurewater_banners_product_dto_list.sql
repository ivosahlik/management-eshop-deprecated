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
-- Table structure for table `banners_product_dto_list`
--

DROP TABLE IF EXISTS `banners_product_dto_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banners_product_dto_list` (
  `banners_id` int(11) NOT NULL,
  `product_dto_list_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_7uw1ai6bchpd6yrj793qk69in` (`product_dto_list_id`),
  KEY `FKp6gwdm3c0u2ntr9u61w96j2jb` (`banners_id`),
  CONSTRAINT `FK2rssnu9ssubysc6cc5393ruw7` FOREIGN KEY (`product_dto_list_id`) REFERENCES `product_dto` (`id`),
  CONSTRAINT `FKp6gwdm3c0u2ntr9u61w96j2jb` FOREIGN KEY (`banners_id`) REFERENCES `banners` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banners_product_dto_list`
--

LOCK TABLES `banners_product_dto_list` WRITE;
/*!40000 ALTER TABLE `banners_product_dto_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `banners_product_dto_list` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-11 22:53:33
