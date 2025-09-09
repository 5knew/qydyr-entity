-- MySQL dump 10.13  Distrib 9.4.0, for macos15.4 (arm64)
--
-- Host: localhost    Database: qydyr
-- ------------------------------------------------------
-- Server version	9.4.0

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
-- Table structure for table `afisha`
--

DROP TABLE IF EXISTS `afisha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `afisha` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `address_link` varchar(255) DEFAULT NULL,
  `address_name` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `created_date_time` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `event_date_time_from` datetime DEFAULT NULL,
  `event_date_time_to` datetime DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `geo_processed` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `afisha`
--

LOCK TABLES `afisha` WRITE;
/*!40000 ALTER TABLE `afisha` DISABLE KEYS */;
INSERT INTO `afisha` VALUES (1,'Алматы','Казахстан','ул. Абая 150',NULL,'Дворец Республики','CONCERT','2025-09-08 15:12:31',_binary '\0','Гала-концерт популярного казахстанского певца','2024-12-25 19:00:00','2024-12-25 22:00:00',NULL,NULL,_binary '','Концерт Димаша Кудайбергена','+7 (727) 250-12-34',5000,'ACTIVE'),(2,'Алматы','Казахстан','пр. Абая 110',NULL,'Театр им. Ауэзова','THEATRE','2025-09-08 15:12:31',_binary '\0','Драматический спектакль по мотивам жизни Абая Кунанбаева','2024-12-20 18:30:00','2024-12-20 21:30:00',NULL,NULL,_binary '','Спектакль \"Абай\"','+7 (727) 272-40-77',2000,'ACTIVE'),(3,'Алматы','Казахстан','ул. Гоголя 1',NULL,'Центральный парк культуры','CONCERT','2025-09-08 15:12:31',_binary '\0','Международный джазовый фестиваль под открытым небом','2024-12-30 16:00:00','2024-12-30 23:00:00',NULL,NULL,_binary '','Фестиваль \"Алматы Джаз\"','+7 (727) 291-90-90',1000,'ACTIVE'),(4,'Алматы','Казахстан','Проспект Абая','https://maps.google.com/?q=Алматы+Арена','Алматы Арена','CONCERT','2025-09-08 22:31:24',_binary '\0','Легендарная ирландская рок-группа U2 в Алматы! Невероятное шоу с лучшими хитами всех времен.','2024-12-31 19:00:00','2024-12-31 22:00:00',43.2339062,76.8720106,_binary '','Концерт группы U2','+7 777 123 4567',15000,'ACTIVE'),(5,'Алматы','Казахстан','Улица Кабанбай батыра','https://maps.google.com/?q=Казахская+государственная+филармония','Казахская государственная филармония','CONCERT','2025-09-08 22:31:24',_binary '\0','Романтический джаз-вечер с лучшими музыкантами города. Французские мелодии и атмосфера Парижа.','2024-12-20 20:00:00','2024-12-20 23:00:00',43.2482885,76.9263527,_binary '','Джаз-концерт \'Ночь в Париже\'','+7 727 123 4567',8000,'ACTIVE'),(6,'Алматы','Казахстан','Улица Гоголя','https://maps.google.com/?q=Центральный+парк+Алматы','Центральный парк культуры и отдыха','CONCERT','2025-09-08 22:31:24',_binary '\0','Масштабный рок-фестиваль с участием лучших казахстанских и зарубежных групп. Еда, напитки, музыка!','2024-12-15 16:00:00','2024-12-15 23:00:00',43.2590769,76.9332418,_binary '','Рок-фестиваль \'Звуки Алматы\'','+7 777 987 6543',5000,'ACTIVE'),(7,'Алматы','Казахстан','Проспект Достык','https://maps.google.com/?q=Дворец+Республики+Алматы','Дворец Республики','CONCERT','2025-09-08 22:31:24',_binary '\0','Симфонический оркестр исполняет \'Времена года\' Вивальди. Незабываемый вечер классической музыки.','2024-12-28 19:30:00','2024-12-28 21:30:00',43.2286716,76.9607095,_binary '','Классический концерт \'Времена года\'','+7 727 345 6789',12000,'ACTIVE'),(8,'Алматы','Казахстан','Улица Кабанбай батыра','https://maps.google.com/?q=Казахский+театр+оперы+и+балета','Казахский театр оперы и балета','THEATRE','2025-09-08 22:31:24',_binary '\0','Классическая опера по роману А.С. Пушкина. Великолепные декорации, костюмы и музыка Чайковского.','2024-12-25 18:00:00','2024-12-25 21:00:00',43.2482885,76.9263527,_binary '','Спектакль \'Евгений Онегин\'','+7 727 123 4567',12000,'ACTIVE'),(9,'Алматы','Казахстан','Проспект Абая','https://maps.google.com/?q=Русский+театр+драмы+Лермонтова','Русский театр драмы имени Лермонтова','THEATRE','2025-09-08 22:31:24',_binary '\0','Трагедия Шекспира в современной интерпретации. Мощная игра актеров и глубокий смысл.','2024-12-18 19:30:00','2024-12-18 22:30:00',43.2339062,76.8720106,_binary '','Драма \'Король Лир\'','+7 727 234 5678',6000,'ACTIVE'),(10,'Алматы','Казахстан','Улица Кабанбай батыра','https://maps.google.com/?q=Казахский+театр+оперы+и+балета','Казахский театр оперы и балета','THEATRE','2025-09-08 22:31:24',_binary '\0','Великолепный балет Чайковского в исполнении ведущих артистов театра. Классика мирового балета.','2024-12-22 18:30:00','2024-12-22 21:00:00',43.2482885,76.9263527,_binary '','Балет \'Лебединое озеро\'','+7 727 456 7890',10000,'ACTIVE'),(11,'Алматы','Казахстан','Улица Сатпаева','https://maps.google.com/?q=Кинотеатр+Арман','Кинотеатр \'Арман\'','CINEMA','2025-09-08 22:31:24',_binary '\0','Премьерный показ нового казахстанского фильма с участием звезд кино. Встреча с режиссером после сеанса.','2024-12-22 20:00:00','2024-12-22 22:30:00',43.236287,76.9161293,_binary '','Премьера фильма \'Астана\'','+7 727 345 6789',3000,'ACTIVE'),(12,'Алматы','Казахстан','Улица Гоголя','https://maps.google.com/?q=Центральный+парк+Алматы','Центральный парк культуры и отдыха','MARATHON','2025-09-08 22:31:24',_binary '\0','Ежегодный марафон по улицам Алматы. Дистанции: 5км, 10км, 21км, 42км. Регистрация обязательна.','2024-12-10 08:00:00','2024-12-10 12:00:00',43.2590769,76.9332418,_binary '','Марафон \'Алматы 2024\'','+7 777 456 7890',2000,'ACTIVE'),(13,'Алматы','Казахстан','Проспект Назарбаева','https://maps.google.com/?q=Конференц+зал+Астана','Конференц-зал \'Астана\'','SEMINAR','2025-09-08 22:31:24',_binary '\0','Однодневный семинар по цифровой трансформации бизнеса. Спикеры из IT-компаний Казахстана и зарубежья.','2024-12-28 10:00:00','2024-12-28 17:00:00',43.2470233,76.9477593,_binary '','Семинар \'Цифровая трансформация\'','+7 727 567 8901',15000,'ACTIVE'),(14,'Алматы','Казахстан','Проспект Абая','https://maps.google.com/?q=Алматы+Арена','Алматы Арена','CONCERT','2025-09-08 22:33:53',_binary '\0','Легендарная ирландская рок-группа U2 в Алматы! Невероятное шоу с лучшими хитами всех времен.','2024-12-31 19:00:00','2024-12-31 22:00:00',43.2339062,76.8720106,_binary '','Концерт группы U2','+7 777 123 4567',15000,'ACTIVE'),(15,'Алматы','Казахстан','Улица Кабанбай батыра','https://maps.google.com/?q=Казахская+государственная+филармония','Казахская государственная филармония','CONCERT','2025-09-08 22:33:53',_binary '\0','Романтический джаз-вечер с лучшими музыкантами города. Французские мелодии и атмосфера Парижа.','2024-12-20 20:00:00','2024-12-20 23:00:00',43.2482885,76.9263527,_binary '','Джаз-концерт \'Ночь в Париже\'','+7 727 123 4567',8000,'ACTIVE'),(16,'Алматы','Казахстан','Улица Гоголя','https://maps.google.com/?q=Центральный+парк+Алматы','Центральный парк культуры и отдыха','CONCERT','2025-09-08 22:33:53',_binary '\0','Масштабный рок-фестиваль с участием лучших казахстанских и зарубежных групп. Еда, напитки, музыка!','2024-12-15 16:00:00','2024-12-15 23:00:00',43.2590769,76.9332418,_binary '','Рок-фестиваль \'Звуки Алматы\'','+7 777 987 6543',5000,'ACTIVE'),(17,'Алматы','Казахстан','Проспект Достык','https://maps.google.com/?q=Дворец+Республики+Алматы','Дворец Республики','CONCERT','2025-09-08 22:33:53',_binary '\0','Симфонический оркестр исполняет \'Времена года\' Вивальди. Незабываемый вечер классической музыки.','2024-12-28 19:30:00','2024-12-28 21:30:00',43.2286716,76.9607095,_binary '','Классический концерт \'Времена года\'','+7 727 345 6789',12000,'ACTIVE'),(18,'Алматы','Казахстан','Улица Кабанбай батыра','https://maps.google.com/?q=Казахский+театр+оперы+и+балета','Казахский театр оперы и балета','THEATRE','2025-09-08 22:33:53',_binary '\0','Классическая опера по роману А.С. Пушкина. Великолепные декорации, костюмы и музыка Чайковского.','2024-12-25 18:00:00','2024-12-25 21:00:00',43.2482885,76.9263527,_binary '','Спектакль \'Евгений Онегин\'','+7 727 123 4567',12000,'ACTIVE'),(19,'Алматы','Казахстан','Проспект Абая','https://maps.google.com/?q=Русский+театр+драмы+Лермонтова','Русский театр драмы имени Лермонтова','THEATRE','2025-09-08 22:33:53',_binary '\0','Трагедия Шекспира в современной интерпретации. Мощная игра актеров и глубокий смысл.','2024-12-18 19:30:00','2024-12-18 22:30:00',43.2339062,76.8720106,_binary '','Драма \'Король Лир\'','+7 727 234 5678',6000,'ACTIVE'),(20,'Алматы','Казахстан','Улица Кабанбай батыра','https://maps.google.com/?q=Казахский+театр+оперы+и+балета','Казахский театр оперы и балета','THEATRE','2025-09-08 22:33:53',_binary '\0','Великолепный балет Чайковского в исполнении ведущих артистов театра. Классика мирового балета.','2024-12-22 18:30:00','2024-12-22 21:00:00',43.2482885,76.9263527,_binary '','Балет \'Лебединое озеро\'','+7 727 456 7890',10000,'ACTIVE'),(21,'Алматы','Казахстан','Улица Сатпаева','https://maps.google.com/?q=Кинотеатр+Арман','Кинотеатр \'Арман\'','CINEMA','2025-09-08 22:33:53',_binary '\0','Премьерный показ нового казахстанского фильма с участием звезд кино. Встреча с режиссером после сеанса.','2024-12-22 20:00:00','2024-12-22 22:30:00',43.236287,76.9161293,_binary '','Премьера фильма \'Астана\'','+7 727 345 6789',3000,'ACTIVE'),(22,'Алматы','Казахстан','Улица Гоголя','https://maps.google.com/?q=Центральный+парк+Алматы','Центральный парк культуры и отдыха','MARATHON','2025-09-08 22:33:53',_binary '\0','Ежегодный марафон по улицам Алматы. Дистанции: 5км, 10км, 21км, 42км. Регистрация обязательна.','2024-12-10 08:00:00','2024-12-10 12:00:00',43.2590769,76.9332418,_binary '','Марафон \'Алматы 2024\'','+7 777 456 7890',2000,'ACTIVE'),(23,'Алматы','Казахстан','Проспект Назарбаева','https://maps.google.com/?q=Конференц+зал+Астана','Конференц-зал \'Астана\'','SEMINAR','2025-09-08 22:33:53',_binary '\0','Однодневный семинар по цифровой трансформации бизнеса. Спикеры из IT-компаний Казахстана и зарубежья.','2024-12-28 10:00:00','2024-12-28 17:00:00',43.2470233,76.9477593,_binary '','Семинар \'Цифровая трансформация\'','+7 727 567 8901',15000,'ACTIVE'),(24,'Test City','Test Country','Test Street',NULL,'Test Venue','CONCERT',NULL,_binary '\0','Test Description','2026-01-01 01:01:00',NULL,41.6010877,-87.7127512,_binary '','Test Event','1234567890',100,'ACTIVE'),(25,'Test City','Test Country','Test Street',NULL,'Test Venue','CONCERT',NULL,_binary '\0','Test Description','2026-01-01 01:01:00',NULL,41.6010877,-87.7127512,_binary '','Test Event','1234567890',100,'ACTIVE'),(26,'1','1','1',NULL,'2','CONCERT',NULL,_binary '\0','1','2026-01-01 01:01:00',NULL,43.3141666,77.0566846,_binary '','1','1',25000,'ACTIVE');
/*!40000 ALTER TABLE `afisha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite_afishes`
--

DROP TABLE IF EXISTS `favorite_afishes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorite_afishes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `afisha_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqnhha2xo0ewoyay5a4s48tsi4` (`afisha_id`),
  KEY `FKh5g2fepvej6vpshcb1gehy7i` (`user_id`),
  CONSTRAINT `FKh5g2fepvej6vpshcb1gehy7i` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKqnhha2xo0ewoyay5a4s48tsi4` FOREIGN KEY (`afisha_id`) REFERENCES `afisha` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite_afishes`
--

LOCK TABLES `favorite_afishes` WRITE;
/*!40000 ALTER TABLE `favorite_afishes` DISABLE KEYS */;
INSERT INTO `favorite_afishes` VALUES (1,1,3),(2,4,3),(3,2,4),(4,8,4),(9,6,7),(10,10,8),(11,1,3),(12,4,3),(13,2,4),(14,8,4),(19,6,7),(20,10,8);
/*!40000 ALTER TABLE `favorite_afishes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite_places`
--

DROP TABLE IF EXISTS `favorite_places`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorite_places` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `place_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdhl0kxgbp5c8rxxuc5h7l2efw` (`place_id`),
  KEY `FKa6bgpdehwgcekarlnet9jgaf3` (`user_id`),
  CONSTRAINT `FKa6bgpdehwgcekarlnet9jgaf3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKdhl0kxgbp5c8rxxuc5h7l2efw` FOREIGN KEY (`place_id`) REFERENCES `place` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite_places`
--

LOCK TABLES `favorite_places` WRITE;
/*!40000 ALTER TABLE `favorite_places` DISABLE KEYS */;
INSERT INTO `favorite_places` VALUES (1,1,3),(2,2,3),(3,4,4),(4,7,4),(9,1,7),(10,9,8),(11,1,3),(12,2,3),(13,4,4),(14,7,4),(19,1,7),(20,9,8);
/*!40000 ALTER TABLE `favorite_places` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `images` (
  `id` bigint NOT NULL,
  `bytes` tinyblob,
  `content_type` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `original_file_name` varchar(255) DEFAULT NULL,
  `preview_image` bit(1) DEFAULT NULL,
  `size` bigint DEFAULT NULL,
  `afisha_id` bigint DEFAULT NULL,
  `place_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrlmg8ko8b3vnb60slvmjbueaf` (`afisha_id`),
  KEY `FKryxx4atyajie7667x1aw2eupk` (`place_id`),
  CONSTRAINT `FKrlmg8ko8b3vnb60slvmjbueaf` FOREIGN KEY (`afisha_id`) REFERENCES `afisha` (`id`),
  CONSTRAINT `FKryxx4atyajie7667x1aw2eupk` FOREIGN KEY (`place_id`) REFERENCES `place` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `count` int DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `afisha_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjpo8e56vydquoh06wn87mmoul` (`afisha_id`),
  KEY `FKel9kyl84ego2otj2accfd8mr7` (`user_id`),
  CONSTRAINT `FKel9kyl84ego2otj2accfd8mr7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKjpo8e56vydquoh06wn87mmoul` FOREIGN KEY (`afisha_id`) REFERENCES `afisha` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,2,'PURCHASED',1,3,NULL),(2,1,'PURCHASED',2,4,NULL),(4,1,'PURCHASED',4,3,NULL),(6,1,'PURCHASED',6,7,NULL),(7,3,'PURCHASED',7,8,NULL),(8,1,'PURCHASED',8,4,NULL),(11,2,'PURCHASED',1,3,NULL),(12,1,'PURCHASED',2,4,NULL),(14,1,'PURCHASED',4,3,NULL),(16,1,'PURCHASED',6,7,NULL),(17,3,'PURCHASED',7,8,NULL),(18,1,'PURCHASED',8,4,NULL),(21,NULL,'PURCHASED',1,2,NULL),(22,1,'PURCHASED',1,26,'2025-09-09 10:42:52.078467'),(23,1,'PURCHASED',1,26,'2025-09-09 10:42:55.643333'),(24,3,'PURCHASED',1,27,'2025-09-10 00:34:25.020480');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `place`
--

DROP TABLE IF EXISTS `place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `place` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `geo_processed` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `place_link` varchar(255) DEFAULT NULL,
  `price_from` int DEFAULT NULL,
  `price_to` int DEFAULT NULL,
  `social_network` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `street_name` varchar(255) DEFAULT NULL,
  `street_number` varchar(255) DEFAULT NULL,
  `published` bit(1) DEFAULT NULL,
  `capacity` int DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `place`
--

LOCK TABLES `place` WRITE;
/*!40000 ALTER TABLE `place` DISABLE KEYS */;
INSERT INTO `place` VALUES (1,'Алматы','Казахстан','ул. Абая 150','ENTERTAINMENT',_binary '\0','Крупнейший концертный зал Алматы',NULL,NULL,_binary '','Дворец Республики','+7 (727) 250-12-34',NULL,1000,5000,NULL,'ACTIVE','Абая','150',_binary '',NULL,NULL),(2,'Алматы','Казахстан','пр. Абая 110','ENTERTAINMENT',_binary '\0','Главный драматический театр Казахстана',NULL,NULL,_binary '','Театр им. Ауэзова','+7 (727) 272-40-77',NULL,500,2000,NULL,'ACTIVE','Абая','110',_binary '',NULL,NULL),(3,'Алматы','Казахстан','ул. Гоголя 1','PARKS',_binary '\0','Открытая площадка для массовых мероприятий',NULL,NULL,_binary '','Центральный парк культуры','+7 (727) 291-90-90',NULL,0,1000,NULL,'ACTIVE','Гоголя','1',_binary '',NULL,NULL),(4,'Алматы','Казахстан','Проспект Абая','CONCERT_HALL',_binary '\0','Современный многофункциональный комплекс для проведения концертов и спортивных мероприятий. Вместимость до 15,000 человек.',43.2339062,76.8720106,_binary '','Алматы Арена','+7 727 123 4567','https://maps.google.com/?q=Алматы+Арена',5000,50000,'@almaty_arena','ACTIVE','Проспект Абая','44',_binary '',NULL,NULL),(5,'Алматы','Казахстан','Улица Кабанбай батыра','CONCERT_HALL',_binary '\0','Историческое здание с отличной акустикой. Идеально для классической музыки и камерных концертов.',43.2482885,76.9263527,_binary '','Казахская государственная филармония','+7 727 234 5678','https://maps.google.com/?q=Казахская+государственная+филармония',2000,15000,'@kazphilharmonic','ACTIVE','Улица Кабанбай батыра','110',_binary '',NULL,NULL),(6,'Алматы','Казахстан','Проспект Достык','CONCERT_HALL',_binary '\0','Престижный концертный зал в центре города. Отличная акустика и элегантный интерьер.',43.2286716,76.9607095,_binary '','Дворец Республики','+7 727 345 6789','https://maps.google.com/?q=Дворец+Республики+Алматы',3000,25000,'@palace_republic','ACTIVE','Проспект Достык','56',_binary '',NULL,NULL),(7,'Алматы','Казахстан','Улица Кабанбай батыра','THEATER_HALL',_binary '\0','Главный театр оперы и балета Казахстана. Великолепная архитектура и профессиональная труппа.',43.2482885,76.9263527,_binary '','Казахский театр оперы и балета','+7 727 456 7890','https://maps.google.com/?q=Казахский+театр+оперы+и+балета',3000,20000,'@kazopera','ACTIVE','Улица Кабанбай батыра','110',_binary '',NULL,NULL),(8,'Алматы','Казахстан','Проспект Абая','THEATER_HALL',_binary '\0','Один из старейших театров Алматы. Классические и современные постановки.',43.2339062,76.8720106,_binary '','Русский театр драмы имени Лермонтова','+7 727 567 8901','https://maps.google.com/?q=Русский+театр+драмы+Лермонтова',1500,8000,'@lermontov_theater','ACTIVE','Проспект Абая','43',_binary '',NULL,NULL),(9,'Алматы','Казахстан','Улица Абая','THEATER_HALL',_binary '\0','Национальный театр драмы с богатой историей. Современные и классические постановки на казахском языке.',43.2339062,76.8720106,_binary '','Казахский театр драмы имени М. Ауэзова','+7 727 678 9012','https://maps.google.com/?q=Казахский+театр+драмы+Ауэзова',2000,10000,'@auzov_theater','ACTIVE','Улица Абая','43',_binary '',NULL,NULL),(10,'Алматы','Казахстан','Улица Сатпаева','CINEMA',_binary '\0','Современный кинотеатр с 8 залами, IMAX и 4DX технологиями. Попкорн и напитки включены.',43.236287,76.9161293,_binary '','Кинотеатр \'Арман\'','+7 727 789 0123','https://maps.google.com/?q=Кинотеатр+Арман',1000,5000,'@arman_cinema','ACTIVE','Улица Сатпаева','90/22',_binary '',NULL,NULL),(11,'Алматы','Казахстан','Улица Гоголя','PARK',_binary '\0','Большой парк в центре города. Идеально для массовых мероприятий, фестивалей и спортивных событий.',43.2590769,76.9332418,_binary '','Центральный парк культуры и отдыха','+7 727 890 1234','https://maps.google.com/?q=Центральный+парк+Алматы',500,3000,'@central_park_almaty','ACTIVE','Улица Гоголя','1',_binary '',NULL,NULL),(12,'Алматы','Казахстан','Проспект Назарбаева','CONFERENCE_HALL',_binary '\0','Современный конференц-зал с полным техническим оснащением. Подходит для семинаров, конференций и корпоративных мероприятий.',43.2470233,76.9477593,_binary '','Конференц-зал \'Астана\'','+7 727 901 2345','https://maps.google.com/?q=Конференц+зал+Астана',10000,50000,'@astana_conference','ACTIVE','Проспект Назарбаева','56',_binary '',NULL,NULL),(13,'Алматы','Казахстан','Проспект Абая','CONCERT_HALL',_binary '\0','Современный многофункциональный комплекс для проведения концертов и спортивных мероприятий. Вместимость до 15,000 человек.',43.2339062,76.8720106,_binary '','Алматы Арена','+7 727 123 4567','https://maps.google.com/?q=Алматы+Арена',5000,50000,'@almaty_arena','ACTIVE','Проспект Абая','44',_binary '',NULL,NULL),(14,'Алматы','Казахстан','Улица Кабанбай батыра','CONCERT_HALL',_binary '\0','Историческое здание с отличной акустикой. Идеально для классической музыки и камерных концертов.',43.2482885,76.9263527,_binary '','Казахская государственная филармония','+7 727 234 5678','https://maps.google.com/?q=Казахская+государственная+филармония',2000,15000,'@kazphilharmonic','ACTIVE','Улица Кабанбай батыра','110',_binary '',NULL,NULL),(15,'Алматы','Казахстан','Проспект Достык','CONCERT_HALL',_binary '\0','Престижный концертный зал в центре города. Отличная акустика и элегантный интерьер.',43.2286716,76.9607095,_binary '','Дворец Республики','+7 727 345 6789','https://maps.google.com/?q=Дворец+Республики+Алматы',3000,25000,'@palace_republic','ACTIVE','Проспект Достык','56',_binary '',NULL,NULL),(16,'Алматы','Казахстан','Улица Кабанбай батыра','THEATER_HALL',_binary '\0','Главный театр оперы и балета Казахстана. Великолепная архитектура и профессиональная труппа.',43.2482885,76.9263527,_binary '','Казахский театр оперы и балета','+7 727 456 7890','https://maps.google.com/?q=Казахский+театр+оперы+и+балета',3000,20000,'@kazopera','ACTIVE','Улица Кабанбай батыра','110',_binary '',NULL,NULL),(17,'Алматы','Казахстан','Проспект Абая','THEATER_HALL',_binary '\0','Один из старейших театров Алматы. Классические и современные постановки.',43.2339062,76.8720106,_binary '','Русский театр драмы имени Лермонтова','+7 727 567 8901','https://maps.google.com/?q=Русский+театр+драмы+Лермонтова',1500,8000,'@lermontov_theater','ACTIVE','Проспект Абая','43',_binary '',NULL,NULL),(18,'Алматы','Казахстан','Улица Абая','THEATER_HALL',_binary '\0','Национальный театр драмы с богатой историей. Современные и классические постановки на казахском языке.',43.2339062,76.8720106,_binary '','Казахский театр драмы имени М. Ауэзова','+7 727 678 9012','https://maps.google.com/?q=Казахский+театр+драмы+Ауэзова',2000,10000,'@auzov_theater','ACTIVE','Улица Абая','43',_binary '',NULL,NULL),(19,'Алматы','Казахстан','Улица Сатпаева','CINEMA',_binary '\0','Современный кинотеатр с 8 залами, IMAX и 4DX технологиями. Попкорн и напитки включены.',43.236287,76.9161293,_binary '','Кинотеатр \'Арман\'','+7 727 789 0123','https://maps.google.com/?q=Кинотеатр+Арман',1000,5000,'@arman_cinema','ACTIVE','Улица Сатпаева','90/22',_binary '',NULL,NULL),(20,'Алматы','Казахстан','Улица Гоголя','PARK',_binary '\0','Большой парк в центре города. Идеально для массовых мероприятий, фестивалей и спортивных событий.',43.2590769,76.9332418,_binary '','Центральный парк культуры и отдыха','+7 727 890 1234','https://maps.google.com/?q=Центральный+парк+Алматы',500,3000,'@central_park_almaty','ACTIVE','Улица Гоголя','1',_binary '',NULL,NULL),(21,'Алматы','Казахстан','Проспект Назарбаева','CONFERENCE_HALL',_binary '\0','Современный конференц-зал с полным техническим оснащением. Подходит для семинаров, конференций и корпоративных мероприятий.',43.2470233,76.9477593,_binary '','Конференц-зал \'Астана\'','+7 727 901 2345','https://maps.google.com/?q=Конференц+зал+Астана',10000,50000,'@astana_conference','ACTIVE','Проспект Назарбаева','56',_binary '',NULL,NULL);
/*!40000 ALTER TABLE `place` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cash` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'5000.0','newuser@example.com','Новый','Пользователь','$2a$10$.R2kba1mlPZKcFshOT5yp.78Ft6XoCPBiFElPphjVpyxCwp6YKwtq','USER',NULL),(3,NULL,'testuser@example.com','Test','User','$2a$10$B7zia.y7m3RgnDxYPPrl/umsRXMLAWPDpKpqtUWbvwlGrfbJ2F0aO','USER','testuser'),(4,NULL,'testuser2@example.com','Test','User2','$2a$10$SwCo1zm1zefqnDH3Ul4tH.eNCE1YyjK3lniTlZ33h8s9YjqJEgzYi','USER','testuser2'),(7,'50000','manager@example.com','Менеджер','Событий','$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi','MANAGER','manager'),(8,'25000','aidar@example.com','Айдар','Куаныш','$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi','USER','aidar'),(9,'30000','aizhan@example.com','Айжан','Нурланова','$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi','USER','aizhan'),(10,'20000','daniyar@example.com','Данияр','Ахметов','$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi','USER','daniyar'),(11,'35000','zhanar@example.com','Жанар','Серик','$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi','USER','zhanar'),(12,'28000','nurlan@example.com','Нурлан','Темирбеков','$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi','USER','nurlan'),(13,'32000','asel@example.com','Асель','Касымова','$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi','USER','asel'),(15,'50000','manager@example.com','Менеджер','Событий','$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi','MANAGER','manager'),(16,'25000','aidar@example.com','Айдар','Куаныш','$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi','USER','aidar'),(17,'30000','aizhan@example.com','Айжан','Нурланова','$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi','USER','aizhan'),(18,'20000','daniyar@example.com','Данияр','Ахметов','$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi','USER','daniyar'),(19,'35000','zhanar@example.com','Жанар','Серик','$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi','USER','zhanar'),(20,'28000','nurlan@example.com','Нурлан','Темирбеков','$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi','USER','nurlan'),(21,'34000','asel@example.com','Асель','Касымова',NULL,'USER',NULL),(23,NULL,'admin@example.com','Админ','Системы','$2a$10$4zrfvNfmbtakyADxb92A6.yqtuEcQsJITjTf0gpg85n/3p1i0fbuq','USER',NULL),(24,'100000','s.muratkhan@aues.kz','Шынгыс','Муратхан',NULL,'USER',NULL),(26,'0.0','test@test.com','Test','User','$2a$10$JSLQ9w3uKnnrR5MUNP5v.Oemiyk/fOB3MIHUvP6h2vjnrVLQQfBxS','USER',NULL),(27,'5000.0','test@example.com','Тест','Пользователь','$2a$10$XonLKsVJM36V1ecYRuN7XOOhnRI0qjnCI.LWF4M/rIK94axmUjYz.','USER','test@example.com');
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

-- Dump completed on 2025-09-10  0:35:25
