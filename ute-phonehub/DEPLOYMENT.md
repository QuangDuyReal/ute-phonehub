# 🚀 Deployment Guide - Railway.app

Hướng dẫn deploy UTE PhoneHub lên Railway.app

## 📋 Yêu cầu trước khi deploy

### 1. Tài khoản Railway
- Đăng ký tại: https://railway.app
- Kết nối với GitHub account

### 2. Repository
- Push code lên GitHub repository
- Đảm bảo có cả frontend và backend

## 🎯 Bước 1: Deploy Backend (Java Servlet)

### 1.1. Tạo project mới trên Railway
```bash
# Login to Railway CLI (optional)
npm install -g @railway/cli
railway login
```

### 1.2. Deploy Backend
1. Truy cập [Railway Dashboard](https://railway.app/dashboard)
2. Click **"New Project"**
3. Chọn **"Deploy from GitHub repo"**
4. Chọn repository `ute-phonehub`
5. Chọn **root directory** (để deploy backend)
6. Railway sẽ tự động detect Dockerfile trong `/backend`

### 1.3. Cấu hình Environment Variables cho Backend
```bash
# Database (nếu sử dụng)
DATABASE_URL=your_database_url
POSTGRESQL_HOST=containers-us-west-xxx.railway.app
POSTGRESQL_PORT=xxxx
POSTGRESQL_DATABASE=railway
POSTGRESQL_USER=postgres
POSTGRESQL_PASSWORD=xxxxxxxxx

# Server configuration
PORT=8080
JAVA_OPTS=-Xmx512m -Xms256m
```

### 1.4. Kiểm tra Deployment
- URL Backend: `https://your-backend-name.railway.app`
- Health check: `https://your-backend-name.railway.app/`
- Swagger UI: `https://your-backend-name.railway.app/api-docs/`

## 🎯 Bước 2: Deploy Frontend (ReactJS)

### 2.1. Tạo project riêng cho Frontend
1. Tạo **New Project** thứ 2
2. Chọn same repository `ute-phonehub`
3. **Quan trọng**: Set **Root Directory** = `/frontend`
4. Railway sẽ detect Dockerfile trong `/frontend`

### 2.2. Cấu hình Environment Variables cho Frontend
```bash
# API URL - Thay bằng URL backend đã deploy
REACT_APP_API_BASE_URL=https://your-backend-name.railway.app

# Environment
REACT_APP_ENVIRONMENT=production

# Build configuration
GENERATE_SOURCEMAP=false
DISABLE_ESLINT_PLUGIN=true
```

### 2.3. Kiểm tra Deployment
- URL Frontend: `https://your-frontend-name.railway.app`
- Test trang chủ và endpoints page

## 🎯 Bước 3: Database (PostgreSQL) - Optional

### 3.1. Thêm PostgreSQL Database
1. Trong Backend project, click **"+ New"**
2. Chọn **"Database" → "PostgreSQL"**
3. Railway sẽ tự tạo database instance

### 3.2. Kết nối Database
```bash
# Railway sẽ tự động provide các variables:
DATABASE_URL=postgresql://postgres:password@host:port/railway
PGHOST=containers-us-west-xxx.railway.app
PGPORT=xxxx
PGDATABASE=railway
PGUSER=postgres
PGPASSWORD=xxxxxxxxx
```

## 📝 File Structure cho Railway

```
ute-phonehub/
├── backend/
│   ├── Dockerfile              ✅ Có
│   ├── railway.json           ✅ Có  
│   ├── pom.xml
│   └── src/
├── frontend/
│   ├── Dockerfile              ✅ Có
│   ├── railway.json           ✅ Có
│   ├── nginx.conf             ✅ Có
│   ├── .env.example           ✅ Có
│   ├── package.json
│   └── src/
└── README.md
```

## 🔧 Custom Domains (Optional)

### Frontend Domain
1. Trong Frontend project settings
2. Chọn **"Settings" → "Domains"**
3. Add custom domain: `ute-phonehub.com`

### Backend Domain  
1. Trong Backend project settings
2. Add custom domain: `api.ute-phonehub.com`

## 🐛 Troubleshooting

### Backend không start
```bash
# Check logs trong Railway dashboard
# Common issues:
- Port configuration (phải dùng PORT env variable)
- Java memory issues
- Database connection
```

### Frontend không load
```bash
# Check nginx logs
# Common issues:
- API URL không đúng
- CORS issues
- Build errors
```

### Database connection failed
```bash
# Verify environment variables:
echo $DATABASE_URL
echo $PGHOST
echo $PGPORT
```

## 🚀 Production URLs

Sau khi deploy thành công:

- **Frontend**: `https://ute-phonehub-frontend.railway.app`
- **Backend API**: `https://ute-phonehub-backend.railway.app`
- **Swagger UI**: `https://ute-phonehub-backend.railway.app/api-docs/`
- **Database**: `Railway-managed PostgreSQL instance`

## 💡 Tips

1. **Separate Projects**: Frontend và Backend nên là 2 projects riêng
2. **Environment Variables**: Nhớ set đúng API URL cho frontend
3. **Logs**: Sử dụng Railway logs để debug
4. **Scaling**: Railway auto-scale theo traffic
5. **Cost**: Free tier có giới hạn, monitor usage

## 📞 Support

Nếu gặp vấn đề:
1. Check Railway logs và documentation
2. Verify environment variables
3. Test locally trước khi deploy
4. Check CORS configuration

---

**Happy Deploying! 🎉**
