CREATE DATABASE IF NOT EXISTS libraryJDBC CHARACTER SET utf8;

USE libraryJDBC;

CREATE TABLE genre (
  id INT NOT NULL AUTO_INCREMENT,
  genreName VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE book (
  id INT NOT NULL AUTO_INCREMENT,
  bookName VARCHAR(100) NOT NULL,
  description TEXT NOT NULL,
  genre_id INT NOT NULL,
  FOREIGN KEY (genre_id) REFERENCES genre (id),
  PRIMARY KEY (id)
);

INSERT INTO genre VALUES (1, 'Утопия');
INSERT INTO genre VALUES (2, 'Роман');
INSERT INTO genre VALUES (3, 'Приключения');
INSERT INTO genre VALUES (4, 'Детектив');

INSERT INTO book VALUES (1, 'Book 1', 'qeweqweqwev fdfsdf', 1);
INSERT INTO book VALUES (2, 'Book 2', 'f fdsf dsf', 2);
INSERT INTO book VALUES (3, 'Book 3', 'fsdfsd dsfds', 3);
INSERT INTO book VALUES (4, 'Book 4', 'fdsff fsdfs', 4);

