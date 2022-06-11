CREATE TABLE users(
id INTEGER,
providerid VARCHAR(20),
targetid VARCHAR(20),
edupersonid VARCHAR(20),
federation VARCHAR(20),
idpid VARCHAR(20) FOREIGN KEY REFERENCES idp(idpid));

drop table users

SHOW TABLES;

CREATE TABLE idp(
idpId VARCHAR(20) PRIMARY KEY,
idpName VARCHAR(20));

