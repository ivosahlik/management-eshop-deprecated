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
-- Table structure for table `product_to_cart_item`
--

DROP TABLE IF EXISTS `product_to_cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_to_cart_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cart_item_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgkjerh5w1ms4ejv7dbj2trwy7` (`cart_item_id`),
  KEY `FK3kinowrsbkpl2f0of8a024vm2` (`product_id`),
  CONSTRAINT `FK3kinowrsbkpl2f0of8a024vm2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKgkjerh5w1ms4ejv7dbj2trwy7` FOREIGN KEY (`cart_item_id`) REFERENCES `cart_item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_to_cart_item`
--

LOCK TABLES `product_to_cart_item` WRITE;
/*!40000 ALTER TABLE `product_to_cart_item` DISABLE KEYS */;
INSERT INTO `product_to_cart_item` VALUES (36,36,14),(48,48,67),(49,49,13),(51,51,15),(59,59,17),(60,60,69),(61,61,84),(62,62,84),(63,63,69),(64,64,72),(65,65,73),(66,66,17),(74,74,13),(75,75,62),(89,89,70),(91,91,71),(92,92,14),(100,100,14),(101,101,23),(102,102,14),(103,103,15),(104,104,65),(105,105,65),(106,106,23),(107,107,23),(108,108,23),(109,109,28),(110,110,24),(111,111,14),(112,112,28);
/*!40000 ALTER TABLE `product_to_cart_item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-11 22:52:47
