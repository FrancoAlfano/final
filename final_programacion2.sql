-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 21-01-2020 a las 18:30:41
-- Versión del servidor: 5.7.28-0ubuntu0.18.04.4
-- Versión de PHP: 5.6.32-1+ubuntu17.04.1+deb.sury.org+1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `final_programacion2`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `cliente_id` bigint(20) NOT NULL,
  `apellido` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `nombre` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`cliente_id`, `apellido`, `nombre`) VALUES
(1, 'perez', 'rodrigo'),
(2, 'mando', 'nor'),
(3, 'quito', 'esteban'),
(4, 'drigo', 'ro'),
(5, 'turito', 'ar'),
(6, 'franco', 'alfano'),
(7, 'rodriguez', 'diego'),
(10, 'test', 'test'),
(11, 'Loco', 'Poco'),
(14, 'ebita', 'elpru'),
(15, 'ebita', 'elpru'),
(16, 'alfano', 'franco');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `logs`
--

CREATE TABLE `logs` (
  `logs_id` bigint(20) NOT NULL,
  `explicacion` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_venta` bigint(20) DEFAULT NULL,
  `paso` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `resultado` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `logs`
--

INSERT INTO `logs` (`logs_id`, `explicacion`, `id_venta`, `paso`, `resultado`) VALUES
(69, 'Monto máximo superado', NULL, 'verificacion de monto', 'FALLO'),
(70, 'Venta exitosa', 47, 'Venta', 'OK'),
(71, 'Monto máximo superado', NULL, 'verificacion de monto', 'FALLO'),
(72, 'Monto máximo superado', NULL, 'verificacion de monto', 'FALLO'),
(73, 'Venta exitosa', 48, 'Venta', 'OK'),
(74, 'Monto máximo superado', NULL, 'verificacion de monto', 'FALLO'),
(75, 'Monto máximo superado', NULL, 'verificacion de monto', 'FALLO'),
(76, 'tarjeta encontrada!', NULL, 'checkeo de tarjeta', 'OK'),
(77, 'Venta exitosa', 49, 'Venta', 'OK'),
(78, 'tarjeta encontrada!', NULL, 'checkeo de tarjeta', 'OK'),
(79, 'Venta exitosa', 50, 'Venta', 'OK'),
(80, 'Monto máximo superado', NULL, 'verificacion de monto', 'FALLO'),
(81, 'tarjeta encontrada!', NULL, 'checkeo de tarjeta', 'OK'),
(82, 'Venta exitosa', 51, 'Venta', 'OK'),
(83, 'tarjeta encontrada!', NULL, 'checkeo de tarjeta', 'OK'),
(84, 'Venta exitosa', 52, 'Venta', 'OK'),
(85, 'tarjeta encontrada!', NULL, 'checkeo de tarjeta', 'OK'),
(86, 'Venta exitosa', 53, 'Venta', 'OK');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarjeta`
--

CREATE TABLE `tarjeta` (
  `tarjeta_id` bigint(20) NOT NULL,
  `cod_seguridad` bigint(20) NOT NULL,
  `numero` bigint(20) NOT NULL,
  `tipo` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `monto` double DEFAULT NULL,
  `vencimiento` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `tarjeta`
--

INSERT INTO `tarjeta` (`tarjeta_id`, `cod_seguridad`, `numero`, `tipo`, `cliente_id`, `monto`, `vencimiento`) VALUES
(3, 999, 101020203030, 'VISA', 1, 6000, '1970-01-01'),
(4, 555, 101020203030, 'VISA', 2, 5000, '2019-10-10'),
(5, 123, 101020203030, 'MC', 3, 5000, '2020-03-03'),
(6, 999, 101020203030, 'MC', 4, 5000, '2020-02-05'),
(7, 456, 101020206060, 'MC', 1, 40000, '2018-08-23'),
(8, 456, 101020206060, 'MC', 5, 5000, '2020-05-08'),
(9, 456, 101020206060, 'MC', 10, 7000, '2023-10-10'),
(10, 456, 101020878956, 'MC', 11, 8000, '2025-11-30'),
(12, 456, 101020878956, 'MC', 15, 5000, '1970-01-01'),
(13, 456, 101020878956, 'MC', 14, 5000, '1970-01-01'),
(14, 456, 101020878956, 'MC', 10, 5000, '2020-12-11'),
(15, 456, 101020878956, 'MC', 11, 5000, '1970-01-01'),
(16, 456, 101020878956, 'MC', 1, 5000, '2020-12-13'),
(17, 456, 101020878956, 'MC', 5, 5000, '2020-12-15'),
(18, 999, 101020878957, 'VISA', 2, 5000, '2020-12-15'),
(19, 489, 101020812345, 'VISA', 16, 5000, '2020-12-15'),
(20, 489, 101020812345, 'VISA', 15, 5000, '2020-12-15'),
(21, 489, 101020812345, 'VISA', 14, 5000, '2020-12-15'),
(22, 489, 101020812345, 'VISA', 11, 5000, '2020-12-15'),
(23, 489, 101020812345, 'VISA', NULL, 5000, '2020-12-15'),
(24, 489, 101020812345, 'VISA', NULL, 5000, '2020-12-15'),
(25, 489, 101020812345, 'VISA', NULL, 5000, '2020-12-15'),
(26, 489, 101020812345, 'VISA', NULL, 5000, '2020-12-15'),
(27, 489, 101020812345, 'VISA', NULL, 5000, '2020-12-15'),
(28, 489, 101020812345, 'VISA', 16, 5000, '2020-12-15'),
(29, 489, 101020812345, 'VISA', NULL, 5000, '2020-12-15'),
(30, 999, 10102088998, 'VISA', NULL, 8000, '2020-12-15');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `venta_id` bigint(20) NOT NULL,
  `cliente` bigint(20) DEFAULT NULL,
  `monto` bigint(20) DEFAULT NULL,
  `tarjeta` bigint(20) DEFAULT NULL,
  `fecha` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`venta_id`, `cliente`, `monto`, `tarjeta`, `fecha`) VALUES
(37, 16, 8000, 1, '2022-11-10'),
(38, 16, 8000, 12, '2022-11-10'),
(39, 16, 4000, 12, '2022-11-10'),
(40, 16, 4000, 12, '2022-11-10'),
(41, 1, 2500, 3, '2022-11-10'),
(42, 1, 2500, 3, '2022-11-10'),
(43, 1, 2500, 3, '2022-11-10'),
(44, 1, 2500, 3, '2022-11-10'),
(45, 1, 3000, 3, '2022-11-10'),
(46, 1, 3000, 3, '2022-11-10'),
(47, 1, 7000, 3, '2022-11-10'),
(48, 1, 7000, 3, '2022-11-10'),
(49, 1, 5000, 8, '2022-11-10'),
(50, 1, 5000, 23, '2022-11-10'),
(51, 2, 4000, 3, '2022-11-10'),
(52, 2, 5000, 3, '2022-11-10'),
(53, 2, 5000, 3, '2022-11-10');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cliente_id`);

--
-- Indices de la tabla `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`logs_id`);

--
-- Indices de la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
  ADD PRIMARY KEY (`tarjeta_id`),
  ADD KEY `FK64s3iwifnd3gi9pyigfrensvf` (`cliente_id`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`venta_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `cliente_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT de la tabla `logs`
--
ALTER TABLE `logs`
  MODIFY `logs_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=87;
--
-- AUTO_INCREMENT de la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
  MODIFY `tarjeta_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `venta_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
  ADD CONSTRAINT `FK64s3iwifnd3gi9pyigfrensvf` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`cliente_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
