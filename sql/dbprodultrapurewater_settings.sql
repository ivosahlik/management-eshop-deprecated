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
-- Table structure for table `settings`
--

DROP TABLE IF EXISTS `settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `settings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `created` tinyblob,
  `html` text COLLATE utf8mb4_bin,
  `title` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `updated` tinyblob,
  `user_created` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `user_updated` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settings`
--

LOCK TABLES `settings` WRITE;
/*!40000 ALTER TABLE `settings` DISABLE KEYS */;
INSERT INTO `settings` VALUES (3,_binary '',_binary '¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\ã%6.=@x','<ul class=\"my-tiles\">\r\n			<li>\r\n				<div class=\"my-tile\">\r\n					<a class=\"my-title-tile\" href=\"/adminportal/product/productList\">\r\n						<img src=\"images/news.svg\" />\r\n						<span class=\"my-title-tile\">Product list</span>\r\n					</a>\r\n				</div>\r\n			</li>\r\n			<li>\r\n				<div class=\"my-tile\">\r\n					<a class=\"my-title-tile\" href=\"/adminportal/product/addProduct\">\r\n						<img src=\"images/writer.svg\" />\r\n						<span class=\"my-title-tile\">Add product</span>\r\n					</a>\r\n				</div>\r\n			</li>\r\n		</ul>\r\n\r\n		<ul class=\"my-tiles\">\r\n			<li>\r\n				<div class=\"my-tile\">\r\n					<a class=\"my-title-tile\" href=\"/adminportal/category/categoryList\">\r\n						<img src=\"images/news.svg\" />\r\n						<span class=\"my-title-tile\">Category list</span>\r\n					</a>\r\n				</div>\r\n			</li>\r\n			<li>\r\n				<div class=\"my-tile\">\r\n					<a class=\"my-title-tile\" href=\"/adminportal/category/addCategory\">\r\n						<img src=\"images/writer.svg\" />\r\n						<span class=\"my-title-tile\">Add Category</span>\r\n					</a>\r\n				</div>\r\n			</li>\r\n		</ul>\r\n\r\n		<ul class=\"my-tiles\">\r\n			<li>\r\n				<div class=\"my-tile\">\r\n					<a class=\"my-title-tile\" href=\"/adminportal/subCategory/subCategoryList\">\r\n						<img src=\"images/news.svg\" />\r\n						<span class=\"my-title-tile\">subCategory list</span>\r\n					</a>\r\n				</div>\r\n			</li>\r\n			<li>\r\n				<div class=\"my-tile\">\r\n					<a class=\"my-title-tile\" href=\"/adminportal/subCategory/addSubCategory\">\r\n						<img src=\"images/writer.svg\" />\r\n						<span class=\"my-title-tile\">Add subCategory</span>\r\n					</a>\r\n				</div>\r\n			</li>\r\n\r\n                        <li>\r\n				<div class=\"my-tile\">\r\n					<a class=\"my-title-tile\" href=\"/adminportal/settings\">\r\n						<img src=\"images/writer.svg\" />\r\n						<span class=\"my-title-tile\">Settings</span>\r\n					</a>\r\n				</div>\r\n			</li>\r\n\r\n                        <li>\r\n				<div class=\"my-tile\">\r\n					<a class=\"my-title-tile\" href=\"/adminportal/settings/message\">\r\n						<img src=\"images/writer.svg\" />\r\n						<span class=\"my-title-tile\">Alert Messages</span>\r\n					</a>\r\n				</div>\r\n			</li>\r\n			<li>\r\n				<div class=\"my-tile\">\r\n					<a class=\"my-title-tile\" href=\"/adminportal/settings/footer\">\r\n						<img src=\"images/writer.svg\" />\r\n						<span class=\"my-title-tile\">Footer&&Copyright</span>\r\n					</a>\r\n				</div>\r\n			</li>\r\n		</ul>\r\n	','tiles',_binary '¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\ä\ã`\0x','adminIvoAdamTestMembrania','adminIvoAdamTestMembrania');
/*!40000 ALTER TABLE `settings` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-11 22:53:31
