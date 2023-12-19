DROP TABLE IF EXISTS `clientes`;
DROP TABLE IF EXISTS `recomendaciones`;


CREATE TABLE `clientes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nif` varchar(9) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `claveSeguridad` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `recomendaciones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `observaciones` varchar(500) NOT NULL,
  `idcliente` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idcliente_UNIQUE` (`idcliente`),
  CONSTRAINT `fk_cliente_recomendacion` FOREIGN KEY (`idcliente`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;