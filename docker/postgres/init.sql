-- UTE Phone Hub Database Initialization Script
-- This script creates the database schema and initial data

-- Create tables
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE,
    email CHARACTER VARYING(100) UNIQUE NOT NULL,
    full_name CHARACTER VARYING(100) NOT NULL,
    password_hash CHARACTER VARYING(255) NOT NULL,
    phone_number CHARACTER VARYING(20),
    role CHARACTER VARYING(255) NOT NULL DEFAULT 'customer',
    status CHARACTER VARYING(255) NOT NULL DEFAULT 'active',
    updated_at TIMESTAMP(6) WITHOUT TIME ZONE,
    username CHARACTER VARYING(50) UNIQUE NOT NULL,
    CONSTRAINT users_role_check CHECK (((role)::text = ANY ((ARRAY['customer'::character varying, 'admin'::character varying])::text[]))),
    CONSTRAINT users_status_check CHECK (((status)::text = ANY ((ARRAY['active'::character varying, 'locked'::character varying, 'pending'::character varying])::text[])))
);

CREATE TABLE IF NOT EXISTS addresses (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE,
    is_default BOOLEAN,
    phone_number CHARACTER VARYING(20) NOT NULL,
    province CHARACTER VARYING(100) NOT NULL,
    province_code CHARACTER VARYING(10),
    recipient_name CHARACTER VARYING(100) NOT NULL,
    street_address TEXT NOT NULL,
    updated_at TIMESTAMP(6) WITHOUT TIME ZONE,
    ward CHARACTER VARYING(100) NOT NULL,
    ward_code CHARACTER VARYING(10),
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS categories (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE,
    description TEXT,
    name CHARACTER VARYING(100) NOT NULL,
    updated_at TIMESTAMP(6) WITHOUT TIME ZONE,
    parent_id BIGINT REFERENCES categories(id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS brands (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE,
    description TEXT,
    logo_url CHARACTER VARYING(500),
    name CHARACTER VARYING(100) NOT NULL,
    updated_at TIMESTAMP(6) WITHOUT TIME ZONE
);

CREATE TABLE IF NOT EXISTS products (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE,
    description TEXT,
    name CHARACTER VARYING(200) NOT NULL,
    price NUMERIC(12,2) NOT NULL CHECK (price > 0),
    specifications JSONB,
    status BOOLEAN NOT NULL,
    stock_quantity INTEGER NOT NULL CHECK (stock_quantity >= 0),
    thumbnail_url CHARACTER VARYING(500),
    updated_at TIMESTAMP(6) WITHOUT TIME ZONE,
    brand_id BIGINT NOT NULL REFERENCES brands(id) ON DELETE RESTRICT,
    category_id BIGINT NOT NULL REFERENCES categories(id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS product_images (
    id BIGSERIAL PRIMARY KEY,
    alt_text CHARACTER VARYING(200),
    created_at TIMESTAMP(6) WITHOUT TIME ZONE,
    image_url CHARACTER VARYING(500) NOT NULL,
    is_primary BOOLEAN,
    product_id BIGINT NOT NULL REFERENCES products(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS carts (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE,
    updated_at TIMESTAMP(6) WITHOUT TIME ZONE,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    UNIQUE(user_id)
);

CREATE TABLE IF NOT EXISTS cart_items (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE,
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    updated_at TIMESTAMP(6) WITHOUT TIME ZONE,
    cart_id BIGINT NOT NULL REFERENCES carts(id) ON DELETE CASCADE,
    product_id BIGINT NOT NULL REFERENCES products(id) ON DELETE CASCADE,
    UNIQUE(cart_id, product_id)
);

CREATE TABLE IF NOT EXISTS vouchers (
    id BIGSERIAL PRIMARY KEY,
    code CHARACTER VARYING(50) UNIQUE NOT NULL,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE,
    discount_type CHARACTER VARYING(255) NOT NULL,
    discount_value NUMERIC(12,2) NOT NULL CHECK (discount_value > 0),
    expiry_date TIMESTAMP(6) WITHOUT TIME ZONE,
    max_usage INTEGER,
    min_order_value NUMERIC(12,2),
    status CHARACTER VARYING(255) NOT NULL,
    updated_at TIMESTAMP(6) WITHOUT TIME ZONE,
    CONSTRAINT vouchers_discount_type_check CHECK (((discount_type)::text = ANY ((ARRAY['PERCENTAGE'::character varying, 'FIXED_AMOUNT'::character varying])::text[]))),
    CONSTRAINT vouchers_status_check CHECK (((status)::text = ANY ((ARRAY['ACTIVE'::character varying, 'INACTIVE'::character varying, 'EXPIRED'::character varying])::text[])))
);

CREATE TABLE IF NOT EXISTS orders (
    id BIGSERIAL PRIMARY KEY,
    city CHARACTER VARYING(100) NOT NULL,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE,
    email CHARACTER VARYING(100) NOT NULL,
    order_code CHARACTER VARYING(50) UNIQUE NOT NULL,
    payment_method CHARACTER VARYING(255) NOT NULL,
    phone_number CHARACTER VARYING(20) NOT NULL,
    recipient_name CHARACTER VARYING(100) NOT NULL,
    status CHARACTER VARYING(255) NOT NULL,
    street_address TEXT NOT NULL,
    total_amount NUMERIC(12,2) NOT NULL CHECK (total_amount > 0),
    updated_at TIMESTAMP(6) WITHOUT TIME ZONE,
    user_id BIGINT REFERENCES users(id) ON DELETE SET NULL,
    voucher_id BIGINT REFERENCES vouchers(id) ON DELETE SET NULL,
    CONSTRAINT orders_payment_method_check CHECK (((payment_method)::text = ANY ((ARRAY['COD'::character varying, 'BANK_TRANSFER'::character varying, 'CREDIT_CARD'::character varying])::text[]))),
    CONSTRAINT orders_status_check CHECK (((status)::text = ANY ((ARRAY['PENDING'::character varying, 'PROCESSING'::character varying, 'SHIPPED'::character varying, 'DELIVERED'::character varying, 'CANCELLED'::character varying])::text[])))
);

CREATE TABLE IF NOT EXISTS order_items (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE,
    price NUMERIC(12,2) NOT NULL CHECK (price > 0),
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    order_id BIGINT NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
    product_id BIGINT NOT NULL REFERENCES products(id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS reviews (
    id BIGSERIAL PRIMARY KEY,
    comment TEXT,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE,
    rating INTEGER NOT NULL CHECK (rating >= 1 AND rating <= 5),
    updated_at TIMESTAMP(6) WITHOUT TIME ZONE,
    product_id BIGINT NOT NULL REFERENCES products(id) ON DELETE CASCADE,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    UNIQUE(user_id, product_id)
);

CREATE TABLE IF NOT EXISTS review_likes (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE,
    review_id BIGINT NOT NULL REFERENCES reviews(id) ON DELETE CASCADE,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    UNIQUE(user_id, review_id)
);

CREATE TABLE IF NOT EXISTS password_reset_tokens (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE,
    expiry_date TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    token CHARACTER VARYING(255) UNIQUE NOT NULL,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE
);

-- Create indexes for performance
CREATE INDEX IF NOT EXISTS idx_users_email ON users(email);
CREATE INDEX IF NOT EXISTS idx_products_name ON products(name);
CREATE INDEX IF NOT EXISTS idx_products_category_id ON products(category_id);
CREATE INDEX IF NOT EXISTS idx_products_brand_id ON products(brand_id);
CREATE INDEX IF NOT EXISTS idx_products_status ON products(status);
CREATE INDEX IF NOT EXISTS idx_orders_user_id ON orders(user_id);
CREATE INDEX IF NOT EXISTS idx_orders_email ON orders(email);
CREATE INDEX IF NOT EXISTS idx_orders_order_code ON orders(order_code);
CREATE INDEX IF NOT EXISTS idx_orders_status ON orders(status);
CREATE INDEX IF NOT EXISTS idx_vouchers_code ON vouchers(code);
CREATE INDEX IF NOT EXISTS idx_reviews_product_id ON reviews(product_id);
CREATE INDEX IF NOT EXISTS idx_reviews_rating ON reviews(rating);

-- Insert initial data (using uppercase values to match constraints)
INSERT INTO categories (name, description) VALUES 
('Điện thoại', 'Các loại điện thoại di động'),
('Phụ kiện', 'Phụ kiện điện thoại'),
('Máy tính bảng', 'Máy tính bảng và iPad')
ON CONFLICT DO NOTHING;

INSERT INTO brands (name, description) VALUES 
('Apple', 'iPhone, iPad, MacBook'),
('Samsung', 'Galaxy series'),
('Xiaomi', 'Mi series'),
('OPPO', 'Find series'),
('Vivo', 'X series')
ON CONFLICT DO NOTHING;

INSERT INTO vouchers (code, discount_type, discount_value, max_usage, min_order_value, expiry_date, status) VALUES 
('WELCOME10', 'PERCENTAGE', 10, 100, 1000000, '2025-12-31 23:59:59', 'ACTIVE'),
('SALE50K', 'FIXED_AMOUNT', 50000, 50, 2000000, '2025-12-31 23:59:59', 'ACTIVE')
ON CONFLICT (code) DO NOTHING;

-- Create admin user (password: AdminPassword123!)
INSERT INTO users (username, full_name, email, password_hash, role, status) VALUES 
('admin', 'Admin User', 'admin@utephonehub.com', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj4J/8Kz8KzK', 'admin', 'active')
ON CONFLICT (email) DO NOTHING;

-- Create sample users (passwords are all "Password123!")
INSERT INTO users (username, full_name, email, password_hash, phone_number, role, status) VALUES 
('customer1', 'Nguyễn Văn An', 'customer1@utephonehub.com', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj4J/8Kz8KzK', '0912345678', 'customer', 'active'),
('customer2', 'Trần Thị Bình', 'customer2@utephonehub.com', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj4J/8Kz8KzK', '0923456789', 'customer', 'active'),
('customer3', 'Lê Văn Cường', 'customer3@utephonehub.com', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj4J/8Kz8KzK', '0934567890', 'customer', 'active')
ON CONFLICT (email) DO NOTHING;

-- Create sample products
INSERT INTO products (name, description, price, stock_quantity, category_id, brand_id, specifications, status, created_at, updated_at) VALUES 
-- iPhone Products
('iPhone 15 Pro Max 256GB', 'iPhone 15 Pro Max với chip A17 Pro mạnh mẽ, camera 48MP, màn hình Super Retina XDR 6.7 inch', 32990000, 50, 1, 1, '{"ram": "8GB", "chip": "A17 Pro", "camera": "48MP", "screen": "6.7 inch", "battery": "4422mAh", "storage": "256GB"}', true, NOW(), NOW()),
('iPhone 15 Pro 128GB', 'iPhone 15 Pro với khung titan, Dynamic Island, camera 48MP', 27990000, 45, 1, 1, '{"ram": "8GB", "chip": "A17 Pro", "camera": "48MP", "screen": "6.1 inch", "battery": "3274mAh", "storage": "128GB"}', true, NOW(), NOW()),
('iPhone 15 Plus 128GB', 'iPhone 15 Plus màn hình lớn 6.7 inch, camera 48MP', 24990000, 60, 1, 1, '{"ram": "6GB", "chip": "A16 Bionic", "camera": "48MP", "screen": "6.7 inch", "battery": "4383mAh", "storage": "128GB"}', true, NOW(), NOW()),
('iPhone 14 128GB', 'iPhone 14 với chip A15 Bionic, camera kép 12MP', 18990000, 80, 1, 1, '{"ram": "6GB", "chip": "A15 Bionic", "camera": "12MP", "screen": "6.1 inch", "battery": "3279mAh", "storage": "128GB"}', true, NOW(), NOW()),
('iPhone 13 128GB', 'iPhone 13 giá tốt, hiệu năng mạnh mẽ', 15990000, 100, 1, 1, '{"ram": "4GB", "chip": "A15 Bionic", "camera": "12MP", "screen": "6.1 inch", "battery": "3240mAh", "storage": "128GB"}', true, NOW(), NOW()),

-- Samsung Products
('Samsung Galaxy S24 Ultra 12GB/256GB', 'Galaxy S24 Ultra với S Pen, camera 200MP, chip Snapdragon 8 Gen 3', 29990000, 40, 1, 2, '{"ram": "12GB", "chip": "Snapdragon 8 Gen 3", "camera": "200MP", "screen": "6.8 inch", "battery": "5000mAh", "storage": "256GB"}', true, NOW(), NOW()),
('Samsung Galaxy S23 Ultra 8GB/256GB', 'Galaxy S23 Ultra camera 200MP, hiệu năng đỉnh cao', 25990000, 35, 1, 2, '{"ram": "8GB", "chip": "Snapdragon 8 Gen 2", "camera": "200MP", "screen": "6.8 inch", "battery": "5000mAh", "storage": "256GB"}', true, NOW(), NOW()),
('Samsung Galaxy Z Fold5 12GB/256GB', 'Điện thoại gập cao cấp với màn hình Dynamic AMOLED 2X', 40990000, 20, 1, 2, '{"ram": "12GB", "chip": "Snapdragon 8 Gen 2", "camera": "50MP", "screen": "7.6 inch", "battery": "4400mAh", "storage": "256GB"}', true, NOW(), NOW()),
('Samsung Galaxy Z Flip5 8GB/256GB', 'Điện thoại gập nhỏ gọn, thời trang', 23990000, 25, 1, 2, '{"ram": "8GB", "chip": "Snapdragon 8 Gen 2", "camera": "12MP", "screen": "6.7 inch", "battery": "3700mAh", "storage": "256GB"}', true, NOW(), NOW()),
('Samsung Galaxy A54 5G 8GB/128GB', 'Galaxy A54 camera 50MP, pin 5000mAh', 9990000, 120, 1, 2, '{"ram": "8GB", "chip": "Exynos 1380", "camera": "50MP", "screen": "6.4 inch", "battery": "5000mAh", "storage": "128GB"}', true, NOW(), NOW()),

-- Xiaomi Products
('Xiaomi 14 Pro 12GB/512GB', 'Xiaomi 14 Pro với camera Leica, chip Snapdragon 8 Gen 3', 21990000, 50, 1, 3, '{"ram": "12GB", "chip": "Snapdragon 8 Gen 3", "camera": "50MP", "screen": "6.73 inch", "battery": "4880mAh", "storage": "512GB"}', true, NOW(), NOW()),
('Xiaomi 13T Pro 12GB/256GB', 'Xiaomi 13T Pro camera 50MP, sạc nhanh 120W', 13990000, 80, 1, 3, '{"ram": "12GB", "chip": "Dimensity 9200+", "camera": "50MP", "screen": "6.67 inch", "battery": "5000mAh", "storage": "256GB"}', true, NOW(), NOW()),
('Xiaomi Redmi Note 13 Pro 8GB/128GB', 'Redmi Note 13 Pro camera 200MP, giá rẻ', 7490000, 150, 1, 3, '{"ram": "8GB", "chip": "Snapdragon 7s Gen 2", "camera": "200MP", "screen": "6.67 inch", "battery": "5100mAh", "storage": "128GB"}', true, NOW(), NOW()),
('Xiaomi Redmi 12 8GB/256GB', 'Redmi 12 pin khủng 5000mAh, giá tốt', 4490000, 200, 1, 3, '{"ram": "8GB", "chip": "Helio G88", "camera": "50MP", "screen": "6.79 inch", "battery": "5000mAh", "storage": "256GB"}', true, NOW(), NOW()),

-- OPPO Products
('OPPO Find N3 Flip 12GB/256GB', 'OPPO Find N3 Flip điện thoại gập camera 50MP', 22990000, 30, 1, 4, '{"ram": "12GB", "chip": "Dimensity 9200", "camera": "50MP", "screen": "6.8 inch", "battery": "4300mAh", "storage": "256GB"}', true, NOW(), NOW()),
('OPPO Reno11 F 5G 8GB/256GB', 'OPPO Reno11 F camera 64MP, thiết kế đẹp', 8990000, 100, 1, 4, '{"ram": "8GB", "chip": "Dimensity 7050", "camera": "64MP", "screen": "6.7 inch", "battery": "5000mAh", "storage": "256GB"}', true, NOW(), NOW()),
('OPPO A78 8GB/256GB', 'OPPO A78 pin 5000mAh, sạc nhanh 67W', 6490000, 150, 1, 4, '{"ram": "8GB", "chip": "Snapdragon 680", "camera": "50MP", "screen": "6.43 inch", "battery": "5000mAh", "storage": "256GB"}', true, NOW(), NOW()),

-- Vivo Products
('Vivo V29e 5G 12GB/256GB', 'Vivo V29e camera 64MP, thiết kế mỏng nhẹ', 8490000, 90, 1, 5, '{"ram": "12GB", "chip": "Snapdragon 695", "camera": "64MP", "screen": "6.67 inch", "battery": "4800mAh", "storage": "256GB"}', true, NOW(), NOW()),
('Vivo Y36 8GB/128GB', 'Vivo Y36 pin 5000mAh, hiệu năng tốt', 5990000, 120, 1, 5, '{"ram": "8GB", "chip": "Snapdragon 680", "camera": "50MP", "screen": "6.64 inch", "battery": "5000mAh", "storage": "128GB"}', true, NOW(), NOW())
ON CONFLICT DO NOTHING;

-- Create sample addresses
INSERT INTO addresses (recipient_name, phone_number, street_address, province, province_code, ward, ward_code, is_default, user_id, created_at, updated_at) VALUES 
('Nguyễn Văn An', '0912345678', '123 Nguyễn Huệ, Quận 1', 'Thành phố Hồ Chí Minh', '79', 'Phường Bến Nghé', '27259', true, 2, NOW(), NOW()),
('Trần Thị Bình', '0923456789', '456 Lê Lợi, Quận 1', 'Thành phố Hồ Chí Minh', '79', 'Phường Bến Thành', '27262', true, 3, NOW(), NOW()),
('Lê Văn Cường', '0934567890', '789 Trần Hưng Đạo, Quận 5', 'Thành phố Hồ Chí Minh', '79', 'Phường 1', '26794', true, 4, NOW(), NOW())
ON CONFLICT DO NOTHING;

-- Create carts for users
INSERT INTO carts (user_id, created_at, updated_at) VALUES 
(2, NOW(), NOW()), (3, NOW(), NOW()), (4, NOW(), NOW())
ON CONFLICT DO NOTHING;
