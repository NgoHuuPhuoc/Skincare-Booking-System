CREATE DATABASE  IF NOT EXISTS `skincareapp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `skincareapp`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: skincareapp
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `appointment_time` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `branch_id` bigint DEFAULT NULL,
  `service_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKirq7r526btqxyk1gsuq4wa2h3` (`branch_id`),
  KEY `FKa8m1smlfsc8kkjn2t6wpdmysk` (`user_id`),
  KEY `FK5tjupy8tsptpusnwkolraen9o` (`service_id`),
  CONSTRAINT `FKa8m1smlfsc8kkjn2t6wpdmysk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKg1ec4bkvxnkor2lx10a49rsuy` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`),
  CONSTRAINT `FKirq7r526btqxyk1gsuq4wa2h3` FOREIGN KEY (`branch_id`) REFERENCES `branch` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (2,'2025-04-16 14:00:00.000000','Đã đặt',1,2,1),(3,'2025-04-15 12:47:00.000000','Đã đặt',1,2,1),(4,'2025-04-17 11:00:00.000000','Đã đặt',1,2,1),(5,'2025-04-18 09:00:00.000000','Đã đặt',1,2,1),(6,'2025-04-18 19:30:00.000000','Đã đặt',1,2,1),(7,'2025-04-18 09:30:00.000000','Đã đặt',1,2,1),(8,'2025-04-15 19:30:00.000000','Đã đặt',1,2,1),(9,'2025-04-15 09:00:00.000000','Đã đặt',1,2,1),(10,'2025-04-15 09:30:00.000000','Đã đặt',1,2,1),(11,'2025-04-16 19:30:00.000000','Đã đặt',1,2,1),(12,'2025-04-16 14:30:00.000000','Đã đặt',1,2,1),(13,'2025-04-16 13:00:00.000000','Đã đặt',1,2,1),(14,'2025-04-16 13:30:00.000000','Đã đặt',1,2,1),(15,'2025-04-16 15:00:00.000000','Đã đặt',1,2,1),(16,'2025-04-16 17:00:00.000000','Đã đặt',1,2,3),(17,'2025-04-16 11:30:00.000000','Đã đặt',1,2,3),(18,'2025-04-16 16:30:00.000000','Đã đặt',1,2,3),(19,'2025-04-18 16:30:00.000000','Đã đặt',1,2,3),(20,'2025-04-17 11:30:00.000000','Đã đặt',1,2,3),(21,'2025-04-17 13:00:00.000000','Đã đặt',1,2,4),(22,'2025-04-17 17:00:00.000000','Đã đặt',1,2,3),(23,'2025-04-19 09:30:00.000000','Đã đặt',1,2,3),(24,'2025-04-17 09:30:00.000000','Đã đặt',1,2,3),(25,'2025-04-04 09:30:00.000000','Đã đặt',1,2,3),(26,'2025-04-18 14:30:00.000000','Đã đặt',1,2,3),(27,'2025-04-18 14:30:00.000000','Đã đặt',2,3,3),(28,'2025-04-27 11:30:00.000000','Đã đặt',3,8,5);
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (1,'2/3 Tô Ký, Hóc Môn, TP.HCM'),(2,'123 Quang Trung, Gò Vấp'),(3,'100 Nguyễn Thị Minh Khai, Quận 1');
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (2,'Dịch vụ massage giảm stress','Massage Thư Giãn'),(3,'Chăm sóc da cơ bản giúp làm sạch, cấp ẩm và thư giãn làn da.','Chăm sóc da'),(4,'Liệu trình điều trị mụn giúp giảm viêm, làm dịu da và ngăn ngừa mụn.','Điều trị mụn'),(5,'Dịch vụ điều trị nám giúp làm sáng vùng da tối màu, đều màu da.','Điều trị nám'),(6,'Giải pháp trị sẹo rỗ bằng công nghệ cao như phi kim, laser CO2.','Điều trị sẹo rỗ'),(7,'Liệu trình giảm béo không xâm lấn, an toàn và hiệu quả.','Giảm béo'),(8,'Triệt lông bằng laser, cho làn da mịn màng lâu dài','Triệt lông'),(9,'Dịch vụ phun xăm môi, mày, mí tự nhiên, không đau.','Phun xăm'),(10,'Tắm trắng toàn thân bằng công nghệ phi thuyền, an toàn tuyệt đối.','Tắm trắng');
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Nguyễn Văn A','0901234567'),(2,NULL,NULL),(3,'Ngô Hữu Phước','0912406259'),(4,'Ngô Hữu Phước','0912345678'),(5,'Nguyễn Khánh Phúc','0815678663');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-16 13:59:35
