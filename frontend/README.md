# 🎨 UTE PhoneHub - Frontend (React TypeScript)

<div align="center">

![React](https://img.shields.io/badge/React-19.1-61DAFB?style=for-the-badge&logo=react&logoColor=black)
![TypeScript](https://img.shields.io/badge/TypeScript-4.9-3178C6?style=for-the-badge&logo=typescript&logoColor=white)
![Axios](https://img.shields.io/badge/Axios-1.11-5A29E4?style=for-the-badge&logo=axios&logoColor=white)
![Nginx](https://img.shields.io/badge/Nginx-latest-009639?style=for-the-badge&logo=nginx&logoColor=white)

**Modern React TypeScript Frontend cho UTE PhoneHub E-commerce Platform**

[🏠 Main Project](../README.md) • [🖥️ Backend API](../backend/README.md) • [🚀 Live Demo](http://localhost:3000)

</div>

---

## 📋 Mục lục

- [🎯 Tổng quan](#-tổng-quan)
- [✨ Tính năng](#-tính-năng)
- [🛠 Công nghệ](#-công-nghệ)
- [📁 Cấu trúc Frontend](#-cấu-trúc-frontend)
- [🚀 Cài đặt & Chạy](#-cài-đặt--chạy)
- [🌐 API Integration](#-api-integration)
- [🎨 Components](#-components)
- [🔄 State Management](#-state-management)
- [📱 Responsive Design](#-responsive-design)
- [📦 Build & Deploy](#-build--deploy)
- [🧪 Testing](#-testing)

---

## 🎯 Tổng quan

Frontend của UTE PhoneHub được xây dựng với **React 19.1** và **TypeScript**, cung cấp giao diện người dùng hiện đại và responsive cho hệ thống e-commerce bán điện thoại.

### 🌟 Highlights

- ⚡ **React 19.1** - Latest React với Server Components và Concurrent Features
- 🔒 **TypeScript** - Type safety và better developer experience
- 📱 **Responsive Design** - Hoạt động tốt trên mọi thiết bị
- 🔄 **Real-time Updates** - Cập nhật dữ liệu real-time
- 🎨 **Modern UI/UX** - Clean và intuitive interface
- 🚀 **Performance Optimized** - Code splitting và lazy loading
- 🛡️ **Security** - JWT authentication integration

---

## ✨ Tính năng

### 👤 Cho Khách hàng

#### 🏠 **Homepage**
- Hero section với sản phẩm nổi bật
- Danh mục sản phẩm
- Sản phẩm mới nhất và bestsellers
- Testimonials và reviews

#### 📱 **Product Pages**
- Danh sách sản phẩm với pagination
- Bộ lọc theo danh mục, thương hiệu, giá
- Tìm kiếm sản phẩm
- Chi tiết sản phẩm với gallery
- Đánh giá và rating

#### 🛒 **Shopping Experience**
- Thêm vào giỏ hàng
- Quản lý giỏ hàng
- Wishlist/Yêu thích
- Checkout process
- Theo dõi đơn hàng

#### 👤 **User Account**
- Đăng ký/Đăng nhập
- Quản lý profile
- Lịch sử đơn hàng
- Quản lý địa chỉ
- Đổi mật khẩu

### ⚙️ Cho Admin

#### 📊 **Dashboard**
- Thống kê tổng quan
- Biểu đồ doanh thu
- Top sản phẩm bán chạy
- Đơn hàng mới

#### 📱 **Product Management**
- CRUD sản phẩm
- Quản lý categories/brands
- Upload hình ảnh
- Quản lý inventory

#### 👥 **User Management**
- Danh sách người dùng
- Thông tin chi tiết user
- Khóa/mở khóa tài khoản
- Phân quyền

#### 📦 **Order Management**
- Danh sách đơn hàng
- Chi tiết đơn hàng
- Cập nhật trạng thái
- In hóa đơn

---

## 🛠 Công nghệ

### 🎯 Core Framework

| Technology | Version | Purpose |
|------------|---------|---------|
| **React** | 19.1.1 | Frontend framework |
| **TypeScript** | 4.9.5 | Type safety |
| **React Scripts** | 5.0.1 | Build tools |

### 📚 Main Dependencies

| Package | Version | Purpose |
|---------|---------|---------|
| **axios** | 1.11.0 | HTTP client for API calls |
| **react-router-dom** | 7.8.2 | Client-side routing |
| **react-dom** | 19.1.1 | DOM manipulation |

### 🧪 Testing Libraries

| Package | Version | Purpose |
|---------|---------|---------|
| **@testing-library/react** | 16.3.0 | Component testing |
| **@testing-library/jest-dom** | 6.8.0 | DOM testing utilities |
| **@testing-library/user-event** | 13.5.0 | User interaction testing |

### 🚀 Production

| Technology | Purpose |
|------------|---------|
| **Nginx** | Production web server |
| **Docker** | Containerization |
| **Railway.app** | Cloud deployment |

---

## 📁 Cấu trúc Frontend

```
frontend/
├── 📄 package.json                 # NPM dependencies & scripts
├── 📄 tsconfig.json               # TypeScript configuration
├── 🐳 Dockerfile                  # Container configuration
├── 🌐 nginx.conf                  # Nginx production config
├── 📄 railway.json                # Railway deployment config
├── 🚀 start.sh                    # Startup script
├── 📂 public/                     # Static assets
│   ├── index.html                 # Main HTML template
│   ├── favicon.ico               # Site favicon
│   ├── logo192.png               # App logo (192x192)
│   ├── logo512.png               # App logo (512x512)
│   ├── manifest.json             # PWA manifest
│   └── robots.txt                # SEO robots file
├── 📂 src/                        # Source code
│   ├── 🏠 index.tsx              # Application entry point
│   ├── 📱 App.tsx                # Main App component
│   ├── 🎨 App.css                # Global styles
│   ├── 🎨 index.css              # Base styles
│   ├── 🖼️ logo.svg               # React logo
│   ├── 📊 reportWebVitals.ts     # Performance monitoring
│   ├── 🧪 setupTests.ts          # Test configuration
│   ├── 📝 react-app-env.d.ts     # React TypeScript definitions
│   ├── 🧪 App.test.tsx           # App component tests
│   ├── 📂 components/            # Reusable UI components
│   │   ├── 📂 common/            # 🔧 Common UI Components
│   │   │   ├── EndpointCard.tsx  # API endpoint display card
│   │   │   ├── EndpointCard.css  # Endpoint card styles
│   │   │   ├── LoadingSpinner.tsx # Loading spinner component
│   │   │   └── LoadingSpinner.css # Spinner styles
│   │   └── 📂 layout/            # 🏗️ Layout Components
│   │       ├── Header.tsx        # Site header/navigation
│   │       ├── Header.css        # Header styles
│   │       ├── Footer.tsx        # Site footer
│   │       └── Footer.css        # Footer styles
│   ├── 📂 pages/                 # 📄 Page Components
│   │   ├── HomePage.tsx          # Landing/home page
│   │   ├── HomePage.css          # Home page styles
│   │   ├── EndpointsPage.tsx     # API endpoints showcase
│   │   └── EndpointsPage.css     # Endpoints page styles
│   └── 📂 services/              # 🔌 API Services
│       ├── api.ts                # Base API configuration
│       └── endpointService.ts    # Endpoint-specific services
└── 📂 build/                     # Production build output (generated)
    ├── static/                   # Static assets (JS, CSS, images)
    └── index.html               # Production HTML
```

### 📂 Component Organization

```
components/
├── 📂 common/                    # 🔧 Shared/Reusable Components
│   ├── Button/                   # Custom button component
│   ├── Input/                    # Form input components
│   ├── Modal/                    # Modal/dialog components
│   ├── Card/                     # Card layout component
│   ├── LoadingSpinner/           # Loading states
│   ├── Pagination/               # Pagination component
│   └── ErrorBoundary/            # Error handling
├── 📂 layout/                    # 🏗️ Layout & Navigation
│   ├── Header/                   # Site header
│   ├── Footer/                   # Site footer
│   ├── Sidebar/                  # Sidebar navigation
│   └── Breadcrumb/               # Breadcrumb navigation
├── 📂 product/                   # 📱 Product Components
│   ├── ProductCard/              # Product display card
│   ├── ProductList/              # Product listing
│   ├── ProductDetail/            # Product detail view
│   ├── ProductFilter/            # Product filters
│   └── ProductSearch/            # Search functionality
├── 📂 auth/                      # 🔐 Authentication
│   ├── LoginForm/                # Login form
│   ├── RegisterForm/             # Registration form
│   ├── ForgotPassword/           # Password reset
│   └── ProtectedRoute/           # Route protection
├── 📂 cart/                      # 🛒 Shopping Cart
│   ├── CartItem/                 # Individual cart item
│   ├── CartSummary/              # Cart totals
│   ├── Checkout/                 # Checkout process
│   └── PaymentForm/              # Payment form
├── 📂 order/                     # 📦 Order Management
│   ├── OrderHistory/             # Order history
│   ├── OrderDetail/              # Order details
│   ├── OrderStatus/              # Status tracking
│   └── OrderSummary/             # Order summary
└── 📂 admin/                     # ⚙️ Admin Components
    ├── Dashboard/                # Admin dashboard
    ├── UserManagement/           # User management
    ├── ProductManagement/        # Product management
    └── OrderManagement/          # Order management
```

---

## 🚀 Cài đặt & Chạy

### 📋 Yêu cầu

- **Node.js**: 18+ ([Download](https://nodejs.org/))
- **npm**: 9+ (đi kèm với Node.js)
- **Git**: Latest ([Download](https://git-scm.com/))

### 🔧 Quick Start

```powershell
# 1. Clone repository (nếu chưa có)
git clone https://github.com/darktheDE/ute-phonehub-testdeploy.git
cd ute-phonehub-testdeploy/frontend

# 2. Cài đặt dependencies
npm install

# 3. Chạy development server
npm start

# 4. Mở browser
Start-Process "http://localhost:3000"
```

### 🔧 Available Scripts

```powershell
# Development server (hot reload)
npm start           # Starts dev server at http://localhost:3000

# Production build
npm run build       # Creates optimized build in ./build/

# Run tests
npm test           # Runs test suite in watch mode

# Eject configuration (⚠️ irreversible)
npm run eject      # Exposes webpack config (not recommended)
```

### 🌍 Environment Variables

Tạo file `.env.local` trong thư mục `frontend/`:

```bash
# API Base URL
REACT_APP_API_BASE_URL=http://localhost:8080/ute-phonehub

# App Configuration
REACT_APP_NAME=UTE PhoneHub
REACT_APP_VERSION=1.0.0

# Development
REACT_APP_DEBUG=true
GENERATE_SOURCEMAP=true

# Build Configuration
BUILD_PATH=build
PUBLIC_URL=/
```

### 🐳 Docker (Alternative)

```powershell
# Build Docker image
docker build -t ute-phonehub-frontend .

# Run container
docker run -p 3000:80 --name phonehub-frontend ute-phonehub-frontend

# With environment variables
docker run -p 3000:80 `
  -e REACT_APP_API_BASE_URL=http://your-backend-url `
  ute-phonehub-frontend
```

---

## 🌐 API Integration

### 🔌 API Service Layer

**File**: `src/services/api.ts`

```typescript
import axios from 'axios';

// Base API configuration
const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || 'http://localhost:8080/ute-phonehub';

const apiClient = axios.create({
  baseURL: API_BASE_URL,
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Request interceptor - Add JWT token
apiClient.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('auth_token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// Response interceptor - Handle errors
apiClient.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      // Redirect to login
      localStorage.removeItem('auth_token');
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

export default apiClient;
```

### 🔗 Service Examples

#### 📱 Product Service
```typescript
// src/services/productService.ts
import apiClient from './api';

export interface Product {
  id: number;
  name: string;
  price: number;
  description: string;
  imageUrl: string;
  categoryId: number;
  brandId: number;
}

export const productService = {
  // Get all products
  getProducts: async (page = 1, limit = 10) => {
    const response = await apiClient.get(`/api/products?page=${page}&limit=${limit}`);
    return response.data;
  },

  // Get product by ID
  getProduct: async (id: number) => {
    const response = await apiClient.get(`/api/products/${id}`);
    return response.data;
  },

  // Search products
  searchProducts: async (query: string) => {
    const response = await apiClient.get(`/api/products/search?q=${encodeURIComponent(query)}`);
    return response.data;
  },
};
```

#### 🔐 Auth Service
```typescript
// src/services/authService.ts
import apiClient from './api';

export interface LoginRequest {
  username: string;
  password: string;
}

export interface AuthResponse {
  token: string;
  user: {
    id: number;
    username: string;
    role: string;
    fullName: string;
  };
}

export const authService = {
  // Login
  login: async (credentials: LoginRequest): Promise<AuthResponse> => {
    const response = await apiClient.post('/api/auth/login', credentials);
    const { token } = response.data;
    
    // Store token
    localStorage.setItem('auth_token', token);
    
    return response.data;
  },

  // Register
  register: async (userData: RegisterRequest) => {
    const response = await apiClient.post('/api/auth/register', userData);
    return response.data;
  },

  // Logout
  logout: async () => {
    await apiClient.post('/api/auth/logout');
    localStorage.removeItem('auth_token');
  },

  // Get current user
  getCurrentUser: async () => {
    const response = await apiClient.get('/api/auth/profile');
    return response.data;
  },
};
```

---

## 🎨 Components

### 🔧 Common Components

#### LoadingSpinner
```typescript
// src/components/common/LoadingSpinner.tsx
import React from 'react';
import './LoadingSpinner.css';

interface LoadingSpinnerProps {
  size?: 'small' | 'medium' | 'large';
  message?: string;
}

const LoadingSpinner: React.FC<LoadingSpinnerProps> = ({ 
  size = 'medium', 
  message = 'Loading...' 
}) => {
  return (
    <div className={`loading-spinner loading-spinner--${size}`}>
      <div className="spinner"></div>
      {message && <p className="loading-message">{message}</p>}
    </div>
  );
};

export default LoadingSpinner;
```

#### EndpointCard  
```typescript
// src/components/common/EndpointCard.tsx
import React from 'react';
import './EndpointCard.css';

interface EndpointCardProps {
  title: string;
  description: string;
  method: 'GET' | 'POST' | 'PUT' | 'DELETE';
  url: string;
  status?: 'active' | 'inactive' | 'maintenance';
}

const EndpointCard: React.FC<EndpointCardProps> = ({
  title,
  description,
  method,
  url,
  status = 'active'
}) => {
  const getMethodColor = (method: string) => {
    switch (method) {
      case 'GET': return 'green';
      case 'POST': return 'blue';
      case 'PUT': return 'orange';
      case 'DELETE': return 'red';
      default: return 'gray';
    }
  };

  return (
    <div className={`endpoint-card endpoint-card--${status}`}>
      <div className="endpoint-header">
        <span className={`method method--${getMethodColor(method)}`}>
          {method}
        </span>
        <h3 className="endpoint-title">{title}</h3>
      </div>
      <p className="endpoint-description">{description}</p>
      <code className="endpoint-url">{url}</code>
      <div className={`status-badge status-badge--${status}`}>
        {status}
      </div>
    </div>
  );
};

export default EndpointCard;
```

### 🏗️ Layout Components

#### Header
```typescript
// src/components/layout/Header.tsx
import React, { useState, useContext } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { AuthContext } from '../../contexts/AuthContext';
import './Header.css';

const Header: React.FC = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const { user, logout } = useContext(AuthContext);
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/');
  };

  return (
    <header className="header">
      <div className="container">
        <Link to="/" className="logo">
          <img src="/logo192.png" alt="UTE PhoneHub" />
          <span>UTE PhoneHub</span>
        </Link>

        <nav className={`nav ${isMenuOpen ? 'nav--open' : ''}`}>
          <Link to="/" className="nav-link">Home</Link>
          <Link to="/products" className="nav-link">Products</Link>
          <Link to="/about" className="nav-link">About</Link>
          <Link to="/contact" className="nav-link">Contact</Link>
        </nav>

        <div className="header-actions">
          {user ? (
            <div className="user-menu">
              <span>Welcome, {user.fullName}</span>
              <Link to="/cart" className="cart-icon">
                Cart (0)
              </Link>
              <button onClick={handleLogout} className="btn btn--secondary">
                Logout
              </button>
            </div>
          ) : (
            <div className="auth-buttons">
              <Link to="/login" className="btn btn--outline">Login</Link>
              <Link to="/register" className="btn btn--primary">Register</Link>
            </div>
          )}
        </div>

        <button 
          className="mobile-menu-toggle"
          onClick={() => setIsMenuOpen(!isMenuOpen)}
        >
          ☰
        </button>
      </div>
    </header>
  );
};

export default Header;
```

---

## 🔄 State Management

### 🔐 Auth Context

```typescript
// src/contexts/AuthContext.tsx
import React, { createContext, useState, useEffect, ReactNode } from 'react';
import { authService } from '../services/authService';

interface User {
  id: number;
  username: string;
  fullName: string;
  role: string;
}

interface AuthContextType {
  user: User | null;
  isLoading: boolean;
  login: (username: string, password: string) => Promise<void>;
  logout: () => void;
  isAuthenticated: boolean;
}

export const AuthContext = createContext<AuthContextType>({} as AuthContextType);

interface AuthProviderProps {
  children: ReactNode;
}

export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
  const [user, setUser] = useState<User | null>(null);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    // Check if user is already logged in
    const token = localStorage.getItem('auth_token');
    if (token) {
      authService.getCurrentUser()
        .then(setUser)
        .catch(() => {
          localStorage.removeItem('auth_token');
        })
        .finally(() => setIsLoading(false));
    } else {
      setIsLoading(false);
    }
  }, []);

  const login = async (username: string, password: string) => {
    const response = await authService.login({ username, password });
    setUser(response.user);
  };

  const logout = () => {
    authService.logout();
    setUser(null);
  };

  const value = {
    user,
    isLoading,
    login,
    logout,
    isAuthenticated: !!user,
  };

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  );
};
```

### 🛒 Cart Context

```typescript
// src/contexts/CartContext.tsx
import React, { createContext, useState, useContext, ReactNode } from 'react';

interface CartItem {
  id: number;
  productId: number;
  name: string;
  price: number;
  quantity: number;
  imageUrl: string;
}

interface CartContextType {
  items: CartItem[];
  itemCount: number;
  totalAmount: number;
  addToCart: (product: any, quantity: number) => void;
  removeFromCart: (productId: number) => void;
  updateQuantity: (productId: number, quantity: number) => void;
  clearCart: () => void;
}

export const CartContext = createContext<CartContextType>({} as CartContextType);

export const CartProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  const [items, setItems] = useState<CartItem[]>([]);

  const addToCart = (product: any, quantity: number) => {
    setItems(prev => {
      const existingItem = prev.find(item => item.productId === product.id);
      
      if (existingItem) {
        return prev.map(item =>
          item.productId === product.id
            ? { ...item, quantity: item.quantity + quantity }
            : item
        );
      }
      
      return [...prev, {
        id: Date.now(),
        productId: product.id,
        name: product.name,
        price: product.price,
        quantity,
        imageUrl: product.imageUrl,
      }];
    });
  };

  const removeFromCart = (productId: number) => {
    setItems(prev => prev.filter(item => item.productId !== productId));
  };

  const updateQuantity = (productId: number, quantity: number) => {
    if (quantity <= 0) {
      removeFromCart(productId);
      return;
    }
    
    setItems(prev => 
      prev.map(item =>
        item.productId === productId
          ? { ...item, quantity }
          : item
      )
    );
  };

  const clearCart = () => {
    setItems([]);
  };

  const itemCount = items.reduce((total, item) => total + item.quantity, 0);
  const totalAmount = items.reduce((total, item) => total + (item.price * item.quantity), 0);

  const value = {
    items,
    itemCount,
    totalAmount,
    addToCart,
    removeFromCart,
    updateQuantity,
    clearCart,
  };

  return (
    <CartContext.Provider value={value}>
      {children}
    </CartContext.Provider>
  );
};
```

---

## 📱 Responsive Design

### 🎨 CSS Architecture

```css
/* src/index.css - Base styles */
:root {
  /* Colors */
  --primary-color: #007bff;
  --secondary-color: #6c757d;
  --success-color: #28a745;
  --danger-color: #dc3545;
  --warning-color: #ffc107;
  --info-color: #17a2b8;
  
  /* Typography */
  --font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', sans-serif;
  --font-size-base: 16px;
  --line-height-base: 1.5;
  
  /* Spacing */
  --spacing-xs: 0.25rem;
  --spacing-sm: 0.5rem;
  --spacing-md: 1rem;
  --spacing-lg: 1.5rem;
  --spacing-xl: 3rem;
  
  /* Breakpoints */
  --breakpoint-sm: 576px;
  --breakpoint-md: 768px;
  --breakpoint-lg: 992px;
  --breakpoint-xl: 1200px;
}

/* Reset & Base */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: var(--font-family);
  font-size: var(--font-size-base);
  line-height: var(--line-height-base);
  color: #333;
  background-color: #fff;
}

/* Container */
.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 var(--spacing-md);
}

@media (max-width: 768px) {
  .container {
    padding: 0 var(--spacing-sm);
  }
}
```

### 📱 Responsive Grid

```css
/* src/styles/grid.css */
.row {
  display: flex;
  flex-wrap: wrap;
  margin: 0 -15px;
}

.col {
  flex: 1;
  padding: 0 15px;
}

/* Responsive columns */
.col-12 { width: 100%; }
.col-6 { width: 50%; }
.col-4 { width: 33.333%; }
.col-3 { width: 25%; }

@media (max-width: 768px) {
  .col-md-12 { width: 100%; }
  .col-md-6 { width: 50%; }
}

@media (max-width: 576px) {
  .col-sm-12 { width: 100%; }
  [class*="col-"] {
    width: 100%;
    margin-bottom: var(--spacing-md);
  }
}
```

---

## 📦 Build & Deploy

### 🔨 Production Build

```powershell
# Build for production
npm run build

# Analyze bundle size (optional)
npm install -g webpack-bundle-analyzer
npx webpack-bundle-analyzer build/static/js/*.js
```

### 🌍 Environment Configurations

#### Development (`.env.development`)
```bash
REACT_APP_API_BASE_URL=http://localhost:8080/ute-phonehub
REACT_APP_DEBUG=true
GENERATE_SOURCEMAP=true
```

#### Production (`.env.production`)
```bash
REACT_APP_API_BASE_URL=https://your-backend-domain.railway.app
REACT_APP_DEBUG=false
GENERATE_SOURCEMAP=false
```

### 🐳 Docker Production

```dockerfile
# frontend/Dockerfile
# Build stage
FROM node:18-alpine AS builder

WORKDIR /app
COPY package*.json ./
RUN npm ci --only=production

COPY . .
RUN npm run build

# Production stage
FROM nginx:alpine

# Copy nginx configuration
COPY nginx.conf /etc/nginx/nginx.conf

# Copy built app
COPY --from=builder /app/build /usr/share/nginx/html

# Expose port
EXPOSE 80

# Start nginx
CMD ["nginx", "-g", "daemon off;"]
```

#### Nginx Configuration
```nginx
# frontend/nginx.conf
events {
    worker_connections 1024;
}

http {
    include       /etc/nginx/mime.types;
    default_type  application/octet-stream;

    server {
        listen 80;
        server_name localhost;
        root /usr/share/nginx/html;
        index index.html;

        # Handle React Router
        location / {
            try_files $uri $uri/ /index.html;
        }

        # Static assets caching
        location /static/ {
            expires 1y;
            add_header Cache-Control "public, immutable";
        }

        # API proxy (optional)
        location /api/ {
            proxy_pass http://backend:8080;
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
        }
    }
}
```

### 🚀 Railway.app Deployment

```json
// frontend/railway.json
{
  "build": {
    "builder": "DOCKERFILE",
    "dockerfilePath": "Dockerfile"
  },
  "deploy": {
    "restartPolicyType": "ON_FAILURE",
    "restartPolicyMaxRetries": 10
  }
}
```

---

## 🧪 Testing

### 🔬 Component Testing

```typescript
// src/components/common/LoadingSpinner.test.tsx
import React from 'react';
import { render, screen } from '@testing-library/react';
import LoadingSpinner from './LoadingSpinner';

describe('LoadingSpinner', () => {
  test('renders with default message', () => {
    render(<LoadingSpinner />);
    expect(screen.getByText('Loading...')).toBeInTheDocument();
  });

  test('renders with custom message', () => {
    render(<LoadingSpinner message="Please wait..." />);
    expect(screen.getByText('Please wait...')).toBeInTheDocument();
  });

  test('renders with correct size class', () => {
    const { container } = render(<LoadingSpinner size="large" />);
    expect(container.firstChild).toHaveClass('loading-spinner--large');
  });
});
```

### 🔌 API Testing

```typescript
// src/services/__tests__/productService.test.ts
import { productService } from '../productService';
import apiClient from '../api';

jest.mock('../api');
const mockedApiClient = apiClient as jest.Mocked<typeof apiClient>;

describe('productService', () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });

  test('getProducts returns products list', async () => {
    const mockProducts = [
      { id: 1, name: 'iPhone 14', price: 999 },
      { id: 2, name: 'Samsung Galaxy S23', price: 899 },
    ];

    mockedApiClient.get.mockResolvedValue({ data: mockProducts });

    const result = await productService.getProducts();
    
    expect(mockedApiClient.get).toHaveBeenCalledWith('/api/products?page=1&limit=10');
    expect(result).toEqual(mockProducts);
  });
});
```

### 🏃‍♂️ Running Tests

```powershell
# Run all tests
npm test

# Run tests in watch mode
npm test -- --watch

# Run tests with coverage
npm test -- --coverage

# Run specific test file
npm test LoadingSpinner.test.tsx

# Run tests matching pattern
npm test -- --testNamePattern="renders"
```

---

## 🚨 Troubleshooting

### ❗ Common Issues

#### 1. **Port 3000 đã được sử dụng**
```powershell
# Tìm process đang sử dụng port 3000
netstat -ano | findstr :3000

# Kill process
taskkill /PID <process_id> /F

# Hoặc sử dụng port khác
set PORT=3001 && npm start
```

#### 2. **API Connection Failed**
```typescript
// Kiểm tra REACT_APP_API_BASE_URL trong .env
// Đảm bảo backend đang chạy
// Kiểm tra CORS configuration
```

#### 3. **Build Failed**
```powershell
# Clear cache và reinstall
Remove-Item node_modules -Recurse -Force
Remove-Item package-lock.json
npm install

# Clear React build cache
npm start -- --reset-cache
```

#### 4. **TypeScript Errors**
```typescript
// Kiểm tra tsconfig.json
// Update @types packages
npm install --save-dev @types/react @types/react-dom

// Skip TypeScript check (temporary)
set TSC_COMPILE_ON_ERROR=true && npm start
```

---

<div align="center">

### 🎉 Frontend Setup Complete!

**Development**: `http://localhost:3000`
**Production Build**: `npm run build`

---

**[⬆ Về đầu trang](#-ute-phonehub---frontend-react-typescript)** • **[🏠 Main Project](../README.md)** • **[🖥️ Backend](../backend/README.md)**

---

**Made with ⚛️ by UTE Frontend Team**

</div>
