drop table if exists  `tbl_schweine`;
CREATE TABLE `tbl_schweine` (
  `id` varchar(36) NOT NULL,
  `gewicht` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `version` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;