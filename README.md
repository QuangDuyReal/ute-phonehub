# 📱 UTE PhoneHub - E-commerce Platform

<div align="center">

![UTE PhoneHub Logo](https://img.shields.io/badge/UTE-PhoneHub-blue?style=for-the-badge&logo=mobile&logoColor=white)

**Hệ thống thương mại điện tử bán điện thoại di động**

Được phát triển bởi sinh viên Đại học Sư phạm Kỹ thuật TP.HCM (UTE)

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![React](https://img.shields.io/badge/React-18-61DAFB?style=flat&logo=react&logoColor=black)](https://reactjs.org/)
[![TypeScript](https://img.shields.io/badge/TypeScript-4.9-3178C6?style=flat&logo=typescript&logoColor=white)](https://www.typescriptlang.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-336791?style=flat&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Latest-2496ED?style=flat&logo=docker&logoColor=white)](https://www.docker.com/)
[![Railway](https://img.shields.io/badge/Railway-Deployed-0B0D0E?style=flat&logo=railway&logoColor=white)](https://railway.app/)

</div>

---

## 📋 Mục lục

- [🎯 Tổng quan dự án](#-tổng-quan-dự-án)
- [✨ Tính năng chính](#-tính-năng-chính)
- [🛠 Công nghệ sử dụng](#-công-nghệ-sử-dụng)
- [📁 Cấu trúc dự án](#-cấu-trúc-dự-án)
- [🚀 Hướng dẫn cài đặt](#-hướng-dẫn-cài-đặt)
- [🔧 Chạy ứng dụng](#-chạy-ứng-dụng)
- [📖 API Documentation](#-api-documentation)
- [🌐 Deployment](#-deployment)
- [📂 Phân chia module](#-phân-chia-module)
- [🤝 Đóng góp](#-đóng-góp)
- [📄 License](#-license)

---

## 🎯 Tổng quan dự án

**UTE PhoneHub** là một hệ thống thương mại điện tử (E-commerce) hoàn chỉnh chuyên về bán điện thoại di động và phụ kiện. Dự án được phát triển với kiến trúc **Microservices** hiện đại, bao gồm:

### 🏗️ Kiến trúc hệ thống

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Frontend      │    │    Backend      │    │    Database     │
│   (React)       │◄──►│ (Java Servlet)  │◄──►│  (PostgreSQL)   │
│   Port: 3000    │    │   Port: 8080    │    │   Port: 5432    │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### 🎭 Phân hệ

- **🛍️ Phân hệ Khách hàng**: 
  - Duyệt và tìm kiếm sản phẩm
  - Quản lý giỏ hàng và wishlist
  - Đặt hàng và thanh toán
  - Quản lý tài khoản cá nhân
  
- **⚙️ Phân hệ Quản trị**: 
  - Dashboard thống kê
  - Quản lý sản phẩm, danh mục, thương hiệu
  - Quản lý đơn hàng và người dùng
  - Quản lý voucher và khuyến mãi

---

## ✨ Tính năng chính

### 👤 Cho Khách hàng
- 🔐 **Xác thực**: Đăng ký, đăng nhập, quên mật khẩu
- 📱 **Quản lý sản phẩm**: Xem danh sách, chi tiết, tìm kiếm, lọc
- 🛒 **Giỏ hàng**: Thêm, sửa, xóa sản phẩm
- 📦 **Đặt hàng**: Thanh toán, theo dõi đơn hàng
- ⭐ **Đánh giá**: Viết review và rating sản phẩm
- 👤 **Tài khoản**: Quản lý thông tin cá nhân, địa chỉ

### 🔧 Cho Admin
- 📊 **Dashboard**: Thống kê doanh thu, đơn hàng
- 📱 **Quản lý sản phẩm**: CRUD sản phẩm, danh mục, thương hiệu
- 👥 **Quản lý người dùng**: Xem, khóa/mở khóa tài khoản
- 📦 **Quản lý đơn hàng**: Xử lý, cập nhật trạng thái
- 🎫 **Quản lý voucher**: Tạo, sửa, xóa mã giảm giá

---

## 🛠 Công nghệ sử dụng

### 🖥️ Backend (Java)
| Công nghệ | Phiên bản | Mô tả |
|-----------|-----------|-------|
| ![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat&logo=openjdk&logoColor=white) | 17 | Ngôn ngữ lập trình chính |
| ![Servlet](https://img.shields.io/badge/Jakarta_Servlet-6.0-orange?style=flat) | 6.0 | Web framework |
| ![Tomcat](https://img.shields.io/badge/Tomcat-11-F8DC75?style=flat&logo=apache-tomcat&logoColor=black) | 11 | Application server |
| ![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?style=flat&logo=apache-maven&logoColor=white) | 3.9+ | Build tool |
| ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-336791?style=flat&logo=postgresql&logoColor=white) | 15 | Cơ sở dữ liệu |
| ![JWT](https://img.shields.io/badge/JWT-0.11.5-000000?style=flat&logo=jsonwebtokens&logoColor=white) | 0.11.5 | Authentication |
| ![Jackson](https://img.shields.io/badge/Jackson-2.15.2-green?style=flat) | 2.15.2 | JSON processing |
| ![Swagger](https://img.shields.io/badge/Swagger-2.2.15-85EA2D?style=flat&logo=swagger&logoColor=black) | 2.2.15 | API documentation |

### 🎨 Frontend (React)
| Công nghệ | Phiên bản | Mô tả |
|-----------|-----------|-------|
| ![React](https://img.shields.io/badge/React-19.1-61DAFB?style=flat&logo=react&logoColor=black) | 19.1 | Frontend framework |
| ![TypeScript](https://img.shields.io/badge/TypeScript-4.9-3178C6?style=flat&logo=typescript&logoColor=white) | 4.9 | Type safety |
| ![Axios](https://img.shields.io/badge/Axios-1.11-5A29E4?style=flat&logo=axios&logoColor=white) | 1.11 | HTTP client |
| ![React Router](https://img.shields.io/badge/React_Router-7.8-CA4245?style=flat&logo=react-router&logoColor=white) | 7.8 | Client-side routing |
| ![Nginx](https://img.shields.io/badge/Nginx-latest-009639?style=flat&logo=nginx&logoColor=white) | Latest | Web server |

### 🚀 DevOps & Deployment
| Công nghệ | Mô tả |
|-----------|-------|
| ![Docker](https://img.shields.io/badge/Docker-Latest-2496ED?style=flat&logo=docker&logoColor=white) | Containerization |
| ![Railway](https://img.shields.io/badge/Railway-Latest-0B0D0E?style=flat&logo=railway&logoColor=white) | Cloud platform |
| ![GitHub](https://img.shields.io/badge/GitHub-Latest-181717?style=flat&logo=github&logoColor=white) | Version control & CI/CD |

---

## 📁 Cấu trúc dự án

```
ute-phonehub/
├── 📂 backend/                    # Java Servlet Backend
│   ├── 📂 src/main/
│   │   ├── 📂 java/com/utephonehub/
│   │   │   ├── 🔐 auth/           # Module xác thực & tài khoản
│   │   │   ├── 📱 product/        # Module sản phẩm
│   │   │   ├── 🛒 cart/           # Module giỏ hàng
│   │   │   ├── 📦 order/          # Module đơn hàng
│   │   │   ├── ⭐ review/         # Module đánh giá
│   │   │   ├── ⚙️ admin/          # Module quản trị
│   │   │   ├── 🎫 voucher/        # Module mã giảm giá
│   │   │   ├── 📄 dto/            # Data Transfer Objects
│   │   │   ├── 🗃️ model/          # Model classes
│   │   │   ├── 🔧 util/           # Utility classes
│   │   │   ├── 🛡️ filter/         # Security filters
│   │   │   └── 🏠 RootServlet.java # Main entry point
│   │   └── 📂 webapp/
│   │       ├── WEB-INF/web.xml    # Servlet configuration
│   │       └── swagger.json       # API documentation
│   ├── 📄 pom.xml                 # Maven dependencies
│   ├── 🐳 Dockerfile              # Backend container
│   └── 📄 README.md               # Backend documentation
├── 📂 frontend/                   # React TypeScript Frontend
│   ├── 📂 src/
│   │   ├── 📂 components/         # Reusable components
│   │   │   ├── common/            # Common UI components
│   │   │   └── layout/            # Layout components
│   │   ├── 📂 pages/              # Page components
│   │   ├── 📂 services/           # API services
│   │   ├── App.tsx                # Main App component
│   │   └── index.tsx              # Entry point
│   ├── 📂 public/                 # Static assets
│   ├── 📄 package.json            # NPM dependencies
│   ├── 🐳 Dockerfile              # Frontend container
│   ├── 🌐 nginx.conf              # Nginx configuration
│   └── 📄 README.md               # Frontend documentation
├── 📄 README.md                   # Project documentation
├── 📄 DEPLOYMENT.md               # Deployment guide
└── 🚀 deploy-railway.ps1          # Deployment script
```

---

## 🚀 Hướng dẫn cài đặt

### 📋 Yêu cầu hệ thống

- **Java**: JDK 17+ ([OpenJDK](https://openjdk.org/) hoặc [Oracle JDK](https://www.oracle.com/java/))
- **Apache Tomcat**: 11+ ([Download](https://tomcat.apache.org/download-11.cgi))
- **PostgreSQL**: 15+ ([Download](https://www.postgresql.org/download/))
- **Maven**: 3.9+ ([Download](https://maven.apache.org/download.cgi))
- **Node.js**: 18+ ([Download](https://nodejs.org/))
- **Git**: Latest ([Download](https://git-scm.com/))

### 🔧 Clone Repository

```powershell
# Clone repository
git clone https://github.com/QuangDuyReal/ute-phonehub.git
cd ute-phonehub-testdeploy
```

### 🗃️ Thiết lập Database

```sql
-- Tạo database
CREATE DATABASE ute_phonehub WITH ENCODING 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';

-- Tạo user (optional)
CREATE USER phonehub_user WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE ute_phonehub TO phonehub_user;
```

---

## 🔧 Chạy ứng dụng

### � Quick Start (Khuyến nghị)

```powershell
# 1. Setup toàn bộ môi trường development
.\scripts\manager-new.ps1 setup

# 2. Khởi động toàn bộ ứng dụng
.\scripts\manager-new.ps1 start

# 3. Kiểm tra trạng thái
.\scripts\manager-new.ps1 status
```

### 📋 Scripts Quản lý Project

**Main Manager Script:**
```powershell
# Script chính - quản lý toàn bộ project
.\scripts\manager-new.ps1 [action]

# Available actions:
#   setup    - Thiết lập môi trường development
#   start    - Khởi động backend + frontend
#   stop     - Dừng các services
#   status   - Kiểm tra trạng thái
#   clean    - Dọn dẹp build artifacts
#   reset    - Reset về trạng thái ban đầu
```

### �🖥️ Backend (Java Servlet)

```powershell
# Chạy backend với Jetty (Development - Hot Reload)
.\scripts\backend\backend.ps1 run

# Build WAR file cho production
.\scripts\backend\backend.ps1 build

# Chạy tests
.\scripts\backend\backend.ps1 test

# Clean build artifacts
.\scripts\backend\backend.ps1 clean
```

**Truy cập Backend:**
- **Development (Jetty)**: http://localhost:8080/ute-phonehub
- **Production (Tomcat)**: http://localhost:8080/ute-phonehub
- **API Documentation**: http://localhost:8080/ute-phonehub/swagger
- **API Endpoints**: http://localhost:8080/ute-phonehub/api

### 🌐 Frontend (React + TypeScript)

```powershell
# Development server với hot reload
.\scripts\frontend\frontend.ps1 dev

# Build production
.\scripts\frontend\frontend.ps1 build

# Run tests
.\scripts\frontend\frontend.ps1 test

# Clean node_modules và build
.\scripts\frontend\frontend.ps1 clean
```

**Truy cập Frontend:**
- **Development**: http://localhost:3000
- **Production Build**: Sau khi build, serve từ thư mục `build/`

### 🗃️ Database Management

```powershell
# Kiểm tra trạng thái database
.\scripts\database\database.ps1 status

# Tạo database mới
.\scripts\database\database.ps1 create

# Chạy migrations
.\scripts\database\database.ps1 migrate

# Seed dữ liệu mẫu
.\scripts\database\database.ps1 seed

# Backup database
.\scripts\database\database.ps1 backup

# Reset database (Cẩn thận!)
.\scripts\database\database.ps1 reset
```

### 🐳 Docker Management

```powershell
# Build Docker images
.\scripts\docker\docker.ps1 build

# Start services với Docker Compose
.\scripts\docker\docker.ps1 start

# Stop Docker services
.\scripts\docker\docker.ps1 stop

# Xem logs
.\scripts\docker\docker.ps1 logs

# Clean Docker resources
.\scripts\docker\docker.ps1 clean
```

### 🚀 Deployment

```powershell
# Deploy to Railway
.\scripts\deployment\deploy.ps1 deploy -Environment railway

# Deploy to local production
.\scripts\deployment\deploy.ps1 deploy -Environment local

# Check deployment status
.\scripts\deployment\deploy.ps1 status

# View deployment logs
.\scripts\deployment\deploy.ps1 logs
```

### 🛠️ Utilities

```powershell
# Check system requirements
.\scripts\utils\utils.ps1 check

# Update dependencies
.\scripts\utils\utils.ps1 update

# Run all tests
.\scripts\utils\utils.ps1 test

# Lint & format code
.\scripts\utils\utils.ps1 lint
.\scripts\utils\utils.ps1 format

# Backup toàn bộ project
.\scripts\utils\utils.ps1 backup
```

### 🖥️ Manual Backend Setup (Nếu cần)

```powershell
# Di chuyển vào thư mục backend
cd backend

# Compile và build project
mvn clean compile
mvn package

# Chạy với Jetty (Development)
mvn jetty:run

# Hoặc deploy to Tomcat (Production)
# Copy WAR file
Copy-Item "target\ute-phonehub.war" "C:\apache-tomcat-11.0.10\webapps\"

# Start Tomcat
C:\apache-tomcat-11.0.10\bin\startup.bat
```

**Backend sẽ chạy tại**: `http://localhost:8080/ute-phonehub`

### 🎨 Manual Frontend Setup (Nếu cần)

```powershell
# Di chuyển vào thư mục frontend  
cd frontend

# Cài đặt dependencies
npm install

# Chạy development server
npm start

# Build production
npm run build
```

**Frontend sẽ chạy tại**: `http://localhost:3000`

---

## 📖 API Documentation

### 🔗 Swagger UI
- **URL**: http://localhost:8080/ute-phonehub/swagger
- **API Base**: http://localhost:8080/ute-phonehub/api

### 📋 API Endpoints

#### 🔐 Authentication
```
POST /api/auth/login      # Đăng nhập
POST /api/auth/register   # Đăng ký
POST /api/auth/logout     # Đăng xuất
GET  /api/auth/profile    # Thông tin profile
```

#### 📱 Products
```
GET    /api/products           # Danh sách sản phẩm
GET    /api/products/{id}      # Chi tiết sản phẩm
POST   /api/products           # Tạo sản phẩm (Admin)
PUT    /api/products/{id}      # Cập nhật sản phẩm (Admin)
DELETE /api/products/{id}      # Xóa sản phẩm (Admin)
```

#### 🛒 Cart
```
GET    /api/cart              # Xem giỏ hàng
POST   /api/cart/add          # Thêm vào giỏ hàng
PUT    /api/cart/update       # Cập nhật số lượng
DELETE /api/cart/remove       # Xóa khỏi giỏ hàng
```

#### � Orders
```
GET    /api/orders            # Danh sách đơn hàng
POST   /api/orders            # Tạo đơn hàng
GET    /api/orders/{id}       # Chi tiết đơn hàng
PUT    /api/orders/{id}       # Cập nhật trạng thái (Admin)
```

#### ⭐ Reviews
```
GET    /api/reviews/product/{id}  # Reviews của sản phẩm
POST   /api/reviews              # Tạo review
PUT    /api/reviews/{id}         # Cập nhật review
DELETE /api/reviews/{id}         # Xóa review
```

#### 🎫 Vouchers
```
GET    /api/vouchers          # Danh sách voucher
POST   /api/vouchers          # Tạo voucher (Admin)
PUT    /api/vouchers/{id}     # Cập nhật voucher (Admin)
DELETE /api/vouchers/{id}     # Xóa voucher (Admin)
```

#### ⚙️ Admin
```
GET    /api/admin/dashboard   # Dashboard thống kê
GET    /api/admin/users       # Quản lý user
GET    /api/admin/statistics  # Thống kê hệ thống
```

---

## 🌐 Deployment

### 🚂 Railway (Production)

```powershell
# Deploy backend + frontend to Railway
.\scripts\deployment\deploy.ps1 deploy -Environment railway

# Hoặc sử dụng script riêng
.\deploy-railway.ps1
```

**Production URLs:**
- **Frontend**: https://ute-phonehub-frontend.railway.app
- **Backend**: https://ute-phonehub-backend.railway.app

### �🐳 Docker Production

```powershell
# Build và start với Docker Compose
.\scripts\docker\docker.ps1 build
.\scripts\docker\docker.ps1 start

# Hoặc manual Docker commands
docker-compose up -d --build
```

### 🖥️ Local Production

```powershell
# Deploy to local Tomcat + Nginx
.\scripts\deployment\deploy.ps1 deploy -Environment local
```

---

## 📂 Phân chia module

```powershell
# Build và chạy cả hệ thống với Docker Compose
docker-compose up --build

# Hoặc chạy riêng từng service
docker build -t ute-phonehub-backend ./backend
docker build -t ute-phonehub-frontend ./frontend

docker run -p 8080:8080 ute-phonehub-backend
docker run -p 3000:80 ute-phonehub-frontend
```

---

## 📖 API Documentation

### 🔗 Swagger UI
- **URL**: `http://localhost:8080/ute-phonehub/api-docs/`
- **Swagger JSON**: `http://localhost:8080/ute-phonehub/api-docs/swagger.json`

### 🌐 Main Endpoints

| Module | Endpoint | Mô tả |
|--------|----------|-------|
| 🏠 **Root** | `GET /` | Thông tin project và danh sách endpoints |
| 🔐 **Auth** | `POST /api/auth/login` | Đăng nhập |
| 🔐 **Auth** | `POST /api/auth/register` | Đăng ký |
| 📱 **Products** | `GET /api/products` | Danh sách sản phẩm |
| 🛒 **Cart** | `GET /api/cart` | Thông tin giỏ hàng |
| 📦 **Orders** | `POST /api/orders` | Tạo đơn hàng |
| ⭐ **Reviews** | `GET /api/reviews` | Danh sách đánh giá |
| ⚙️ **Admin** | `GET /api/admin/dashboard` | Dashboard quản trị |
| 🎫 **Vouchers** | `GET /api/vouchers` | Danh sách voucher |

### 🔑 Authentication

API sử dụng **JWT (JSON Web Token)** cho authentication:

```http
Authorization: Bearer <your-jwt-token>
```

---

## 🌐 Deployment

### 🚂 Railway.app (Recommended)

Chi tiết xem tại: [`DEPLOYMENT.md`](./DEPLOYMENT.md)

```powershell
# Sử dụng script tự động
.\deploy-railway.ps1
```

### 🐳 Docker Production

```dockerfile
# Build production images
docker build -t ute-phonehub-backend:prod ./backend
docker build -t ute-phonehub-frontend:prod ./frontend

# Deploy với docker-compose
docker-compose -f docker-compose.prod.yml up -d
```

---

## 📂 Phân chia module

Hệ thống được chia thành **10 modules** chính:

| # | Module | Mô tả | API Endpoints |
|---|---------|-------|---------------|
| 1️⃣ | **Auth & User** | Xác thực, quản lý tài khoản | `/api/auth/*` |
| 2️⃣ | **Product** | Quản lý sản phẩm | `/api/products/*` |
| 3️⃣ | **Cart** | Quản lý giỏ hàng | `/api/cart/*` |
| 4️⃣ | **Order** | Quản lý đơn hàng | `/api/orders/*` |
| 5️⃣ | **Review** | Đánh giá sản phẩm | `/api/reviews/*` |
| 6️⃣ | **Admin Dashboard** | Thống kê, báo cáo | `/api/admin/dashboard/*` |
| 7️⃣ | **Admin Product** | Quản lý sản phẩm (Admin) | `/api/admin/products/*` |
| 8️⃣ | **Admin Order** | Quản lý đơn hàng (Admin) | `/api/admin/orders/*` |
| 9️⃣ | **Admin User** | Quản lý người dùng (Admin) | `/api/admin/users/*` |
| 🔟 | **Voucher** | Quản lý mã giảm giá | `/api/vouchers/*` |

---

## 🤝 Đóng góp

### 👥 Thành viên nhóm

- **Leader**: [Tên leader]
- **Member 1**: [Tên thành viên 1] - Auth & User Module
- **Member 2**: [Tên thành viên 2] - Product Module  
- **Member 3**: [Tên thành viên 3] - Cart Module
- **Member 4**: [Tên thành viên 4] - Order Module
- **Member 5**: [Tên thành viên 5] - Review Module
- **Member 6**: [Tên thành viên 6] - Admin Dashboard
- **Member 7**: [Tên thành viên 7] - Admin Product
- **Member 8**: [Tên thành viên 8] - Admin Order
- **Member 9**: [Tên thành viên 9] - Admin User
- **Member 10**: [Tên thành viên 10] - Voucher Module

### 🔀 Workflow

```powershell
# 1. Fork repository
git fork https://github.com/quangduyreal/ute-phonehub

# 2. Tạo branch cho feature
git checkout -b feature/your-module-name

# 3. Develop và commit
git add .
git commit -m "feat: implement your feature"

# 4. Push và tạo Pull Request
git push origin feature/your-module-name
```

### 📋 Coding Standards

- **Java**: Follow [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
- **TypeScript**: Follow [Airbnb TypeScript Style Guide](https://github.com/airbnb/javascript/tree/master/packages/eslint-config-airbnb-typescript)
- **Commit**: Follow [Conventional Commits](https://www.conventionalcommits.org/)

---

## 📄 License

Dự án này được phát triển cho mục đích học tập tại **Đại học Sư phạm Kỹ thuật TP.HCM (UTE)**.

**© 2025 UTE PhoneHub Team. All rights reserved.**

---

<div align="center">

### 🌟 Cảm ơn bạn đã quan tâm đến UTE PhoneHub! 

**[⬆ Về đầu trang](#-ute-phonehub---e-commerce-platform)**

---

**Made with ❤️ by UTE Students**

</div>
