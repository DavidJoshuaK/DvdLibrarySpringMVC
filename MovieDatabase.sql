CREATE DATABASE IF NOT EXISTS MovieDatabase;

USE MovieDatabase;

CREATE  TABLE IF NOT EXISTS `dvd` (
 `dvdId` int(11) NOT NULL AUTO_INCREMENT,
 `title` varchar(50) NOT NULL,
  `releaseYear` int(4) NOT NULL,
 `director` varchar(50) NOT NULL,
  `rating` varchar(10) DEFAULT NULL,
 `notes` text(250) NOT NULL,
 PRIMARY KEY (`dvdId`)
 ) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=23;


