# 📱 **UTE Phone Hub** - Website Thương mại Điện tử Bán Điện thoại

<div align="center">

[![Live Demo](https://img.shields.io/badge/🌐_Live_Demo-www.utephonehub.me-blue?style=for-the-badge)](https://www.utephonehub.me)
[![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)](https://openjdk.org/)
[![Servlet](https://img.shields.io/badge/Servlet-6.1-red?style=for-the-badge)](https://jakarta.ee/specifications/servlet/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-13+-blue?style=for-the-badge&logo=postgresql)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Ready-blue?style=for-the-badge&logo=docker)](https://www.docker.com/)

**Website thương mại điện tử chuyên bán điện thoại di động, được xây dựng với kiến trúc MVC truyền thống sử dụng Java Servlet/JSP**

[🚀 Truy cập Demo](#-demo) •
[✨ Tính năng](#-tính-năng-chính) •
[🏗️ Tech Stack](#️-tech-stack) •
[👥 Team](#-development-team)

</div>

---

## 🎯 **Giới thiệu**

**UTE Phone Hub** là một hệ thống thương mại điện tử hoàn chỉnh được phát triển như một đồ án môn học **Lập trình Web** tại **Đại học Sư phạm Kỹ thuật TP.HCM (UTE)**. Dự án mô phỏng hoạt động của một website bán điện thoại thực tế với đầy đủ các chức năng từ quản lý sản phẩm, giỏ hàng, đặt hàng cho đến quản trị nội dung.

### **🎨 Thiết kế UI/UX**
Giao diện được lấy cảm hứng từ **[Thegioididong.com](https://www.thegioididong.com/)** - một trong những website bán lẻ điện thoại hàng đầu Việt Nam, tập trung vào:
- **Trải nghiệm người dùng** mượt mà và trực quan
- **Thiết kế responsive** hoàn hảo trên mọi thiết bị
- **Hiệu năng cao** với thời gian tải trang nhanh
- **Giao diện hiện đại** với Bootstrap 5

### **💳 Phương thức Thanh toán**
Hệ thống hỗ trợ **3 phương thức thanh toán** (giao diện demo):
1. **COD (Cash on Delivery)** - Thanh toán khi nhận hàng
2. **Thanh toán tại cửa hàng** - Thanh toán trực tiếp tại showroom
3. **Thẻ tín dụng/ghi nợ** - Form nhập thông tin thẻ (demo UI)

### **🔐 OAuth Integration**
- **Google Sign-In** - Đăng nhập nhanh chóng với tài khoản Google
- Tự động tạo tài khoản từ thông tin Google profile
- Không cần nhập mật khẩu, bảo mật cao hơn

---

## 🚀 **Demo**

### **🌐 Website đang hoạt động tại:**
```
https://www.utephonehub.me
```

---

## ✨ **Tính năng chính**

### **🛍️ Dành cho Khách hàng**

#### **Xác thực & Tài khoản**
- ✅ Đăng ký tài khoản mới với Email/Username/Password
- ✅ Đăng nhập với JWT Authentication (thời hạn token)
- ✅ **OAuth 2.0 - Đăng nhập bằng Google Account**
- ✅ Đăng xuất an toàn
- ✅ Quản lý thông tin cá nhân (Họ tên, SĐT)
- ✅ Thay đổi mật khẩu
- ✅ Quản lý danh sách địa chỉ giao hàng (Thêm/Sửa/Xóa)

#### **Duyệt & Tìm kiếm Sản phẩm**
- ✅ Hiển thị danh sách sản phẩm với phân trang
- ✅ Lọc sản phẩm theo:
  - Thương hiệu (Apple, Samsung, Xiaomi...)
  - Khoảng giá
  - RAM (4GB, 6GB, 8GB...)
  - Dung lượng lưu trữ (64GB, 128GB, 256GB...)
- ✅ Sắp xếp theo: Mới nhất, Giá tăng/giảm dần
- ✅ Tìm kiếm sản phẩm theo từ khóa
- ✅ Xem chi tiết sản phẩm với:
  - Thư viện ảnh đa phương tiện
  - Tùy chọn phiên bản (màu sắc, dung lượng)
  - Mô tả chi tiết & thông số kỹ thuật
  - Đánh giá từ người dùng khác

#### **Giỏ hàng & Đặt hàng**
- ✅ Thêm sản phẩm vào giỏ hàng
- ✅ Cập nhật số lượng hoặc xóa sản phẩm
- ✅ Hiển thị số lượng giỏ hàng real-time trên header
- ✅ **Redis Cache** - Lưu trữ giỏ hàng tạm thời
- ✅ Kiểm tra tồn kho tự động khi thêm vào giỏ
- ✅ **Guest Checkout** - Đặt hàng không cần đăng nhập
- ✅ Áp dụng mã giảm giá (voucher)
- ✅ Chọn phương thức thanh toán:
  - **COD** (Thanh toán khi nhận hàng)
  - **Tại cửa hàng** (Thanh toán trực tiếp)
  - **Thẻ tín dụng/ghi nợ** (Form demo)
- ✅ Tạo đơn hàng với trạng thái "Chờ xác nhận"

#### **Quản lý Đơn hàng**
- ✅ Xem lịch sử đơn hàng (cho người dùng đã đăng nhập)
- ✅ Xem chi tiết từng đơn hàng
- ✅ **Tra cứu đơn hàng công khai** (nhập Mã đơn + Email)

#### **Đánh giá Sản phẩm**
- ✅ Chấm điểm sản phẩm (1-5 sao)
- ✅ Viết bình luận chi tiết
- ✅ **Chỉ người đã mua mới được đánh giá**
- ✅ Like/Unlike đánh giá của người khác
- ✅ Hiển thị tổng số lượt thích

---

### **👨‍💼 Dành cho Quản trị viên**

#### **Dashboard & Thống kê**
- ✅ Tổng quan doanh thu, đơn hàng, sản phẩm
- ✅ Số liệu người dùng và đánh giá

#### **Quản lý Sản phẩm**
- ✅ Thêm/Sửa/Xóa sản phẩm (CRUD)
- ✅ Upload ảnh sản phẩm
- ✅ Quản lý phiên bản sản phẩm (variants)
- ✅ Cập nhật tồn kho
- ✅ **Soft Delete** - Ẩn sản phẩm thay vì xóa vĩnh viễn

#### **Quản lý Danh mục & Thương hiệu**
- ✅ Thêm/Sửa/Xóa danh mục sản phẩm
- ✅ Thêm/Sửa/Xóa thương hiệu
- ✅ **Kiểm tra ràng buộc** - Ngăn xóa nếu còn sản phẩm liên kết

#### **Quản lý Đơn hàng**
- ✅ Xem danh sách tất cả đơn hàng
- ✅ Lọc theo trạng thái (Chờ xác nhận, Đang xử lý, Hoàn thành...)
- ✅ Cập nhật trạng thái đơn hàng
- ✅ Xem chi tiết đơn hàng và thông tin khách

#### **Quản lý Người dùng**
- ✅ Xem danh sách người dùng
- ✅ Tìm kiếm theo tên hoặc email
- ✅ Khóa/Mở khóa tài khoản
- ✅ **Soft Delete** - Vô hiệu hóa thay vì xóa

#### **Quản lý Mã giảm giá (Voucher)**
- ✅ Tạo mã giảm giá mới
- ✅ Cấu hình:
  - Mã code duy nhất
  - Loại giảm giá (% hoặc số tiền cố định)
  - Giá trị giảm
  - Ngày hết hạn
  - Số lượng sử dụng tối đa
- ✅ Cập nhật/Xóa voucher

---

## 🏗️ **Tech Stack**

### **Backend**
```
Java 17                    - Ngôn ngữ lập trình chính
Jakarta Servlet 6.1        - Web framework (Tomcat 10.1+)
JSP 2.3+ & JSTL 1.2+      - Server-side rendering
JPA/Hibernate 6.4+         - ORM framework
PostgreSQL 13+             - Relational database
Redis 7+                   - Cache & Session storage
C3P0                       - Connection pooling
Google Cloud Platform      - Backend & Database hosting
```

### **Frontend**
```
HTML5 & CSS3               - Markup & Styling
JavaScript ES6+            - Client-side logic
Bootstrap 5                - UI framework
AJAX & Fetch API           - Asynchronous requests
```

### **Security**
```
JWT (JSON Web Token)       - Authentication
OAuth 2.0                  - Google Sign-In integration
BCrypt (salt=12)           - Password hashing
PreparedStatement          - SQL Injection prevention
JSTL escaping             - XSS prevention
CORS Filter               - Cross-origin handling
Redis Session Store        - Secure session management
```

### **Build & Deployment**
```
Maven 3.8+                 - Build automation
Docker & Docker Compose    - Containerization
Tomcat 10.1                - Application server
```

---

## 🗄️ **Database Schema**

### **PostgreSQL - Relational Database (14 tables):**

#### **Quản lý Người dùng**
- `users` - Thông tin tài khoản (email, password, role)
- `addresses` - Địa chỉ giao hàng của người dùng
- `password_reset_tokens` - Token reset mật khẩu

#### **Quản lý Sản phẩm**
- `products` - Thông tin sản phẩm
- `product_images` - Ảnh sản phẩm
- `product_specifications` - Thông số kỹ thuật
- `categories` - Danh mục sản phẩm
- `brands` - Thương hiệu

#### **Quản lý Đơn hàng**
- `orders` - Thông tin đơn hàng
- `order_items` - Chi tiết sản phẩm trong đơn

#### **Giỏ hàng**
- `carts` - Giỏ hàng của người dùng
- `cart_items` - Sản phẩm trong giỏ

#### **Đánh giá & Khuyến mãi**
- `reviews` - Đánh giá sản phẩm
- `review_likes` - Lượt thích đánh giá
- `vouchers` - Mã giảm giá

### **Mối quan hệ chính:**
- `users` (1) ↔ (N) `addresses`
- `users` (1) ↔ (N) `orders`
- `users` (1) ↔ (1) `carts`
- `products` (N) ↔ (1) `categories`
- `products` (N) ↔ (1) `brands`
- `orders` (1) ↔ (N) `order_items`
- `carts` (1) ↔ (N) `cart_items`
- `products` (1) ↔ (N) `reviews`

### **Redis - Cache Layer:**

#### **Session Management**
- `session:{sessionId}` - User session data (JWT tokens, user info)
- TTL: 30 minutes (auto-refresh on activity)

#### **Shopping Cart Cache**
- `cart:{userId}` - Temporary cart data for logged-in users
- `cart:guest:{sessionId}` - Guest user cart data
- TTL: 24 hours

#### **Password Reset**
- `reset_token:{token}` - Password reset token validation
- TTL: 15 minutes

#### **API Rate Limiting**
- `rate_limit:{ip}:{endpoint}` - Request counting for rate limiting
- TTL: 15 minutes

---

## 🏛️ **Kiến trúc Hệ thống**

### **MVC Pattern (Strict Layer Separation)**

```
┌─────────────────────────────────────────────────────────┐
│                    CLIENT (Browser)                      │
│              HTML/CSS/JS + Bootstrap 5                   │
└─────────────────────────────────────────────────────────┘
                          ↓ HTTP Request
┌─────────────────────────────────────────────────────────┐
│                 CONTROLLER LAYER                         │
│          (Servlet) - Handle HTTP only                    │
│    - Request parsing                                     │
│    - Response formatting                                 │
│    - NO business logic                                   │
└─────────────────────────────────────────────────────────┘
                          ↓ Service call
┌─────────────────────────────────────────────────────────┐
│                  SERVICE LAYER                           │
│            All business logic here                       │
│    - Validation                                          │
│    - Transaction management                              │
│    - DTO mapping                                         │
└─────────────────────────────────────────────────────────┘
                          ↓ Data access
┌─────────────────────────────────────────────────────────┐
│                REPOSITORY LAYER                          │
│           JPA/Hibernate queries only                     │
│    - EntityManager operations                            │
│    - JPQL & Native SQL                                   │
└─────────────────────────────────────────────────────────┘
                          ↓
┌─────────────────────────────────────────────────────────┐
│                   DATABASE                               │
│              PostgreSQL 13+                              │
└─────────────────────────────────────────────────────────┘
```

### **Package Structure**
```
com.utephonehub
├── controller/          # Servlet controllers
├── service/            # Business logic
├── repository/         # Data access layer
├── entity/             # JPA entities
├── dto/
│   ├── request/        # Request DTOs
│   └── response/       # Response DTOs
├── filter/             # Servlet filters (JWT, CORS)
├── util/               # Utility classes
├── exception/          # Custom exceptions
└── config/             # Configuration classes
```

---

## 🔒 **Bảo mật**

### **Các biện pháp bảo mật đã áp dụng:**

✅ **Authentication & Authorization**
- JWT Authentication với thời hạn token
- **OAuth 2.0 - Google Sign-In** tích hợp
- Refresh token mechanism
- Secure token storage
- Role-based access control (USER/ADMIN)

✅ **Password Security**
- BCrypt hashing với salt=12
- Mật khẩu phải mạnh (ít nhất 8 ký tự, chữ hoa, chữ thường, số, ký tự đặc biệt)
- Không lưu plain text password
- **Redis-based password reset tokens** với thời hạn

✅ **Session Management**
- **Redis Session Store** cho hiệu năng cao
- Session timeout tự động
- Concurrent session control

✅ **SQL Injection Prevention**
- Sử dụng PreparedStatement/JPA
- Input validation server-side
- Parameterized queries

✅ **XSS Prevention**
- JSTL `<c:out>` cho output escaping
- Content Security Policy headers
- Input sanitization

✅ **Rate Limiting**
- Giới hạn 10 lần thử đăng nhập/15 phút
- Chống brute-force attacks

✅ **Soft Delete**
- Dữ liệu quan trọng không bị xóa vĩnh viễn
- Có thể khôi phục khi cần
- Đảm bảo tính toàn vẹn dữ liệu lịch sử

✅ **CORS Configuration**
- Chỉ cho phép origins đã được định nghĩa
- Secure headers configuration

✅ **Cache Security**
- **Redis Cache** cho giỏ hàng và session
- Encrypted cache data
- Automatic cache expiration

---

## 🚀 **Deployment**

### **Production Environment:**
- **Domain:** [www.utephonehub.me](https://www.utephonehub.me)
- **Backend Hosting:** Google Cloud Platform (GCP)
  - Compute Engine VM instance
  - Docker containerized application
- **Database:** PostgreSQL 13+ on Google Cloud SQL
  - Automated backups
  - High availability configuration
- **Cache Layer:** Redis 7+ on Google Cloud Memorystore
  - Session storage
  - Shopping cart cache
  - Password reset tokens
- **SSL/TLS:** Let's Encrypt certificates
- **CDN:** Cloudflare for static assets (optional)

### **Docker Compose Architecture:**
```yaml
services:
  app:         # Tomcat 10.1 + WAR file
  postgres:    # PostgreSQL 13+ (Cloud SQL in production)
  redis:       # Redis 7+ (Memorystore in production)
```

### **Infrastructure Highlights:**
- ☁️ **Google Cloud Platform** - Enterprise-grade infrastructure
- 🔄 **Auto-scaling** - Dynamic resource allocation
- 💾 **Automated Backups** - Daily database snapshots
- 🚀 **High Performance** - Redis caching for sub-millisecond response
- 🔒 **Secure** - VPC networking with firewall rules

---

## 📊 **Hiệu năng**

### **Performance Metrics:**
- ⚡ Thời gian tải trang chủ: **< 3 giây**
- 🔄 API response time: **< 500ms** (< 100ms với Redis cache)
- 📦 Database connection pool: **min=5, max=20**
- 🎯 Concurrent users: **Tested up to 100 users**
- 🗄️ Redis cache hit rate: **> 90%**

### **Optimizations:**
- **Redis caching strategy:**
  - Shopping cart data (TTL: 24h)
  - User session data (TTL: 30 minutes)
  - Password reset tokens (TTL: 15 minutes)
- Lazy loading cho JPA relationships
- Connection pooling (C3P0)
- Image optimization & lazy loading
- Minified CSS/JS
- Browser caching headers
- Database query optimization with indexes

---

## 👥 **Development Team**

### **Đại học Sư phạm Kỹ thuật TP.HCM (UTE)**
**Môn:** Lập trình Web  
**Học kỳ:** 1 - Năm học 2025-2026

| Vai trò | Thành viên | Module phụ trách |
|---------|-----------|------------------|
| **Project Manager** | Đỗ Kiến Hưng | Đặt hàng & Thanh toán |
| **CI/CD Lead** | Nguyễn Kim Tú | Xác thực & Quản lý Tài khoản |
| **Backend Dev** | Phan Trọng Quí | Quản lý Sản phẩm (Admin) |
| **Backend Dev** | Huỳnh Ngọc Thạch | Quản lý Danh mục & Thương hiệu |
| **Frontend Dev** | Trần Quốc Giăng | Hiển thị & Tìm kiếm Sản phẩm |
| **Frontend Dev** | Lưu Trần Kim Phú | Giỏ hàng |
| **Fullstack Dev** | Phan Trọng Phú | Quản lý Người dùng (Admin) |
| **Backend Dev** | Huỳnh Hữu Huy | Đánh giá & Bình luận |
| **Backend Dev** | Nguyễn Văn Quang Duy | Khuyến mãi & Voucher |

---

## 📄 **License**

Dự án này được phát triển cho mục đích học tập tại **Đại học Sư phạm Kỹ thuật TP.HCM**.

---

## 📞 **Liên hệ**

- **Website:** [www.utephonehub.me](https://www.utephonehub.me)
- **Repository:** [github.com/QuangDuyReal/ute-phonehub](https://github.com/QuangDuyReal/ute-phonehub)
- **Email:** 23133030@student.hcmute.edu.vn

---

<div align="center">

**Cảm ơn bạn đã quan tâm đến dự án! ⭐**

Made with ❤️ by UTE Phone Hub Team

</div>
