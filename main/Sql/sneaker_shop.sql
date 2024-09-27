-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 27, 2024 at 11:31 AM
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
-- Database: `sneaker_shop`
--

-- --------------------------------------------------------

--
-- Table structure for table `billdetail`
--

CREATE TABLE `billdetail` (
  `id` bigint(22) NOT NULL,
  `id_product` bigint(22) NOT NULL,
  `id_bills` bigint(22) NOT NULL,
  `total` double NOT NULL,
  `quanty` int(11) NOT NULL,
  `selected_size` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `billdetail`
--

INSERT INTO `billdetail` (`id`, `id_product`, `id_bills`, `total`, `quanty`, `selected_size`) VALUES
(74, 110, 104, 3600000, 3, '36'),
(75, 110, 115, 1200000, 1, '40'),
(76, 112, 116, 2400000, 2, '36'),
(77, 110, 116, 1200000, 1, '39'),
(78, 110, 117, 1200000, 1, '40'),
(79, 111, 118, 4800000, 4, '36'),
(80, 110, 119, 1200000, 1, '42'),
(81, 111, 119, 1200000, 1, '40');

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE `bills` (
  `id` bigint(20) NOT NULL,
  `user` varchar(255) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `address` text DEFAULT NULL,
  `total` double NOT NULL,
  `quanty` int(11) NOT NULL,
  `note` text DEFAULT NULL,
  `status` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `date` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bills`
--

INSERT INTO `bills` (`id`, `user`, `phone`, `display_name`, `address`, `total`, `quanty`, `note`, `status`, `id_client`, `date`) VALUES
(104, 'Quang@gmail.com', '0964429321', 'quang', 'null22', 3168000, 3, 'ada', 1, 2, '2024-09-17'),
(115, 'Quang@gmail.com', '9644298138', 'quang', 'null', 1056000, 1, '11', 1, 13, '2024-09-17'),
(116, 'Quang@gmail.com', '9644298138', 'quang', 'dsadsa', 3168000, 3, '11', 1, 13, '2024-09-18'),
(117, 'hoang5@gmail.com', '9644298138', 'quang', 'null', 1056000, 1, '11', 1, 11, '2024-09-27'),
(118, 'hkq2@gmail.com', '09644298138', 'Hoàng khai Quang', 'hieohtuan', 4224000, 4, 'ghbjk', 1, 24, '2024-09-27'),
(119, 'hkq2@gmail.com', '09644298138', 'Hoàng khai Quang', 'hieohtuan', 2112000, 2, 'ghbjk', 2, 24, '2024-09-27');

-- --------------------------------------------------------

--
-- Table structure for table `categorys`
--

CREATE TABLE `categorys` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categorys`
--

INSERT INTO `categorys` (`id`, `name`, `description`) VALUES
(2, 'Giày adidas', 'sản xuất và nhập ở trung quốc'),
(18, 'Giày Nike', 'Sản xuất ở mỹ'),
(19, 'Jordan ', 'fcvads');

-- --------------------------------------------------------

--
-- Table structure for table `contacts`
--

CREATE TABLE `contacts` (
  `id` int(11) NOT NULL,
  `content` text NOT NULL,
  `office_Hotline` varchar(11) NOT NULL,
  `customer_care_Hotline` varchar(11) NOT NULL,
  `purchase_Hotline` varchar(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `contacts`
--

INSERT INTO `contacts` (`id`, `content`, `office_Hotline`, `customer_care_Hotline`, `purchase_Hotline`, `address`, `email`) VALUES
(1, 'Chúng tôi luôn sẵn sàng lắng nghe và hỗ trợ bạn.\r\n Nếu bạn có bất kỳ câu hỏi nào hoặc cần thêm thông tin,\r\n xin vui lòng liên hệ với chúng tôi qua các kênh sau:', '0964889723', '0967401815', '0333892767', 'Hiệp thuận - phúc thọ - Hà Nội', 'hkquang@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `menus`
--

CREATE TABLE `menus` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `menus`
--

INSERT INTO `menus` (`id`, `name`, `url`) VALUES
(1, 'Trang Chủ', ''),
(2, 'Sản Phẩm', ''),
(3, 'So Sánh', ''),
(4, 'Giỏ Hàng', ''),
(5, 'Bài Viết', ''),
(6, 'Liên Hệ', '');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` bigint(20) NOT NULL,
  `id_category` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `sale` int(3) NOT NULL,
  `title` varchar(200) NOT NULL,
  `highlight` tinyint(1) NOT NULL,
  `new_product` tinyint(1) NOT NULL,
  `details` longtext NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `img` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `id_category`, `name`, `price`, `sale`, `title`, `highlight`, `new_product`, `details`, `created_at`, `updated_at`, `img`) VALUES
(109, 18, 'nike trắng', 1200000, 12, 'jgfhfg', 0, 0, 'aaaa', '2024-09-16 14:52:19', '2024-09-16 15:04:02', 'BLAZER+LOW+\'77+VNTG 1 - Copy.png,BLAZER+LOW+\'77+VNTG 1.png,BLAZER+LOW+\'77+VNTG 2 - Copy.png,BLAZER+LOW+\'77+VNTG 2.png'),
(110, 2, 'nike trắng3', 1200000, 12, 'rất đẹp', 3, 3, 'chua có gi', '2024-09-16 15:04:51', '2024-09-16 15:04:51', 'JORDAN+SPIZIKE+LOW (1).png,JORDAN+SPIZIKE+LOW (2).png,JORDAN+SPIZIKE+LOW.jpg,JORDAN+SPIZIKE+LOW.png'),
(111, 18, 'nike đỏ', 1200000, 12, 'rất đẹp', 3, 3, 'rất dẹp quá ok', '2024-09-16 15:38:04', '2024-09-16 15:38:04', 'NIKE+CORTEZD1.jpg,NIKE+CORTEZD2.png,NIKE+CORTEZD3.png,NIKE+CORTEZD4.png'),
(112, 18, 'nike hồng', 1200000, 12, 'rất đẹp', 1, 0, 'rất dẹo và chưa biết ghi gì', '2024-09-17 21:24:30', '2024-09-17 21:24:30', 'W+NIKE+CORTEZ 1.png,W+NIKE+CORTEZ 3.png,W+NIKE+CORTEZ.jpg,W+NIKE+CORTEZ.png');

-- --------------------------------------------------------

--
-- Table structure for table `product_size`
--

CREATE TABLE `product_size` (
  `id` int(11) NOT NULL,
  `id_productsize` bigint(20) DEFAULT NULL,
  `sizes` varchar(255) DEFAULT NULL,
  `quantity` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product_size`
--

INSERT INTO `product_size` (`id`, `id_productsize`, `sizes`, `quantity`) VALUES
(317, 109, '36', '32'),
(318, 109, '37', '2'),
(319, 109, '38', '2'),
(320, 109, '39', '2'),
(321, 109, '40', '2'),
(322, 109, '41', '2'),
(323, 109, '42', '2'),
(324, 110, '36', '0'),
(325, 110, '37', '2'),
(326, 110, '38', '2'),
(327, 110, '39', '1'),
(328, 110, '40', '0'),
(329, 110, '41', '2'),
(330, 110, '42', '1'),
(331, 111, '36', '-1'),
(332, 111, '37', '2'),
(333, 111, '38', '2'),
(334, 111, '39', '2'),
(335, 111, '40', '1'),
(336, 111, '41', '2'),
(337, 111, '42', '2'),
(338, 112, '36', '1'),
(339, 112, '37', '2'),
(340, 112, '38', '2'),
(341, 112, '39', '2'),
(342, 112, '40', '2'),
(343, 112, '41', '2'),
(344, 112, '42', '2');

-- --------------------------------------------------------

--
-- Table structure for table `slides`
--

CREATE TABLE `slides` (
  `id` int(11) NOT NULL,
  `img` varchar(255) NOT NULL,
  `caption` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `slides`
--

INSERT INTO `slides` (`id`, `img`, `caption`, `content`) VALUES
(1, '3258216.webp', 'Nike real', 'sale 15%'),
(61, 'slide5.jpg', 'Sản xuất ở hiệp thuận', 'Nike Fake'),
(62, 'th.jpg', 'Chất xịn', 'Giày Brave'),
(65, 'slide1.jpg', 'Sale sập sàn', 'Nike mới ra mắt');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_admin`
--

CREATE TABLE `tbl_admin` (
  `id` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `role` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_admin`
--

INSERT INTO `tbl_admin` (`id`, `email`, `username`, `password`, `address`, `phone`, `role`) VALUES
(17, 'Quang@gmail.com', 'Hoàng Khai Quang', '22', 'Xã hiệp thuận - Phúc thọ - Hà Nội', '964889723', 1),
(22, 'Qucaasdasdsadng@gmail.com', 'Hoanfg Quangasc', 's', 's', '0964429813', 2),
(23, 'Qucg@gmail.com', 'Hoanfg Quangasc', '12', 'agjcsajkcas', '0964429813', 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `address` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `display_name`, `address`, `created_at`, `updated_at`) VALUES
(2, 'q@gmail.com', '$2a$12$njOMCHFVoSf/kwbqYq/YmO6ZGVvP13uH0Z.gdgpDvqWDWUbnw6j9y', 'quangHoang', 'Xã hiệp thuận - Phúc thọ - Hà Nội', '2024-09-08 10:00:48', '2024-09-08 10:00:48'),
(3, 'Quang2@gmail.com', '$2a$12$69dHhcHiorTFKYpXTHyrbOANc1F66Jq/hiR.VWe8ynT05ywF3WuPm', 'quang', 'Hiewp thuan', '2024-09-08 13:11:39', '2024-09-08 13:11:39'),
(7, 'hkq@gmail.com', '$2a$12$jMzJohcyUJA4ZxXKD3.nweeSESxFf9K7pTIsJsfA4Vb69wANi9m8W', 'quang', 'null', '2024-09-11 09:50:05', '2024-09-11 09:50:05'),
(10, 'hoangquangdo245@gmail.com', '1', 'quang', 'sdvf', '2024-09-12 15:55:41', '2024-09-12 15:56:09'),
(11, 'hoang5@gmail.com', '1', 'quang', 'null', '2024-09-13 14:05:32', '2024-09-13 14:05:32'),
(13, 'Quang@gmail.com', '1', 'quang', 'null', '2024-09-15 15:44:51', '2024-09-15 15:44:51'),
(14, 'Quang222@gmail.com', '1', 'quang', 'null', '2024-09-15 15:45:05', '2024-09-15 15:45:05'),
(17, 'hoang44@gmail.com', '1', 'Hoàng khai Quang', 'Xã hiệp thuận - Phúc thọ - Hà Nội', '2024-09-17 16:27:52', '2024-09-17 16:27:52'),
(21, 'hoang224ww4@gmail.com', '2', 'Hoàng khai Quang', 'Xã hiệp thuận - Phúc thọ - Hà Nội', '2024-09-17 16:32:29', '2024-09-17 16:32:29'),
(23, 'hoang22ww4ww4@gmail.com', '22', 'Hoàng khai Quang', 'Xã hiệp thuận - Phúc thọ - Hà Nội', '2024-09-17 16:35:29', '2024-09-17 16:35:29'),
(24, 'hkq2@gmail.com', '11111111', 'Hoàng khai Quang', 'hieohtuan', '2024-09-27 05:15:29', '2024-09-27 05:15:29');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `billdetail`
--
ALTER TABLE `billdetail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_bills` (`id_bills`),
  ADD KEY `fk_id_product` (`id_product`);

--
-- Indexes for table `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_client` (`id_client`);

--
-- Indexes for table `categorys`
--
ALTER TABLE `categorys`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `contacts`
--
ALTER TABLE `contacts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `menus`
--
ALTER TABLE `menus`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_category` (`id_category`);

--
-- Indexes for table `product_size`
--
ALTER TABLE `product_size`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_productsize` (`id_productsize`);

--
-- Indexes for table `slides`
--
ALTER TABLE `slides`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_admin`
--
ALTER TABLE `tbl_admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `billdetail`
--
ALTER TABLE `billdetail`
  MODIFY `id` bigint(22) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=82;

--
-- AUTO_INCREMENT for table `bills`
--
ALTER TABLE `bills`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=120;

--
-- AUTO_INCREMENT for table `categorys`
--
ALTER TABLE `categorys`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `contacts`
--
ALTER TABLE `contacts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `menus`
--
ALTER TABLE `menus`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=113;

--
-- AUTO_INCREMENT for table `product_size`
--
ALTER TABLE `product_size`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=345;

--
-- AUTO_INCREMENT for table `slides`
--
ALTER TABLE `slides`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- AUTO_INCREMENT for table `tbl_admin`
--
ALTER TABLE `tbl_admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `billdetail`
--
ALTER TABLE `billdetail`
  ADD CONSTRAINT `fk_id_bills` FOREIGN KEY (`id_bills`) REFERENCES `bills` (`id`),
  ADD CONSTRAINT `fk_id_product` FOREIGN KEY (`id_product`) REFERENCES `products` (`id`);

--
-- Constraints for table `bills`
--
ALTER TABLE `bills`
  ADD CONSTRAINT `fk_id_client` FOREIGN KEY (`id_client`) REFERENCES `users` (`id`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`id_category`) REFERENCES `categorys` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `product_size`
--
ALTER TABLE `product_size`
  ADD CONSTRAINT `product_size_ibfk_1` FOREIGN KEY (`id_productsize`) REFERENCES `products` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
