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
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `app` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `background_color` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `color` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `font_size` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `font_weight` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `show_alert` bit(1) NOT NULL,
  `text_message` text COLLATE utf8mb4_bin,
  `title` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,_binary '','membrania','#ff3333','black','18','bold',_binary '\0','test','test'),(2,_binary '','dcsolutions','#ff3333','black','18','bold',_binary '\0','test 2','test 2'),(3,_binary '','dcsolutions','white','black','25','normal',_binary '\0','Vážení klienti, dne 19. 12. 2019, v čase od 00.00 do 06.00 hod. proběhne plánovaná odstávka systému DC Solutions. V tento čas nebude možné vykonávat žádné akce. Děkujeme za pochopení.','Odstávka - šablona'),(4,_binary '',NULL,'#ff3333','black','18','bold',_binary '\0','Dear clients, on November 19, 2019, the planned shutdown of the Membrania, DcSolutions system will take place from 00.00 to 06.00. You will not be able to perform any actions at this time. Thank you for your understanding.','Outage - template'),(5,_binary '','dcsolutions,membrania','#0099ff','white','13','normal',_binary '','Hydranautics membranes // Dear Customers, if you need advice on choosing a suitable membranes, product comparison or membrane design, please feel free to contact us via e-mail sales@dcsolutions.cz.','Technical support');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-11 22:52:49
