create user 'coop'@'localhost' identified by 'coop';
grant usage on * . * to 'coop'@'localhost' identified by 'coop' ;
create database if not exists `coop`  default character set utf8 collate utf8_spanish_ci;;
grant all privileges on `coop` . * to 'coop'@'localhost';
use `coop`;
