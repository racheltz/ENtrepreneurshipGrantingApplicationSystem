-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: granting_app
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `projectapplication`
--

DROP TABLE IF EXISTS `projectapplication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projectapplication` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `entrepreneur` varchar(255) DEFAULT NULL,
  `published` datetime(6) DEFAULT NULL,
  `announcement_id` bigint DEFAULT NULL,
  `isFunded` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKebbouxju49f5mijl6qc4kl9vc` (`announcement_id`),
  CONSTRAINT `FKebbouxju49f5mijl6qc4kl9vc` FOREIGN KEY (`announcement_id`) REFERENCES `announcement` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projectapplication`
--

LOCK TABLES `projectapplication` WRITE;
/*!40000 ALTER TABLE `projectapplication` DISABLE KEYS */;
INSERT INTO `projectapplication` VALUES (1,'hdoiwhdioqwjdoiwqdjoiwqjd jwendiwekdx','annay','2023-06-13 20:48:42.587000',3,_binary ''),(2,'my application is akanisdfbuiw vbuidcn nicciowdi ','mammy','2023-06-14 23:43:38.284000',3,NULL),(3,'construction idea will be this way: nnnnnnnnnnnnnnnnnnnnn sd  weddd ded ','ibramwinyi','2023-07-02 23:00:44.578000',4,NULL),(4,'Food production has some business ideas which leads to high profit. The following is my business idea on food production; \ni. Market searching','ibramwinyi','2023-07-02 23:42:55.092000',3,NULL),(5,'mknonioni jioj ok','elvis','2023-07-03 12:39:44.983000',3,_binary ''),(6,'ksopxkosa joskmxposak kspkxpask jiodioc ','elvis','2023-07-03 13:37:49.339000',5,_binary ''),(7,'ufwsyzfwszuwfy','elvis','2023-07-03 17:31:07.671000',4,_binary '\0');
/*!40000 ALTER TABLE `projectapplication` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-04  9:09:18
