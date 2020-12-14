-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-12-2020 a las 12:47:00
-- Versión del servidor: 10.1.38-MariaDB
-- Versión de PHP: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `scig`
--

----------------------------------------------------------
--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id_categoria` int(11) NOT NULL,
  `nombre_categoria` varchar(30) NOT NULL,
  `operacion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id_categoria`, `nombre_categoria`, `operacion`) VALUES
(1, 'COMISIONES', 'INGRESO'),
(2, 'PAGO IMPUESTOS SEDE SOCIAL', 'GASTO'),
(4, 'COIMAS', 'GASTO'),
(5, 'CUOTAS MENSUALES', 'INGRESO'),
(6, 'HONORARIOS', 'INGRESO'),
(7, 'APORTES VOLUNTARIOS', 'INGRESO'),
(8, 'SERVICIOS BASICOS', 'GASTO'),
(9, 'MANTENIMIENTO', 'GASTO');

----------------------------------------------------------
--
-- Estructura de tabla para la tabla `gastos`
--

CREATE TABLE `gastos` (
  `id_gasto` int(11) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `categoria` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `monto` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `gastos`
--

INSERT INTO `gastos` (`id_gasto`, `descripcion`, `categoria`, `fecha`, `monto`) VALUES
(1, 'Pago de alquiler de sede social del mes de NOVIEMBRE', 2, '2020-11-11', 500),
(2, 'PAGO DEL MES DE NOVIEMBRE', 2, '2020-11-13', 1000),
(3, 'Pago del mes de diciembre', 2, '2020-12-01', 2000),
(4, 'Pago de factura de AGUA del mes de DICIEMBRE', 2, '2020-12-01', 200),
(5, 'GASTOS GASTOS LUZ DICIEMBRE', 2, '2020-12-02', 100),
(6, 'PAGO PARA ACELERAR TRAMITE GAS', 4, '2020-12-02', 50),
(7, '09/11/2020 GASTOS 03:16', 2, '2020-12-09', 1400),
(13, 'PAGO DEL MES DE DICIEMBRE IMPUESTOS', 2, '2020-12-09', 200),
(17, 'GASTOS 10/12/2020', 4, '2020-12-10', 400),
(18, 'PAGO IMPUESTOS SEDE SOCIAL 12/12/2020 DICIEMBRE', 2, '2020-12-12', 1000),
(19, 'PAGO DE SERVICIOS BASICOS AGUA 12/12/2020 DICIEMBRE', 8, '2020-12-12', 19);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ingresos`
--

CREATE TABLE `ingresos` (
  `id_ingreso` int(11) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `categoria` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `monto` double NOT NULL,
  `vecino` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `ingresos`
--

INSERT INTO `ingresos` (`id_ingreso`, `descripcion`, `categoria`, `fecha`, `monto`, `vecino`) VALUES
(1, 'comision para el proceso de instalacion de gas', 1, '2020-11-11', 100, 1),
(3, 'pago del mes de enero concepto cuota mensual\r\n                ', 5, '2020-11-13', 1000, 2),
(4, 'Aporte para fotocopias', 1, '2020-11-26', 2000, 1),
(5, 'COUTA DE EL MES DE DICIEMBRE COMPLETO', 5, '2020-12-01', 500, 3),
(6, 'comision para la instalacion de gas 2020-12-02', 1, '2020-12-02', 45, 3),
(7, '2020-12-02', 1, '2020-12-02', 50, 2),
(8, '2020-12-01 ', 1, '2020-12-02', 60, 1),
(9, '2020-12-02 INGRESOS', 5, '2020-12-02', 50, 1),
(10, 'PRUEBA 5 2020-12-02', 5, '2020-12-02', 20, 3),
(11, '09/12/2020 INGRESOS 11:31 ', 1, '2020-12-09', 300, 1),
(16, '10/12/2020 INGRESOS 11:21 ', 7, '2020-12-10', 999, 3),
(17, '10/12/2020 INGRESOS 2 11:22 ', 5, '2020-12-10', 500, 1),
(26, '10/12/2020 INGRESOS 06:39', 7, '2020-12-10', 888, 3),
(27, '12/12/2020 CUOTA MENSUAL DEL MES DE DICIEMBRE', 5, '2020-12-12', 20, 1),
(28, '12/12/2020 CUOTA MENSUAL DEL MES DE DICIEMBRE', 5, '2020-12-12', 20, 2);

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellidos` varchar(30) NOT NULL,
  `celular` int(11) NOT NULL,
  `usuario` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `nombre`, `apellidos`, `celular`, `usuario`, `password`, `estado`) VALUES
(1, 'victor hugo', 'titirico siñani', 79632, 'admin_victor', '12345', 1),
(2, 'Selva Maria', 'Chuquimia ', 654343, 'admin_maria', '12345', 1),
(3, 'administrador', 'administrador', 7777777, 'admin', 'admin', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vecinos`
--

CREATE TABLE `vecinos` (
  `id_vecino` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellidos` varchar(30) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `celular` int(11) NOT NULL,
  `lote` int(11) NOT NULL,
  `manzana` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `vecinos`
--

INSERT INTO `vecinos` (`id_vecino`, `nombre`, `apellidos`, `direccion`, `celular`, `lote`, `manzana`) VALUES
(1, 'victor', 'Titirico', 'avenida juan pablo II', 79628529, 10, 2),
(2, 'Selva Maria', 'Chuquimia ', 'calle los portillos', 654343, 3, 1),
(3, 'Mariana', 'Z Condori', 'avenida JUNIN', 45456, 54, 45);

--
-- Índices para tablas volcadas
--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id_categoria`);

--
-- Indices de la tabla `gastos`
--
ALTER TABLE `gastos`
  ADD PRIMARY KEY (`id_gasto`),
  ADD KEY `categoria` (`categoria`);

--
-- Indices de la tabla `ingresos`
--
ALTER TABLE `ingresos`
  ADD PRIMARY KEY (`id_ingreso`),
  ADD KEY `categoria` (`categoria`),
  ADD KEY `vecino` (`vecino`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`);

--
-- Indices de la tabla `vecinos`
--
ALTER TABLE `vecinos`
  ADD PRIMARY KEY (`id_vecino`);

--
-- AUTO_INCREMENT de las tablas volcadas
--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id_categoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `gastos`
--
ALTER TABLE `gastos`
  MODIFY `id_gasto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `ingresos`
--
ALTER TABLE `ingresos`
  MODIFY `id_ingreso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `vecinos`
--
ALTER TABLE `vecinos`
  MODIFY `id_vecino` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--
-- Filtros para la tabla `gastos`
--
ALTER TABLE `gastos`
  ADD CONSTRAINT `gastos_ibfk_1` FOREIGN KEY (`categoria`) REFERENCES `categorias` (`id_categoria`);

--
-- Filtros para la tabla `ingresos`
--
ALTER TABLE `ingresos`
  ADD CONSTRAINT `ingresos_ibfk_1` FOREIGN KEY (`vecino`) REFERENCES `vecinos` (`id_vecino`),
  ADD CONSTRAINT `ingresos_ibfk_2` FOREIGN KEY (`categoria`) REFERENCES `categorias` (`id_categoria`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
