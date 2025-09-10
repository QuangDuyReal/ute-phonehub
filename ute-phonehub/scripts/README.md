# 🛠️ UTE PhoneHub -  Scripts Documentation

<div align="center">

![PowerShell](https://img.shields.io/badge/PowerShell-5.1+-0078D4?style=for-the-badge&logo=powershell&logoColor=white)
![Scripts](https://img.shields.io/badge/Scripts-Management-green?style=for-the-badge)

**Simplified PowerShell Scripts for UTE PhoneHub Project Management**

[🏠 Main Project](../README.md) • [📖 Detailed Docs](README-.md)

</div>

---

## 📁 Script Structure

```
scripts/
├── 🎯 manager.ps1     # Main project orchestrator
├── backend/
│   ├── backend.ps1        # Backend management (Jetty/Tomcat)
├── frontend/
│   ├── frontend.ps1       # Frontend management (React)
├── database/
│   ├── database.ps1       # Database operations
├── docker/
│   └── docker.ps1         # Docker container management
├── deployment/
│   └── deploy.ps1         # Multi-platform deployment
├── utils/
│   ├── utils.ps1          # Development utilities
└── README.md              # Detailed documentation
```

---

## 🚀 Quick Start

### 🎯 One-Command Setup & Start

```powershell
# Complete setup và start development
.\scripts\manager.ps1 setup && .\scripts\manager.ps1 start
```

### 📋 Step-by-Step

```powershell
# 1. Check system requirements
.\scripts\utils\utils.ps1 check

# 2. Setup development environment
.\scripts\manager.ps1 setup

# 3. Start backend + frontend
.\scripts\manager.ps1 start

# 4. Check everything is running
.\scripts\manager.ps1 status
```

### 🌐 URLs sau khi start

- **Frontend**: http://localhost:3000
- **Backend**: http://localhost:8080/ute-phonehub
- **API Docs**: http://localhost:8080/ute-phonehub/swagger

---

## 📋 Available Scripts

### 🎯 Main Manager (`manager.ps1`)
```powershell
# Core operations
.\manager.ps1 help          # Show help
.\manager.ps1 setup         # Setup environment  
.\manager.ps1 start         # Start development
.\manager.ps1 stop          # Stop all services
.\manager.ps1 status        # Show status
.\manager.ps1 clean         # Clean all artifacts
.\manager.ps1 reset         # Reset to fresh state

# Component delegation
.\manager.ps1 backend run       # Start backend only
.\manager.ps1 frontend dev      # Start frontend only
.\manager.ps1 database status   # Check database
.\manager.ps1 docker start      # Start Docker services
```

### 🖥️ Backend (`backend/backend.ps1`)
```powershell
.\scripts\backend\backend.ps1 help              # Show help
.\scripts\backend\backend.ps1 build             # Build WAR file
.\scripts\backend\backend.ps1 run               # Run with Jetty (dev)
.\scripts\backend\backend.ps1 run -Server tomcat # Run with Tomcat 11
.\scripts\backend\backend.ps1 test              # Run tests
.\scripts\backend\backend.ps1 clean             # Clean artifacts
.\scripts\backend\backend.ps1 status            # Check status
```

**Features:**
- ⚡ **Jetty 11** for development (hot reload, debugging)
- 🚀 **Tomcat 11** for production (stable deployment)
- 🎯 **Unified context path**: `/ute-phonehub`

### 🎨 Frontend (`frontend/frontend.ps1`)
```powershell
.\scripts\frontend\frontend.ps1 help            # Show help
.\scripts\frontend\frontend.ps1 install         # Install dependencies
.\scripts\frontend\frontend.ps1 dev             # Development server
.\scripts\frontend\frontend.ps1 build           # Production build
.\scripts\frontend\frontend.ps1 test            # Run tests
.\scripts\frontend\frontend.ps1 clean           # Clean artifacts
.\scripts\frontend\frontend.ps1 status          # Check status
```

**Features:**
- 📱 **React 19** with TypeScript
- 🔄 **Hot reload** development server
- 🌐 **Environment-specific** API configuration

### 🗃️ Database (`database/database.ps1`)
```powershell
.\scripts\database\database.ps1 help            # Show help
.\scripts\database\database.ps1 status          # Check connection
.\scripts\database\database.ps1 create          # Create database
.\scripts\database\database.ps1 migrate         # Run migrations
.\scripts\database\database.ps1 seed            # Insert sample data
.\scripts\database\database.ps1 backup          # Backup database
.\scripts\database\database.ps1 reset           # Reset database

# Environment-specific
.\scripts\database\database.ps1 create -Environment local
.\scripts\database\database.ps1 seed -Environment dev
```

**Supported Databases:** PostgreSQL, MySQL, SQL Server

### 🐳 Docker (`docker/docker.ps1`)
```powershell
.\scripts\docker\docker.ps1 help                # Show help
.\scripts\docker\docker.ps1 build               # Build images
.\scripts\docker\docker.ps1 start               # Start containers
.\scripts\docker\docker.ps1 stop                # Stop containers
.\scripts\docker\docker.ps1 restart             # Restart containers
.\scripts\docker\docker.ps1 logs                # Show logs
.\scripts\docker\docker.ps1 status              # Show status
.\scripts\docker\docker.ps1 clean               # Clean up
.\scripts\docker\docker.ps1 shell -Service backend # Open shell

# Service-specific operations
.\scripts\docker\docker.ps1 start -Service backend
.\scripts\docker\docker.ps1 logs -Service frontend
```

**Services:** Backend, Frontend, Database, Nginx

### 🚀 Deployment (`deployment/deploy.ps1`)
```powershell
.\scripts\deployment\deploy.ps1 help            # Show help
.\scripts\deployment\deploy.ps1 build           # Build for deployment
.\scripts\deployment\deploy.ps1 deploy          # Deploy to Railway
.\scripts\deployment\deploy.ps1 status          # Check deployment
.\scripts\deployment\deploy.ps1 logs            # Show deployment logs
.\scripts\deployment\deploy.ps1 env             # Manage environment vars
.\scripts\deployment\deploy.ps1 rollback        # Rollback deployment

# Platform-specific deployment
.\scripts\deployment\deploy.ps1 deploy -Environment railway
.\scripts\deployment\deploy.ps1 deploy -Environment heroku
.\scripts\deployment\deploy.ps1 deploy -Environment aws
.\scripts\deployment\deploy.ps1 deploy -Environment local

# Service-specific deployment
.\scripts\deployment\deploy.ps1 deploy -Service backend
.\scripts\deployment\deploy.ps1 deploy -Service frontend
```

**Platforms:** Railway, Heroku, AWS, Local Production

### 🛠️ Utils (`utils/utils.ps1`)
```powershell
.\scripts\utils\utils.ps1 help                  # Show help
.\scripts\utils\utils.ps1 check                 # Check system requirements
.\scripts\utils\utils.ps1 setup                 # Setup environment
.\scripts\utils\utils.ps1 clean                 # Clean project
.\scripts\utils\utils.ps1 reset                 # Reset project
.\scripts\utils\utils.ps1 update                # Update dependencies
.\scripts\utils\utils.ps1 backup                # Backup project
.\scripts\utils\utils.ps1 test                  # Run all tests
.\scripts\utils\utils.ps1 lint                  # Run linting
.\scripts\utils\utils.ps1 format                # Format code
```

**Features:**
- ✅ **System requirements** checking
- 🔄 **Dependency management**
- 🧹 **Project maintenance**
- 💾 **Backup utilities**

---

## 🔧 Configuration

### 🌍 Environment Files

**Frontend:**
- `.env.local` - Local development
- `.env.development` - Development environment
- `.env.production` - Production environment

**Backend:**
- `application.properties` - Database và server config

### 🌐 Default URLs

- **Frontend (Dev):** http://localhost:3000
- **Backend (Dev):** http://localhost:8080/ute-phonehub
- **Backend (Prod):** http://localhost:8080/ute-phonehub
- **API Docs:** http://localhost:8080/ute-phonehub/swagger
- **Database:** localhost:5432 (PostgreSQL)

### ⚙️ Server Configuration

- **Development:** Jetty 11 (hot reload, debugging)
- **Production:** Tomcat 11 (stable, production-ready)
- **Context Path:** `/ute-phonehub` (unified)

---

## 📦 System Requirements

### ✅ Required

| Tool | Version | Purpose |
|------|---------|---------|
| **Java JDK** | 17+ | Backend compilation |
| **Maven** | 3.9+ | Backend build tool |
| **Node.js** | 18+ | Frontend runtime |
| **npm** | 9+ | Frontend package manager |
| **Git** | Latest | Version control |

### 🔧 Optional

| Tool | Version | Purpose |
|------|---------|---------|
| **Docker** | Latest | Containerization |
| **PostgreSQL** | 15+ | Database (or use Docker) |
| **Railway CLI** | Latest | Deployment |

---

## 🎯 Workflows

### 🚀 Development Workflow
```powershell
# 1. First time setup
.\scripts\manager.ps1 setup

# 2. Start development
.\scripts\manager.ps1 start

# 3. Check status
.\scripts\manager.ps1 status

# 4. Code & test
# (Backend: http://localhost:8080/ute-phonehub)
# (Frontend: http://localhost:3000)

# 5. Stop when done
.\scripts\manager.ps1 stop
```

### 🧪 Testing Workflow
```powershell
# Run all tests
.\scripts\utils\utils.ps1 test

# Or test individually
.\scripts\backend\backend.ps1 test
.\scripts\frontend\frontend.ps1 test
```

### 🚢 Deployment Workflow
```powershell
# 1. Build for production
.\scripts\deployment\deploy.ps1 build

# 2. Deploy to Railway
.\scripts\deployment\deploy.ps1 deploy -Environment railway

# 3. Check deployment status
.\scripts\deployment\deploy.ps1 status

# 4. Monitor logs
.\scripts\deployment\deploy.ps1 logs
```

### 🗃️ Database Workflow
```powershell
# 1. Setup database
.\scripts\database\database.ps1 create
.\scripts\database\database.ps1 migrate

# 2. Add sample data
.\scripts\database\database.ps1 seed

# 3. Backup before changes
.\scripts\database\database.ps1 backup

# 4. Reset if needed
.\scripts\database\database.ps1 reset
```

---

## 🚨 Troubleshooting

### 🔧 Common Issues

**Port already in use:**
```powershell
.\scripts\manager.ps1 stop
```

**Build fails:**
```powershell
.\scripts\utils\utils.ps1 clean
.\scripts\utils\utils.ps1 setup
```

**Dependencies out of date:**
```powershell
.\scripts\utils\utils.ps1 update
```

**Frontend không connect được backend:**
```powershell
# Check backend status
.\scripts\backend\backend.ps1 status

# Check environment files
# Frontend .env.local should have:
# REACT_APP_API_BASE_URL=http://localhost:8080/ute-phonehub
```

### 📋 Health Check

```powershell
# Quick health check
.\scripts\utils\utils.ps1 check
.\scripts\manager.ps1 status
```

---

## 📖 Documentation

- **[Detailed Scripts Guide](README-.md)** - Comprehensive documentation
- **[Main Project](../README.md)** - Project overview
- **[Backend Guide](../backend/README.md)** - Backend documentation
- **[Frontend Guide](../frontend/README.md)** - Frontend documentation
- **[Deployment Guide](../DEPLOYMENT.md)** - Deployment instructions

4. **Environment issues**
   ```powershell
   .\scripts\utils\utils.ps1 check
   ```

### Getting Help
```powershell
# Main help
.\scripts\manager.ps1 help

# Component-specific help
.\scripts\backend\backend.ps1 help
.\scripts\frontend\frontend.ps1 help
.\scripts\database\database.ps1 help
```

## 📝 Notes

- All scripts use Windows PowerShell
- Context path for all environments: `/ute-phonehub`
- Jetty used for development (hot reload)
- Tomcat 11 for production deployment
- Environment variables configured automatically
- Scripts handle dependency management
- Colored output for better visibility
