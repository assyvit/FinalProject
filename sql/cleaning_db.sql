-- ------------------------------------------------------
CREATE SCHEMA `cleaning_db` ;

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `role_id` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name_UNIQUE` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` VALUES (1,'Admin'),(2,'Cleaner'),(3,'Client');

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `users_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `fk_user_role` tinyint(3) unsigned NOT NULL,
  `active_status` tinyint(1) NOT NULL,
  `avatar_path` mediumblob,
  PRIMARY KEY (`users_id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `fk_user_role_name_idx` (`fk_user_role`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`fk_user_role`) REFERENCES `roles` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--


INSERT INTO `users` VALUES (1,'admin@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',1,1,NULL),(2,'cleaner1@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',2,1,NULL),(3,'client1@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,1,NULL),(4,'client2@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,1,NULL),(5,'client3@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,0,NULL),(6,'client13@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,1,NULL),(7,'cleaner2@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',2,1,NULL),(8,'cleaner3@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',2,1,NULL),(9,'cleaner4@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',2,0,NULL),(10,'cleaner5@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',2,1,NULL),(11,'client5@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,1,NULL),(26,'client4@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,1,NULL),(33,'client6@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,1,NULL),(35,'client7@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,1,NULL),(36,'client8@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,1,NULL),(37,'client14@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,1,NULL),(38,'client9@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,1,NULL),(56,'client10@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,0,NULL),(57,'client11@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,0,NULL),(58,'client12@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,1,NULL),(59,'client15@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,1,NULL),(60,'client16@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,1,NULL),(62,'cleaner6@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',2,1,NULL),(63,'cleaner7@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',2,1,NULL),(64,'cleaner8@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',2,1,NULL),(65,'cleaner9@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',2,1,NULL),(66,'cleaner10@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',2,1,NULL),(67,'client17@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,1,NULL),(68,'client19@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,0,NULL),(69,'client18@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,1,NULL),(70,'client20@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,0,NULL),(71,'client21@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,1,NULL),(72,'cleaner11@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',2,0,NULL),(73,'client22@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,0,NULL),(74,'client23@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,1,NULL),(75,'client24@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,1,NULL),(76,'cleaner12@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',2,1,NULL),(77,'cleaner13@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',2,0,NULL),(78,'cleaner14@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',2,1,NULL),(79,'cleaner45@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,1,NULL),(80,'client996@gmail.com','e84ceafb2fadee8f4290972263face2f1d7887b9',3,1,NULL);

--
-- Table structure for table `cleaners`
--

CREATE TABLE `cleaners` (
  `cleaner_id` int(10) unsigned NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `telephone_number` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  PRIMARY KEY (`cleaner_id`),
  KEY `fk_cleaner_id_idx` (`cleaner_id`),
  CONSTRAINT `cleaner_ids` FOREIGN KEY (`cleaner_id`) REFERENCES `users` (`users_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cleaners`
--

INSERT INTO `cleaners` VALUES (2,'Юлия','Малышева','375258524589','Пушкина 15-79'),(7,'Антон','Смирнов','375251478523','Никифорова 36-154'),(8,'Сергей','Шпак','375259876543','Столетова 12-9'),(9,'Анна','Баранова','375333692587','Гикало 3-1'),(10,'Надежда','Свидрицкая','375254566541','Пулихова 18-8'),(62,'Антонина','Петрова','375444478521','Воронянского 15-74'),(63,'Тамара','Устинова','375257741596','Немига 15-9'),(64,'Татьяна','Паламарчук','375447418529','Стариновская 17-96'),(65,'Леокадия','Нестерович','375339968524','Руссиянова 14-85'),(66,'Ольга','Акимова','375258527481','Широкая 15-85'),(72,'Анастасия','Витковская','375258527485','Куйбышева 15-9'),(76,'Марк','Соловьев','375257418526','Калиновского 179-9'),(77,'Виктория','Каплан','375298741589','Славинского 8-15'),(78,'Маринна','Харлап','375296698574','Карбышева 7-96');

--
-- Table structure for table `clients`
--

CREATE TABLE `clients` (
  `client_id` int(10) unsigned NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `telephone_number` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  PRIMARY KEY (`client_id`),
  KEY `fk_client_id_idx` (`client_id`),
  CONSTRAINT `fk_client_id` FOREIGN KEY (`client_id`) REFERENCES `users` (`users_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `clients`
--

INSERT INTO `clients` VALUES (3,'Дмитрий','Иванов','375296764447','Свердлова 26-9'),(4,'Даниил','Каверин','375293268974','Козлова 19-3'),(5,'Светлана','Козловская','375333267485','Фроликова 8-3'),(6,'Юлия','Бандик','375297894561','Нововиленская 2-98'),(11,'Алена','Караневич','375441234567','Тимирязева 123-1'),(26,'Валентин','Сидоренко','375337415896','Киселёва 25-74'),(33,'Виталий','Куликовский','375257418932','Долгобродская17-96'),(35,'Алина','Брановицкая','37544785963','Харьковская 47-96'),(36,'Станислав','Волынец','375297897418','Кнорина 18-89'),(37,'Виктория','Белоус','375297896541','Кижеватого 36-74'),(38,'Алексей','Дедов','375297896548','Колесникова 74-96'),(56,'Максим','Просолов','375441234567','Широкая 45-15'),(57,'Сергей','Агеев','375447893241','Солнечная 18-89'),(58,'Василий','Байдак','375334415892','Притыцкого 56-96'),(59,'Артем','Красовский','375297418526','Карбышева 18-89'),(60,'Дарья','Ботяновская','375297896541','Асаналиева 54-78'),(67,'Анастасия','Дубинская','375446521422','Красная 13-6'),(68,'Ирина','Власова','375336521499','В.Хоружей 25-9'),(69,'Нина','Иващенко','375336321485','Первомайская 15-9'),(70,'Анастасия','Шпилевская','375296418521','Кропоткина, 44-5'),(71,'Татьяна','Дьяченко','375254569631','Гинтовта 54-9'),(73,'Валерий','Волынец','375254418596','Чечето 7-89'),(74,'Елена','Продовикова','375254417896','Матусевича 85-96'),(75,'Оксана','Петкевич','375441596325','Лещинского 53-74'),(79,'Анна','Петрова','Толстого 8-10','Толстого 8-10'),(80,'Ольга','Кравцова','Купрянова 15-6','Купрянова 15-6');

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_sum` decimal(10,2) unsigned NOT NULL,
  `order_date_incoming` timestamp NOT NULL,
  `order_date_execute` timestamp NOT NULL,
  `order_status` varchar(45) NOT NULL,
  `order_payment_type` varchar(45) NOT NULL,
  `order_payment_fulfilled` tinyint(1) NOT NULL,
  `order_comment` varchar(500) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` VALUES (1,150.50,'2020-01-30 21:00:00','2020-02-02 09:00:00','cancelled','cash',1,'call back'),(2,30.50,'2020-01-31 16:22:39','2020-02-02 12:30:00','new','card',0,'call back'),(3,150.50,'2020-01-31 17:00:00','2020-02-02 07:15:00','new','cash',0,'call back'),(4,100.00,'2020-01-31 18:30:00','2020-02-01 15:00:00','new','cash',0,'call back'),(11,30.50,'2020-02-01 12:05:13','2020-02-02 12:30:00','fulfilled','cash',1,'call back'),(12,30.50,'2020-02-01 14:09:47','2020-02-02 12:30:00','new','cash',0,'call back'),(13,30.50,'2020-02-01 15:06:34','2020-02-02 12:30:00','new','cash',0,'call back'),(14,79.00,'2020-02-20 18:19:18','2020-02-24 06:35:00','new','bank_transfer',0,'don\'t be late'),(19,29.00,'2020-02-20 23:16:16','2020-02-23 10:35:00','fulfilled','card',1,'commnet'),(20,29.00,'2020-02-20 23:16:16','2020-02-23 10:35:00','fulfilled','card',0,'commnet'),(21,29.00,'2020-02-20 23:19:49','2020-02-27 11:35:00','cancelled','bank_transfer',0,'Clean good'),(22,29.00,'2020-02-20 23:19:49','2020-02-27 11:35:00','fulfilled','bank_transfer',1,'Clean good'),(23,29.00,'2020-02-20 23:45:54','2020-02-22 07:35:00','fulfilled','card',1,'comment'),(24,29.00,'2020-02-20 23:45:54','2020-02-22 07:35:00','cancelled','card',0,'comment'),(25,29.00,'2020-02-20 23:48:38','2020-02-25 10:25:00','fulfilled','card',0,'comment'),(26,29.00,'2020-02-20 23:48:38','2020-02-25 10:25:00','fulfilled','card',1,'comment'),(27,29.00,'2020-02-20 23:55:31','2020-02-26 04:50:00','new','card',0,'comment'),(28,14.00,'2020-02-21 00:12:28','2020-02-21 04:00:00','cancelled','cash',0,'comment'),(29,10.00,'2020-02-21 07:38:55','2020-02-23 10:50:00','new','card',0,'comment'),(30,10.00,'2020-02-21 07:38:58','2020-02-23 10:50:00','new','card',0,'comment'),(31,10.00,'2020-02-21 07:39:49','2020-02-23 10:50:00','new','card',0,'comment'),(32,29.00,'2020-02-21 07:47:19','2020-02-22 10:00:00','new','card',0,'comment'),(33,50.00,'2020-02-21 07:47:58','2020-02-22 10:00:00','new','card',0,'comment'),(34,50.00,'2020-02-21 07:52:07','2020-02-21 21:00:00','new','card',0,'comment'),(35,50.00,'2020-02-21 07:52:20','2020-02-21 21:00:00','new','card',0,'comment'),(36,14.00,'2020-02-21 08:27:46','2020-02-22 12:00:00','fulfilled','card',1,'comment'),(37,45.00,'2020-02-21 08:27:51','2020-02-22 12:00:00','processed','card',0,'comment'),(38,45.00,'2020-02-21 08:28:28','2020-02-21 04:00:00','new','card',0,'comment'),(39,15.00,'2020-02-21 08:31:33','2020-02-21 10:50:00','cancelled','cash',0,'comment'),(40,50.00,'2020-02-21 08:32:14','2020-02-21 04:00:00','cancelled','card',0,'comment'),(41,29.00,'2020-02-21 12:02:26','2020-02-21 04:00:00','new','cash',0,'comment'),(42,50.00,'2020-02-21 12:02:47','2020-02-21 04:00:00','new','cash',0,'comment'),(43,14.00,'2020-02-21 12:06:13','2020-02-21 04:00:00','new','card',0,'comment'),(44,30.50,'2020-02-22 12:35:03','2020-02-24 12:30:00','new','card',0,'comment'),(45,30.50,'2020-02-22 12:45:39','2020-02-24 12:30:00','new','card',0,'comment'),(46,30.50,'2020-02-22 12:47:06','2020-02-24 12:30:00','new','card',0,'comment'),(47,30.50,'2020-02-22 18:05:48','2020-02-24 12:30:00','new','card',0,'comment'),(48,30.50,'2020-02-22 18:06:54','2020-02-24 12:30:00','new','card',0,'comment'),(49,30.50,'2020-02-22 18:08:29','2020-02-24 12:30:00','new','card',0,'comment'),(50,30.50,'2020-02-22 18:09:10','2020-02-24 12:30:00','new','card',0,'comment'),(51,30.50,'2020-02-22 18:09:53','2020-02-24 12:30:00','new','card',0,'comment'),(52,30.50,'2020-02-22 18:14:35','2020-02-24 12:30:00','new','card',0,'comment'),(53,15.00,'2020-02-23 21:04:54','2020-02-23 10:50:00','cancelled','card',0,'comment'),(54,30.50,'2020-02-24 14:51:37','2020-02-24 12:30:00','new','card',0,'comment'),(55,30.50,'2020-02-24 14:52:58','2020-02-24 12:30:00','new','card',0,'comment'),(56,15.00,'2020-02-25 10:05:29','2020-02-26 10:50:00','new','card',0,'comment'),(57,55.50,'2020-02-25 10:05:29','2020-02-26 10:50:00','new','card',0,'comment'),(58,150.00,'2020-02-25 10:20:13','2020-02-27 10:35:00','cancelled','card',0,'Comment'),(59,52.50,'2020-02-25 10:20:13','2020-02-27 10:35:00','cancelled','card',0,'Comment'),(60,45.00,'2020-02-25 12:09:00','2020-02-26 09:35:00','cancelled','cash',0,'comment'),(61,60.00,'2020-02-25 12:09:00','2020-02-26 09:35:00','new','cash',0,'comment'),(62,45.00,'2020-02-25 12:09:44','2020-02-26 09:35:00','cancelled','cash',0,'comment'),(63,60.00,'2020-02-25 12:09:44','2020-02-26 09:35:00','new','cash',0,'comment'),(64,120.00,'2020-02-25 12:51:00','2020-02-27 10:50:00','new','cash',0,'comment for the order'),(67,15.00,'2020-02-27 23:54:42','2020-02-28 14:00:00','fulfilled','cash',1,'Comment'),(68,215.00,'2020-02-28 11:31:27','2020-02-29 15:00:00','new','cash',0,'Comment'),(69,45.40,'2020-03-02 08:40:01','2020-03-02 04:00:00','new','card',0,'Comment'),(70,123.50,'2020-03-02 08:40:01','2020-03-02 04:00:00','new','card',0,'Comment'),(71,90.00,'2020-03-02 09:01:11','2020-03-17 14:55:00','new','cash',0,'comment'),(72,45.00,'2020-03-02 23:07:38','2020-03-11 11:25:00','new','card',0,'Комментарий'),(73,23.00,'2020-03-02 23:07:38','2020-03-11 11:25:00','cancelled','card',0,'Комментарий'),(74,55.50,'2020-03-02 23:07:39','2020-03-11 11:25:00','new','card',0,'Комментарий'),(75,45.00,'2020-03-02 23:12:08','2020-03-28 06:30:00','new','card',0,'Комментарий'),(76,15.00,'2020-03-02 23:12:09','2020-03-28 06:30:00','new','card',0,'Комментарий'),(77,15.00,'2020-03-02 23:12:09','2020-03-28 06:30:00','new','card',0,'Комментарий');

--
-- Table structure for table `cleaning`
--

CREATE TABLE `cleaning` (
  `cleaning_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cleaning_name` varchar(45) NOT NULL,
  `price_per_unit` decimal(10,2) unsigned NOT NULL,
  `cleaning_type` varchar(45) NOT NULL,
  `cleaning_description` text NOT NULL,
  `cleaning_quantity` tinyint(3) unsigned NOT NULL,
  `available_status` tinyint(1) NOT NULL,
  `fk_cleaner_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`cleaning_id`),
  KEY `cleaner_id_idx` (`fk_cleaner_id`),
  CONSTRAINT `cleaners_id` FOREIGN KEY (`fk_cleaner_id`) REFERENCES `cleaners` (`cleaner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cleaning`
--

INSERT INTO `cleaning` VALUES (1,'Уборка  комнаты',45.00,'cleaning','Мойка пола, протирка всех горизонтальных поверхностей, сухая уборка ковра (при наличии), уборка пыли',1,1,7),(2,'Уборка кухни',65.00,'cleaning','Вся техника моется внутри и снаружи. Протрем всю мебель, наведем порядок, выкинем мусор и заменим мусорные мешки',1,1,7),(3,'Уборка ванной ',50.00,'cleaning','В уборку входит мытье стен и полов, плинтусов, ручек и дверей, зеркал, открытых полок и поверхностей',1,1,7),(4,'Уборка балкона',20.00,'cleaning','Мойка окон, рам, подоконников',1,1,7),(5,'Мойка окон',15.00,'washing','Мойка окон, рам, подоконников',1,1,8),(6,'Мойка окон',17.00,'washing','Мойка окон, рам, подоконников',1,1,9),(7,'Уборка коридора',40.00,'cleaning','Мойка пола, протирка всех горизонтальных поверхностей, мойка шкафа, тумбы, полки',1,1,7),(8,'Мойка окон',22.00,'washing','Мойка окон, рам, подоконников',1,1,7),(9,'Мойка окон',23.00,'washing','Мойка окон, рам, подоконников',1,1,8),(10,'Химчистка матраса ',20.00,'mattress_cleaning','Химчистка матраса с двух сторон',1,0,7),(11,'Уборка  комнаты',55.50,'cleaning','Мойка пола, протирка всех горизонтальных поверхностей, сухая уборка ковра (при наличии), уборка пыли',1,1,9),(12,'Уборка  комнаты',60.00,'cleaning','Мойка пола, протирка всех горизонтальных поверхностей, сухая уборка ковра (при наличии), уборка пыли',1,0,10),(13,'Уборка  комнаты',60.00,'cleaning','Мойка пола, протирка всех горизонтальных поверхностей, сухая уборка ковра (при наличии), уборка пыли',1,1,8),(14,'Уборка после пожара',500.00,'cleaning','Чтобы убрать квартиру после пожара, мы используем: аппараты высокого давления; пылеводососы; парогенераторы ; специальные чистящие и моющие средства и прочий уборочный инвентарь.',1,1,10),(15,'Уборка ванной ',52.50,'cleaning','В уборку входит мытье стен и полов, плинтусов, ручек и дверей, зеркал, открытых полок и поверхностей',1,1,9),(24,'Уборка балкона',19.50,'cleaning','Мойка окон, рам, подоконников',1,1,10),(25,'Мойка окон',15.00,'washing','Мойка окон, рам, подоконников',1,1,10),(26,'Поддерживающая уборка квартиры ',100.00,'regular_cleaning','Поддерживающая уборка',1,0,10),(27,'Уборка после смерти',10.00,'cleaning','сбор и вынос мусора, сортировка документов и ценных вещей (при необходимости); тщательное очищение всех поверхностей (корпусная и мягкая мебель, оконные конструкции, двери, напольные покрытия) от пыли, въевшихся пятен, различного рода загрязнений; дезинфекция квартиры после смерти; дезодорация помещений.',1,1,8),(28,'Дезинфекция квартиры',150.00,'other',' мойка пола и стен, обработка посуды и предметов домашнего обихода с помощью специальных препаратов и генератора тумана',1,1,10),(29,'Химчистка матраса ',15.00,'mattress_cleaning','Химчистка матраса с двух сторон',1,1,8),(30,'Химчистка кожаной мебели',50.00,'leather_cleaning','На поверхность мебели наносится специальный шампунь. Этот состав содержит в себе моющее средство, которое раскрывает поры кожи и деликатно удаляет загрязнения, а также восстанавливающую пасту, которая смягчает кожу и придает ей блеск',1,1,10),(31,'Химчистка ковра',10.00,'carpet_cleaning','Химчистка ковра',1,1,10),(32,'Химчистка ковровых покрытий',45.40,'carpet_cleaning',' выполняется сухая чистка с применением промышленного пылесоса для удаления поверхностной пыли и крупного мусора',1,1,8),(33,'Уборка после ремонта',500.00,'cleaning','Сбор мусора и чистка поверхностей от остатков отделочных материалов',1,1,10),(37,'Химчистка штор ',40.00,'curtain_cleaning','Химчистка штор на дому парогенератором',1,1,7),(38,'Глажка',10.00,'other','Гладим вещи',1,1,7),(39,'Мойка микроволновки',8.00,'other','Мойка микроволновки внутри и снаружи',1,1,7),(40,'Поверхностная уборка кухни',45.00,'cleaning','Поверхностная уборка кухни',1,1,10),(41,'Уборка после ремонта',350.00,'cleaning','будет убрана вся квартира, все открытые поверхности, мебель, сан.узлы, вынесен мелкогабаритный мусор',1,1,7),(42,'Уборка квартиры',150.00,'cleaning','Уборка квартиры: кухня, ванная,',1,1,7),(44,'Уборка квартиры',120.00,'cleaning','Уборка квартиры: кухня, ванная,',1,1,8),(45,'Уборка квартиры',150.00,'cleaning','Уборка квартиры: кухня, ванная,',1,1,9),(46,'Уборка квартиры',125.00,'cleaning','Уборка квартиры: кухня, ванная,',1,1,10),(47,'Уборка после ремонта',400.00,'cleaning','будет убрана вся квартира, все открытые поверхности, мебель, сан.узлы, вынесен мелкогабаритный мусор',1,1,10),(48,'Уборка после ремонта',410.00,'cleaning','будет убрана вся квартира, все открытые поверхности, мебель, сан.узлы, вынесен мелкогабаритный мусор',1,1,9),(49,'Уборка после ремонта',425.00,'cleaning','будет убрана вся квартира, все открытые поверхности, мебель, сан.узлы, вынесен мелкогабаритный мусор',1,1,8);

--
-- Table structure for table `cleaner_orders`
--

CREATE TABLE `cleaner_orders` (
  `cleaner_id` int(10) unsigned NOT NULL,
  `cleaner_order_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`cleaner_order_id`),
  KEY `cleaner_id_idx` (`cleaner_id`),
  KEY `cleaner_order_id_idx` (`cleaner_order_id`),
  CONSTRAINT `cleaner_id` FOREIGN KEY (`cleaner_id`) REFERENCES `cleaners` (`cleaner_id`),
  CONSTRAINT `cleaner_order_id` FOREIGN KEY (`cleaner_order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cleaner_orders`
--

INSERT INTO `cleaner_orders` VALUES (2,13),(7,1),(7,11),(7,19),(7,20),(7,21),(7,22),(7,23),(7,24),(7,25),(7,26),(7,27),(7,28),(7,32),(7,34),(7,36),(7,39),(7,41),(7,43),(7,44),(7,45),(7,46),(7,47),(7,48),(7,49),(7,50),(7,51),(7,52),(7,53),(7,54),(7,55),(7,56),(7,58),(7,60),(7,62),(7,64),(7,67),(7,68),(7,71),(7,72),(7,75),(8,2),(8,29),(8,61),(8,63),(8,69),(8,73),(8,76),(9,3),(9,57),(9,59),(9,70),(9,74),(10,4),(10,77),(66,12);

--
-- Table structure for table `cleaning_in_order`
--

CREATE TABLE `cleaning_in_order` (
  `cleaning_id` int(10) unsigned NOT NULL,
  `orders_id` int(10) unsigned NOT NULL,
  `items_in_order` int(10) unsigned NOT NULL,
  KEY `cleaning_id_idx` (`cleaning_id`),
  KEY `orders_id_idx` (`orders_id`),
  CONSTRAINT `cleaning_id` FOREIGN KEY (`cleaning_id`) REFERENCES `cleaning` (`cleaning_id`),
  CONSTRAINT `orders_id` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cleaning_in_order`
--

INSERT INTO `cleaning_in_order` VALUES (3,1,1),(1,1,1),(1,1,1),(10,1,1),(9,2,1),(5,2,1),(9,2,1),(15,3,1),(6,3,1),(12,4,1),(14,4,1),(15,11,1),(15,11,1),(15,11,1),(15,11,1),(1,19,1),(2,19,1),(1,20,1),(2,20,1),(1,21,1),(2,21,1),(1,22,1),(2,22,1),(1,23,1),(2,23,1),(1,24,1),(2,24,1),(1,25,1),(2,25,1),(1,26,1),(2,26,1),(1,27,1),(2,27,1),(1,28,1),(5,29,1),(1,32,1),(2,32,1),(3,34,1),(1,36,1),(2,39,1),(1,41,1),(2,41,1),(1,43,1),(1,44,1),(2,44,2),(1,45,1),(2,45,2),(1,46,1),(2,46,2),(1,47,1),(2,47,2),(1,48,1),(2,48,2),(1,49,1),(2,49,2),(1,50,1),(2,50,2),(1,51,1),(2,51,2),(1,52,1),(2,52,2),(2,53,1),(1,54,1),(2,54,2),(1,55,1),(2,55,2),(2,56,1),(11,57,1),(3,58,1),(10,58,2),(15,59,1),(2,60,3),(13,61,1),(2,62,3),(13,63,1),(2,64,1),(3,64,1),(4,64,1),(2,67,1),(2,68,1),(3,68,4),(32,69,1),(6,70,4),(11,70,1),(3,71,1),(7,71,1),(1,72,1),(9,73,1),(11,74,1),(1,75,1),(5,76,1),(25,77,1);

--
-- Table structure for table `client_orders`
--

CREATE TABLE `client_orders` (
  `client_id` int(10) unsigned NOT NULL,
  `order_id` int(10) unsigned NOT NULL,
  `quantity_in_order` int(10) unsigned NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `client_id_idx` (`client_id`),
  KEY `order_id_idx` (`order_id`),
  CONSTRAINT `client_id` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`),
  CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `client_orders`
--

INSERT INTO `client_orders` VALUES (3,1,1),(4,2,1),(11,3,1),(3,4,1),(3,11,1),(11,12,2),(3,13,3),(3,19,2),(4,20,2),(3,21,2),(4,22,2),(4,23,2),(3,24,2),(3,25,2),(3,26,2),(3,27,2),(3,28,1),(3,29,1),(3,30,0),(3,31,0),(3,32,2),(3,33,0),(4,34,1),(4,35,0),(4,36,1),(4,37,0),(4,38,0),(4,39,1),(4,40,0),(3,41,2),(3,42,0),(3,43,1),(4,44,2),(3,45,2),(4,46,2),(3,47,2),(4,48,2),(3,49,2),(4,50,2),(3,51,2),(3,52,2),(4,53,1),(3,54,2),(4,55,2),(3,56,1),(4,57,1),(3,58,2),(3,59,1),(3,60,1),(3,61,1),(3,62,1),(3,63,1),(3,64,3),(3,67,1),(3,68,2),(3,69,1),(3,70,2),(3,71,2),(38,72,1),(38,73,1),(38,74,1),(38,75,1),(38,76,1),(38,77,1);

