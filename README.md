# 📱 UTE PhoneHub - E-commerce Platform

<div align="center">

![UTE PhoneHub Logo](https://img.shields.io/badge/UTE-PhoneHub-blue?style=for-the-badge&logo=mobile&logoColor=white)

**Hệ thống thương mại điện tử bán điện thoại di động**

Được phát triển bởi sinh viên Đại học Sư phạm Kỹ thuật TP.HCM (UTE)

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![React](https://img.shields.io/badge/React-18-61DAFB?style=flat&logo=react&logoColor=black)](https://reactjs.org/)
[![TypeScript](https://img.shields.io/badge/TypeScript-4.9-3178C6?style=flat&logo=typescript&logoColor=white)](https://www.typescriptlang.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=flat&logo=mysql&logoColor=white)](https://www.mysql.com/)
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
│   (React)       │◄──►│ (Java Servlet)  │◄──►│    (MySQL)      │
│   Port: 3000    │    │   Port: 8080    │    │   Port: 3306    │
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
| ![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=flat&logo=mysql&logoColor=white) | 8.0 | Cơ sở dữ liệu |
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
- **MySQL**: 8.0+ ([Download](https://dev.mysql.com/downloads/mysql/))
- **Maven**: 3.9+ ([Download](https://maven.apache.org/download.cgi))
- **Node.js**: 18+ ([Download](https://nodejs.org/))
- **Git**: Latest ([Download](https://git-scm.com/))

### 🔧 Clone Repository

```powershell
# Clone repository
git clone https://github.com/darktheDE/ute-phonehub-testdeploy.git
cd ute-phonehub-testdeploy
```

### 🗃️ Thiết lập Database

```sql
-- Tạo database
CREATE DATABASE ute_phonehub CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Tạo user (optional)
CREATE USER 'phonehub_user'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON ute_phonehub.* TO 'phonehub_user'@'localhost';
FLUSH PRIVILEGES;
```

---

## 🔧 Chạy ứng dụng

### 🖥️ Backend (Java Servlet)

```powershell
# Di chuyển vào thư mục backend
cd backend

# Compile và build project
mvn clean compile
mvn package

# Deploy to Tomcat
# Cách 1: Copy WAR file
Copy-Item "target\ute-phonehub.war" "C:\apache-tomcat-11.0.10\webapps\"

# Cách 2: Sử dụng Maven Tomcat plugin
mvn tomcat7:deploy

# Start Tomcat
C:\apache-tomcat-11.0.10\bin\startup.bat
```

**Backend sẽ chạy tại**: `http://localhost:8080/ute-phonehub`

### 🎨 Frontend (React)

```powershell
# Di chuyển vào thư mục frontend  
cd frontend

# Cài đặt dependencies
npm install

# Chạy development server
npm start
```

**Frontend sẽ chạy tại**: `http://localhost:3000`

### 🐳 Docker (Tùy chọn)

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
git fork https://github.com/darktheDE/ute-phonehub-testdeploy

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
│   │   │   ├── admin/    # Module quản trị
│   │   │   ├── voucher/  # Module mã giảm giá
│   │   │   ├── filter/   # Filters (CORS, Auth)
│   │   │   ├── model/    # Entity classes
│   │   │   ├── dto/      # Data Transfer Objects
│   │   │   └── util/     # Utility classes
│   │   └── webapp/
│   │       └── WEB-INF/web.xml
│   └── pom.xml
├── frontend/             # React.js Frontend (Sẽ phát triển)
├── .gitignore
└── README.md
```

## ⚙️ Hướng dẫn cài đặt

### Yêu cầu hệ thống
- **Java JDK 17** hoặc cao hơn
- **Apache Tomcat 11** 
- **MySQL 8.0** hoặc cao hơn
- **Maven 3.6** hoặc cao hơn

### Cài đặt môi trường

1. **Cài đặt Java JDK 17**
   ```bash
   # Kiểm tra version Java
   java -version
   javac -version
   ```

2. **Cài đặt Apache Tomcat 11**
   - Tải về từ: https://tomcat.apache.org/download-11.cgi
   - Giải nén vào thư mục (ví dụ: `C:\apache-tomcat-11.0.10`)

3. **Cài đặt MySQL**
   - Tải và cài đặt MySQL Server 8.0
   - Tạo database: `ute_phonehub`

4. **Cài đặt Maven**
   ```bash
   # Kiểm tra version Maven
   mvn -version
   ```

## 🚀 Hướng dẫn chạy Backend

### Bước 1: Clone repository
```bash
git clone https://github.com/QuangDuyReal/ute-phonehub.git
cd ute-phonehub
```

### Bước 2: Build project
```bash
cd backend
mvn clean package
```

### Bước 3: Deploy lên Tomcat

#### Cách 1: Manual deployment
```bash
# Copy file WAR vào webapps
copy target\ute-phonehub.war C:\apache-tomcat-11.0.10\webapps\

# Khởi động Tomcat
cd C:\apache-tomcat-11.0.10\bin
startup.bat
```

#### Cách 2: Sử dụng PowerShell (Windows)
```powershell
# Build và deploy
cd D:\path\to\ute-phonehub\backend
mvn clean package

# Copy WAR file
Copy-Item "target\ute-phonehub.war" "C:\apache-tomcat-11.0.10\webapps\" -Force

# Start Tomcat
$env:CATALINA_HOME = "C:\apache-tomcat-11.0.10"
cd C:\apache-tomcat-11.0.10\bin
.\startup.bat
```

### Bước 4: Kiểm tra ứng dụng
- **Root endpoint**: http://localhost:8080/ute-phonehub/
- **API Documentation**: http://localhost:8080/ute-phonehub/api-docs/

### Troubleshooting

#### Lỗi thường gặp:
1. **Port 8080 đã được sử dụng**
   ```bash
   # Kiểm tra process đang sử dụng port
   netstat -ano | findstr :8080
   
   # Kill process (thay PID bằng số thực tế)
   taskkill /PID [PID] /F
   ```

2. **Java version không khớp**
   - Đảm bảo đang sử dụng Java 17
   - Kiểm tra biến môi trường JAVA_HOME

3. **Tomcat không khởi động được**
   - Kiểm tra log tại `logs/catalina.YYYY-MM-DD.log`
   - Đảm bảo CATALINA_HOME được set đúng

## � Deployment

### Development
- **Frontend**: http://localhost:3000
- **Backend**: http://localhost:8080/ute-phonehub
- **API Docs**: http://localhost:8080/ute-phonehub/api-docs/

### Production (Railway.app)
Dự án được cấu hình để deploy lên Railway.app với:

- **Containerization**: Docker containers cho cả Frontend và Backend
- **Auto-scaling**: Tự động scale theo traffic
- **CI/CD**: Deploy tự động từ GitHub

#### Quick Deploy
```bash
# Install Railway CLI
npm install -g @railway/cli

# Login to Railway
railway login

# Deploy (từ root directory)
.\deploy-railway.ps1
```

📖 **Xem hướng dẫn chi tiết**: [DEPLOYMENT.md](./DEPLOYMENT.md)

## �📚 API Documentation

### Root API
- **GET** `/` - Thông tin project
- **GET** `/api-docs/` - Swagger UI Documentation

### Module APIs
- **GET** `/api/auth` - Authentication module info
- **GET** `/api/products` - Product module info  
- **GET** `/api/cart` - Cart module info
- **GET** `/api/orders` - Order module info
- **GET** `/api/reviews` - Review module info
- **GET** `/api/admin` - Admin module info
- **GET** `/api/vouchers` - Voucher module info

### Swagger UI
Truy cập: http://localhost:8080/ute-phonehub/api-docs/

API documentation được tự động tạo và cập nhật thông qua Swagger annotations.

## ✨ Tính năng chính

### Cho Khách hàng
- 🔐 Đăng ký/Đăng nhập tài khoản
- 🏠 Xem trang chủ với sản phẩm nổi bật
- 📱 Duyệt danh sách sản phẩm với bộ lọc
- 🔍 Tìm kiếm sản phẩm
- 📄 Xem chi tiết sản phẩm
- 🛒 Quản lý giỏ hàng
- 💳 Đặt hàng và thanh toán COD
- 📦 Theo dõi đơn hàng
- ⭐ Đánh giá sản phẩm

### Cho Admin
- 📊 Dashboard tổng quan
- 📱 Quản lý sản phẩm (CRUD)
- 📂 Quản lý danh mục và thương hiệu
- 📋 Quản lý đơn hàng
- 👥 Quản lý người dùng
- 🎫 Quản lý mã giảm giá

## 👥 Phân chia module

| Module | Chức năng | Người phụ trách |
|--------|-----------|-----------------|
| **M01** | Xác thực & Quản lý Tài khoản | Thành viên 1 |
| **M02** | Quản lý Sản phẩm (Admin) | Thành viên 2 |
| **M03** | Quản lý Danh mục & Thương hiệu (Admin) | Thành viên 3 |
| **M04** | Hiển thị Sản phẩm & Tìm kiếm (Client) | Thành viên 4 |
| **M05** | Giỏ hàng (Shopping Cart) | Thành viên 5 |
| **M06** | Đặt hàng & Thanh toán (Checkout) | Thành viên 6 |
| **M07** | Quản lý Đơn hàng (Client & Admin) | Thành viên 7 |
| **M08** | Đánh giá & Bình luận | Thành viên 8 |
| **M09** | Quản lý Người dùng (Admin) | Thành viên 9 |
| **M10** | Khuyến mãi & Mã giảm giá | Thành viên 10 |

## 🤝 Đóng góp

### Quy trình phát triển
1. Fork repository
2. Tạo branch mới cho feature: `git checkout -b feature/ten-feature`
3. Commit changes: `git commit -m 'Add some feature'`
4. Push to branch: `git push origin feature/ten-feature`
5. Tạo Pull Request

### Coding Convention
- **Java**: Tuân thủ Google Java Style Guide
- **Commit message**: Sử dụng Conventional Commits
- **API**: RESTful API design principles

## 📞 Liên hệ

- **Repository**: https://github.com/QuangDuyReal/ute-phonehub
- **Documentation**: [Link tới docs]
- **Issues**: [Link tới issues]

---

**© 2025 UTE PhoneHub - Đại học Sư phạm Kỹ thuật TP.HCM**