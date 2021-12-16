-- MySQL dump 10.13  Distrib 8.0.27, for Linux (x86_64)
--
-- Host: localhost    Database: lista_3
-- ------------------------------------------------------
-- Server version	8.0.27-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `People`
--

DROP TABLE IF EXISTS `People`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `People` (
  `person_id` int NOT NULL AUTO_INCREMENT,
  `PESEL` char(11) DEFAULT NULL,
  `firstName` varchar(30) DEFAULT NULL,
  `surrname` varchar(30) DEFAULT NULL,
  `birth_day` date DEFAULT NULL,
  `sex` enum('W','M') DEFAULT NULL,
  PRIMARY KEY (`person_id`),
  CONSTRAINT `PESELDD` CHECK ((substr(`PESEL`,5,2) = date_format(`birth_day`,_utf8mb4'%d'))),
  CONSTRAINT `PESELK` CHECK ((right(`PESEL`,1) = ((10 - (((((((((((substr(`PESEL`,1,1) * 1) + (substr(`PESEL`,2,1) * 3)) + (substr(`PESEL`,3,1) * 7)) + (substr(`PESEL`,4,1) * 9)) + (substr(`PESEL`,5,1) * 1)) + (substr(`PESEL`,6,1) * 3)) + (substr(`PESEL`,7,1) * 7)) + (substr(`PESEL`,8,1) * 9)) + (substr(`PESEL`,9,1) * 1)) + (substr(`PESEL`,10,1) * 3)) % 10)) % 10))),
  CONSTRAINT `PESELMM` CHECK ((substr(`PESEL`,3,2) = if((year(`birth_day`) < 2000),date_format(`birth_day`,_utf8mb4'%m'),(date_format(`birth_day`,_utf8mb4'%m') + 20)))),
  CONSTRAINT `PESELPPPP` CHECK (((substr(`PESEL`,10,1) % 2) = if((`sex` = _utf8mb4'M'),1,0))),
  CONSTRAINT `PESELRR` CHECK ((left(`PESEL`,2) = date_format(`birth_day`,_utf8mb4'%y')))
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `People`
--

LOCK TABLES `People` WRITE;
/*!40000 ALTER TABLE `People` DISABLE KEYS */;
INSERT INTO `People` VALUES (1,'41082589090','Tomasz','Piotrowski','1941-08-25','M'),(2,'37051433635','Bartek','Grelewski','1937-05-14','M'),(3,'33031967152','Tomasz','Piotrowski','1933-03-19','M'),(4,'46090137871','Kacper','Kowalski','1946-09-01','M'),(5,'45121554641','Lena','Nowak','1945-12-15','W'),(6,'84021421549','Basia','Zawojska','1984-02-14','W'),(7,'73053167329','Malwina','Rzezniczek','1973-05-31','W'),(8,'02292886638','Artur','Grelewski','2002-09-28','M'),(9,'84093076744','Marcela','Kowalska','1984-09-30','W'),(10,'01262218231','Piotr','Grelewski','2001-06-22','M'),(11,'01222645897','Artur','Trzesniewski','2001-02-26','M'),(12,'96010896225','Wiktoria','Zawojska','1996-01-08','W'),(13,'79061564152','Artur','Szwed','1979-06-15','M'),(14,'72080528004','Wiktoria','Zozla','1972-08-05','W'),(15,'85071947296','Kacper','Zawojski','1985-07-19','M'),(16,'99072735217','Dawid','Kowalski','1999-07-27','M'),(17,'63020775736','Kacper','Kowalski','1963-02-07','M'),(18,'97122111769','Malwina','Rzezniczek','1997-12-21','W'),(19,'64091851286','Asia','Rzezniczek','1964-09-18','W'),(20,'73120690572','Piotr','Kowalski','1973-12-06','M'),(21,'85052055451','Dawid','Kowalski','1985-05-20','M'),(22,'56122496391','Tomasz','Piotrowski','1956-12-24','M'),(23,'99123027788','Asia','Rzezniczek','1999-12-30','W'),(24,'82101620921','Malwina','Zawojska','1982-10-16','W'),(25,'68080547880','Wiktoria','Nowak','1968-08-05','W'),(26,'90092922795','Artur','Zawojski','1990-09-29','M'),(27,'88120361096','Piotr','Trzesniewski','1988-12-03','M'),(28,'71022538170','Piotr','Szwed','1971-02-25','M'),(29,'80022194239','Piotr','Kowalski','1980-02-21','M'),(30,'60101915960','Lena','Kowalska','1960-10-19','W'),(31,'85122652030','Bartek','Kowalski','1985-12-26','M'),(32,'75031092887','Wiktoria','Zozla','1975-03-10','W'),(33,'02290565618','Kacper','Kowalski','2002-09-05','M'),(34,'68012884607','Lena','Zozla','1968-01-28','W'),(35,'81070877758','Dawid','Zawojski','1981-07-08','M'),(36,'74022271977','Artur','Zawojski','1974-02-22','M'),(37,'81120745376','Bartek','Trzesniewski','1981-12-07','M'),(38,'00302943876','Piotr','Piotrowski','2000-10-29','M'),(39,'98051429165','Malwina','Kowalska','1998-05-14','W'),(40,'68040223045','Basia','Kowalska','1968-04-02','W'),(41,'81041542957','Piotr','Szwed','1981-04-15','M'),(42,'02310920157','Bartek','Szwed','2002-11-09','M'),(43,'81010198545','Marcela','Nowak','1981-01-01','W'),(44,'72062023598','Piotr','Trzesniewski','1972-06-20','M'),(45,'59082984258','Kacper','Piotrowski','1959-08-29','M'),(46,'02271156349','Basia','Zawojska','2002-07-11','W'),(47,'71070955055','Tomasz','Piotrowski','1971-07-09','M'),(48,'91040966252','Kacper','Trzesniewski','1991-04-09','M'),(49,'54112629217','Artur','Trzesniewski','1954-11-26','M'),(50,'80070883378','Artur','Kowalski','1980-07-08','M'),(51,'18230864040','Lena','Rzezniczek','2018-03-08','W'),(52,'04303141196','Kacper','Grelewski','2004-10-31','M'),(53,'04233187439','Kacper','Kowalski','2004-03-31','M'),(54,'05292783851','Dawid','Szwed','2005-09-27','M'),(55,'11262192392','Dawid','Piotrowski','2011-06-21','M');
/*!40000 ALTER TABLE `People` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Professions`
--

DROP TABLE IF EXISTS `Professions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Professions` (
  `profession_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `salary_min` float DEFAULT NULL,
  `salary_max` float DEFAULT NULL,
  PRIMARY KEY (`profession_id`),
  CONSTRAINT `ProfessionConstraint` CHECK (((`salary_min` >= 0) and (`salary_max` >= 0) and (`salary_min` < `salary_max`)))
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Professions`
--

LOCK TABLES `Professions` WRITE;
/*!40000 ALTER TABLE `Professions` DISABLE KEYS */;
INSERT INTO `Professions` VALUES (1,'Polityk',2000,10000),(2,'Nauczyciel',2000,4000),(3,'Lekarz',2000,8000),(4,'Programista',10000,400000);
/*!40000 ALTER TABLE `Professions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Workers`
--

DROP TABLE IF EXISTS `Workers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Workers` (
  `worker` int DEFAULT NULL,
  `profession_id` int DEFAULT NULL,
  `salary` float DEFAULT NULL,
  KEY `worker` (`worker`),
  KEY `profession_id` (`profession_id`),
  CONSTRAINT `Workers_ibfk_1` FOREIGN KEY (`worker`) REFERENCES `People` (`person_id`),
  CONSTRAINT `Workers_ibfk_2` FOREIGN KEY (`profession_id`) REFERENCES `Professions` (`profession_id`),
  CONSTRAINT `WorkersConstraint` CHECK ((`salary` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Workers`
--

LOCK TABLES `Workers` WRITE;
/*!40000 ALTER TABLE `Workers` DISABLE KEYS */;
INSERT INTO `Workers` VALUES (1,4,175373),(2,2,3173.73),(3,4,140301),(4,1,2529.87),(5,4,108082),(6,1,3457.9),(7,2,2398.16),(8,4,65515.2),(9,4,366026),(10,4,34859.6),(11,2,3990.24),(12,3,4001.64),(13,3,4294.66),(14,4,112948),(15,3,5440.68),(16,4,212702),(17,1,7713.57),(18,2,3783.99),(19,1,6161.48),(20,4,343664),(21,3,5442.65),(22,4,88695.4),(23,1,7359.2),(24,1,9296.38),(25,1,4398.52),(26,2,2976.11),(27,1,6597.88),(28,4,215706),(29,1,8791.75),(30,4,159508),(31,4,260772),(32,2,3533.77),(33,4,315689),(34,2,3981.16),(35,3,6599.77),(36,1,7205.87),(37,3,4785.66),(38,2,3929.33),(39,1,3201.43),(40,3,3028.38),(41,3,7429.64),(42,2,3302.6),(43,4,99776.5),(44,3,5072.27),(45,3,5942.6),(46,2,3752.96),(47,2,3478.38),(48,4,70953),(49,1,4534.44),(50,1,6901.53);
/*!40000 ALTER TABLE `Workers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-16 13:15:43
