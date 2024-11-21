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
-- Table structure for table `custom_settings`
--

DROP TABLE IF EXISTS `custom_settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `custom_settings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) NOT NULL,
  `app` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `copyrigt_html_fragment` text COLLATE utf8mb4_bin,
  `created` tinyblob,
  `footer_html_fragment` text COLLATE utf8mb4_bin,
  `html` text COLLATE utf8mb4_bin,
  `title` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `updated` tinyblob,
  `user_created` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `user_updated` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `custom_settings`
--

LOCK TABLES `custom_settings` WRITE;
/*!40000 ALTER TABLE `custom_settings` DISABLE KEYS */;
INSERT INTO `custom_settings` VALUES (1,_binary '','dcsolutions','<div class=\"copyright\">\r\n			<div class=\"container\">\r\n				<div class=\"row\">\r\n					<div class=\"col\">\r\n\r\n						<div class=\"copyright_container d-flex flex-sm-row flex-column align-items-center justify-content-start\">\r\n							<div class=\"copyright_content\">\r\n								Ing. Ivo Vo≈°ahl√≠k (C) 2018 - 2021. V≈°echna pr√°va vyhrazena.\r\n							</div>\r\n						</div>\r\n					</div>\r\n				</div>\r\n			</div>\r\n		</div>',_binary '¨\Ì\0sr\0\rjava.time.Serï]Ñ∫\"H≤\0\0xpw\0\0\Â2,:Äx','<!-- Footer -->\r\n		<footer class=\"footer\">\r\n			<div class=\"container\">\r\n				<div class=\"row\">\r\n\r\n					<div class=\"col-lg-4\">\r\n						<div class=\"footer_column\">\r\n							<div class=\"footer_column footer_title\">DC Solutions s.r.o.</div>\r\n							<ul class=\"footer_list\">\r\n								<li>Voskovcova 1075/51</li>\r\n							</ul>\r\n							<ul class=\"footer_list\">\r\n								<li>152 00 PRAHA 5</li>\r\n							</ul>\r\n							<ul class=\"footer_list\">\r\n								<li>CZECH REPUBLIC</li>\r\n							</ul>\r\n						</div>\r\n					</div>\r\n\r\n					<div class=\"col-lg-2 offset-lg-2\">\r\n						<div class=\"footer_column\">\r\n							<div class=\"footer_title\">Find it Fast</div>\r\n							<ul class=\"footer_list\">\r\n								<li>+420 777 07 08 15</li>\r\n							</ul>\r\n							<ul class=\"footer_list\">\r\n								<li><a href=\"mailto: info@dcsolutions.cz\">info@dcsolutions.cz</a></li>\r\n							</ul>\r\n							<ul class=\"footer_list\">\r\n								<li>\r\n									<a href=\"/\">www.dcsolutions.cz</a>\r\n								</li>\r\n							</ul>\r\n						</div>\r\n					</div>\r\n\r\n					<div class=\"col-lg-2\">\r\n						<div class=\"footer_column\">\r\n							<ul class=\"footer_list footer_list_2\">\r\n								<li><a href=\"/myProfile\">My account</a></li>\r\n								<li><a href=\"/myProfile?order=true\">My orders</a></li>\r\n								<li><a href=\"/getAQoute\">My quotes</a></li>\r\n							</ul>\r\n						</div>\r\n					</div>\r\n\r\n					<div class=\"col-lg-2\">\r\n						<div class=\"footer_column\">\r\n							<div class=\"footer_title\">Customer Care</div>\r\n							<ul class=\"footer_list\">\r\n								<li><a href=\"/termsAndConditions\">Terms and conditions</a></li>\r\n								<li><a href=\"/gdpr\">GDPR</a></li>\r\n								<li><a href=\"/aboutUs\">About us</a></li>\r\n							</ul>\r\n						</div>\r\n					</div>\r\n\r\n				</div>\r\n\r\n				<div class=\"row\" style=\"margin-top: 60px;\">\r\n\r\n					<div class=\"col-lg-3 footer_col\">\r\n						<div class=\"footer_column footer_contact\">\r\n							<div class=\"logo\">\r\n								<a href=\"/\">\r\n									<img src=\"/images/dcsolutionslogo.png\" style=\"height: 50px;\" alt=\"\"/>\r\n								</a>\r\n							</div>\r\n						</div>\r\n					</div>\r\n\r\n					<div class=\"col-lg-3 footer_col\">\r\n						<div class=\"footer_column footer_contact\">\r\n							<div class=\"logo\">\r\n								<a href=\"/\">\r\n									<img src=\"/images/logohydro.png\" style=\"height: 25px;\" alt=\"\"/>\r\n								</a>\r\n							</div>\r\n						</div>\r\n					</div>\r\n				</div>\r\n			</div>\r\n			</footer>','','dcsolutions',_binary '¨\Ì\0sr\0\rjava.time.Serï]Ñ∫\"H≤\0\0xpw\0\0\Â2,:Äx','adminIvoAdamTestMembrania','adminIvoAdamTestMembrania'),(2,_binary '','membrania','<div class=\"copyright\">\r\n			<div class=\"container\">\r\n				<div class=\"row\">\r\n					<div class=\"col\">\r\n\r\n						<div class=\"copyright_container d-flex flex-sm-row flex-column align-items-center justify-content-start\">\r\n							<div class=\"copyright_content\">\r\n								Ing. Ivo Vo≈°ahl√≠k (C) 2018 - 2021. V≈°echna pr√°va vyhrazena.\r\n							</div>\r\n						</div>\r\n					</div>\r\n				</div>\r\n			</div>\r\n		</div>',_binary '¨\Ì\0sr\0\rjava.time.Serï]Ñ∫\"H≤\0\0xpw\0\0\Â18\Ï$¿x','<!-- Footer -->\r\n		<footer class=\"footer\">\r\n			<div class=\"container\">\r\n				<div class=\"row\">\r\n\r\n					<div class=\"col-lg-4\">\r\n						<div class=\"footer_column\">\r\n							<div class=\"footer_column footer_title\">DC Solutions s.r.o.</div>\r\n							<ul class=\"footer_list\">\r\n								<li>Voskovcova 1075/51</li>\r\n							</ul>\r\n							<ul class=\"footer_list\">\r\n								<li>152 00 PRAHA 5</li>\r\n							</ul>\r\n							<ul class=\"footer_list\">\r\n								<li>CZECH REPUBLIC</li>\r\n							</ul>\r\n						</div>\r\n					</div>\r\n\r\n					<div class=\"col-lg-2 offset-lg-2\">\r\n						<div class=\"footer_column\">\r\n							<div class=\"footer_title\">Find it Fast</div>\r\n							<ul class=\"footer_list\">\r\n								<li>+420 739 09 89 89</li>\r\n							</ul>\r\n							<ul class=\"footer_list\">\r\n								<li><a href=\"mailto: sales@dcsolutions.cz\">sales@dcsolutions.cz</a></li>\r\n							</ul>\r\n							<ul class=\"footer_list\">\r\n								<li>\r\n									<a thref=\"/\">www.membrania.eu</a>\r\n								</li>\r\n							</ul>\r\n						</div>\r\n					</div>\r\n\r\n					<div class=\"col-lg-2\">\r\n						<div class=\"footer_column\">\r\n							<ul class=\"footer_list footer_list_2\">\r\n								<li><a href=\"/myProfile\">My account</a></li>\r\n								<li><a href=\"/myProfile?order=true\">My orders</a></li>\r\n								<li><a href=\"/getAQoute\">My quotes</a></li>\r\n							</ul>\r\n						</div>\r\n					</div>\r\n\r\n					<div class=\"col-lg-2\">\r\n						<div class=\"footer_column\">\r\n							<div class=\"footer_title\">Customer Care</div>\r\n							<ul class=\"footer_list\">\r\n								<li><a href=\"/termsAndConditions\">Terms and conditions</a></li>\r\n								<li><a href=\"/gdpr\">GDPR</a></li>\r\n								<li><a href=\"/category/about-us\">About us</a></li>\r\n							</ul>\r\n						</div>\r\n					</div>\r\n\r\n				</div>\r\n\r\n				<div class=\"row\" style=\"margin-top: 60px;\">\r\n\r\n					<div class=\"col-lg-3 footer_col\">\r\n						<div class=\"footer_column footer_contact\">\r\n							<div class=\"logo\">\r\n								<a href=\"/\">\r\n									<img src=\"/images/membranialogo.png\" style=\"height: 50px;\" alt=\"\"/>\r\n								</a>\r\n							</div>\r\n						</div>\r\n					</div>\r\n\r\n					<div class=\"col-lg-3 footer_col\">\r\n						<div class=\"footer_column footer_contact\">\r\n							<div class=\"logo\">\r\n								<a href=\"/\">\r\n									<img src=\"/images/logohydro.png\" style=\"height: 25px;\" alt=\"\"/>\r\n								</a>\r\n							</div>\r\n						</div>\r\n					</div>\r\n				</div>\r\n			</div>\r\n			</footer>','','membrania',_binary '¨\Ì\0sr\0\rjava.time.Serï]Ñ∫\"H≤\0\0xpw\0\0\Â18\Ï$¿x','adminIvoAdamTestMembrania','adminIvoAdamTestMembrania');
/*!40000 ALTER TABLE `custom_settings` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-11 22:52:46
