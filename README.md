# 🚀 UTE Phone Hub - Setup Guide
---

## 📋 Yêu cầu

- **Java 17+** - [Download](https://adoptium.net/)
- **Docker & Docker Compose** - [Download](https://www.docker.com/products/docker-desktop/)
- **Maven 3.8+** (tùy chọn, có thể dùng Maven wrapper)

---

## ⚡ Quick Start

### 1. Clone Repository
```bash
git clone https://github.com/QuangDuyReal/ute-phonehub
cd ute-phonehub
```

### 2. Khởi động Docker Services
```bash
# Start PostgreSQL và Redis
docker-compose up -d

# Kiểm tra services đang chạy
docker-compose ps
```

### 3. Build Project
```bash
# Build với Maven wrapper
./mvnw clean package

# Hoặc với Maven đã cài
mvn clean package
```

### 4. Chạy Application

#### Option 1: Docker Compose (Khuyến nghị)
```bash
# Application sẽ tự động chạy trong container
docker-compose up -d

# Xem logs
docker-compose logs -f app
```

#### Option 2: Local Tomcat
```bash
# Copy WAR file vào Tomcat webapps
cp target/ute-phone-hub.war $TOMCAT_HOME/webapps/

# Start Tomcat
$TOMCAT_HOME/bin/startup.sh
```

### 5. Truy cập Application
```
http://localhost:8080
```

---

## 🔐 Tài khoản Mặc định

### Admin Account
- **Username**: `admin123`
- **Password**: `admin123`
- **Email**: `admin123@utephonehub.me`

### Customer Account (mẫu)
- **Username**: `nguyenvana`
- **Password**: `User@123`
- **Email**: `nguyenvana@gmail.com`

---

## 🗄️ Database

### Thông tin kết nối
- **Host**: `localhost:5432`
- **Database**: `utephonehub_dev`
- **User**: `utephonehub_user`
- **Password**: `utephonehub_password`

### Database Schema
Schema sẽ được tự động tạo khi chạy Docker lần đầu từ file:
- `docker/postgres/cloud-sql-init.sql`

### Reset Database
```bash
# Xóa volume và khởi động lại
docker-compose down -v
docker-compose up -d
```

---

## 🔧 Cấu hình

### Environment Variables
File: `docker-compose.yml`

```yaml
DB_HOST: postgres
DB_PORT: 5432
DB_NAME: utephonehub_dev
DB_USER: utephonehub_user
DB_PASSWORD: utephonehub_password

REDIS_HOST: redis
REDIS_PORT: 6379
```

### Database Configuration
File: `src/main/resources/META-INF/persistence.xml`

Cấu hình database connection (tự động từ environment variables khi chạy Docker).

---

## 🛠️ Development

### Build Commands
```bash
# Clean và compile
./mvnw clean compile

# Build WAR file
./mvnw clean package

# Run tests
./mvnw test

# Skip tests khi build
./mvnw clean package -DskipTests
```

### Docker Commands
```bash
# Start services
docker-compose up -d

# Stop services
docker-compose down

# View logs
docker-compose logs -f

# Restart service
docker-compose restart app

# Access PostgreSQL
docker-compose exec postgres psql -U utephonehub_user -d utephonehub_dev
```

### Hot Reload
```bash
# Rebuild và restart container
docker-compose up -d --build app
```

---

## 🐛 Troubleshooting

### Database Connection Error
```bash
# Kiểm tra PostgreSQL đang chạy
docker-compose ps postgres

# Xem logs
docker-compose logs postgres

# Test connection
docker-compose exec postgres psql -U utephonehub_user -d utephonehub_dev -c "SELECT 1;"
```

### Port Conflict
```bash
# Kiểm tra port đang sử dụng
netstat -tulpn | grep :5432  # PostgreSQL
netstat -tulpn | grep :6379  # Redis
netstat -tulpn | grep :8080  # Application

# Thay đổi port trong docker-compose.yml nếu cần
```

### Build Error
```bash
# Clean Maven cache
./mvnw clean

# Update dependencies
./mvnw dependency:resolve

# Rebuild
./mvnw clean package
```

### Reset Everything
```bash
# Stop và xóa tất cả containers, volumes
docker-compose down -v

# Xóa build artifacts
./mvnw clean

# Khởi động lại
docker-compose up -d --build
```

---

## 📚 Tài liệu

- **README.md** - Tổng quan dự án
- **PROJECT_OVERVIEW.md** - Chi tiết dự án
- **docs/** - Tài liệu kỹ thuật
- **postman/** - Postman collection để test API

---

## 🆘 Hỗ trợ

Nếu gặp vấn đề, vui lòng:
1. Kiểm tra logs: `docker-compose logs -f`
2. Xem tài liệu trong `docs/`
3. Tạo issue trên repository

---

**📝 Last Updated**: November 2025  
**👥 Maintained by**: UTE Phone Hub Development Team
