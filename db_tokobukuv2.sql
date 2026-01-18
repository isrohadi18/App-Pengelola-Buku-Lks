-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 05, 2025 at 01:13 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_tokobukuv2`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_admin`
--

CREATE TABLE `tb_admin` (
  `kodeadmin` varchar(50) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `level` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_admin`
--

INSERT INTO `tb_admin` (`kodeadmin`, `nama`, `username`, `password`, `level`) VALUES
('ADM001', 'admin', 'admin', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `tb_dsiswa`
--

CREATE TABLE `tb_dsiswa` (
  `id_siswa` varchar(50) NOT NULL,
  `namasiswa` varchar(50) NOT NULL,
  `kelas` varchar(50) NOT NULL,
  `jeniskelamin` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_dsiswa`
--

INSERT INTO `tb_dsiswa` (`id_siswa`, `namasiswa`, `kelas`, `jeniskelamin`) VALUES
('IDS001', 'HAFIIDH', '10 TKJ B', 'LAKI-LAKI'),
('IDS002', 'RIDHO FIRMANSYAH', '10 TKJ D', 'LAKI-LAKI'),
('IDS003', 'TAUFIQ HIDAYTULLAH', '12 TKJ A', 'LAKI-LAKI');

-- --------------------------------------------------------

--
-- Table structure for table `tb_inputbuku`
--

CREATE TABLE `tb_inputbuku` (
  `kodetransaksi` varchar(50) NOT NULL,
  `namasupplier` varchar(50) NOT NULL,
  `kodebuku` varchar(50) NOT NULL,
  `namabuku` varchar(50) NOT NULL,
  `pengarang` varchar(50) NOT NULL,
  `penerbit` varchar(50) NOT NULL,
  `tahunterbit` varchar(50) NOT NULL,
  `tanggalinput` date NOT NULL,
  `jumlah` int(50) NOT NULL,
  `hargabeli` int(50) NOT NULL,
  `hargajual` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_inputbuku`
--

INSERT INTO `tb_inputbuku` (`kodetransaksi`, `namasupplier`, `kodebuku`, `namabuku`, `pengarang`, `penerbit`, `tahunterbit`, `tanggalinput`, `jumlah`, `hargabeli`, `hargajual`) VALUES
('TR001', 'HANTORO', 'KB001', 'BAHASA INDONESIA', 'PATRIOT', 'ERLANGGA', '2024', '2024-11-03', 8, 10000, 10000);

--
-- Triggers `tb_inputbuku`
--
DELIMITER $$
CREATE TRIGGER `replacehargajual` AFTER INSERT ON `tb_inputbuku` FOR EACH ROW BEGIN
UPDATE tb_stokbuku SET hargabuku=new.hargajual WHERE kodebuku=new.kodebuku;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `updatestok` AFTER INSERT ON `tb_inputbuku` FOR EACH ROW BEGIN
UPDATE tb_stokbuku SET stok=stok+new.jumlah WHERE kodebuku=new.kodebuku;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_penjualan`
--

CREATE TABLE `tb_penjualan` (
  `kodetransaksi` varchar(50) NOT NULL,
  `kodebuku` varchar(50) NOT NULL,
  `id_siswa` varchar(50) NOT NULL,
  `namasiswa` varchar(100) NOT NULL,
  `kelas` varchar(30) NOT NULL,
  `namabuku` varchar(50) NOT NULL,
  `pengarang` varchar(50) NOT NULL,
  `penerbit` varchar(50) NOT NULL,
  `tahunterbit` varchar(50) NOT NULL,
  `tanggal` date NOT NULL,
  `harga` int(50) NOT NULL,
  `jumlah` int(50) NOT NULL,
  `subtotal` int(50) NOT NULL,
  `total` int(50) NOT NULL,
  `bayar` int(50) NOT NULL,
  `kembalian` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_penjualan`
--

INSERT INTO `tb_penjualan` (`kodetransaksi`, `kodebuku`, `id_siswa`, `namasiswa`, `kelas`, `namabuku`, `pengarang`, `penerbit`, `tahunterbit`, `tanggal`, `harga`, `jumlah`, `subtotal`, `total`, `bayar`, `kembalian`) VALUES
('TR001', 'KB001', '', '', '', 'BAHASA INDONESIA', 'PATRIOT', 'ERLANGGA', '2024', '2024-04-20', 10000, 1, 10000, 10000, 15000, 5000),
('TR002', 'KB001', '', 'HAFIIDH', '10 TKJ B', 'BAHASA INDONESIA', 'PATRIOT', 'ERLANGGA', '2024', '2024-04-28', 10000, 1, 10000, 10000, 10000, 0),
('TR003', 'KB001', 'IDS001', 'HAFIIDH', '10 TKJ B', 'BAHASA INDONESIA', 'PATRIOT', 'ERLANGGA', '2024', '2024-04-28', 10000, 1, 10000, 10000, 10000, 0),
('TR004', 'KB001', 'IDS001', 'HAFIIDH', '10 TKJ B', 'BAHASA INDONESIA', 'PATRIOT', 'ERLANGGA', '2024', '2024-04-28', 10000, 1, 10000, 10000, 10000, 0),
('TR005', 'KB001', 'IDS001', 'HAFIIDH', '10 TKJ B', 'BAHASA INDONESIA', 'PATRIOT', 'ERLANGGA', '2024', '2024-04-28', 10000, 2, 20000, 20000, 20000, 0),
('TR006', 'KB001', 'IDS001', 'HAFIIDH', '10 TKJ B', 'BAHASA INDONESIA', 'PATRIOT', 'ERLANGGA', '2024', '2024-04-28', 10000, 2, 20000, 20000, 20000, 0);

--
-- Triggers `tb_penjualan`
--
DELIMITER $$
CREATE TRIGGER `updatestok2` AFTER INSERT ON `tb_penjualan` FOR EACH ROW BEGIN
UPDATE tb_stokbuku SET stok=stok-new.jumlah WHERE kodebuku=new.kodebuku;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_return`
--

CREATE TABLE `tb_return` (
  `idreturn` varchar(50) NOT NULL,
  `namasupplier` varchar(50) NOT NULL,
  `kodebuku` varchar(50) NOT NULL,
  `namabuku` varchar(50) NOT NULL,
  `pengarang` varchar(50) NOT NULL,
  `penerbit` varchar(50) NOT NULL,
  `tahunterbit` varchar(50) NOT NULL,
  `tanggalinput` date NOT NULL,
  `jumlah` int(50) NOT NULL,
  `hargabeli` int(50) NOT NULL,
  `hargajual` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_return`
--

INSERT INTO `tb_return` (`idreturn`, `namasupplier`, `kodebuku`, `namabuku`, `pengarang`, `penerbit`, `tahunterbit`, `tanggalinput`, `jumlah`, `hargabeli`, `hargajual`) VALUES
('IDR001', 'HANTORO', 'KB001', 'BAHASA INDONESIA', 'PATRIOT', 'ERLANGGA', '2024', '2024-11-03', 100, 9000, 10000),
('IDR002', 'DRS. SULAEMAN', 'KB001', 'BAHASA INDONESIA', 'PATRIOT', 'ERLANGGA', '2024', '2024-12-13', 100, 9000, 10000);

-- --------------------------------------------------------

--
-- Table structure for table `tb_stokbuku`
--

CREATE TABLE `tb_stokbuku` (
  `kodebuku` varchar(50) NOT NULL,
  `namabuku` varchar(50) NOT NULL,
  `pengarang` varchar(50) NOT NULL,
  `penerbit` varchar(50) NOT NULL,
  `tahunterbit` varchar(50) NOT NULL,
  `hargabuku` int(50) NOT NULL,
  `stok` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_stokbuku`
--

INSERT INTO `tb_stokbuku` (`kodebuku`, `namabuku`, `pengarang`, `penerbit`, `tahunterbit`, `hargabuku`, `stok`) VALUES
('KB001', 'BAHASA INDONESIA', 'PATRIOT', 'ERLANGGA', '2024', 10000, 100),
('KB002', 'BAHASA INGGRIS', 'PATRIOT', 'ERLANGGA', '2024', 10000, 100);

-- --------------------------------------------------------

--
-- Table structure for table `tb_supplier`
--

CREATE TABLE `tb_supplier` (
  `kodesupplier` varchar(50) NOT NULL,
  `namasupplier` varchar(50) NOT NULL,
  `tlp` varchar(50) NOT NULL,
  `alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_supplier`
--

INSERT INTO `tb_supplier` (`kodesupplier`, `namasupplier`, `tlp`, `alamat`) VALUES
('KDS001', 'HANTORO', '089647632429', 'JAKARTA'),
('KDS002', 'DRS. SULAEMAN', '089747246718', 'JAKARTA');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_admin`
--
ALTER TABLE `tb_admin`
  ADD PRIMARY KEY (`kodeadmin`);

--
-- Indexes for table `tb_dsiswa`
--
ALTER TABLE `tb_dsiswa`
  ADD PRIMARY KEY (`id_siswa`);

--
-- Indexes for table `tb_inputbuku`
--
ALTER TABLE `tb_inputbuku`
  ADD PRIMARY KEY (`kodetransaksi`);

--
-- Indexes for table `tb_return`
--
ALTER TABLE `tb_return`
  ADD PRIMARY KEY (`idreturn`);

--
-- Indexes for table `tb_stokbuku`
--
ALTER TABLE `tb_stokbuku`
  ADD PRIMARY KEY (`kodebuku`);

--
-- Indexes for table `tb_supplier`
--
ALTER TABLE `tb_supplier`
  ADD PRIMARY KEY (`kodesupplier`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
