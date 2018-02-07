DROP DATABASE IF EXISTS `springdemodb`;
CREATE DATABASE `springdemodb`;

-- aanmaken
CREATE USER 'demo_user'@'localhost' IDENTIFIED BY 'secret';

-- geef in een keer alle rechten - soort administrator!
GRANT ALL ON `springdemodb`.* TO 'demo_user'@'localhost';