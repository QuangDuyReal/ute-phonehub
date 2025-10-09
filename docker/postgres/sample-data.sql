-- ============================================================================
-- UTE Phone Hub - Sample Data Script
-- ============================================================================
-- This script inserts comprehensive sample data for all tables
-- Safe to run on Cloud SQL - uses ON CONFLICT to prevent duplicates
-- Execute this after init.sql
-- ============================================================================

-- ============================================================================
-- 1. USERS - Sample users with different roles
-- ============================================================================
-- Password for all users: Password123!
-- Hash generated with BCrypt (strength 12)

INSERT INTO users (username, full_name, email, password_hash, phone_number, role, status) VALUES 
('admin', 'Quản Trị Viên', 'admin@utephonehub.com', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj4J/8Kz8KzK', '0901234567', 'admin', 'active'),
('nguyenvana', 'Nguyễn Văn A', 'nguyenvana@gmail.com', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj4J/8Kz8KzK', '0912345678', 'customer', 'active'),
('tranthib', 'Trần Thị B', 'tranthib@gmail.com', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj4J/8Kz8KzK', '0923456789', 'customer', 'active'),
('levanc', 'Lê Văn C', 'levanc@gmail.com', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj4J/8Kz8KzK', '0934567890', 'customer', 'active'),
('phamthid', 'Phạm Thị D', 'phamthid@gmail.com', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj4J/8Kz8KzK', '0945678901', 'customer', 'active'),
('hoangvane', 'Hoàng Văn E', 'hoangvane@gmail.com', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj4J/8Kz8KzK', '0956789012', 'customer', 'active'),
('vuthif', 'Vũ Thị F', 'vuthif@gmail.com', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj4J/8Kz8KzK', '0967890123', 'customer', 'active'),
('dovan.g', 'Đỗ Văn G', 'dovang@gmail.com', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj4J/8Kz8KzK', '0978901234', 'customer', 'active'),
('buithih', 'Bùi Thị H', 'buithih@gmail.com', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj4J/8Kz8KzK', '0989012345', 'customer', 'active'),
('ngothii', 'Ngô Thị I', 'ngothii@gmail.com', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj4J/8Kz8KzK', '0990123456', 'customer', 'active'),
('duongvank', 'Dương Văn K', 'duongvank@gmail.com', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj4J/8Kz8KzK', '0901234568', 'customer', 'active')
ON CONFLICT (email) DO NOTHING;

-- ============================================================================
-- 2. ADDRESSES - Multiple addresses for users
-- ============================================================================

INSERT INTO addresses (user_id, recipient_name, phone_number, street_address, city, is_default) VALUES 
(2, 'Nguyễn Văn A', '0912345678', '123 Đường Lê Lợi, Phường Bến Thành, Quận 1', 'Hồ Chí Minh', true),
(2, 'Nguyễn Văn A', '0912345678', '456 Đường Nguyễn Huệ, Phường Bến Nghé, Quận 1', 'Hồ Chí Minh', false),
(3, 'Trần Thị B', '0923456789', '789 Đường Trần Hưng Đạo, Phường Cầu Kho, Quận 1', 'Hồ Chí Minh', true),
(4, 'Lê Văn C', '0934567890', '321 Đường Hai Bà Trưng, Phường Tân Định, Quận 1', 'Hồ Chí Minh', true),
(5, 'Phạm Thị D', '0945678901', '654 Đường Võ Văn Tần, Phường 5, Quận 3', 'Hồ Chí Minh', true),
(6, 'Hoàng Văn E', '0956789012', '987 Đường Pasteur, Phường 6, Quận 3', 'Hồ Chí Minh', true),
(7, 'Vũ Thị F', '0967890123', '147 Đường Điện Biên Phủ, Phường Đa Kao, Quận 1', 'Hồ Chí Minh', true),
(8, 'Đỗ Văn G', '0978901234', '258 Đường Nam Kỳ Khởi Nghĩa, Phường 7, Quận 3', 'Hồ Chí Minh', true),
(9, 'Bùi Thị H', '0989012345', '369 Đường Lý Tự Trọng, Phường Bến Thành, Quận 1', 'Hồ Chí Minh', true),
(10, 'Ngô Thị I', '0990123456', '741 Đường Cách Mạng Tháng 8, Phường 6, Quận 3', 'Hồ Chí Minh', true)
ON CONFLICT DO NOTHING;

-- ============================================================================
-- 3. CATEGORIES - Product categories with hierarchical structure
-- ============================================================================

INSERT INTO categories (id, name, description, parent_id) VALUES 
(1, 'Điện thoại', 'Điện thoại di động các loại', NULL),
(2, 'Phụ kiện', 'Phụ kiện điện thoại', NULL),
(3, 'Máy tính bảng', 'Máy tính bảng và iPad', NULL),
(4, 'Laptop', 'Máy tính xách tay', NULL),
(5, 'Đồng hồ thông minh', 'Smartwatch và fitness tracker', NULL),

-- Subcategories for Điện thoại
(11, 'iPhone', 'Điện thoại iPhone của Apple', 1),
(12, 'Samsung', 'Điện thoại Samsung', 1),
(13, 'Xiaomi', 'Điện thoại Xiaomi', 1),
(14, 'OPPO', 'Điện thoại OPPO', 1),
(15, 'Vivo', 'Điện thoại Vivo', 1),

-- Subcategories for Phụ kiện
(21, 'Ốp lưng', 'Ốp lưng bảo vệ điện thoại', 2),
(22, 'Sạc và cáp', 'Sạc dự phòng, cáp sạc', 2),
(23, 'Tai nghe', 'Tai nghe có dây và không dây', 2),
(24, 'Kính cường lực', 'Miếng dán kính cường lực', 2),
(25, 'Giá đỡ', 'Giá đỡ điện thoại, tripod', 2)
ON CONFLICT (id) DO NOTHING;

-- Reset sequence for categories
SELECT setval('categories_id_seq', (SELECT MAX(id) FROM categories));

-- ============================================================================
-- 4. BRANDS - Phone and accessory brands
-- ============================================================================

INSERT INTO brands (id, name, description, logo_url) VALUES 
(1, 'Apple', 'Thương hiệu công nghệ hàng đầu với iPhone, iPad, MacBook', 'https://upload.wikimedia.org/wikipedia/commons/f/fa/Apple_logo_black.svg'),
(2, 'Samsung', 'Tập đoàn công nghệ Hàn Quốc với dòng Galaxy', 'https://upload.wikimedia.org/wikipedia/commons/2/24/Samsung_Logo.svg'),
(3, 'Xiaomi', 'Thương hiệu công nghệ Trung Quốc với Mi, Redmi', 'https://upload.wikimedia.org/wikipedia/commons/2/29/Xiaomi_logo.svg'),
(4, 'OPPO', 'Thương hiệu điện thoại với công nghệ camera tiên tiến', 'https://upload.wikimedia.org/wikipedia/commons/c/c0/OPPO_Logo.svg'),
(5, 'Vivo', 'Thương hiệu điện thoại với thiết kế sang trọng', 'https://upload.wikimedia.org/wikipedia/commons/e/e7/Vivo_logo.svg'),
(6, 'Realme', 'Thương hiệu điện thoại dành cho giới trẻ', 'https://upload.wikimedia.org/wikipedia/commons/9/91/Realme_logo.svg'),
(7, 'OnePlus', 'Flagship killer với hiệu năng cao', 'https://upload.wikimedia.org/wikipedia/commons/0/09/OnePlus_logo.svg'),
(8, 'Huawei', 'Thương hiệu công nghệ Trung Quốc', 'https://upload.wikimedia.org/wikipedia/commons/0/04/Huawei_Standard_logo.svg'),
(9, 'Nokia', 'Thương hiệu điện thoại kinh điển', 'https://upload.wikimedia.org/wikipedia/commons/0/08/Nokia_logo_2023.svg'),
(10, 'Sony', 'Điện thoại Xperia cao cấp', 'https://upload.wikimedia.org/wikipedia/commons/c/ca/Sony_logo.svg'),
(11, 'Anker', 'Phụ kiện sạc dự phòng, cáp sạc', 'https://d2zv4gzhlr4ud6.cloudfront.net/media/pictures/tagged_items/540x0/21_ANKERDIRECT/01.jpg'),
(12, 'Baseus', 'Phụ kiện điện thoại chất lượng cao', NULL),
(13, 'JBL', 'Tai nghe và loa Bluetooth', 'https://upload.wikimedia.org/wikipedia/commons/c/c1/JBL_Logo.svg'),
(14, 'Beats', 'Tai nghe cao cấp của Apple', 'https://upload.wikimedia.org/wikipedia/commons/9/90/Beats_Electronics_logo.svg'),
(15, 'AirPods', 'Tai nghe không dây của Apple', NULL)
ON CONFLICT (id) DO NOTHING;

-- Reset sequence for brands
SELECT setval('brands_id_seq', (SELECT MAX(id) FROM brands));

-- ============================================================================
-- 5. PRODUCTS - Comprehensive product catalog
-- ============================================================================

INSERT INTO products (name, description, price, stock_quantity, thumbnail_url, specifications, status, category_id, brand_id) VALUES 
-- iPhone Series
('iPhone 15 Pro Max 256GB', 'iPhone 15 Pro Max với chip A17 Pro mạnh mẽ, camera 48MP, màn hình Super Retina XDR 6.7 inch', 32990000, 50, 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-blue-1.jpg', '{"screen": "6.7 inch", "ram": "8GB", "storage": "256GB", "camera": "48MP", "battery": "4422mAh", "chip": "A17 Pro"}', true, 11, 1),
('iPhone 15 Pro 128GB', 'iPhone 15 Pro với khung titan, Dynamic Island, camera 48MP', 27990000, 45, 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-natural-1.jpg', '{"screen": "6.1 inch", "ram": "8GB", "storage": "128GB", "camera": "48MP", "battery": "3274mAh", "chip": "A17 Pro"}', true, 11, 1),
('iPhone 15 Plus 128GB', 'iPhone 15 Plus màn hình lớn 6.7 inch, camera 48MP', 24990000, 60, 'https://cdn.tgdd.vn/Products/Images/42/303891/iphone-15-plus-128gb-blue-1.jpg', '{"screen": "6.7 inch", "ram": "6GB", "storage": "128GB", "camera": "48MP", "battery": "4383mAh", "chip": "A16 Bionic"}', true, 11, 1),
('iPhone 14 128GB', 'iPhone 14 với chip A15 Bionic, camera kép 12MP', 18990000, 80, 'https://cdn.tgdd.vn/Products/Images/42/289441/iphone-14-blue-1.jpg', '{"screen": "6.1 inch", "ram": "6GB", "storage": "128GB", "camera": "12MP", "battery": "3279mAh", "chip": "A15 Bionic"}', true, 11, 1),
('iPhone 13 128GB', 'iPhone 13 giá tốt, hiệu năng mạnh mẽ', 15990000, 100, 'https://cdn.tgdd.vn/Products/Images/42/223602/iphone-13-pink-1.jpg', '{"screen": "6.1 inch", "ram": "4GB", "storage": "128GB", "camera": "12MP", "battery": "3240mAh", "chip": "A15 Bionic"}', true, 11, 1),

-- Samsung Galaxy Series
('Samsung Galaxy S24 Ultra 12GB/256GB', 'Galaxy S24 Ultra với S Pen, camera 200MP, chip Snapdragon 8 Gen 3', 29990000, 40, 'https://cdn.tgdd.vn/Products/Images/42/307174/samsung-galaxy-s24-ultra-grey-1.jpg', '{"screen": "6.8 inch", "ram": "12GB", "storage": "256GB", "camera": "200MP", "battery": "5000mAh", "chip": "Snapdragon 8 Gen 3"}', true, 12, 2),
('Samsung Galaxy S23 Ultra 8GB/256GB', 'Galaxy S23 Ultra camera 200MP, hiệu năng đỉnh cao', 25990000, 35, 'https://cdn.tgdd.vn/Products/Images/42/249948/samsung-galaxy-s23-ultra-1.jpg', '{"screen": "6.8 inch", "ram": "8GB", "storage": "256GB", "camera": "200MP", "battery": "5000mAh", "chip": "Snapdragon 8 Gen 2"}', true, 12, 2),
('Samsung Galaxy Z Fold5 12GB/256GB', 'Điện thoại gập cao cấp với màn hình Dynamic AMOLED 2X', 40990000, 20, 'https://cdn.tgdd.vn/Products/Images/42/306174/samsung-galaxy-z-fold5-1.jpg', '{"screen": "7.6 inch", "ram": "12GB", "storage": "256GB", "camera": "50MP", "battery": "4400mAh", "chip": "Snapdragon 8 Gen 2"}', true, 12, 2),
('Samsung Galaxy Z Flip5 8GB/256GB', 'Điện thoại gập nhỏ gọn, thời trang', 23990000, 25, 'https://cdn.tgdd.vn/Products/Images/42/306172/samsung-galaxy-z-flip5-1.jpg', '{"screen": "6.7 inch", "ram": "8GB", "storage": "256GB", "camera": "12MP", "battery": "3700mAh", "chip": "Snapdragon 8 Gen 2"}', true, 12, 2),
('Samsung Galaxy A54 5G 8GB/128GB', 'Galaxy A54 camera 50MP, pin 5000mAh', 9990000, 120, 'https://cdn.tgdd.vn/Products/Images/42/301726/samsung-galaxy-a54-5g-1.jpg', '{"screen": "6.4 inch", "ram": "8GB", "storage": "128GB", "camera": "50MP", "battery": "5000mAh", "chip": "Exynos 1380"}', true, 12, 2),

-- Xiaomi Series
('Xiaomi 14 Pro 12GB/512GB', 'Xiaomi 14 Pro với camera Leica, chip Snapdragon 8 Gen 3', 21990000, 50, 'https://cdn.tgdd.vn/Products/Images/42/309816/xiaomi-14-pro-1.jpg', '{"screen": "6.73 inch", "ram": "12GB", "storage": "512GB", "camera": "50MP", "battery": "4880mAh", "chip": "Snapdragon 8 Gen 3"}', true, 13, 3),
('Xiaomi 13T Pro 12GB/256GB', 'Xiaomi 13T Pro camera 50MP, sạc nhanh 120W', 13990000, 80, 'https://cdn.tgdd.vn/Products/Images/42/307594/xiaomi-13t-pro-1.jpg', '{"screen": "6.67 inch", "ram": "12GB", "storage": "256GB", "camera": "50MP", "battery": "5000mAh", "chip": "Dimensity 9200+"}', true, 13, 3),
('Xiaomi Redmi Note 13 Pro 8GB/128GB', 'Redmi Note 13 Pro camera 200MP, giá rẻ', 7490000, 150, 'https://cdn.tgdd.vn/Products/Images/42/309816/xiaomi-redmi-note-13-pro-1.jpg', '{"screen": "6.67 inch", "ram": "8GB", "storage": "128GB", "camera": "200MP", "battery": "5100mAh", "chip": "Snapdragon 7s Gen 2"}', true, 13, 3),
('Xiaomi Redmi 12 8GB/256GB', 'Redmi 12 pin khủng 5000mAh, giá tốt', 4490000, 200, 'https://cdn.tgdd.vn/Products/Images/42/307127/xiaomi-redmi-12-1.jpg', '{"screen": "6.79 inch", "ram": "8GB", "storage": "256GB", "camera": "50MP", "battery": "5000mAh", "chip": "Helio G88"}', true, 13, 3),

-- OPPO Series
('OPPO Find N3 Flip 12GB/256GB', 'OPPO Find N3 Flip điện thoại gập camera 50MP', 22990000, 30, 'https://cdn.tgdd.vn/Products/Images/42/309321/oppo-find-n3-flip-1.jpg', '{"screen": "6.8 inch", "ram": "12GB", "storage": "256GB", "camera": "50MP", "battery": "4300mAh", "chip": "Dimensity 9200"}', true, 14, 4),
('OPPO Reno11 F 5G 8GB/256GB', 'OPPO Reno11 F camera 64MP, thiết kế đẹp', 8990000, 100, 'https://cdn.tgdd.vn/Products/Images/42/309914/oppo-reno11-f-1.jpg', '{"screen": "6.7 inch", "ram": "8GB", "storage": "256GB", "camera": "64MP", "battery": "5000mAh", "chip": "Dimensity 7050"}', true, 14, 4),
('OPPO A78 8GB/256GB', 'OPPO A78 pin 5000mAh, sạc nhanh 67W', 6490000, 150, 'https://cdn.tgdd.vn/Products/Images/42/301816/oppo-a78-1.jpg', '{"screen": "6.43 inch", "ram": "8GB", "storage": "256GB", "camera": "50MP", "battery": "5000mAh", "chip": "Snapdragon 680"}', true, 14, 4),

-- Vivo Series
('Vivo V29e 5G 12GB/256GB', 'Vivo V29e camera 64MP, thiết kế mỏng nhẹ', 8490000, 90, 'https://cdn.tgdd.vn/Products/Images/42/307951/vivo-v29e-1.jpg', '{"screen": "6.67 inch", "ram": "12GB", "storage": "256GB", "camera": "64MP", "battery": "4800mAh", "chip": "Snapdragon 695"}', true, 15, 5),
('Vivo Y36 8GB/128GB', 'Vivo Y36 pin 5000mAh, hiệu năng tốt', 5990000, 120, 'https://cdn.tgdd.vn/Products/Images/42/307421/vivo-y36-1.jpg', '{"screen": "6.64 inch", "ram": "8GB", "storage": "128GB", "camera": "50MP", "battery": "5000mAh", "chip": "Snapdragon 680"}', true, 15, 5),

-- Tablets
('iPad Pro M2 11 inch WiFi 128GB', 'iPad Pro M2 với chip M2 mạnh mẽ, màn hình Liquid Retina', 21990000, 40, 'https://cdn.tgdd.vn/Products/Images/522/294184/ipad-pro-11-2022-m2-wifi-1.jpg', '{"screen": "11 inch", "ram": "8GB", "storage": "128GB", "chip": "Apple M2", "os": "iPadOS 16"}', true, 3, 1),
('iPad Air 5 M1 WiFi 64GB', 'iPad Air 5 với chip M1, hỗ trợ Apple Pencil 2', 14990000, 50, 'https://cdn.tgdd.vn/Products/Images/522/285589/ipad-air-5-2022-m1-wifi-1.jpg', '{"screen": "10.9 inch", "ram": "8GB", "storage": "64GB", "chip": "Apple M1", "os": "iPadOS 15"}', true, 3, 1),
('iPad Gen 10 WiFi 64GB', 'iPad Gen 10 màn hình 10.9 inch, chip A14 Bionic', 10990000, 80, 'https://cdn.tgdd.vn/Products/Images/522/289686/ipad-gen-10-2022-wifi-1.jpg', '{"screen": "10.9 inch", "ram": "4GB", "storage": "64GB", "chip": "A14 Bionic", "os": "iPadOS 16"}', true, 3, 1),
('Samsung Galaxy Tab S9 FE 8GB/128GB', 'Galaxy Tab S9 FE với S Pen, màn hình 10.9 inch', 10490000, 60, 'https://cdn.tgdd.vn/Products/Images/522/309126/samsung-galaxy-tab-s9-fe-1.jpg', '{"screen": "10.9 inch", "ram": "8GB", "storage": "128GB", "chip": "Exynos 1380", "os": "Android 13"}', true, 3, 2),

-- Accessories - Ốp lưng
('Ốp lưng iPhone 15 Pro Max MagSafe', 'Ốp lưng silicone hỗ trợ MagSafe cho iPhone 15 Pro Max', 890000, 300, NULL, '{"material": "Silicone", "features": "MagSafe", "color": "Nhiều màu"}', true, 21, 1),
('Ốp lưng Samsung S24 Ultra Clear', 'Ốp lưng trong suốt chống sốc cho Galaxy S24 Ultra', 450000, 250, NULL, '{"material": "TPU", "features": "Chống sốc", "color": "Trong suốt"}', true, 21, 2),
('Ốp lưng Xiaomi 14 Pro Carbon', 'Ốp lưng sợi Carbon cao cấp cho Xiaomi 14 Pro', 350000, 200, NULL, '{"material": "Carbon", "features": "Chống va đập", "color": "Đen"}', true, 21, 3),

-- Accessories - Sạc và cáp
('Sạc nhanh Apple 20W USB-C', 'Củ sạc nhanh 20W chính hãng Apple với cổng USB-C', 490000, 500, NULL, '{"power": "20W", "port": "USB-C", "compatible": "iPhone 8 trở lên"}', true, 22, 1),
('Cáp Lightning to USB-C 1m', 'Cáp sạc Lightning to USB-C chính hãng Apple', 490000, 600, NULL, '{"length": "1m", "connector": "Lightning to USB-C", "support": "Fast charging"}', true, 22, 1),
('Sạc dự phòng Anker 20000mAh 65W', 'Sạc dự phòng Anker PowerCore III Elite dung lượng 20000mAh, sạc nhanh 65W', 1990000, 150, NULL, '{"capacity": "20000mAh", "power": "65W", "ports": "2x USB-C, 1x USB-A"}', true, 22, 11),
('Sạc nhanh Baseus GaN 100W', 'Củ sạc GaN 100W nhỏ gọn với 4 cổng sạc', 890000, 200, NULL, '{"power": "100W", "ports": "3x USB-C, 1x USB-A", "technology": "GaN"}', true, 22, 12),
('Cáp USB-C to USB-C 2m Baseus', 'Cáp sạc nhanh USB-C to USB-C 100W, dài 2m', 250000, 400, NULL, '{"length": "2m", "power": "100W", "connector": "USB-C to USB-C"}', true, 22, 12),

-- Accessories - Tai nghe
('AirPods Pro 2 USB-C', 'Tai nghe AirPods Pro thế hệ 2 với chip H2, chống ồn chủ động', 5990000, 100, NULL, '{"type": "In-ear", "connectivity": "Bluetooth 5.3", "features": "ANC, Spatial Audio", "battery": "6 giờ"}', true, 23, 15),
('AirPods 3', 'Tai nghe AirPods thế hệ 3 với Spatial Audio', 4490000, 120, NULL, '{"type": "Open-ear", "connectivity": "Bluetooth 5.0", "features": "Spatial Audio", "battery": "6 giờ"}', true, 23, 15),
('Samsung Galaxy Buds2 Pro', 'Tai nghe Galaxy Buds2 Pro chống ồn, âm thanh Hi-Fi', 3990000, 90, NULL, '{"type": "In-ear", "connectivity": "Bluetooth 5.3", "features": "ANC, 360 Audio", "battery": "5 giờ"}', true, 23, 2),
('JBL Tune 230NC TWS', 'Tai nghe JBL Tune 230NC chống ồn chủ động', 1790000, 150, NULL, '{"type": "In-ear", "connectivity": "Bluetooth 5.3", "features": "ANC", "battery": "8 giờ"}', true, 23, 13),
('Beats Studio Buds+', 'Tai nghe Beats Studio Buds+ chống ồn, âm bass mạnh', 3490000, 80, NULL, '{"type": "In-ear", "connectivity": "Bluetooth 5.3", "features": "ANC, Transparency", "battery": "9 giờ"}', true, 23, 14),

-- Accessories - Kính cường lực
('Kính cường lực iPhone 15 Pro Max', 'Miếng dán kính cường lực full màn hình cho iPhone 15 Pro Max', 290000, 500, NULL, '{"hardness": "9H", "coverage": "Full screen", "features": "Chống vỡ, chống xước"}', true, 24, 1),
('Kính cường lực Samsung S24 Ultra UV', 'Kính cường lực UV cho Galaxy S24 Ultra, bảo vệ toàn diện', 390000, 300, NULL, '{"hardness": "9H", "coverage": "Full screen", "features": "UV glue, chống vân tay"}', true, 24, 2),

-- Accessories - Giá đỡ
('Giá đỡ điện thoại MagSafe 3in1', 'Đế sạc không dây MagSafe 3 trong 1 cho iPhone, AirPods, Apple Watch', 1290000, 100, NULL, '{"type": "Wireless charger stand", "devices": "3 devices", "features": "MagSafe, Fast charging"}', true, 25, 11),
('Tripod mini Bluetooth', 'Gậy chụp ảnh kiêm tripod điều khiển Bluetooth', 250000, 400, NULL, '{"height": "Tối đa 70cm", "features": "Bluetooth remote", "weight": "150g"}', true, 25, 12)
ON CONFLICT DO NOTHING;

-- ============================================================================
-- 6. PRODUCT IMAGES - Multiple images per product
-- ============================================================================

INSERT INTO product_images (product_id, image_url, alt_text, is_primary) VALUES 
-- iPhone 15 Pro Max images
(1, 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-blue-1.jpg', 'iPhone 15 Pro Max màu xanh', true),
(1, 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-blue-2.jpg', 'iPhone 15 Pro Max mặt sau', false),
(1, 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-blue-3.jpg', 'iPhone 15 Pro Max camera', false),
(1, 'https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-blue-4.jpg', 'iPhone 15 Pro Max màn hình', false),

-- Samsung S24 Ultra images
(6, 'https://cdn.tgdd.vn/Products/Images/42/307174/samsung-galaxy-s24-ultra-grey-1.jpg', 'Samsung S24 Ultra màu xám', true),
(6, 'https://cdn.tgdd.vn/Products/Images/42/307174/samsung-galaxy-s24-ultra-grey-2.jpg', 'Samsung S24 Ultra S Pen', false),
(6, 'https://cdn.tgdd.vn/Products/Images/42/307174/samsung-galaxy-s24-ultra-grey-3.jpg', 'Samsung S24 Ultra camera', false),

-- Xiaomi 14 Pro images
(11, 'https://cdn.tgdd.vn/Products/Images/42/309816/xiaomi-14-pro-1.jpg', 'Xiaomi 14 Pro màu đen', true),
(11, 'https://cdn.tgdd.vn/Products/Images/42/309816/xiaomi-14-pro-2.jpg', 'Xiaomi 14 Pro mặt sau', false),
(11, 'https://cdn.tgdd.vn/Products/Images/42/309816/xiaomi-14-pro-3.jpg', 'Xiaomi 14 Pro camera Leica', false)
ON CONFLICT DO NOTHING;

-- ============================================================================
-- 7. VOUCHERS - Discount vouchers
-- ============================================================================

INSERT INTO vouchers (code, discount_type, discount_value, max_usage, min_order_value, expiry_date, status) VALUES 
('WELCOME10', 'percentage', 10.00, 1000, 1000000, '2025-12-31 23:59:59', 'active'),
('SALE50K', 'fixed_amount', 50000, 500, 2000000, '2025-12-31 23:59:59', 'active'),
('FREESHIP', 'fixed_amount', 30000, 2000, 500000, '2025-12-31 23:59:59', 'active'),
('NEWYEAR2025', 'percentage', 15.00, 100, 5000000, '2025-12-31 23:59:59', 'active'),
('FLASH100K', 'fixed_amount', 100000, 50, 3000000, '2025-11-30 23:59:59', 'active'),
('MEMBER20', 'percentage', 20.00, 200, 10000000, '2025-12-31 23:59:59', 'active'),
('TECH200K', 'fixed_amount', 200000, 30, 15000000, '2025-10-31 23:59:59', 'active'),
('SUMMER2025', 'percentage', 25.00, 150, 8000000, '2025-08-31 23:59:59', 'active'),
('VIP500K', 'fixed_amount', 500000, 20, 20000000, '2025-12-31 23:59:59', 'active'),
('STUDENT15', 'percentage', 15.00, 500, 3000000, '2025-12-31 23:59:59', 'active')
ON CONFLICT (code) DO NOTHING;

-- ============================================================================
-- 8. CARTS - Shopping carts for users
-- ============================================================================

INSERT INTO carts (user_id) VALUES 
(2), (3), (4), (5), (6), (7), (8), (9), (10), (11)
ON CONFLICT (user_id) DO NOTHING;

-- ============================================================================
-- 9. CART ITEMS - Items in shopping carts
-- ============================================================================

INSERT INTO cart_items (cart_id, product_id, quantity) VALUES 
-- Cart của Nguyễn Văn A (cart_id = 1)
(1, 1, 1),  -- iPhone 15 Pro Max
(1, 31, 1), -- Ốp lưng iPhone 15 Pro Max
(1, 32, 2), -- Kính cường lực

-- Cart của Trần Thị B (cart_id = 2)
(2, 6, 1),  -- Samsung S24 Ultra
(2, 38, 1), -- Tai nghe Galaxy Buds2 Pro

-- Cart của Lê Văn C (cart_id = 3)
(3, 11, 1), -- Xiaomi 14 Pro
(3, 35, 1), -- Sạc dự phòng Anker

-- Cart của Phạm Thị D (cart_id = 4)
(4, 21, 1), -- iPad Pro M2
(4, 37, 1), -- AirPods Pro 2

-- Cart của Hoàng Văn E (cart_id = 5)
(5, 14, 2), -- Xiaomi Redmi 12
(5, 40, 2)  -- Tripod mini
ON CONFLICT (cart_id, product_id) DO NOTHING;

-- ============================================================================
-- 10. ORDERS - Sample orders with various statuses
-- ============================================================================

INSERT INTO orders (order_code, user_id, email, recipient_name, phone_number, street_address, city, status, payment_method, total_amount, voucher_id, created_at, updated_at) VALUES 
('ORD20250901001', 2, 'nguyenvana@gmail.com', 'Nguyễn Văn A', '0912345678', '123 Đường Lê Lợi, Phường Bến Thành, Quận 1', 'Hồ Chí Minh', 'delivered', 'bank_transfer', 17991000, 2, '2025-09-01 10:30:00', '2025-09-05 14:20:00'),
('ORD20250902002', 3, 'tranthib@gmail.com', 'Trần Thị B', '0923456789', '789 Đường Trần Hưng Đạo, Phường Cầu Kho, Quận 1', 'Hồ Chí Minh', 'delivered', 'COD', 29990000, NULL, '2025-09-02 14:15:00', '2025-09-07 16:45:00'),
('ORD20250905003', 4, 'levanc@gmail.com', 'Lê Văn C', '0934567890', '321 Đường Hai Bà Trưng, Phường Tân Định, Quận 1', 'Hồ Chí Minh', 'delivered', 'credit_card', 9990000, 1, '2025-09-05 09:20:00', '2025-09-08 11:30:00'),
('ORD20250910004', 5, 'phamthid@gmail.com', 'Phạm Thị D', '0945678901', '654 Đường Võ Văn Tần, Phường 5, Quận 3', 'Hồ Chí Minh', 'shipped', 'bank_transfer', 26980000, 2, '2025-09-10 16:40:00', '2025-10-07 10:15:00'),
('ORD20250915005', 6, 'hoangvane@gmail.com', 'Hoàng Văn E', '0956789012', '987 Đường Pasteur, Phường 6, Quận 3', 'Hồ Chí Minh', 'processing', 'COD', 40990000, NULL, '2025-09-15 11:25:00', '2025-10-01 09:30:00'),
('ORD20250920006', 7, 'vuthif@gmail.com', 'Vũ Thị F', '0967890123', '147 Đường Điện Biên Phủ, Phường Đa Kao, Quận 1', 'Hồ Chí Minh', 'delivered', 'credit_card', 13990000, 1, '2025-09-20 13:50:00', '2025-09-25 15:20:00'),
('ORD20250925007', 8, 'dovang@gmail.com', 'Đỗ Văn G', '0978901234', '258 Đường Nam Kỳ Khởi Nghĩa, Phường 7, Quận 3', 'Hồ Chí Minh', 'pending', 'COD', 7490000, NULL, '2025-09-25 10:10:00', '2025-09-25 10:10:00'),
('ORD20251001008', 9, 'buithih@gmail.com', 'Bùi Thị H', '0989012345', '369 Đường Lý Tự Trọng, Phường Bến Thành, Quận 1', 'Hồ Chí Minh', 'processing', 'bank_transfer', 32990000, 3, '2025-10-01 15:30:00', '2025-10-05 08:45:00'),
('ORD20251005009', 10, 'ngothii@gmail.com', 'Ngô Thị I', '0990123456', '741 Đường Cách Mạng Tháng 8, Phường 6, Quận 3', 'Hồ Chí Minh', 'shipped', 'credit_card', 21990000, 1, '2025-10-05 12:20:00', '2025-10-08 14:30:00'),
('ORD20251008010', 2, 'nguyenvana@gmail.com', 'Nguyễn Văn A', '0912345678', '456 Đường Nguyễn Huệ, Phường Bến Nghé, Quận 1', 'Hồ Chí Minh', 'processing', 'COD', 25990000, NULL, '2025-10-08 09:15:00', '2025-10-08 09:15:00'),
('ORD20251009011', 3, 'tranthib@gmail.com', 'Trần Thị B', '0923456789', '789 Đường Trần Hưng Đạo, Phường Cầu Kho, Quận 1', 'Hồ Chí Minh', 'pending', 'bank_transfer', 10990000, 2, '2025-10-09 14:30:00', '2025-10-09 14:30:00'),
('ORD20251009012', 11, 'duongvank@gmail.com', 'Dương Văn K', '0901234568', '852 Đường Cộng Hòa, Phường 4, Quận Tân Bình', 'Hồ Chí Minh', 'cancelled', 'COD', 4490000, NULL, '2025-10-09 16:00:00', '2025-10-09 17:30:00')
ON CONFLICT (order_code) DO NOTHING;

-- ============================================================================
-- 11. ORDER ITEMS - Items in orders
-- ============================================================================

INSERT INTO order_items (order_id, product_id, quantity, price) VALUES 
-- Order 1: iPhone 14 + accessories
(1, 4, 1, 18990000),
(1, 31, 1, 890000),
(1, 32, 1, 290000),

-- Order 2: Samsung S24 Ultra
(2, 6, 1, 29990000),

-- Order 3: Samsung A54
(3, 10, 1, 9990000),

-- Order 4: iPhone 15 Pro
(4, 2, 1, 27990000),
(4, 33, 1, 490000),

-- Order 5: Samsung Z Fold5
(5, 8, 1, 40990000),

-- Order 6: Xiaomi 13T Pro
(6, 12, 1, 13990000),

-- Order 7: Redmi Note 13 Pro
(7, 13, 1, 7490000),

-- Order 8: iPhone 15 Pro Max
(8, 1, 1, 32990000),

-- Order 9: Xiaomi 14 Pro
(9, 11, 1, 21990000),

-- Order 10: Samsung S23 Ultra
(10, 7, 1, 25990000),

-- Order 11: iPad Gen 10
(11, 23, 1, 10990000),

-- Order 12: Xiaomi Redmi 12 (cancelled)
(12, 14, 1, 4490000)
ON CONFLICT DO NOTHING;

-- ============================================================================
-- 12. REVIEWS - Product reviews from customers
-- ============================================================================

INSERT INTO reviews (user_id, product_id, rating, comment, created_at, updated_at) VALUES 
-- iPhone 15 Pro Max reviews
(2, 1, 5, 'Máy đẹp, hiệu năng mạnh mẽ, camera chụp ảnh rất tốt. Giá hơi cao nhưng xứng đáng!', '2025-09-06 10:30:00', '2025-09-06 10:30:00'),
(3, 1, 5, 'iPhone đỉnh nhất hiện nay, pin trâu, màn hình đẹp!', '2025-09-08 14:20:00', '2025-09-08 14:20:00'),
(9, 1, 4, 'Sản phẩm tốt nhưng giá hơi cao. Nếu có tiền thì nên mua.', '2025-10-02 16:15:00', '2025-10-02 16:15:00'),

-- iPhone 14 reviews
(2, 4, 5, 'Máy dùng mượt, giá tốt hơn iPhone 15. Rất hài lòng!', '2025-09-06 11:00:00', '2025-09-06 11:00:00'),
(4, 4, 4, 'Máy tốt, pin ổn, camera đẹp. Giá cả hợp lý.', '2025-09-09 09:30:00', '2025-09-09 09:30:00'),

-- Samsung S24 Ultra reviews
(3, 6, 5, 'Màn hình đẹp xuất sắc, S Pen rất tiện, camera 200MP chụp ảnh siêu nét!', '2025-09-08 15:45:00', '2025-09-08 15:45:00'),
(5, 6, 5, 'Flagship của Samsung, không có gì để chê. Rất đáng mua!', '2025-09-11 10:20:00', '2025-09-11 10:20:00'),

-- Samsung A54 reviews
(4, 10, 4, 'Giá rẻ mà chất lượng tốt, pin trâu, camera ổn. Recommend!', '2025-09-09 10:15:00', '2025-09-09 10:15:00'),
(6, 10, 5, 'Máy đẹp, màn hình AMOLED sáng rõ, pin dùng cả ngày.', '2025-09-12 14:30:00', '2025-09-12 14:30:00'),

-- Xiaomi 14 Pro reviews
(10, 11, 5, 'Camera Leica chụp ảnh đẹp không thua kém iPhone. Hiệu năng đỉnh!', '2025-10-06 13:40:00', '2025-10-06 13:40:00'),
(2, 11, 4, 'Máy ngon, giá tốt. MIUI hơi nhiều quảng cáo nhưng có thể tắt được.', '2025-10-07 09:15:00', '2025-10-07 09:15:00'),

-- Xiaomi 13T Pro reviews
(7, 12, 5, 'Sạc nhanh 120W quá đỉnh, 20 phút là đầy pin. Camera đẹp!', '2025-09-26 16:20:00', '2025-09-26 16:20:00'),
(8, 12, 4, 'Giá tốt, hiệu năng mạnh, màn hình 144Hz mượt mà.', '2025-09-28 11:45:00', '2025-09-28 11:45:00'),

-- Redmi Note 13 Pro reviews
(9, 13, 5, 'Camera 200MP giá rẻ nhất thị trường. Chụp ảnh rất đẹp!', '2025-10-01 15:30:00', '2025-10-01 15:30:00'),
(10, 13, 4, 'Máy tốt trong tầm giá, pin khỏe, hiệu năng ổn định.', '2025-10-03 10:20:00', '2025-10-03 10:20:00'),

-- iPad Pro reviews
(5, 21, 5, 'Chip M2 mạnh mẽ, làm việc thiết kế rất mượt. Màn hình đẹp!', '2025-09-11 11:30:00', '2025-09-11 11:30:00'),

-- iPad Air reviews
(6, 22, 5, 'Chip M1 dùng mượt mà, vẽ bằng Apple Pencil rất tuyệt!', '2025-09-13 14:15:00', '2025-09-13 14:15:00'),

-- AirPods Pro 2 reviews
(2, 37, 5, 'Chống ồn tốt nhất trong tầm giá. Âm thanh trong trẻo!', '2025-09-07 16:40:00', '2025-09-07 16:40:00'),
(5, 37, 5, 'Spatial Audio rất đỉnh, nghe nhạc như ở rạp. Pin trâu!', '2025-09-12 09:20:00', '2025-09-12 09:20:00'),

-- Samsung Buds2 Pro reviews
(4, 38, 4, 'Âm thanh tốt, chống ồn hiệu quả. Giá hợp lý hơn AirPods.', '2025-09-10 13:50:00', '2025-09-10 13:50:00'),

-- Sạc dự phòng Anker reviews
(3, 35, 5, 'Dung lượng lớn, sạc nhanh 65W. Mang đi công tác rất tiện!', '2025-09-09 11:20:00', '2025-09-09 11:20:00'),
(7, 35, 5, 'Pin trâu, thiết kế đẹp, có thể sạc laptop. Rất đáng mua!', '2025-09-27 10:30:00', '2025-09-27 10:30:00')
ON CONFLICT (user_id, product_id) DO NOTHING;

-- ============================================================================
-- 13. REVIEW LIKES - Users liking helpful reviews
-- ============================================================================

INSERT INTO review_likes (user_id, review_id) VALUES 
-- Likes for iPhone 15 Pro Max reviews
(3, 1), (4, 1), (5, 1), (6, 1), (7, 1),
(2, 2), (4, 2), (5, 2), (6, 2),
(2, 3), (3, 3), (4, 3),

-- Likes for Samsung S24 Ultra reviews
(2, 6), (4, 6), (6, 6), (7, 6),
(2, 7), (3, 7), (4, 7),

-- Likes for Xiaomi 14 Pro reviews
(2, 10), (3, 10), (4, 10), (5, 10),
(3, 11), (4, 11), (5, 11),

-- Likes for accessory reviews
(3, 18), (4, 18), (5, 18), (6, 18),
(2, 19), (3, 19), (4, 19),
(2, 21), (4, 21), (5, 21)
ON CONFLICT (user_id, review_id) DO NOTHING;

-- ============================================================================
-- End of Sample Data Script
-- ============================================================================
-- Summary:
-- - 11 Users (1 admin, 10 customers)
-- - 10 Addresses
-- - 25 Categories (5 main + 20 subcategories)
-- - 15 Brands
-- - 42 Products (phones, tablets, accessories)
-- - Product images for selected products
-- - 10 Vouchers with various discounts
-- - 10 Shopping carts
-- - Cart items for 5 active carts
-- - 12 Orders (various statuses: pending, processing, shipped, delivered, cancelled)
-- - Order items for all orders
-- - 21 Product reviews with ratings and comments
-- - Review likes showing engagement
-- ============================================================================
