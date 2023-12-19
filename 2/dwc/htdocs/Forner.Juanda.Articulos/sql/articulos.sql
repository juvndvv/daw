create user 'dwc'@'localhost' identified by 'dwc';
grant usage on * . * to 'dwc'@'localhost' identified by 'dwc' ;
create database if not exists `ajaxdwc` default character set utf8 collate utf8_spanish_ci;
grant all privileges on `ajaxdwc` . * to 'dwc'@'localhost';
use `ajaxdwc`;
drop table if exists `articulos`;
drop table if exists `familias`;
drop table if exists `tiposiva`;
drop table if exists `proveedores`;
-- ----------------------------------------------
-- Tabla Tipos de IVA
-- ---------------------------------------------
create table if not exists `tiposiva`(
  `codigo` char(3),
  `porcentaje` int(2),
  `recargo` int(2),
  primary key(`codigo`)
)
engine InnoDB character set utf8 collate utf8_spanish_ci;
-- ----------------------------------------------
-- tabla Familias
-- ---------------------------------------------
create table if not exists `familias`(
  `idfamilia` int(2),
  `nombre` varchar(30),
  `iva` char(3),
  `imagen` varchar(50),
   primary key `pkfamilias` (`idfamilia`),
   key `knombre` (`nombre` asc),
   constraint `fkfamtiposiva` foreign key(`iva`) references `tiposiva`(`codigo`)
)
engine InnoDB character set utf8 collate utf8_spanish_ci;
-- ----------------------------------------------
-- Tabla Proveedores
-- ---------------------------------------------
create table if not exists `proveedores`(
  `idproveedor` int(3),
  `nombre` varchar(30),
  `direccion` varchar(30),
  `codpostal` int(5),
  `cifnif` char(10),
  `telefono` varchar(11),
  `movil` varchar(11),
  `fax` varchar(11),
  `personacontacto` varchar(30),
  `acucompras` float(8,2),
  primary key `pkproveedores`(`idproveedor`)
)
engine InnoDB character set utf8 collate utf8_spanish_ci;
-- ----------------------------------------------
-- tabla Artículos
-- ---------------------------------------------
create table if not exists `articulos` (
    `idarticulo` int(4),
    `familia` int(2),
    `descripcion` varchar(30),
    `proveedor` int(3),
    `precioventa` float(8 , 2 ),
    `descuento` int(2),
    `stockminimo` int(4),
    primary key `pkarticulos` (`idarticulo`),
    constraint `fkartfamilias` foreign key (`familia`)
        references `familias` (`idfamilia`),
	constraint `fkartproveedores` foreign key (`proveedor`)
        references `proveedores` (`idproveedor`)
   )  engine InnoDB character set utf8 collate utf8_spanish_ci;
-- ----------------------------------------------
-- inserts Tipos de IVA
-- ---------------------------------------------
insert into `tiposiva` (`codigo`,`porcentaje`,`recargo`) values ('EXE',0,0);
insert into `tiposiva` (`codigo`,`porcentaje`,`recargo`) values ('GEN',21,4);
insert into `tiposiva` (`codigo`,`porcentaje`,`recargo`) values ('RED',10,2);
insert into `tiposiva` (`codigo`,`porcentaje`,`recargo`) values ('SRE',4,0);
-- ----------------------------------------------
-- inserts Familias
-- ---------------------------------------------
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (1,'Procesadores','GEN','Procesadores.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (2,'Placas Base','GEN','PlacasBase.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (3,'Refrigeradores','GEN','Refrigeradores.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (4,'Discos Duros','GEN','DiscosDuros.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (5,'Memoria RAM','GEN','MemoriaRam.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (6,'Tarjetas Gráficas','GEN','TarjetasGraficas.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (7,'Tarjetas Sonido','GEN','TarjetasSonido.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (8,'Disqueteras','GEN','Disqueteras.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (9,'Consumibles CD/DVD','GEN','ConsumiblesCDDVD.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (10,'Grabadoras CD/DVD','GEN','GrabadorasCDDVD.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (11,'Cajas','GEN','Cajas.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (12,'Software','GEN','Software.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (13,'Cables/Adaptadores','GEN','CablesAdaptadores.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (14,'Teclados','GEN','Teclados.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (15,'Monitores','GEN','Monitores.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (16,'Tarjetas Red','GEN','TarjetasRed.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (17,'Altavoces','GEN','Altavoces.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (18,'Ordenadores Sobremesa','GEN','OrdenadoresSobremesa.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (19,'Ratones/Trackballs','GEN','RatonesTrackballs.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (20,'Discos Externos','GEN','DiscosExternos.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (21,'Routers','GEN','Routers.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (22,'Tinta impresoras','GEN','TintaImpresoras.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (23,'Microfonos/Auriculares','GEN','MicrofonosAuriculares.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (24,'Papeles Fotograficos','GEN','PapelesFotograficos.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (25,'Tablets','GEN','Tablets.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (26,'Toners','GEN','Toners.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (27,'Webcams','GEN','Webcams.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (28,'Modems','GEN','Modems.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (29,'PDA / GPS','GEN','PDAGPS.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (30,'Netbooks','GEN','Netbooks.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (31,'Reproductores MP3/MP4','GEN','ReproductoresMP3.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (32,'Fuentes Alimentación','GEN','FuentesAlimentacion.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (33,'Moviles','GEN','Moviles.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (34,'Portatiles','GEN','Portatiles.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (35,'Controladoras','GEN','Controladoras.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (36,'Impresoras','GEN','Impresoras.png');
insert into `familias` (`idfamilia`,`nombre`,`iva`,`imagen`) values (37,'Redes Inalambricas','GEN','RedesInalambricas.png');

-- ----------------------------------------------
-- inserts Proveedores
-- ---------------------------------------------
insert into `proveedores`(`idproveedor`,`nombre`,`direccion`,`codpostal`,`cifnif`,`telefono`,`movil`,`fax`,`personacontacto`,`acucompras`) 
values (11,'Javier Bronchud Gea','direccion',46520,null,null,null,null,null,0);
insert into `proveedores`(`idproveedor`,`nombre`,`direccion`,`codpostal`,`cifnif`,`telefono`,`movil`,`fax`,`personacontacto`,`acucompras`)
values (12,'Alejandro Dolz Verdu','direccion',46520,null,null,null,null,null,0);
insert into `proveedores`(`idproveedor`,`nombre`,`direccion`,`codpostal`,`cifnif`,`telefono`,`movil`,`fax`,`personacontacto`,`acucompras`)
values (13,'Claudiu Ilina','`direccion',46520,null,null,null,null,null,0);
insert into `proveedores`(`idproveedor`,`nombre`,`direccion`,`codpostal`,`cifnif`,`telefono`,`movil`,`fax`,`personacontacto`,`acucompras`) 
values (14,'Juan Fernandez Liviano','`direccion',46520,null,null,null,null,null,0);
insert into `proveedores`(`idproveedor`,`nombre`,`direccion`,`codpostal`,`cifnif`,`telefono`,`movil`,`fax`,`personacontacto`,`acucompras`)
values (15,'Gonzalo Macho Arana','`direccion`',46520,null,null,null,null,null,0);
insert into `proveedores`(`idproveedor`,`nombre`,`direccion`,`codpostal`,`cifnif`,`telefono`,`movil`,`fax`,`personacontacto`,`acucompras`) 
values (16,'Jorge Millan Vaz','`direccion`',46520,null,null,null,null,null,0);
insert into `proveedores`(`idproveedor`,`nombre`,`direccion`,`codpostal`,`cifnif`,`telefono`,`movil`,`fax`,`personacontacto`,`acucompras`) 
values (17,'Sara Pablo Palmero','`direccion`',46520,null,null,null,null,null,0);
insert into `proveedores`(`idproveedor`,`nombre`,`direccion`,`codpostal`,`cifnif`,`telefono`,`movil`,`fax`,`personacontacto`,`acucompras`) 
values (18,'Carlos Pacheco Algaba','`direccion`',46520,null,null,null,null,null,0);
insert into `proveedores`(`idproveedor`,`nombre`,`direccion`,`codpostal`,`cifnif`,`telefono`,`movil`,`fax`,`personacontacto`,`acucompras`) 
values (19,'Borja Pons Paterna','`direccion`',46520,null,null,null,null,null,0);
insert into `proveedores`(`idproveedor`,`nombre`,`direccion`,`codpostal`,`cifnif`,`telefono`,`movil`,`fax`,`personacontacto`,`acucompras`) 
values (20,'Alumnos DWEC SM','`direccion`',46520,null,null,null,null,null,0);
insert into `proveedores`(`idproveedor`,`nombre`,`direccion`,`codpostal`,`cifnif`,`telefono`,`movil`,`fax`,`personacontacto`,`acucompras`) 
values (30,'Amparo Morten SA','`direccion`',46520,null,null,null,null,null,0);
insert into `proveedores`(`idproveedor`,`nombre`,`direccion`,`codpostal`,`cifnif`,`telefono`,`movil`,`fax`,`personacontacto`,`acucompras`) 
values (40,'Julia Nogueroles','`direccion`',46520,null,null,null,null,null,0);
insert into `proveedores`(`idproveedor`,`nombre`,`direccion`,`codpostal`,`cifnif`,`telefono`,`movil`,`fax`,`personacontacto`,`acucompras`) 
values (50,'Alumnos DWEC M','`direccion`',46520,null,null,null,null,null,0);

-- ----------------------------------------------
-- inserts Artículos
-- ---------------------------------------------
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (101,1,'AMD DURON 1,2 GHZ',11,38.41,10,100);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (102,1,'INTEL PENT4 1,5G  S478',12,137.33,5,100);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (103,1,'INTEL PENT4 1,8G  S478',13,163.37,5,50);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (104,1,'INTEL PENT4 2,8G  S478',11,733.67,5,100);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (154,1,'INTEL P4 3,0G (1MB/800)',14,157.5,5,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (201,2,'AMD ELITEG K7VTA3',12,61.14,5,200);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (202,2,'P4 ELITEG L4S8A2',20,79.66,10,150);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (223,2,'P4 QDI BA1-6AL',11,86.25,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (224,2,'P4 GIGA GA-8PE667',12,122.15,0,50);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (239,2,'P4-775 GIGA GA-8L915P DUO',12,131,5,50); 
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (303,3,'VENT SUPL CAJA ATX',13,2.75,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (305,3,'VENT PENT-AMD',13,7.01,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (306,3,'VENT P4 GREEN COLOR',17,10.45,5,10);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (307,3,'VENT AMD GREEN COOLER',13,8.19,5,10);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (318,3,'VENT CPU TUNEL KIT',13,16.46,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (322,3,'VENT. P4 PRESCOTT',18,34.5,5,10);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (403,4,'DD 20GB 1 AÑO GAR',14,69.64,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (404,4,'DD 60 SAMSUNG 3 AÑOS',16,96.15,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (405,4,'DD 120GB 133/7200',14,151.15,20,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (412,4,'DD 80 USB2 IOMEGA',15,234.93,5,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (415,4,'DD 180GB 100/720',14,390.66,5,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (420,4,'DISCO DURO 200GB 133/7200',14,93.9,15,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (511,5,'DDR 128 MB 266',15,19.73,15,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (512,5,'DIMM 128 (133)',11,21.38,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (513,5,'DDR 256 (266) SIEMENS',15,45.19,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (522,5,'DDR 512(333) Kingston',12,81.4,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (534,5,'DDR 512(400) KINGSTON',15,59.5,10,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (612,6,'R`iva` TNT2 32M AGP',16,28.69,5,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (613,6,'GFORCE2 MX400 64 TVOT',16,42.94,5,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (614,6,'WINFAST GF4 64 TVOT',13,80.14,5,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (615,6,'XELO GF4 TI 4600 128DDR',16,352.08,5,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (634,6,'ATI RAD 9250 128MB',16,43.2,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (708,7,'S.B. 4.1. DIGITAL BULK',17,17.37,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (709,7,'S.B. 4.1 DIGITAL',17,45.84,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (710,7,'SOUND BLASTER EXTIGY',11,184.61,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (711,7,'S.B. AUDIGY2 PLAT EX',17,249.45,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (809,8,'DISQUETERA 31/2 1.44',18,9.04,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (811,8,'DISQUETERA 31/2 1.44 BLACK',18,10.23,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (904,9,'CD ROM 52X LG',19,22.2,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (905,9,'DVD LG 16X',19,43.02,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (912,9,'DVD SAMSUNG 48X NEGRO',19,50.47,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (915,9,'DVD MUSTEK V562',19,136.72,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1001,10,'LG 52X24X52',20,59.52,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1002,10,'SAMSUNG 48X16X48 NEGRO',20,62.03,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1004,10,'IOMEGA 40X12X48 USB 2.0',20,168.08,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1007,10,'GRAB. DVD Y CD PIONER AO5',20,330.9,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1051,10,'DVD PIONEER 109 NG',20,83.9,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1101,11,'SEMIT ATX DIAMOND',20,43.28,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1102,11,'SEMIT GENIUS APOLLO',20,65.89,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1103,11,'TORRE SERVIDOR ONIX',20,306.19,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1104,11,'SEMIT ALUM STEREO',20,238.77,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1207,12,'WINDOWS XP HOME',20,90.1,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1209,12,'WINDOWS XP PROFESIONAL',20,152.4,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1215,12,'WINDOWS 2000 PRO',20,154.6,20,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1203,12,'WINDOWS 98',20,98.8,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1238,12,'WINDOWS XP PROF',20,137.6,10,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1241,12,'PANDA TITANIUM 2004',20,33,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1242,12,'NORTON ANTIVIRUS 2004',20,42.8,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1250,12,'NORTON SYSTEM WORKS',20,82.4,10,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1260,12,'PINACLE ACT ESTUDIO 9',20,51.2,10,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1276,12,'NORTON ATIVIRUS 2004',20,40.4,10,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1302,13,'CABLE P. IMPRESORA',20,1.3,10,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1303,13,'CABLE USB 1,8 NTS',20,2.1,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1304,13,'C. REDONDO IDE ATA100-133',13,6.2,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1305,13,'CABLE BUS DD',15,1.1,5,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1311,13,'CABLE USB1 1,3 M',14,2.8,10,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1312,13,'CABLE USB2.0 1,8',15,5,5,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1412,14,'GENIUS TECLADO ÓPTICO',16,17.1,10,60);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1509,15,'17 TFT SAMSUNG SM710V',17,244.1,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1607,16,'GENIUS 100MB PCI',18,8.7,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (1912,19,'GENIUS RATÓN ÓPTICO',19,8.7,10,500);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (2104,21,'ROUTER USR 10/100',20,50.8,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (2105,21,'SWITCH GENIUS GS4080',20,27,10,200);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (2106,21,'ROUTER CONCEPTRONIC ADSL',19,134.6,5,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (2107,21,'ROUTER USR 209105',18,92.6,5,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (2103,21,'ROUTER USR 81800A',17,64,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (5003,37,'BLUETOOTH USB-100',16,31.6,0,0);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (5004,37,'CONCEPT. PCI 54MBPS',15,43.8,10,80);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (5005,37,'TRUST DVD VIDEO VIEWER',14,57,5,40);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (5007,37,'ROUTER SMC 54MBPS',13,85.8,5,100);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (5017,37,'CONCEP. BLUETOOTH PCMCIA',12,54.4,5,50);
insert into `articulos` (`idarticulo`,`familia`,`descripcion`,`proveedor`,`precioventa`,`descuento`,`stockminimo`) values (5022,37,'KIT POWERLINE SMC',11,139.7,5,100);
