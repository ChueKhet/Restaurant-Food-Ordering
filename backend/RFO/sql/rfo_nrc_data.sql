CREATE DATABASE  IF NOT EXISTS `rfo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `rfo`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: rfo
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `nrc_data`
--

DROP TABLE IF EXISTS `nrc_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nrc_data` (
  `id` int NOT NULL,
  `nrc_code` int NOT NULL,
  `township_names` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nrc_data`
--

LOCK TABLES `nrc_data` WRITE;
/*!40000 ALTER TABLE `nrc_data` DISABLE KEYS */;
INSERT INTO `nrc_data` (`id`, `nrc_code`, `township_names`) VALUES (1,1,'AhGaYa'),(2,1,'BaMaNa'),(3,1,'KhaPhaNa'),(4,1,'DaPhaYa'),(5,1,'HaPaNa'),(6,1,'KaMaNa'),(7,1,'KhaLaPha'),(8,1,'LaGaNa'),(9,1,'MaKhaBa'),(10,1,'MaSaNa'),(11,1,'MaNyaNa'),(12,1,'MaKaTa'),(13,1,'MaMaNa'),(14,1,'MaKaNa'),(15,1,'NaMaNa'),(16,1,'PhaKaNa'),(17,1,'PaTaAh'),(18,1,'YaKaNa'),(19,1,'SaBaNa'),(20,1,'SaLaNa'),(21,1,'SaPaBa'),(22,1,'TaNaNa'),(23,1,'WaMaNa'),(24,2,'BaLaKha'),(25,2,'DaMaSa'),(26,2,'LaKaNa'),(27,2,'MaSaNa'),(28,2,'PhaSaNa'),(29,2,'PhaYaSa'),(30,2,'YaTaNa'),(31,3,'LaBaNa'),(32,3,'KaKaYa'),(33,3,'KaSaKa'),(34,3,'KaDaNa'),(35,3,'MaWaTa'),(36,3,'PhaAhNa'),(37,3,'BaAhNa'),(38,3,'PhaPaNa'),(39,3,'ThaTaNa'),(40,4,'HaKhaNa'),(41,4,'HtaTaLa'),(42,4,'KaPaLa'),(43,4,'MaTaPa'),(44,4,'MaTaNa'),(45,4,'PhaLaNa'),(46,4,'PaLaWa'),(47,4,'TaTaNa'),(48,4,'TaZaNa'),(49,5,'AhYaTa'),(50,5,'BaMaNa'),(51,5,'BaTaLa'),(52,5,'KhaOuNa'),(53,5,'DaPaYa'),(54,5,'HaMaLa'),(55,5,'HtaKhaNa'),(56,5,'AhTaNa'),(57,5,'KaNaNa'),(58,5,'KaThaNa'),(59,5,'KaLaHta'),(60,5,'KaLaWa'),(61,5,'KaBaLa'),(62,5,'KaLaTa'),(63,5,'KhaTaNa'),(64,5,'KhaOuTa'),(65,5,'KaLaNa'),(66,5,'MaLaNa'),(67,5,'MaKaNa'),(68,5,'MaYaNa'),(69,5,'MaMaNa'),(70,5,'NaYaNa'),(71,5,'NgaZaNa'),(72,5,'PaLaNa'),(73,5,'PhaPaNa'),(74,5,'PaLanBa'),(75,5,'SaKaNa'),(76,5,'SaLaKa'),(77,5,'YaBaNa'),(78,5,'TaMaNa'),(79,5,'TaSaNa'),(80,5,'WaLaNa'),(81,5,'WaThaNa'),(82,5,'YaOuNa'),(83,5,'YaMaPa'),(84,5,'YaThaKa'),(85,6,'BaPaNa'),(86,6,'HtaWaNa'),(87,6,'KaThaNa'),(88,6,'KaSaNa'),(89,6,'LaLaNa'),(90,6,'MaMaNa'),(91,6,'MaAhYa'),(92,6,'NgaYaKa'),(93,6,'PaLaNa'),(94,6,'TaNaTha'),(95,6,'TaThaYa'),(96,6,'ThaYaKha'),(97,6,'YaPhaNa'),(98,7,'AhPhaNa'),(99,7,'AhPhaNa'),(100,7,'AhTaNa'),(101,7,'DaOuNa'),(102,7,'HtaTaPa'),(103,7,'KaTaTa'),(104,7,'KaPaKa'),(105,7,'KaKaNa'),(106,7,'KaTaKha'),(107,7,'KaKaNa'),(108,7,'MaDaNa'),(109,7,'MaLaNa'),(110,7,'MaNyaNa'),(111,7,'NaTaLa'),(112,7,'NyaLaPa'),(113,7,'PaNaKa'),(114,7,'PaKhaNa'),(115,7,'PaTaNa'),(116,7,'PaKhaTa'),(117,7,'PaTaTa'),(118,7,'PhaMaNa'),(119,7,'PaMaNa'),(120,7,'PaTaSa'),(121,7,'YaKaNa'),(122,7,'YaTaNa'),(123,7,'TaNgaNa'),(124,7,'ThaNaPa'),(125,7,'ThaKaNa'),(126,7,'ThaWaTa'),(127,7,'ThaSaNa'),(128,7,'WaMaNa'),(129,7,'YaTaYa'),(130,7,'ZaKaNa'),(131,8,'AhLaNa'),(132,8,'KhaMaNa'),(133,8,'GaGaNa'),(134,8,'SaPhaNa'),(135,8,'SaPaWa'),(136,8,'HtaLaNa'),(137,8,'KaMaNa'),(138,8,'MaKaNa'),(139,8,'MaBaNa'),(140,8,'MaLaNa'),(141,8,'MaTaNa'),(142,8,'MaMaNa'),(143,8,'MaHtaNa'),(144,8,'MaThaNa'),(145,8,'NaMaNa'),(146,8,'NgaPhaNa'),(147,8,'PaKhaKa'),(148,8,'PaMaNa'),(149,8,'PaPhaNa'),(150,8,'SaLaNa'),(151,8,'SaTaYa'),(152,8,'SaKaNa'),(153,8,'TaTaKa'),(154,8,'ThaYaNa'),(155,8,'SaMaNa'),(156,8,'YaNaKha'),(157,8,'YaSaKa'),(158,9,'DaKhaTha'),(159,9,'LaWaNa'),(160,9,'OuTaTha'),(161,9,'PaBaTha'),(162,9,'PaMaNa'),(163,9,'TaKaNa'),(164,9,'ZaBaTha'),(165,9,'ZaYaTha'),(166,9,'AhMaYa'),(167,9,'AhMaZa'),(168,9,'KhaAhZa'),(169,9,'KhaMaSa'),(170,9,'KaPaTa'),(171,9,'KaSaNa'),(172,9,'MaLaNa'),(173,9,'MaHaMa'),(174,9,'MaNaMa'),(175,9,'MaNaTa'),(176,9,'MaYaMa'),(177,9,'MaYaTa'),(178,9,'MaTaYa'),(179,9,'MaMaNa'),(180,9,'MaHtaLa'),(181,9,'MaKaNa'),(182,9,'MaKhaNa'),(183,9,'MaThaNa'),(184,9,'NaHtaKa'),(185,9,'NgaTaYa'),(186,9,'NyaOuNa'),(187,9,'PaLaNa'),(188,9,'PaThaKa'),(189,9,'PaBaNa'),(190,9,'PaKaKha'),(191,9,'PaOuLa'),(192,9,'PaMaNa'),(193,9,'SaKaTa'),(194,9,'SaKaNa'),(195,9,'TaKaNa'),(196,9,'TaTaOu'),(197,9,'TaThaNa'),(198,9,'ThaPaKa'),(199,9,'ThaSaNa'),(200,9,'WaTaNa'),(201,9,'YaMaTha'),(202,10,'BaLaNa'),(203,10,'KhaSaNa'),(204,10,'KaMaYa'),(205,10,'KaHtaNa'),(206,10,'MaLaMa'),(207,10,'MaDaNa'),(208,10,'PaMaNa'),(209,10,'ThaPhaYa'),(210,10,'ThaHtaNa'),(211,10,'KhaZaNa'),(212,10,'LaMaNa'),(213,10,'YaMaNa'),(214,11,'AaMaNa'),(215,11,'BaThaTa'),(216,11,'GaMaNa'),(217,11,'KaPhaNa'),(218,11,'KaTaNa'),(219,11,'MaAhNa'),(220,11,'MaTaNa'),(221,11,'MaPaNa'),(222,11,'MaOuNa'),(223,11,'MaPaTa'),(224,11,'PaTaNa'),(225,11,'PaNaKa'),(226,11,'SaTaNa'),(227,11,'TaKaNa'),(228,11,'ThaTaNa'),(229,11,'YaBaNa'),(230,11,'YaThaTa'),(231,12,'AaLaNa'),(232,12,'BaHaNa'),(233,12,'BaTaHta'),(234,12,'KaKaKa'),(235,12,'DaGaNa'),(236,12,'DaGaYa'),(237,12,'DaGaMa'),(238,12,'DaSaKa'),(239,12,'DaGaTa'),(240,12,'DaLaNa'),(241,12,'DaPaNa'),(242,12,'LaMaNa'),(243,12,'LaThaYa'),(244,12,'LaKaNa'),(245,12,'MaBaNa'),(246,12,'HtaTaPa'),(247,12,'AhSaNa'),(248,12,'KaMaYa'),(249,12,'KaMaNa'),(250,12,'KhaYaNa'),(251,12,'KaKhaKa'),(252,12,'KaTaTa'),(253,12,'KaTaNa'),(254,12,'KaMaTa'),(255,12,'LaMata'),(256,12,'LaThaNa'),(257,12,'MaYaKa'),(258,12,'MaGaTa'),(259,12,'MaGaDa'),(260,12,'OuKaMa'),(261,12,'PaBaTa'),(262,12,'PaZaDa'),(263,12,'SaKhaNa'),(264,12,'SaKakha'),(265,12,'SaKaNa'),(266,12,'YaPaKa'),(267,12,'YaPaTha'),(268,12,'OuKaTa'),(269,12,'TaKaNa'),(270,12,'TaMaNa'),(271,12,'ThaKaTa'),(272,12,'ThaLaNa'),(273,12,'ThaGaKa'),(274,12,'ThaKhaNa'),(275,12,'TaTaNa'),(276,12,'YaKaNa'),(277,13,'KhaYaHa'),(278,13,'HaPaTa'),(279,13,'HaPaNa'),(280,13,'KaLaNa'),(281,13,'KaLaTa'),(282,13,'KaHaNa'),(283,13,'KaThaNa'),(284,13,'KaTaTa'),(285,13,'KaTaNa'),(286,13,'KaMaNa'),(287,13,'KaKhaNa'),(288,13,'LaYaNa'),(289,13,'LaKaNa'),(290,13,'LaKhaTa'),(291,13,'LaKhaNa'),(292,13,'LaLaNa'),(293,13,'MaBaNa'),(294,13,'MaKaNa'),(295,13,'MaKhaNa'),(296,13,'MaPHaNa'),(297,13,'MaPaTa'),(298,13,'MaSaNa'),(299,13,'MaYaNa'),(300,13,'MaYaTa'),(301,13,'MaTaTa'),(302,13,'MaMaTa'),(303,13,'MaNaNa'),(304,13,'MaKaNa'),(305,13,'MaSaTa'),(306,13,'NaMaTa'),(307,13,'NaKhaNa'),(308,13,'NaSaNa'),(309,13,'NaPaNa'),(310,13,'NaKhaTa'),(311,13,'NyaYaNa'),(312,13,'PhaKhaNa'),(313,13,'PaLaNa'),(314,13,'PaTaYa'),(315,13,'SaSaNa'),(316,13,'YaNyaNa'),(317,13,'TaYaNa'),(318,13,'TaMaNya'),(319,13,'TaKhaLa'),(320,13,'TaLaNa'),(321,13,'TaKaNa'),(322,13,'ThaNaNa'),(323,13,'ThaPaNa'),(324,13,'YaNgaNa'),(325,13,'YaSaNa'),(326,14,'BaKaLa'),(327,14,'DaNaPha'),(328,14,'DaDaYa'),(329,14,'PaThaYa'),(330,14,'AhMaNa'),(331,14,'HaKaKa'),(332,14,'HaThaTa'),(333,14,'AhGaPa'),(334,14,'KaNaNa'),(335,14,'KaLaNa'),(336,14,'KaKhaNa'),(337,14,'KaKaNa'),(338,14,'KaPaNa'),(339,14,'LaPaTa'),(340,14,'LaMaNa'),(341,14,'MaAhPa'),(342,14,'MaMaKa'),(343,14,'MaAhaNa'),(344,14,'MaMaNa'),(345,14,'NgaPaTa'),(346,14,'NgaThaKha'),(347,14,'NyaTaNa'),(348,14,'PaTaNa'),(349,14,'PhaPaNa'),(350,14,'ThaPaNa'),(351,14,'WaKhaMa'),(352,14,'PaThaNa'),(353,14,'YaKaNa'),(354,14,'ZaLaNa'),(355,14,'KaKaHta'),(356,6,'KhaMaKa'),(357,1,'KaMaTa'),(358,1,'KaPaTa'),(359,1,'MaLaNa'),(360,1,'PaNaDa'),(361,1,'PaWaNa'),(362,1,'SaDaNa'),(363,1,'YaBaYa'),(364,2,'MaSaNa'),(365,2,'YaThaNa'),(366,3,'BaGaLa'),(367,3,'BaThaSa'),(368,3,'KaMaMa'),(369,3,'LaThaNa'),(370,3,'SaKaLa'),(371,3,'ThaTaKa'),(372,3,'WaLaMa'),(373,3,'YaYaTha'),(374,4,'KaKhaNa'),(375,4,'SaMaNa'),(376,4,'YaKhaDa'),(377,4,'YaZaNa'),(378,5,'DaHaNa'),(379,5,'SaMaYa'),(380,5,'HtaPaKha'),(381,5,'KaMaNa'),(382,5,'KhaPaNa'),(383,5,'LaHaNa'),(384,5,'LaYaNa'),(385,5,'MaMaNa'),(386,5,'MaPaLa'),(387,5,'MaThaNa'),(388,5,'PaSaNa'),(389,6,'KaLaAh'),(390,6,'KaYaYa'),(391,6,'MaAhNa'),(392,6,'PaKaMa'),(393,6,'PaLaTa'),(394,7,'KaWaNa'),(395,7,'LaPATa'),(396,7,'PaTaLa'),(397,8,'KaHtaNa'),(398,9,'NgaZaNa'),(399,9,'PaBaNa'),(400,9,'OoTaYa'),(401,10,'KaKhaMa'),(402,11,'KaTaLa'),(403,11,'MaAhTa'),(404,11,'TaPaWa'),(405,13,'AhPaNa'),(406,13,'AhTaNa'),(407,13,'AhTaYa'),(408,13,'HaHaNa'),(409,13,'HaMaNa'),(410,13,'KaLaHta'),(411,13,'KaLaNa'),(412,13,'MaHtaNa'),(413,13,'MaKhaTa'),(414,13,'MaNgaNa'),(415,13,'MaPhaHta'),(416,13,'NaTaYa'),(417,13,'PaPaKa'),(418,13,'PaWaNa'),(419,13,'TaTaNa'),(420,14,'AhMaTa'),(421,14,'NgaYaKa'),(422,14,'PaSaLa'),(423,14,'YaThaYa'),(424,9,'KhAaHsa'),(425,12,'TaTaHta');
/*!40000 ALTER TABLE `nrc_data` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-22 20:08:47
