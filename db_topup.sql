-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 28, 2026 at 06:55 AM
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
-- Database: `db_topup`
--

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE `game` (
  `id_game` int(11) NOT NULL,
  `nama_game` varchar(100) NOT NULL,
  `developer` varchar(100) DEFAULT NULL,
  `kategori` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `game`
--

INSERT INTO `game` (`id_game`, `nama_game`, `developer`, `kategori`) VALUES
(1, 'Mobile Legends Bang Bang', 'Moonton', 'MOBA'),
(2, 'Free Fire', 'Garena', 'Battle Royale'),
(3, 'PUBG Mobile', 'Tencent', 'Battle Royale'),
(4, 'Valorant', 'Riot', 'FPS'),
(6, 'E-Football 2026', 'Konami', 'Mobile'),
(7, 'Roblox', 'Restu', 'Anak Anak');

-- --------------------------------------------------------

--
-- Table structure for table `nominal_topup`
--

CREATE TABLE `nominal_topup` (
  `id_nominal` int(11) NOT NULL,
  `id_game` int(11) DEFAULT NULL,
  `nama_nominal` varchar(100) DEFAULT NULL,
  `harga` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nominal_topup`
--

INSERT INTO `nominal_topup` (`id_nominal`, `id_game`, `nama_nominal`, `harga`) VALUES
(1, 1, '86 Diamonds', 20000),
(2, 1, '172 Diamonds\r\n', 40000),
(3, 1, '257 Diamonds', 60000),
(4, 2, '100 Diamonds', 15000),
(5, 2, '300 Diamonds', 45000),
(6, 2, '500 Diamonds', 70000),
(7, 3, '60 UC', 15000),
(8, 3, '180 UC', 50000),
(9, 3, '400 UC', 100000),
(10, 4, '500 Points', 60000),
(11, 4, '1000 Points', 110000),
(12, 4, '1500 Points', 170000),
(13, 6, '50 Points', 12000),
(14, 7, '20 Robux', 14000);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_game` int(11) DEFAULT NULL,
  `id_nominal` int(11) DEFAULT NULL,
  `user_game` varchar(100) DEFAULT NULL,
  `metode_pembayaran` varchar(50) NOT NULL,
  `tanggal` timestamp NOT NULL DEFAULT current_timestamp(),
  `status_pembayaran` enum('Pending','Berhasil') DEFAULT 'Pending'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_user`, `id_game`, `id_nominal`, `user_game`, `metode_pembayaran`, `tanggal`, `status_pembayaran`) VALUES
(1, 2, 1, 1, '123456789', '', '2026-05-27 15:46:11', 'Berhasil'),
(2, 2, 1, 1, '123456789', '', '2026-05-27 16:32:06', 'Berhasil'),
(3, 2, 1, 1, '123456789', '', '2026-05-27 16:32:57', 'Berhasil'),
(4, 2, 1, 1, '123240050', '', '2026-05-28 02:20:56', 'Berhasil'),
(5, 2, 2, 6, '123240099', '', '2026-05-28 02:21:49', 'Berhasil'),
(6, 2, 1, 1, '123240050', '', '2026-05-28 02:44:18', 'Berhasil'),
(8, 2, 3, 7, '11133313', '', '2026-05-28 02:45:17', 'Berhasil'),
(12, 4, 1, 3, '11223344', 'OVO', '2026-05-28 04:17:39', 'Berhasil'),
(13, 4, 3, 7, '12212122', 'Dana', '2026-05-28 04:24:30', 'Berhasil'),
(14, 4, 4, 10, '1221222', 'Dana', '2026-05-28 04:27:28', 'Berhasil');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nama_lengkap` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `role` enum('admin','user') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `username`, `password`, `nama_lengkap`, `email`, `role`) VALUES
(1, 'admin', 'admin123', 'Administrator', 'admin@gmail.com', 'admin'),
(2, 'user1', '123', 'Restu', 'restu@gmail.com', 'user'),
(3, 'restu', '1234', NULL, NULL, 'user'),
(4, 'res', 'res123', NULL, NULL, 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`id_game`);

--
-- Indexes for table `nominal_topup`
--
ALTER TABLE `nominal_topup`
  ADD PRIMARY KEY (`id_nominal`),
  ADD KEY `id_game` (`id_game`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_game` (`id_game`),
  ADD KEY `id_nominal` (`id_nominal`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `game`
--
ALTER TABLE `game`
  MODIFY `id_game` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `nominal_topup`
--
ALTER TABLE `nominal_topup`
  MODIFY `id_nominal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `nominal_topup`
--
ALTER TABLE `nominal_topup`
  ADD CONSTRAINT `nominal_topup_ibfk_1` FOREIGN KEY (`id_game`) REFERENCES `game` (`id_game`) ON DELETE CASCADE;

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE,
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`id_game`) REFERENCES `game` (`id_game`) ON DELETE CASCADE,
  ADD CONSTRAINT `transaksi_ibfk_3` FOREIGN KEY (`id_nominal`) REFERENCES `nominal_topup` (`id_nominal`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
