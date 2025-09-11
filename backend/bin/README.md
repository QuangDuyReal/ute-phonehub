# 🖥️ UTE PhoneHub - Backend (Java Servlet)

<div align="center">

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Servlet](https://img.shields.io/badge/Jakarta_Servlet-6.0-orange?style=for-the-badge)
![Jetty](https://img.shields.io/badge/Jetty-11-4A90E2?style=for-the-badge&logo=eclipse&logoColor=white)
![Tomcat](https://img.shields.io/badge/Tomcat-11-F8DC75?style=for-the-badge&logo=apache-tomcat&logoColor=black)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-336791?style=for-the-badge&logo=postgresql&logoColor=white)

**RESTful API Backend cho UTE PhoneHub E-commerce Platform**

[🏠 Main Project](../README.md) • [📖 API Docs](http://localhost:8080/ute-phonehub/swagger) • [🚀 Deployment](../DEPLOYMENT.md)

</div>

---

## 📋 Mục lục

- [🎯 Tổng quan](#-tổng-quan)
- [🚀 Quick Start](#-quick-start)
- [🏗️ Kiến trúc](#️-kiến-trúc)
- [🛠 Công nghệ](#-công-nghệ)
- [📁 Cấu trúc Backend](#-cấu-trúc-backend)
- [🔌 API Endpoints](#-api-endpoints)
- [🗃️ Database](#️-database)
- [🔐 Authentication](#-authentication)
- [📦 Build & Deploy](#-build--deploy)
- [🧪 Testing](#-testing)

---

## 🎯 Tổng quan

Backend của UTE PhoneHub được xây dựng với **Java Servlet API 6.0**, hỗ trợ cả **Jetty 11** (development) và **Apache Tomcat 11** (production), cung cấp RESTful API cho toàn bộ hệ thống e-commerce.

### ✨ Tính năng chính

- 🔐 **JWT Authentication** - Xác thực bảo mật với JWT tokens
- 🛡️ **CORS Support** - Cross-Origin Resource Sharing
- 📖 **Swagger Integration** - Tự động tạo API documentation  
- 🗃️ **PostgreSQL Integration** - Kết nối cơ sở dữ liệu PostgreSQL
- 🔄 **JSON Processing** - Jackson library cho xử lý JSON
- 🛡️ **Security Filters** - Authentication và Authorization filters
- 📊 **Modular Architecture** - Chia theo modules chức năng
- ⚡ **Hot Reload** - Development với Jetty hot reload

---

## 🚀 Quick Start

### 📋 Simple Scripts (Khuyến nghị)

```powershell
# Chạy backend với Jetty (Development + Hot Reload)
..\scripts\backend\backend.ps1 run

# Build WAR file cho Production
..\scripts\backend\backend.ps1 build

# Chạy tests
..\scripts\backend\backend.ps1 test

# Clean build artifacts
..\scripts\backend\backend.ps1 clean

# Kiểm tra trạng thái
..\scripts\backend\backend.ps1 status
```

### 🌐 Truy cập Backend

- **Development (Jetty)**: http://localhost:8080/ute-phonehub
- **Production (Tomcat)**: http://localhost:8080/ute-phonehub  
- **API Documentation**: http://localhost:8080/ute-phonehub/swagger
- **API Base URL**: http://localhost:8080/ute-phonehub/api

### 🎯 Context Path

Cả Jetty và Tomcat đều sử dụng context path: **`/ute-phonehub`**

---

## 🏗️ Kiến trúc

```
┌─────────────────────────────────────────────────────────────┐
│                    UTE PhoneHub Backend                     │
│                                                             │
│  ┌─────────────┐    ┌─────────────┐    ┌─────────────┐     │
│  │   Filters   │    │  Controllers │    │   Services  │     │
│  │             │    │              │    │             │     │
│  │ • CorsFilter│    │ • AuthCtrl   │    │ • AuthSvc   │     │
│  │ • AuthFilter│    │ • ProductCtrl│    │ • ProductSvc│     │
│  │             │    │ • CartCtrl   │    │ • CartSvc   │     │
│  └─────────────┘    │ • OrderCtrl  │    │ • OrderSvc  │     │
│          │           │ • ReviewCtrl │    │ • ReviewSvc │     │
│          ▼           │ • AdminCtrl  │    │ • AdminSvc  │     │
│  ┌─────────────┐    │ • VoucherCtrl│    │ • VoucherSvc│     │
│  │   Servlet   │    └─────────────┘    └─────────────┘     │
│  │  Container  │             │                  │           │
│  │  (Tomcat)   │             ▼                  ▼           │
│  └─────────────┘    ┌─────────────┐    ┌─────────────┐     │
│                     │     DTOs    │    │   Models    │     │
│                     │             │    │             │     │
│                     │ • UserDTO   │    │ • User      │     │
│                     │ • ProductDTO│    │ • Product   │     │
│                     │ • OrderDTO  │    │ • Order     │     │
│                     └─────────────┘    └─────────────┘     │
│                              │                  │           │
│                              ▼                  ▼           │
│                     ┌─────────────────────────────────┐     │
│                     │        PostgreSQL Database     │     │
│                     │                                 │     │
│                     │ • users     • orders           │     │
│                     │ • products  • order_items      │     │
│                     │ • categories• reviews          │     │
│                     │ • brands    • vouchers         │     │
│                     │ • cart_items• addresses        │     │
│                     └─────────────────────────────────┘     │
└─────────────────────────────────────────────────────────────┘
```

---

## 🛠 Công nghệ

### 🎯 Core Technologies

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 17 | Programming language |
| **Jakarta Servlet API** | 6.0.0 | Web application framework |
| **Apache Tomcat** | 11.x | Servlet container |
| **Maven** | 3.9+ | Build & dependency management |

### 📚 Dependencies

| Dependency | Version | Purpose |
|------------|---------|---------|
| **Jackson Databind** | 2.15.2 | JSON processing |
| **JJWT** | 0.11.5 | JWT token handling |
| **BCrypt** | 0.4 | Password hashing |
| **PostgreSQL JDBC** | 42.6.0 | Database connectivity |
| **Swagger Core** | 2.2.15 | API documentation |
| **Swagger UI** | 4.15.5 | API documentation UI |

---

## 📁 Cấu trúc Backend

```
backend/
├── 📄 pom.xml                      # Maven configuration
├── 🐳 Dockerfile                   # Container configuration  
├── 📄 railway.json                 # Railway deployment config
├── 📂 src/main/
│   ├── 📂 java/com/utephonehub/
│   │   ├── 🏠 RootServlet.java     # Main entry point
│   │   ├── 📂 auth/                # 🔐 Authentication Module
│   │   │   ├── AuthController.java
│   │   │   ├── AuthService.java
│   │   │   └── LoginDTO.java
│   │   ├── 📂 product/             # 📱 Product Module
│   │   │   ├── ProductController.java
│   │   │   ├── ProductService.java
│   │   │   └── ProductDTO.java
│   │   ├── 📂 cart/                # 🛒 Shopping Cart Module
│   │   │   ├── CartController.java
│   │   │   ├── CartService.java
│   │   │   └── CartItemDTO.java
│   │   ├── 📂 order/               # 📦 Order Module
│   │   │   ├── OrderController.java
│   │   │   ├── OrderService.java
│   │   │   └── OrderDTO.java
│   │   ├── 📂 review/              # ⭐ Review Module
│   │   │   ├── ReviewController.java
│   │   │   ├── ReviewService.java
│   │   │   └── ReviewDTO.java
│   │   ├── 📂 admin/               # ⚙️ Admin Module
│   │   │   ├── AdminController.java
│   │   │   ├── AdminService.java
│   │   │   └── AdminDTO.java
│   │   ├── 📂 voucher/             # 🎫 Voucher Module
│   │   │   ├── VoucherController.java
│   │   │   ├── VoucherService.java
│   │   │   └── VoucherDTO.java
│   │   ├── 📂 model/               # 🗃️ Data Models
│   │   │   ├── User.java
│   │   │   ├── Product.java
│   │   │   ├── Order.java
│   │   │   ├── Review.java
│   │   │   └── Voucher.java
│   │   ├── 📂 dto/                 # 📄 Data Transfer Objects
│   │   │   ├── ResponseDTO.java
│   │   │   ├── ErrorDTO.java
│   │   │   └── PaginationDTO.java
│   │   ├── 📂 util/                # 🔧 Utilities
│   │   │   ├── JwtUtil.java
│   │   │   ├── DatabaseUtil.java
│   │   │   ├── PasswordUtil.java
│   │   │   └── SwaggerUIServlet.java
│   │   └── 📂 filter/              # 🛡️ Security Filters
│   │       ├── CorsFilter.java
│   │       └── AuthFilter.java
│   └── 📂 webapp/
│       ├── 📂 WEB-INF/
│       │   └── web.xml             # Servlet configuration
│       └── swagger.json            # Static API documentation
└── 📂 target/                      # Build output
    └── ute-phonehub.war           # Deployable WAR file
```

---

## 🚀 Cài đặt & Chạy

### 📋 Yêu cầu

- **JDK 17+** ([Download](https://openjdk.org/))
- **Apache Tomcat 11+** ([Download](https://tomcat.apache.org/download-11.cgi)) - Production
- **Jetty 11+** (Included in Maven) - Development
- **PostgreSQL 15+** ([Download](https://www.postgresql.org/download/))
- **Maven 3.9+** ([Download](https://maven.apache.org/download.cgi))

### 🗃️ Thiết lập Database

```sql
-- Tạo database
CREATE DATABASE ute_phonehub WITH ENCODING 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';

-- Cấu hình user (optional)
CREATE USER phonehub_user WITH PASSWORD 'your_secure_password';
GRANT ALL PRIVILEGES ON DATABASE ute_phonehub TO phonehub_user;
```

### 🎯 Method 1: Simple Scripts (Khuyến nghị)

```powershell
# Development với Jetty (Hot Reload)
..\scripts\backend\backend.ps1 run

# Build WAR cho Production
..\scripts\backend\backend.ps1 build

# Test backend
..\scripts\backend\backend.ps1 test

# Clean build
..\scripts\backend\backend.ps1 clean
```

### 🔧 Method 2: Manual Build & Deploy

```powershell
# 1. Clone repository (nếu chưa có)
git clone https://github.com/darktheDE/ute-phonehub-testdeploy.git
cd ute-phonehub-testdeploy/backend

# 2. Cấu hình database connection
# Sửa file: src/main/java/com/utephonehub/util/DatabaseUtil.java

# 3. Development với Jetty
mvn clean compile
mvn jetty:run

# 4. Hoặc build cho Production (Tomcat)
mvn clean package

# 5. Deploy to Tomcat
Copy-Item "target\ute-phonehub.war" "C:\apache-tomcat-11.0.10\webapps\"

# 6. Start Tomcat
C:\apache-tomcat-11.0.10\bin\startup.bat

# 7. Verify deployment
Start-Process "http://localhost:8080/ute-phonehub"
```

### 🐳 Method 3: Docker

```powershell
# Build Docker image
docker build -t ute-phonehub-backend .

# Run container
docker run -p 8080:8080 --name phonehub-backend ute-phonehub-backend

# Hoặc sử dụng Docker script
..\scripts\docker\docker.ps1 build
..\scripts\docker\docker.ps1 start
```

### 🌐 URLs sau khi chạy

- **Backend Root**: http://localhost:8080/ute-phonehub
- **API Documentation**: http://localhost:8080/ute-phonehub/swagger  
- **API Base**: http://localhost:8080/ute-phonehub/api
- **Swagger JSON**: http://localhost:8080/ute-phonehub/swagger.json

---

## 🔌 API Endpoints

### 🏠 Root & Documentation

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/` | Project info và danh sách endpoints |
| `GET` | `/swagger` | Swagger UI documentation |
| `GET` | `/swagger.json` | OpenAPI JSON specification |

### 🔐 Authentication (`/api/auth`)

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `GET` | `/api/auth` | Module info | ❌ |
| `POST` | `/api/auth/login` | Đăng nhập | ❌ |
| `POST` | `/api/auth/register` | Đăng ký | ❌ |
| `POST` | `/api/auth/logout` | Đăng xuất | ✅ |
| `GET` | `/api/auth/profile` | Thông tin profile | ✅ |
| `PUT` | `/api/auth/profile` | Cập nhật profile | ✅ |
| `PUT` | `/api/auth/change-password` | Đổi mật khẩu | ✅ |
| `GET` | `/api/auth/addresses` | Danh sách địa chỉ | ✅ |
| `POST` | `/api/auth/addresses` | Thêm địa chỉ | ✅ |
| `PUT` | `/api/auth/addresses/{id}` | Cập nhật địa chỉ | ✅ |
| `DELETE` | `/api/auth/addresses/{id}` | Xóa địa chỉ | ✅ |

### 📱 Products (`/api/products`)

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `GET` | `/api/products` | Danh sách sản phẩm | ❌ |
| `GET` | `/api/products/{id}` | Chi tiết sản phẩm | ❌ |
| `GET` | `/api/products/search` | Tìm kiếm sản phẩm | ❌ |
| `GET` | `/api/products/categories` | Danh sách danh mục | ❌ |
| `GET` | `/api/products/brands` | Danh sách thương hiệu | ❌ |

### 🛒 Shopping Cart (`/api/cart`)

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `GET` | `/api/cart` | Xem giỏ hàng | ✅ |
| `POST` | `/api/cart/add` | Thêm vào giỏ hàng | ✅ |
| `PUT` | `/api/cart/update/{itemId}` | Cập nhật số lượng | ✅ |
| `DELETE` | `/api/cart/remove/{itemId}` | Xóa khỏi giỏ hàng | ✅ |
| `DELETE` | `/api/cart/clear` | Xóa toàn bộ giỏ hàng | ✅ |

### 📦 Orders (`/api/orders`)

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `GET` | `/api/orders` | Danh sách đơn hàng | ✅ |
| `GET` | `/api/orders/{id}` | Chi tiết đơn hàng | ✅ |
| `POST` | `/api/orders` | Tạo đơn hàng | ✅ |
| `PUT` | `/api/orders/{id}/cancel` | Hủy đơn hàng | ✅ |

### ⭐ Reviews (`/api/reviews`)

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `GET` | `/api/reviews` | Danh sách đánh giá | ❌ |
| `GET` | `/api/reviews/product/{productId}` | Đánh giá theo sản phẩm | ❌ |
| `POST` | `/api/reviews` | Tạo đánh giá | ✅ |
| `PUT` | `/api/reviews/{id}` | Cập nhật đánh giá | ✅ |
| `DELETE` | `/api/reviews/{id}` | Xóa đánh giá | ✅ |

### ⚙️ Admin (`/api/admin`) - Admin Only

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/admin/dashboard` | Dashboard thống kê |
| `GET` | `/api/admin/statistics` | Thống kê chi tiết |
| `GET` | `/api/admin/products` | Quản lý sản phẩm |
| `POST` | `/api/admin/products` | Tạo sản phẩm |
| `PUT` | `/api/admin/products/{id}` | Cập nhật sản phẩm |
| `DELETE` | `/api/admin/products/{id}` | Xóa sản phẩm |
| `GET` | `/api/admin/orders` | Quản lý đơn hàng |
| `PUT` | `/api/admin/orders/{id}/status` | Cập nhật trạng thái |
| `GET` | `/api/admin/users` | Quản lý người dùng |
| `PUT` | `/api/admin/users/{id}/lock` | Khóa tài khoản |
| `PUT` | `/api/admin/users/{id}/unlock` | Mở khóa tài khoản |

### 🎫 Vouchers (`/api/vouchers`)

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `GET` | `/api/vouchers` | Danh sách voucher | ❌ |
| `GET` | `/api/vouchers/available` | Voucher khả dụng | ✅ |
| `POST` | `/api/vouchers/apply` | Áp dụng voucher | ✅ |

---

## 🗃️ Database

### 📊 Database Schema

```sql
-- Core Tables
users               # Người dùng
products            # Sản phẩm  
categories          # Danh mục
brands              # Thương hiệu
orders              # Đơn hàng
order_items         # Chi tiết đơn hàng
cart_items          # Giỏ hàng
reviews             # Đánh giá
vouchers            # Mã giảm giá
addresses           # Địa chỉ
```

### 🔗 Database Connection

**File**: `src/main/java/com/utephonehub/util/DatabaseUtil.java`

```java
// Cấu hình kết nối PostgreSQL
private static final String DB_URL = "jdbc:postgresql://localhost:5432/ute_phonehub";
private static final String DB_USERNAME = "phonehub_user";
private static final String DB_PASSWORD = "your_password";
```

---

## 🔐 Authentication

### 🎟️ JWT Token Structure

```json
{
  "header": {
    "typ": "JWT",
    "alg": "HS256"
  },
  "payload": {
    "userId": "123",
    "username": "user@example.com",
    "role": "customer", // "customer" | "admin"
    "iat": 1609459200,
    "exp": 1609545600
  }
}
```

### 🛡️ Security Filters

#### 1. **CorsFilter** - Cross-Origin Resource Sharing
```java
// Cho phép frontend (localhost:3000) truy cập API
response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
response.setHeader("Access-Control-Allow-Headers", "Content-Type,Authorization");
```

#### 2. **AuthFilter** - JWT Authentication
```java
// Kiểm tra JWT token trong header Authorization
String token = request.getHeader("Authorization");
if (token != null && token.startsWith("Bearer ")) {
    // Validate và decode JWT
    Claims claims = JwtUtil.validateToken(token.substring(7));
    request.setAttribute("userId", claims.get("userId"));
    request.setAttribute("role", claims.get("role"));
}
```

### 🔑 Protected Routes

- `/api/auth/*` - Requires valid JWT (except login/register)
- `/api/admin/*` - Requires JWT + role "admin"
- `/api/cart/*` - Requires valid JWT
- `/api/orders/*` - Requires valid JWT
- `/api/reviews/*` - Requires valid JWT (for POST/PUT/DELETE)

---

## 📦 Build & Deploy

### 🔨 Maven Build Lifecycle

```powershell
# Clean previous build
mvn clean

# Compile source code
mvn compile

# Run tests (nếu có)
mvn test

# Package as WAR file
mvn package

# Install to local repository
mvn install
```

### 🚀 Deployment Options

#### 1. **Traditional Tomcat Deployment**
```powershell
# Copy WAR to Tomcat webapps
Copy-Item "target\ute-phonehub.war" "C:\apache-tomcat-11.0.10\webapps\"

# Start Tomcat
C:\apache-tomcat-11.0.10\bin\startup.bat
```

#### 2. **Docker Deployment**
```dockerfile
FROM tomcat:11-jdk17-openjdk
COPY target/ute-phonehub.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
```

#### 3. **Railway.app Deployment**
```powershell
# Sử dụng script tự động
.\deploy-railway.ps1

# Hoặc manual deployment
railway login
railway link
railway up
```

### 🌍 Environment Variables

```bash
# Database Configuration
DB_HOST=localhost
DB_PORT=3306
DB_NAME=ute_phonehub
DB_USERNAME=phonehub_user
DB_PASSWORD=your_password

# JWT Configuration  
JWT_SECRET=your_super_secret_key
JWT_EXPIRATION=86400

# Server Configuration
SERVER_PORT=8080
CORS_ORIGIN=http://localhost:3000
```

---

## 🧪 Testing

### 🔍 API Testing với Swagger UI

1. **Truy cập Swagger UI**: `http://localhost:8080/ute-phonehub/api-docs/`
2. **Test Authentication**:
   - POST `/api/auth/register` - Đăng ký tài khoản
   - POST `/api/auth/login` - Lấy JWT token
   - Authorize bằng token: `Bearer <your-jwt-token>`
3. **Test Endpoints**: Thử nghiệm các API endpoints

### 📝 Manual Testing với curl

```powershell
# Test registration
curl -X POST "http://localhost:8080/ute-phonehub/api/auth/register" `
  -H "Content-Type: application/json" `
  -d '{"username":"test@example.com","password":"123456","fullName":"Test User"}'

# Test login
curl -X POST "http://localhost:8080/ute-phonehub/api/auth/login" `
  -H "Content-Type: application/json" `
  -d '{"username":"test@example.com","password":"123456"}'

# Test protected endpoint
curl -X GET "http://localhost:8080/ute-phonehub/api/auth/profile" `
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### 🐛 Debug & Logs

```powershell
# Xem Tomcat logs
Get-Content "C:\apache-tomcat-11.0.10\logs\catalina.out" -Wait

# Xem application logs
Get-Content "C:\apache-tomcat-11.0.10\logs\localhost.*.log" -Wait
```

---

## 🚨 Troubleshooting

### ❗ Common Issues

#### 1. **Port 8080 đã được sử dụng**
```powershell
# Kiểm tra process đang sử dụng port 8080
netstat -ano | findstr :8080

# Kill process
taskkill /PID <process_id> /F
```

#### 2. **Database Connection Failed**
```java
// Kiểm tra cấu hình trong DatabaseUtil.java
// Đảm bảo PostgreSQL service đang chạy
// Kiểm tra username/password/database name
```

#### 3. **JWT Token Invalid**
```java
// Kiểm tra JWT_SECRET
// Đảm bảo token chưa hết hạn
// Verify token format: "Bearer <token>"
```

#### 4. **CORS Error**
```java
// Kiểm tra CorsFilter configuration
// Đảm bảo frontend URL được allowed
// Verify CORS headers
```

---

<div align="center">

### 🎉 Backend Setup Complete!

**Backend API**: `http://localhost:8080/ute-phonehub`
**Swagger UI**: `http://localhost:8080/ute-phonehub/api-docs/`

---

**[⬆ Về đầu trang](#️-ute-phonehub---backend-java-servlet)** • **[🏠 Main Project](../README.md)** • **[🎨 Frontend](../frontend/README.md)**

---

**Made with ☕ by UTE Backend Team**

</div>
