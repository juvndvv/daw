DROP TABLE IF EXISTS `recomendaciones`;
drop table if exists `movimientos`;
drop table if exists `cuentas`;
DROP TABLE IF EXISTS `clientes`;

CREATE TABLE `clientes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nif` varchar(9) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `clave_seguridad` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `recomendaciones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `observaciones` varchar(500) NOT NULL,
  `id_cliente` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idcliente_UNIQUE` (`id_cliente`),
  CONSTRAINT `fk_cliente_recomendacion` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Datos de prueba para la tabla `clientes`
INSERT INTO `clientes` (`apellidos`, `clave_seguridad`, `email`, `nif`, `nombre`) VALUES
                                                                                      ('González', 'clave123', 'gonzalez@email.com', '12345789A', 'Ana'),
                                                                                      ('López', 'pass456', 'lopez@email.com', '98765432B', 'Carlos'),
                                                                                      ('Martínez', 'secret789', 'martinez@email.com', '56789012C', 'Elena'),
                                                                                      ('Pérez', 'mypassword', 'perez@email.com', '34568901D', 'Juan'),
                                                                                      ('Rodríguez', 'securepass', 'rodriguez@email.com', '34567890E', 'Laura');

-- Datos de prueba para la tabla `cuentas`
INSERT INTO `cuentas` (`banco`, `dc`, `numero_cuenta`, `saldo`, `sucursal`, `id_cliente`) VALUES
                                                                                              ('Banco A', '1234', '5678901234', 1500.0, 'Sucursal A', 1),
                                                                                              ('Banco B', '5678', '9876543210', 2000.0, 'Sucursal B', 2),
                                                                                              ('Banco C', '4321', '1234567890', 1000.0, 'Sucursal C', 3),
                                                                                              ('Banco A', '8765', '5432109876', 2500.0, 'Sucursal A', 4),
                                                                                              ('Banco B', '9876', '1098765432', 3000.0, 'Sucursal B', 5);

-- Datos de prueba para la tabla `movimiento`
INSERT INTO `movimientos` (`descripcion`, `fecha`, `importe`, `tipo`, `cuenta_destino_id_cuenta`, `cuenta_origen_id_cuenta`) VALUES
                                                                                                                                ('Compra en tienda A', '2023-01-01 10:00:00', 50.0, 'Gasto', 2, 1),
                                                                                                                                ('Transferencia a amigo B', '2023-01-02 12:30:00', 100.0, 'Transferencia', 4, 3),
                                                                                                                                ('Depósito de salario', '2023-01-03 15:45:00', 200.0, 'Ingreso', 1, 5),
                                                                                                                                ('Pago de factura', '2023-01-04 18:20:00', 30.0, 'Gasto', 3, 2),
                                                                                                                                ('Transferencia a familiar C', '2023-01-05 20:10:00', 120.0, 'Transferencia', 5, 4);

-- Datos de prueba para la tabla `recomendaciones`
INSERT INTO `recomendaciones` (`observaciones`, `id_cliente`) VALUES
                                                                  ('Cliente recomendado por amigo', 2),
                                                                  ('Cliente referido por familiar', 4),
                                                                  ('Cliente sugerido por compañero de trabajo', 1),
                                                                  ('Nuevo cliente proveniente de publicidad', 3),
                                                                  ('Cliente recomendado por conocido', 5);
