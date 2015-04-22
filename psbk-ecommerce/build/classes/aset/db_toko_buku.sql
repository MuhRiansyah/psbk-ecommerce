-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 04, 2014 at 04:04 AM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_toko_buku`
--

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE IF NOT EXISTS `buku` (
  `id_buku` int(4) NOT NULL AUTO_INCREMENT,
  `judul` char(100) NOT NULL,
  `penulis` char(60) NOT NULL,
  `harga` int(7) NOT NULL,
  `stok` int(7) NOT NULL,
  `id_suplier` int(3) NOT NULL,
  PRIMARY KEY (`id_buku`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`id_buku`, `judul`, `penulis`, `harga`, `stok`, `id_suplier`) VALUES
(1, 'Naruto', 'Mashashi Kishimoto', 12500, 98, 2),
(2, 'One Piece', 'Oda Sensei', 12500, 1000, 2),
(3, 'Fiqih Islam Wadhitu', 'Prof Dr Wahbah Zuhaili', 120000, 60, 1),
(4, 'Tafsir Al Munir', 'Prof Dr Wahbah Zuhaili', 150000, 110, 1),
(10, 'Ilmuwan-Ilmuwan Muslim', 'Ehsan Masood', 45000, 10, 1),
(11, 'Bermain Fisika Itu Asyik', 'Harry Burowardi Johan', 55000, 10, 1),
(12, 'Keajaiban-Keajaiban Dalam Tubuh Manusia', 'Albert M. Hutapea', 55000, 10, 2),
(13, 'The Hunger Games #3: Mockingjay', 'Suzanne Collins', 68000, 10, 2),
(14, 'Trio Detektif #6: Misteri Pulau Tengkorak', 'Robert Arthur', 40000, 10, 1),
(15, 'Koleksi Kasus Sherlock Holmes', 'Sir Arthur Conan Doyle', 38000, 10, 3),
(16, '7 Jam Belajar Interaktif Photoshop CS6 Untuk Orang Awam + CD', 'Jayan', 52500, 20, 4),
(17, '7 jam Belajar Interkatif CorelDraw X6 Untuk Orang Awam + CD', 'Chandra', 52500, 10, 4),
(18, '50 Efek Profesional Photoshop CS5 + CD', 'Fandi', 43500, 10, 4),
(19, '7 Jam Belajar Interaktif Flash CS5 Untuk Orang Awam + CD', 'Chandra', 46500, 10, 4),
(20, 'After Effect CS4 Untuk Orang Awam + CD', 'Fandi', 43500, 10, 4),
(21, 'Adobe Premiere Pro CS4 Untuk Orang Awam + CD', 'Fandi', 41500, 10, 4),
(22, '7 Jam Belajar Interaktif AutoCAD Untuk Orang Awam + CD', 'Handi Chandra', 52500, 10, 4),
(23, '7 Jam Belajar Interaktif JAVA Untuk Orang Awam + CD', 'Irawan', 39500, 10, 4),
(24, 'PHP & MYSQL Untuk Orang Awam (Edisi Ke-2) + CD', 'Rulianto. K', 43500, 10, 4),
(25, 'WordPress Untuk Orang Awam + CD', 'Rulianto. K', 41500, 10, 4);

-- --------------------------------------------------------

--
-- Table structure for table `pembelian`
--

CREATE TABLE IF NOT EXISTS `pembelian` (
  `id_pembelian` int(6) NOT NULL AUTO_INCREMENT,
  `tanggal` date NOT NULL,
  `jumlah` int(7) NOT NULL,
  `biaya` int(11) NOT NULL,
  `id_buku` int(3) NOT NULL,
  PRIMARY KEY (`id_pembelian`),
  KEY `id_buku` (`id_buku`),
  KEY `id_buku_2` (`id_buku`),
  KEY `id_buku_3` (`id_buku`),
  KEY `id_buku_4` (`id_buku`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `pembelian`
--

INSERT INTO `pembelian` (`id_pembelian`, `tanggal`, `jumlah`, `biaya`, `id_buku`) VALUES
(1, '2014-12-18', 10, 10000, 1),
(2, '2014-11-02', 20, 100000, 1),
(3, '2014-11-27', 30, 200000, 3),
(4, '2014-11-19', 20, 90000, 4),
(5, '2014-11-20', 10, 100000, 2),
(6, '2014-11-02', 10, 250000, 3),
(7, '2014-12-11', 10, 1200000, 3),
(8, '2014-12-06', 2, 300000, 4),
(9, '2014-12-20', 2, 90000, 10),
(10, '2014-12-20', 8, 1200000, 4),
(11, '2014-12-19', 10, 1500000, 4),
(12, '2014-12-02', 10, 525000, 16);

-- --------------------------------------------------------

--
-- Table structure for table `penjualan`
--

CREATE TABLE IF NOT EXISTS `penjualan` (
  `id_penjualan` int(6) NOT NULL AUTO_INCREMENT,
  `tanggal` date NOT NULL,
  `jumlah` int(5) NOT NULL,
  `biaya` int(7) NOT NULL,
  `id_buku` int(3) NOT NULL,
  PRIMARY KEY (`id_penjualan`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `penjualan`
--

INSERT INTO `penjualan` (`id_penjualan`, `tanggal`, `jumlah`, `biaya`, `id_buku`) VALUES
(1, '2014-11-13', 10, 10000000, 1),
(2, '2014-11-15', 20, 250000, 2),
(3, '2014-11-13', 40, 1000000, 1),
(4, '2014-11-15', 20, 450000, 2),
(5, '2014-12-13', 2, 90000, 10),
(6, '2014-12-20', 90, 4950000, 11),
(7, '2014-12-02', 2, 25000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `suplier`
--

CREATE TABLE IF NOT EXISTS `suplier` (
  `id_suplier` int(3) NOT NULL AUTO_INCREMENT,
  `nama` char(100) NOT NULL,
  `kontak` char(13) NOT NULL,
  `alamat` char(100) NOT NULL,
  PRIMARY KEY (`id_suplier`),
  KEY `id` (`id_suplier`),
  KEY `id_suplier` (`id_suplier`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `suplier`
--

INSERT INTO `suplier` (`id_suplier`, `nama`, `kontak`, `alamat`) VALUES
(1, 'Gema Insani', '738191', 'Jln Setiabudhi No 193'),
(2, 'Gramedia Books', '738121', 'Jln Merdeka No 100'),
(3, 'Gramedia Pustaka Utama', '738131', 'Jln Riau No 9'),
(4, 'Maxikom', '738141', 'Jln Ternate No 193'),
(5, 'BijiSuplier', '0298293', 'Jln Mekar no 25'),
(6, 'Komik House', '0292933', 'Jln Mekar no 201'),
(7, 'Books House', '2738382', 'Jln Setiabudhi No 198'),
(8, '', '', ''),
(9, 'Andini Books', '9839383', 'Bandung'),
(10, '', '', '');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `pembelian`
--
ALTER TABLE `pembelian`
  ADD CONSTRAINT `pembelian_ibfk_2` FOREIGN KEY (`id_buku`) REFERENCES `pembelian` (`id_buku`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
