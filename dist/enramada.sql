-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.45-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema enramada
--

CREATE DATABASE IF NOT EXISTS enramada;
USE enramada;

--
-- Definition of table `alimentos`
--

DROP TABLE IF EXISTS `alimentos`;
CREATE TABLE `alimentos` (
  `codigo_al` varchar(20) NOT NULL default '',
  `tipo_al` varchar(50) default NULL,
  `nombre_al` varchar(50) default NULL,
  `precio_al` decimal(10,2) default NULL,
  PRIMARY KEY  (`codigo_al`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `alimentos`
--

/*!40000 ALTER TABLE `alimentos` DISABLE KEYS */;
/*!40000 ALTER TABLE `alimentos` ENABLE KEYS */;


--
-- Definition of table `registro_venta`
--

DROP TABLE IF EXISTS `registro_venta`;
CREATE TABLE `registro_venta` (
  `numero` varchar(20) NOT NULL default '',
  `total` decimal(10,2) default NULL,
  `fecha` varchar(50) default NULL,
  PRIMARY KEY  (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `registro_venta`
--

/*!40000 ALTER TABLE `registro_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `registro_venta` ENABLE KEYS */;


--
-- Definition of table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `codigo_us` varchar(20) NOT NULL default '',
  `nombre_us` varchar(50) default NULL,
  `sexo_us` varchar(10) default NULL,
  `tipo_us` varchar(50) default NULL,
  `pass` varchar(50) default NULL,
  PRIMARY KEY  (`codigo_us`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuarios`
--

/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`codigo_us`,`nombre_us`,`sexo_us`,`tipo_us`,`pass`) VALUES 
 ('US0001','ROGER','MASCULINO','ADMINISTRADOR','nose');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
