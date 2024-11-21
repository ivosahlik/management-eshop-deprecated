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
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `countries` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `code` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `country` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=199 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES (1,_binary '','1','Afghanistan'),(2,_binary '','2','Albania'),(3,_binary '','3','Algeria'),(4,_binary '','4','Andorra'),(5,_binary '','5','Angola'),(6,_binary '','6','Antigua and Barbuda'),(7,_binary '','7','Argentina'),(8,_binary '','8','Armenia'),(9,_binary '','9','Australia'),(10,_binary '','10','Austria'),(11,_binary '','11','Azerbaijan'),(12,_binary '','12','The Bahamas'),(13,_binary '','13','Bahrain'),(14,_binary '','14','Bangladesh'),(15,_binary '','15','Barbados'),(16,_binary '','16','Belarus'),(17,_binary '','17','Belgium'),(18,_binary '','18','Belize'),(19,_binary '','19','Benin'),(20,_binary '','20','Bhutan'),(21,_binary '','21','Bolivia'),(22,_binary '','22','Bosnia and Herzegovina'),(23,_binary '','23','Botswana'),(24,_binary '','24','Brazil'),(25,_binary '','25','Brunei'),(26,_binary '','26','Bulgaria'),(27,_binary '','27','Burkina Faso'),(28,_binary '','28','Burundi'),(30,_binary '','30','Cabo Verde'),(31,_binary '','31','Cambodia'),(32,_binary '','32','Cameroon'),(33,_binary '','33','Canada'),(34,_binary '','34','Central African Republic'),(35,_binary '','35','Chad'),(36,_binary '','36','Chile'),(37,_binary '','37','China'),(38,_binary '','38','Colombia'),(39,_binary '','39','Comoros'),(40,_binary '','40','Congo, Democratic Republic of the'),(41,_binary '','41','Congo, Republic of the'),(42,_binary '','42','Costa Rica'),(43,_binary '','43','Côte d’Ivoire'),(44,_binary '','44','Croatia'),(45,_binary '','45','Cuba'),(46,_binary '','46','Cyprus'),(47,_binary '','47','Czech Republic'),(48,_binary '','48','Denmark'),(49,_binary '','49','Djibouti'),(50,_binary '','50','Dominica'),(51,_binary '','51','Dominican Republic'),(52,_binary '','52','East Timor (Timor-Leste)'),(53,_binary '','53','Ecuador'),(54,_binary '','54','Egypt'),(55,_binary '','55','El Salvador'),(56,_binary '','56','Equatorial Guinea'),(57,_binary '','57','Eritrea'),(58,_binary '','58','Estonia'),(59,_binary '','59','Eswatini'),(60,_binary '','60','Ethiopia'),(61,_binary '','61','Fiji'),(62,_binary '','62','Finland'),(63,_binary '','63','France'),(64,_binary '','64','Gabon'),(65,_binary '','65','The Gambia'),(66,_binary '','66','Georgia'),(67,_binary '','67','Germany'),(68,_binary '','68','Ghana'),(69,_binary '','69','Greece'),(70,_binary '','70','Grenada'),(71,_binary '','71','Guatemala'),(72,_binary '','72','Guinea'),(73,_binary '','73','Guinea-Bissau'),(74,_binary '','74','Guyana'),(75,_binary '','75','Haiti'),(76,_binary '','76','Honduras'),(77,_binary '','77','Hungary'),(78,_binary '','78','Iceland'),(79,_binary '','79','India'),(80,_binary '','80','Indonesia'),(81,_binary '','81','Iran'),(82,_binary '','82','Iraq'),(83,_binary '','83','Ireland'),(84,_binary '','84','Israel'),(85,_binary '','85','Italy'),(86,_binary '','86','Jamaica'),(87,_binary '','87','Japan'),(88,_binary '','88','Jordan'),(89,_binary '','89','Kazakhstan'),(90,_binary '','90','Kenya'),(91,_binary '','91','Kiribati'),(92,_binary '','92','Korea, North'),(93,_binary '','93','Korea, South'),(94,_binary '','94','Kosovo'),(95,_binary '','95','Kuwait'),(96,_binary '','96','Kyrgyzstan'),(97,_binary '','97','Laos'),(98,_binary '','98','Latvia'),(99,_binary '','99','Lebanon'),(100,_binary '','100','Lesotho'),(101,_binary '','101','Liberia'),(102,_binary '','102','Libya'),(103,_binary '','103','Liechtenstein'),(104,_binary '','104','Lithuania'),(105,_binary '','105','Luxembourg'),(106,_binary '','106','Madagascar'),(107,_binary '','107','Malawi'),(108,_binary '','108','Malaysia'),(109,_binary '','109','Maldives'),(110,_binary '','110','Mali'),(111,_binary '','111','Malta'),(112,_binary '','112','Marshall Islands'),(113,_binary '','113','Mauritania'),(114,_binary '','114','Mauritius'),(115,_binary '','115','Mexico'),(116,_binary '','116','Micronesia, Federated States of'),(117,_binary '','117','Moldova'),(118,_binary '','118','Monaco'),(119,_binary '','119','Mongolia'),(120,_binary '','120','Montenegro'),(121,_binary '','121','Morocco'),(122,_binary '','122','Mozambique'),(123,_binary '','123','Myanmar (Burma)'),(124,_binary '','124','Namibia'),(125,_binary '','125','Nauru'),(126,_binary '','126','Nepal'),(127,_binary '','127','Netherlands'),(128,_binary '','128','New Zealand'),(129,_binary '','129','Nicaragua'),(130,_binary '','130','Niger'),(131,_binary '','131','Nigeria'),(132,_binary '','132','North Macedonia'),(133,_binary '','133','Norway'),(134,_binary '','134','Oman'),(135,_binary '','135','Pakistan'),(136,_binary '','136','Palau'),(137,_binary '','137','Panama'),(138,_binary '','138','Papua New Guinea'),(139,_binary '','139','Paraguay'),(140,_binary '','140','Peru'),(141,_binary '','141','Philippines'),(142,_binary '','142','Poland'),(143,_binary '','143','Portugal'),(145,_binary '','145','Qatar'),(146,_binary '','146','Romania'),(147,_binary '','147','Russia'),(148,_binary '','148','Rwanda'),(149,_binary '','149','Saint Kitts and Nevis'),(150,_binary '','150','Saint Lucia'),(151,_binary '','151','Saint Vincent and the Grenadines'),(152,_binary '','152','Samoa'),(153,_binary '','153','San Marino'),(154,_binary '','154','Sao Tome and Principe'),(155,_binary '','155','Saudi Arabia'),(156,_binary '','156','Senegal'),(157,_binary '','157','Serbia'),(158,_binary '','158','Seychelles'),(159,_binary '','159','Sierra Leone'),(160,_binary '','160','Singapore'),(161,_binary '','161','Slovakia'),(162,_binary '','162','Slovenia'),(163,_binary '','163','Solomon Islands'),(164,_binary '','164','Somalia'),(165,_binary '','165','South Africa'),(166,_binary '','166','Spain'),(167,_binary '','167','Sri Lanka'),(168,_binary '','168','Sudan'),(169,_binary '','169','Sudan, South'),(170,_binary '','170','Suriname'),(171,_binary '','171','Sweden'),(172,_binary '','172','Switzerland'),(173,_binary '','173','Syria'),(174,_binary '','174','Taiwan'),(175,_binary '','175','Tajikistan'),(176,_binary '','176','Tanzania'),(177,_binary '','177','Thailand'),(178,_binary '','178','Togo'),(179,_binary '','179','Tonga'),(180,_binary '','180','Trinidad and Tobago'),(181,_binary '','181','Tunisia'),(182,_binary '','182','Turkey'),(183,_binary '','183','Turkmenistan'),(184,_binary '','184','Tuvalu'),(185,_binary '','185','Uganda'),(186,_binary '','186','Ukraine'),(187,_binary '','187','United Arab Emirates'),(188,_binary '','188','United Kingdom'),(189,_binary '','189','United States'),(190,_binary '','190','Uruguay'),(191,_binary '','191','Uzbekistan'),(192,_binary '','192','Vanuatu'),(193,_binary '','193','Vatican City'),(194,_binary '','194','Venezuela'),(195,_binary '','195','Vietnam'),(196,_binary '','196','Yemen'),(197,_binary '','197','Zambia'),(198,_binary '','198','Zimbabwe');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-11 22:53:27
