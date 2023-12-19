-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-06-2019 a las 10:57:25
-- Versión del servidor: 10.1.39-MariaDB
-- Versión de PHP: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
use `coop`;
--
-- Base de datos: `coop`
--

-- --------------------------------------------------------
drop table if exists `articulos`;
drop table if exists `categorias`;
drop table if exists `socios`;
--
-- Estructura de tabla para la tabla `articulos`
--

CREATE TABLE `articulos` (
  `id` int(11) NOT NULL,
  `categoria` int(11) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `precio` float NOT NULL,
  `imagen` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `vendedor` int(11) NOT NULL,
  `estado` char(1) COLLATE utf8_spanish_ci NOT NULL DEFAULT 'D'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `articulos`
--

INSERT INTO `articulos` (`id`, `categoria`, `nombre`, `descripcion`, `precio`, `imagen`, `vendedor`, `estado`) VALUES
(1, 13, 'Mochila senderismo', 'Mochila senderismo 50 L, cubierta impermiable ', 33.99, ' mochilaSenderismo.png', 1, 'D'),
(2, 5, 'Panel solar ', 'Panel solar de policristalino de 1,5 x 5 m. ', 109.9, ' panelSolar.png', 1, 'D'),
(3, 4, 'Patín eléctrico', 'Scooter eléctrico M365 ', 369, ' patinElectrico.png', 1, 'D'),
(4, 7, 'Portatil Lenovo', 'Portatil táctil convertible 14\' Intel core I-3, 4GB RAM, 128 GB SSD, Windows 10  ', 539.99, ' portatilLenovo.png', 1, 'D');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `imagen` varchar(255) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `nombre`, `imagen`) VALUES
(1, 'Cine, TV y Música', ''),
(2, 'Coche y moto', ''),
(3, 'Libros', ''),
(4, 'Movilidad urbana', ''),
(5, 'Hogar, Jardín y Bricolaje', ''),
(6, 'Mascotas', ''),
(7, 'Informática', ''),
(8, 'Moda', ''),
(9, 'Electrónica', ''),
(10, 'Videojuegos', ''),
(11, 'Juguetes y Bebé', ''),
(12, 'Deportes y aire libre', ''),
(13, 'Acampada y senderismo', ''),
(14, 'Ofertas y Chollos', ''),
(15, 'Otros', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `socios`
--

CREATE TABLE `socios` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `apellidos` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `socios`
--

INSERT INTO `socios` (`id`, `nombre`, `apellidos`, `email`, `password`) VALUES
(1, 'Isabel', 'López', 'isabel.lopez@iescamp.es', 'isabel'),
(2, 'Prueba', 'Prueba Prue', 'prueba.prueba@iescamp.es', 'prueba');


--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `articulos`
--
ALTER TABLE `articulos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `categoria` (`categoria`),
  ADD KEY `vendedor` (`vendedor`);

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `socios`
--
ALTER TABLE `socios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `EMAIL_UNIQUE` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `articulos`
--
ALTER TABLE `articulos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `socios`
--
ALTER TABLE `socios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `articulos`
--
ALTER TABLE `articulos`
  ADD CONSTRAINT `FK_CATEGORIA` FOREIGN KEY (`categoria`) REFERENCES `categorias` (`id`),
  ADD CONSTRAINT `FK_VENDEDOR` FOREIGN KEY (`vendedor`) REFERENCES `socios` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
