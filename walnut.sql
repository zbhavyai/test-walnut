-- database schema deletion
DROP DATABASE IF EXISTS walnutdb;

-- user deletion
DROP USER IF EXISTS 'walnutapp'@'%';

-- schema creation
CREATE DATABASE IF NOT EXISTS walnutdb;

-- user creation
CREATE USER IF NOT EXISTS 'walnutapp'@'%' IDENTIFIED BY 'walnutpassword';
GRANT ALL ON walnutdb.* TO 'walnutapp'@'%';

-- select db
USE walnutdb;

-- create post table
CREATE TABLE post (
  post_id INT NOT NULL AUTO_INCREMENT,
  author VARCHAR(255) DEFAULT NULL,
  author_id INT DEFAULT NULL,
  likes_count BIGINT DEFAULT NULL,
  popularity double DEFAULT NULL,
  reads_count BIGINT DEFAULT NULL,
  PRIMARY KEY (post_id)
);

-- create tag table
CREATE TABLE tag (
  tag_id INT NOT NULL AUTO_INCREMENT,
  tag_name VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (tag_id)
);

-- create post_tag table
CREATE TABLE post_tag (
  post_id INT NOT NULL,
  tag_id INT NOT NULL,
  KEY fk_posttag_tag (tag_id),
  KEY fk_posttag_post (post_id),
  CONSTRAINT fk_posttag_post FOREIGN KEY (post_id) REFERENCES post (post_id),
  CONSTRAINT fk_posttag_tag FOREIGN KEY (tag_id) REFERENCES tag (tag_id)
);


INSERT INTO post(post_id,author,author_id,likes_count,popularity,reads_count) VALUES
(1,"Rylee Paul","9","960","0.13","50361"),
(2,"Zackery Turner","12","469","0.68","90406"),
(4,"Elisha Friedman","8","728","0.88","19645"),
(12,"Adalyn Blevins","11","590","0.32","80351"),
(13,"Elisha Friedman","8","230","0.31","64058"),
(14,"Trevon Rodriguez","5","311","0.67","25644"),
(15,"Lainey Ritter","1","560","0.8","81549"),
(18,"Jaden Bryant","3","983","0.09","33952"),
(24,"Zackery Turner","12","940","0.74","89299"),
(25,"Elisha Friedman","8","365","0.12","32949"),
(26,"Zackery Turner","12","748","0.75","28239"),
(35,"Kinley Crosby","10","868","0.2","66926"),
(37,"Adalyn Blevins","11","107","0.55","35946"),
(43,"Jon Abbott","4","149","0.07","77776"),
(46,"Jon Abbott","4","89","0.96","79298"),
(51,"Jaden Bryant","3","487","0.01","98798"),
(54,"Bryson Bowers","6","723","0.56","312"),
(58,"Trevon Rodriguez","5","466","0.1","17389"),
(59,"Tia Roberson","2","971","0.21","36154"),
(76,"Lainey Ritter","1","122","0.01","75771"),
(77,"Trevon Rodriguez","5","407","0.21","664"),
(82,"Lainey Ritter","1","140","0.09","3201"),
(84,"Rylee Paul","9","233","0.65","17854"),
(85,"Bryson Bowers","6","25","0.18","16861"),
(89,"Adalyn Blevins","11","251","0.6","75503"),
(93,"Trevon Rodriguez","5","881","0.41","73964"),
(95,"Jon Abbott","4","985","0.42","55875"),
(99,"Tia Roberson","2","473","0.34","97868");

INSERT INTO tag (tag_id,tag_name) VALUES
(1,"culture"),
(2,"design"),
(3,"health"),
(4,"history"),
(5,"politics"),
(6,"science"),
(7,"startups"),
(8,"tech");

INSERT INTO post_tag (post_id,tag_id) VALUES
(1,8),
(1,3),
(2,8),
(2,7),
(2,4),
(4,8),
(4,6),
(4,2),
(12,8),
(13,8),
(13,2),
(14,8),
(14,4),
(15,8),
(15,1),
(15,7),
(18,8),
(18,4),
(24,8),
(24,1),
(24,5),
(25,8),
(25,5),
(26,8),
(35,8),
(37,3),
(37,4),
(37,8),
(43,6),
(43,8),
(46,1),
(46,8),
(51,2),
(51,7),
(51,8),
(54,1),
(54,8),
(58,6),
(58,8),
(59,5),
(59,8),
(76,8),
(76,3),
(76,5),
(77,8),
(77,7),
(77,5),
(77,6),
(82,8),
(84,8),
(84,5),
(84,4),
(85,8),
(89,8),
(89,5),
(89,7),
(89,4),
(93,8),
(93,4),
(95,8),
(95,5),
(95,3),
(95,4),
(99,8),
(99,1),
(99,7);
