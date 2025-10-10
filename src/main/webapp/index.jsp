<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>UTE Phone Hub - Cửa hàng điện thoại uy tín</title>
    <meta
      name="description"
      content="UTE Phone Hub - Cửa hàng điện thoại, laptop, phụ kiện chính hãng với giá tốt nhất. Giao hàng nhanh, bảo hành chính hãng."
    />
    <meta
      name="keywords"
      content="điện thoại, laptop, phụ kiện, iPhone, Samsung, MacBook, iPad"
    />

    <!-- Favicon -->
    <link
      rel="icon"
      type="image/png"
      href="${pageContext.request.contextPath}/static/favicon.png"
    />
    <link
      rel="shortcut icon"
      type="image/png"
      href="${pageContext.request.contextPath}/static/favicon.png"
    />
    <link
      rel="apple-touch-icon"
      href="${pageContext.request.contextPath}/static/favicon.png"
    />

    <!-- CSS -->
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/static/css/main.css"
    />
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/static/css/components/header.css"
    />
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/static/css/components/product.css"
    />

    <!-- Font Awesome -->
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
    />

    <!-- Google Fonts - Roboto -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap"
      rel="stylesheet"
    />

    <!-- Favicon -->
    <link
      rel="icon"
      type="image/x-icon"
      href="${pageContext.request.contextPath}/static/images/favicon.ico"
    />
  </head>
  <body>
    <!-- Header -->
    <header class="header">
      <!-- Header Top -->
      <div class="header-top">
        <div class="header-top-content">
          <div class="header-promo">
            <i class="fas fa-gift"></i>
            <span>Khuyến mãi đặc biệt - Giảm giá lên đến 50%</span>
          </div>
          <div class="header-location">
            <i class="fas fa-map-marker-alt"></i>
            <span>Hồ Chí Minh</span>
          </div>
        </div>
      </div>

      <!-- Header Main -->
      <div class="header-main">
        <div class="header-content">
          <!-- Logo -->
          <a href="${pageContext.request.contextPath}/" class="logo">
            <div class="logo-icon">U</div>
            <span>UTE Phone Hub</span>
          </a>

          <!-- Search Bar -->
          <div class="search-container">
            <div class="search-bar">
              <input
                type="text"
                class="search-input"
                placeholder="Tìm kiếm điện thoại, laptop, phụ kiện..."
              />
              <button class="search-btn">
                <i class="fas fa-search"></i>
              </button>
              <div class="search-suggestions"></div>
            </div>
          </div>

          <!-- Header Actions -->
          <div class="header-actions">
            <!-- User Account - Show login or profile based on token -->
            <div class="user-account-dropdown" id="userAccountDropdown">
              <a
                href="${pageContext.request.contextPath}/login"
                class="header-action"
                id="userAccountBtn"
              >
                <i class="fas fa-user"></i>
                <span id="userAccountText">Đăng nhập</span>
              </a>
              <!-- Dropdown menu cho user đã đăng nhập -->
              <div
                class="account-dropdown-menu"
                id="accountDropdownMenu"
                style="display: none"
              >
                <a
                  href="${pageContext.request.contextPath}/profile"
                  class="dropdown-item"
                >
                  <i class="fas fa-user-circle"></i>
                  Thông tin tài khoản
                </a>
                <a
                  href="${pageContext.request.contextPath}/orders"
                  class="dropdown-item"
                >
                  <i class="fas fa-box"></i>
                  Đơn hàng của tôi
                </a>
                <a
                  href="${pageContext.request.contextPath}/wishlist"
                  class="dropdown-item"
                >
                  <i class="fas fa-heart"></i>
                  Yêu thích
                </a>
                <hr
                  style="
                    margin: 5px 0;
                    border: none;
                    border-top: 1px solid #e0e0e0;
                  "
                />
                <a href="#" class="dropdown-item" id="logoutBtn">
                  <i class="fas fa-sign-out-alt"></i>
                  Đăng xuất
                </a>
              </div>
            </div>

            <a
              href="${pageContext.request.contextPath}/wishlist"
              class="header-action"
            >
              <i class="fas fa-heart"></i>
              <span>Yêu thích</span>
              <span class="wishlist-badge" style="display: none">0</span>
            </a>
            <a
              href="${pageContext.request.contextPath}/cart"
              class="header-action"
            >
              <i class="fas fa-shopping-cart"></i>
              <span>Giỏ hàng</span>
              <span class="cart-badge" style="display: none">0</span>
            </a>
          </div>

          <!-- Mobile Menu Button -->
          <button class="mobile-menu-btn">
            <i class="fas fa-bars"></i>
          </button>
        </div>
      </div>

      <!-- Navigation -->
      <nav class="nav">
        <div class="nav-container">
          <ul class="nav-list">
            <li class="nav-item">
              <a
                href="${pageContext.request.contextPath}/"
                class="nav-link active"
              >
                <i class="fas fa-home"></i>
                <span>Trang chủ</span>
              </a>
            </li>
            <li class="nav-item">
              <a
                href="${pageContext.request.contextPath}/products?category=phone"
                class="nav-link"
              >
                <i class="fas fa-mobile-alt"></i>
                <span>Điện thoại</span>
              </a>
            </li>
            <li class="nav-item">
              <a
                href="${pageContext.request.contextPath}/products?category=laptop"
                class="nav-link"
              >
                <i class="fas fa-laptop"></i>
                <span>Laptop</span>
              </a>
            </li>
            <li class="nav-item">
              <a
                href="${pageContext.request.contextPath}/products?category=accessories"
                class="nav-link"
              >
                <i class="fas fa-headphones"></i>
                <span>Phụ kiện</span>
              </a>
            </li>
            <li class="nav-item">
              <a
                href="${pageContext.request.contextPath}/products?category=smartwatch"
                class="nav-link"
              >
                <i class="fas fa-clock"></i>
                <span>Smartwatch</span>
              </a>
            </li>
            <li class="nav-item">
              <a
                href="${pageContext.request.contextPath}/products?category=tablet"
                class="nav-link"
              >
                <i class="fas fa-tablet-alt"></i>
                <span>Tablet</span>
              </a>
            </li>
            <li class="nav-item">
              <a
                href="${pageContext.request.contextPath}/products?category=used"
                class="nav-link"
              >
                <i class="fas fa-recycle"></i>
                <span>Máy cũ</span>
              </a>
            </li>
            <li class="nav-item">
              <a
                href="${pageContext.request.contextPath}/products?category=monitor"
                class="nav-link"
              >
                <i class="fas fa-desktop"></i>
                <span>Màn hình</span>
              </a>
            </li>
            <li class="nav-item">
              <a
                href="${pageContext.request.contextPath}/products?category=sim"
                class="nav-link"
              >
                <i class="fas fa-sim-card"></i>
                <span>Sim, Thẻ cào</span>
              </a>
            </li>
            <li class="nav-item">
              <a
                href="${pageContext.request.contextPath}/services"
                class="nav-link"
              >
                <i class="fas fa-tools"></i>
                <span>Dịch vụ</span>
              </a>
            </li>
          </ul>
        </div>
      </nav>

      <!-- Mobile Menu -->
      <div class="mobile-menu">
        <ul class="mobile-nav-list">
          <li class="mobile-nav-item">
            <a
              href="${pageContext.request.contextPath}/"
              class="mobile-nav-link"
            >
              <i class="fas fa-home"></i>
              <span>Trang chủ</span>
            </a>
          </li>
          <li class="mobile-nav-item">
            <a
              href="${pageContext.request.contextPath}/products?category=phone"
              class="mobile-nav-link"
            >
              <i class="fas fa-mobile-alt"></i>
              <span>Điện thoại</span>
            </a>
          </li>
          <li class="mobile-nav-item">
            <a
              href="${pageContext.request.contextPath}/products?category=laptop"
              class="mobile-nav-link"
            >
              <i class="fas fa-laptop"></i>
              <span>Laptop</span>
            </a>
          </li>
          <li class="mobile-nav-item">
            <a
              href="${pageContext.request.contextPath}/products?category=accessories"
              class="mobile-nav-link"
            >
              <i class="fas fa-headphones"></i>
              <span>Phụ kiện</span>
            </a>
          </li>
          <li class="mobile-nav-item">
            <a
              href="${pageContext.request.contextPath}/products?category=smartwatch"
              class="mobile-nav-link"
            >
              <i class="fas fa-clock"></i>
              <span>Smartwatch</span>
            </a>
          </li>
          <li class="mobile-nav-item">
            <a
              href="${pageContext.request.contextPath}/products?category=tablet"
              class="mobile-nav-link"
            >
              <i class="fas fa-tablet-alt"></i>
              <span>Tablet</span>
            </a>
          </li>
        </ul>
      </div>
    </header>

    <!-- Main Content -->
    <main class="main-content">
      <!-- Hero Section -->
      <section class="hero">
        <div class="hero-content">
          <h1 class="fade-in">Chào mừng đến với UTE Phone Hub</h1>
          <p class="fade-in">
            Cửa hàng điện thoại, laptop và phụ kiện chính hãng với giá tốt nhất
          </p>
          <div class="hero-buttons fade-in">
            <a
              href="${pageContext.request.contextPath}/products"
              class="btn btn-primary btn-lg"
            >
              <i class="fas fa-shopping-bag"></i>
              Mua sắm ngay
            </a>
            <a
              href="${pageContext.request.contextPath}/api/v1/health"
              class="btn btn-secondary btn-lg"
            >
              <i class="fas fa-heartbeat"></i>
              Kiểm tra API
            </a>
          </div>
        </div>
      </section>

      <!-- Promotional Section -->
      <section class="promo-section">
        <div class="promo-header">
          <h2>Khuyến mãi online</h2>
          <div class="promo-timer">
            <div class="timer-box">
              <span class="timer-label">Chỉ còn:</span>
              <span class="timer-value">00 : 00 : 55</span>
            </div>
            <div class="promo-slots">
              <div class="slot">Sắp diễn ra 21:30</div>
              <div class="slot">Ngày mai 09:00</div>
              <div class="slot">Ngày mai 12:00</div>
              <div class="slot">Ngày mai 15:00</div>
            </div>
          </div>
        </div>

        <div class="promo-banners">
          <div class="promo-banner flash-sale">
            <div class="banner-icon">⚡</div>
            <div class="banner-text">
              <h3>FLASH SALE</h3>
              <p>GIÁ SỐC</p>
            </div>
          </div>
          <div class="promo-banner online-only">
            <div class="banner-icon">🛒</div>
            <div class="banner-text">
              <h3>ONLINE ONLY</h3>
              <p>GIẢM ĐẾN 50%</p>
            </div>
          </div>
        </div>
      </section>

      <!-- Featured Products -->
      <section class="featured-products">
        <div class="section-header">
          <h2>Sản phẩm nổi bật</h2>
          <a
            href="${pageContext.request.contextPath}/products"
            class="btn btn-secondary"
          >
            Xem tất cả
            <i class="fas fa-arrow-right"></i>
          </a>
        </div>

        <div class="product-grid">
          <!-- Product 1 -->
          <div class="product-card" data-product-id="samsung-galaxy-a16-5g">
            <div class="product-image-container">
              <img
                src="https://via.placeholder.com/300x200/ff6b35/ffffff?text=Samsung+Galaxy+A16+5G"
                alt="Samsung Galaxy A16 5G"
                class="product-image"
              />
              <div class="product-badges">
                <span class="badge badge-sale">-5%</span>
                <span class="badge badge-ai">AI</span>
              </div>
              <button class="product-wishlist">
                <i class="fas fa-heart"></i>
              </button>
            </div>
            <div class="product-info">
              <div class="product-category">Điện thoại</div>
              <h3 class="product-title">Samsung Galaxy A16 5G 8GB/256GB</h3>
              <div class="product-rating">
                <div class="rating-stars">
                  <i class="fas fa-star star"></i>
                  <i class="fas fa-star star"></i>
                  <i class="fas fa-star star"></i>
                  <i class="fas fa-star star"></i>
                  <i class="fas fa-star star empty"></i>
                </div>
                <span class="rating-text">(4.2)</span>
              </div>
              <div class="product-price">
                <span class="price-current">6.520.000₫</span>
                <span class="price-original">6.870.000₫</span>
                <span class="discount-percent">-5%</span>
              </div>
              <div class="product-stock">
                <div class="stock-bar">
                  <div class="stock-progress" style="width: 20%"></div>
                </div>
                <div class="stock-text">Còn 2/10 suất</div>
              </div>
              <div class="product-actions">
                <button class="btn-add-cart">Mua ngay</button>
                <button class="btn-quick-view">
                  <i class="fas fa-eye"></i>
                </button>
              </div>
            </div>
          </div>

          <!-- Product 2 -->
          <div class="product-card" data-product-id="vivo-v40-5g">
            <div class="product-image-container">
              <img
                src="https://via.placeholder.com/300x200/ff6b35/ffffff?text=vivo+V40+5G"
                alt="vivo V40 5G"
                class="product-image"
              />
              <div class="product-badges">
                <span class="badge badge-sale">-8%</span>
              </div>
              <button class="product-wishlist">
                <i class="fas fa-heart"></i>
              </button>
            </div>
            <div class="product-info">
              <div class="product-category">Điện thoại</div>
              <h3 class="product-title">vivo V40 5G 12GB/256GB</h3>
              <div class="product-rating">
                <div class="rating-stars">
                  <i class="fas fa-star star"></i>
                  <i class="fas fa-star star"></i>
                  <i class="fas fa-star star"></i>
                  <i class="fas fa-star star"></i>
                  <i class="fas fa-star star"></i>
                </div>
                <span class="rating-text">(4.8)</span>
              </div>
              <div class="product-price">
                <span class="price-current">11.660.000₫</span>
                <span class="price-original">12.760.000₫</span>
                <span class="discount-percent">-8%</span>
              </div>
              <div class="product-stock">
                <div class="stock-bar">
                  <div class="stock-progress" style="width: 100%"></div>
                </div>
                <div class="stock-text">Còn 10/10 suất</div>
              </div>
              <div class="product-actions">
                <button class="btn-add-cart">Mua ngay</button>
                <button class="btn-quick-view">
                  <i class="fas fa-eye"></i>
                </button>
              </div>
            </div>
          </div>

          <!-- Product 3 -->
          <div class="product-card" data-product-id="airpods-max">
            <div class="product-image-container">
              <img
                src="https://via.placeholder.com/300x200/ff6b35/ffffff?text=AirPods+Max"
                alt="AirPods Max"
                class="product-image"
              />
              <div class="product-badges">
                <span class="badge badge-sale">-6%</span>
              </div>
              <button class="product-wishlist">
                <i class="fas fa-heart"></i>
              </button>
            </div>
            <div class="product-info">
              <div class="product-category">Phụ kiện</div>
              <h3 class="product-title">AirPods Max cổng USB C</h3>
              <div class="product-features">
                <ul class="feature-list">
                  <li class="feature-item">
                    <i class="fas fa-check"></i>
                    <span>Nghe 20h Sạc 3h</span>
                  </li>
                  <li class="feature-item">
                    <i class="fas fa-check"></i>
                    <span>Chống ồn chủ động</span>
                  </li>
                  <li class="feature-item">
                    <i class="fas fa-check"></i>
                    <span>Chip H1</span>
                  </li>
                </ul>
              </div>
              <div class="product-price">
                <span class="price-current">12.090.000₫</span>
                <span class="price-original">12.990.000₫</span>
                <span class="discount-percent">-6%</span>
              </div>
              <div class="product-stock">
                <div class="stock-bar">
                  <div class="stock-progress" style="width: 100%"></div>
                </div>
                <div class="stock-text">Còn 3/3 suất</div>
              </div>
              <div class="product-actions">
                <button class="btn-add-cart">Mua ngay</button>
                <button class="btn-quick-view">
                  <i class="fas fa-eye"></i>
                </button>
              </div>
            </div>
          </div>

          <!-- Product 4 -->
          <div class="product-card" data-product-id="thermal-printer">
            <div class="product-image-container">
              <img
                src="https://via.placeholder.com/300x200/ff6b35/ffffff?text=Máy+in+nhiệt"
                alt="Máy in nhiệt"
                class="product-image"
              />
              <div class="product-badges">
                <span class="badge badge-sale">-12%</span>
              </div>
              <button class="product-wishlist">
                <i class="fas fa-heart"></i>
              </button>
            </div>
            <div class="product-info">
              <div class="product-category">Máy in</div>
              <h3 class="product-title">Máy in nhiệt HPRT GT1 Wifi</h3>
              <div class="product-features">
                <ul class="feature-list">
                  <li class="feature-item">
                    <i class="fas fa-check"></i>
                    <span>In 1 mặt</span>
                  </li>
                  <li class="feature-item">
                    <i class="fas fa-check"></i>
                    <span>In Wifi</span>
                  </li>
                  <li class="feature-item">
                    <i class="fas fa-check"></i>
                    <span>50 mm/s</span>
                  </li>
                </ul>
              </div>
              <div class="product-price">
                <span class="price-current">2.190.000₫</span>
                <span class="price-original">2.490.000₫</span>
                <span class="discount-percent">-12%</span>
              </div>
              <div class="product-stock">
                <div class="stock-bar">
                  <div class="stock-progress" style="width: 100%"></div>
                </div>
                <div class="stock-text">Còn 5/5 suất</div>
              </div>
              <div class="product-actions">
                <button class="btn-add-cart">Mua ngay</button>
                <button class="btn-quick-view">
                  <i class="fas fa-eye"></i>
                </button>
              </div>
            </div>
          </div>

          <!-- Product 5 -->
          <div class="product-card" data-product-id="befit-watch">
            <div class="product-image-container">
              <img
                src="https://via.placeholder.com/300x200/ff6b35/ffffff?text=BeFit+Watch"
                alt="BeFit Watch"
                class="product-image"
              />
              <div class="product-badges">
                <span class="badge badge-sale">-63%</span>
                <span class="badge badge-hot">HOT</span>
              </div>
              <button class="product-wishlist">
                <i class="fas fa-heart"></i>
              </button>
            </div>
            <div class="product-info">
              <div class="product-category">Smartwatch</div>
              <h3 class="product-title">
                BeFit Watch Ultra 52.6mm dây silicone
              </h3>
              <div class="product-features">
                <ul class="feature-list">
                  <li class="feature-item">
                    <i class="fas fa-check"></i>
                    <span>Pin 5 ngày Sạc 2 giờ</span>
                  </li>
                  <li class="feature-item">
                    <i class="fas fa-check"></i>
                    <span>Kính cường lực</span>
                  </li>
                  <li class="feature-item">
                    <i class="fas fa-check"></i>
                    <span>Có nghe gọi</span>
                  </li>
                </ul>
              </div>
              <div class="product-price">
                <span class="price-current">550.000₫</span>
                <span class="price-original">1.490.000₫</span>
                <span class="discount-percent">-63%</span>
              </div>
              <div class="product-stock">
                <div class="stock-bar">
                  <div class="stock-progress" style="width: 50%"></div>
                </div>
                <div class="stock-text">Còn 5/10 suất</div>
              </div>
              <div class="product-actions">
                <button class="btn-add-cart">Mua ngay</button>
                <button class="btn-quick-view">
                  <i class="fas fa-eye"></i>
                </button>
              </div>
            </div>
          </div>

          <!-- Product 6 -->
          <div class="product-card" data-product-id="realme-c75">
            <div class="product-image-container">
              <img
                src="https://via.placeholder.com/300x200/ff6b35/ffffff?text=realme+C75"
                alt="realme C75"
                class="product-image"
              />
              <div class="product-badges">
                <span class="badge badge-sale">-15%</span>
              </div>
              <button class="product-wishlist">
                <i class="fas fa-heart"></i>
              </button>
            </div>
            <div class="product-info">
              <div class="product-category">Điện thoại</div>
              <h3 class="product-title">realme C75 8GB/256GB</h3>
              <div class="product-rating">
                <div class="rating-stars">
                  <i class="fas fa-star star"></i>
                  <i class="fas fa-star star"></i>
                  <i class="fas fa-star star"></i>
                  <i class="fas fa-star star"></i>
                  <i class="fas fa-star star empty"></i>
                </div>
                <span class="rating-text">(4.1)</span>
              </div>
              <div class="product-price">
                <span class="price-current">5.380.000₫</span>
                <span class="price-original">6.380.000₫</span>
                <span class="discount-percent">-15%</span>
              </div>
              <div class="product-stock">
                <div class="stock-bar">
                  <div class="stock-progress" style="width: 80%"></div>
                </div>
                <div class="stock-text">Còn 8/10 suất</div>
              </div>
              <div class="product-actions">
                <button class="btn-add-cart">Mua ngay</button>
                <button class="btn-quick-view">
                  <i class="fas fa-eye"></i>
                </button>
              </div>
            </div>
          </div>
        </div>
      </section>

      <!-- API Status Section -->
      <section class="api-status">
        <div class="card">
          <div class="card-header">
            <h3>Trạng thái hệ thống</h3>
          </div>
          <div class="card-body">
            <div class="status-grid">
              <div class="status-item">
                <div class="status-icon success">
                  <i class="fas fa-check-circle"></i>
                </div>
                <div class="status-info">
                  <h4>API Health</h4>
                  <p>Hệ thống hoạt động bình thường</p>
                </div>
              </div>
              <div class="status-item">
                <div class="status-icon success">
                  <i class="fas fa-database"></i>
                </div>
                <div class="status-info">
                  <h4>Database</h4>
                  <p>Kết nối ổn định</p>
                </div>
              </div>
              <div class="status-item">
                <div class="status-icon success">
                  <i class="fas fa-server"></i>
                </div>
                <div class="status-info">
                  <h4>Server</h4>
                  <p>Phản hồi nhanh</p>
                </div>
              </div>
            </div>
            <div class="status-actions">
              <a
                href="${pageContext.request.contextPath}/api/v1/health"
                class="btn btn-primary"
              >
                <i class="fas fa-heartbeat"></i>
                Kiểm tra API
              </a>
              <a
                href="${pageContext.request.contextPath}/docs/api/postman-collection.json"
                class="btn btn-secondary"
              >
                <i class="fas fa-download"></i>
                Postman Collection
              </a>
            </div>
          </div>
        </div>
      </section>
    </main>

    <!-- Footer -->
    <footer class="footer">
      <div class="footer-content">
        <div class="footer-section">
          <h3>Về UTE Phone Hub</h3>
          <ul>
            <li><a href="#">Giới thiệu</a></li>
            <li><a href="#">Tuyển dụng</a></li>
            <li><a href="#">Liên hệ</a></li>
            <li><a href="#">Tin tức</a></li>
          </ul>
        </div>
        <div class="footer-section">
          <h3>Hỗ trợ khách hàng</h3>
          <ul>
            <li><a href="#">Hướng dẫn mua hàng</a></li>
            <li><a href="#">Chính sách bảo hành</a></li>
            <li><a href="#">Chính sách đổi trả</a></li>
            <li><a href="#">FAQ</a></li>
          </ul>
        </div>
        <div class="footer-section">
          <h3>Dịch vụ</h3>
          <ul>
            <li><a href="#">Sửa chữa điện thoại</a></li>
            <li><a href="#">Thu cũ đổi mới</a></li>
            <li><a href="#">Bảo hành mở rộng</a></li>
            <li><a href="#">Cài đặt phần mềm</a></li>
          </ul>
        </div>
        <div class="footer-section">
          <h3>Liên hệ</h3>
          <ul>
            <li><i class="fas fa-phone"></i> 1900 1234</li>
            <li><i class="fas fa-envelope"></i> support@utephonehub.com</li>
            <li>
              <i class="fas fa-map-marker-alt"></i> 123 Đường ABC, Quận 1,
              TP.HCM
            </li>
            <li><i class="fas fa-clock"></i> 8:00 - 22:00 (T2-T7)</li>
          </ul>
        </div>
      </div>
      <div class="footer-bottom">
        <p>&copy; 2024 UTE Phone Hub. Tất cả quyền được bảo lưu.</p>
      </div>
    </footer>

    <!-- JavaScript -->
    <script src="${pageContext.request.contextPath}/static/js/auth.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/main.js"></script>

    <!-- User Account JavaScript -->
    <script>
      // Handle OAuth callback - extract access token from cookie and store in localStorage
      (function handleOAuthCallback() {
        const urlParams = new URLSearchParams(window.location.search);
        const oauthSuccess = urlParams.get("oauth_success");
        
        if (oauthSuccess === "true") {
          // Read access token from cookie
          const cookies = document.cookie.split(';');
          let accessToken = null;
          
          for (let cookie of cookies) {
            const [name, value] = cookie.trim().split('=');
            if (name === 'accessToken') {
              accessToken = value;
              break;
            }
          }
          
          if (accessToken) {
            // Store access token in localStorage
            localStorage.setItem("accessToken", accessToken);
            
            // Delete access token cookie (we only use localStorage for access token)
            document.cookie = "accessToken=; Path=/; Max-Age=0";
            
            // Clean URL
            window.history.replaceState({}, document.title, window.location.pathname);
            
            // Reload to fetch user info via checkUserLogin()
            window.location.reload();
          } else {
            console.error("No access token found in cookie after OAuth");
          }
        }
      })();
      
      // Check if user is logged in
      function checkUserLogin() {
        const token = localStorage.getItem("accessToken");
        const userAccountBtn = document.getElementById("userAccountBtn");
        const userAccountText = document.getElementById("userAccountText");
        const accountDropdownMenu = document.getElementById(
          "accountDropdownMenu"
        );
        const userAccountDropdown = document.getElementById(
          "userAccountDropdown"
        );

        if (token) {
          // User is logged in - fetch user info
          fetch("${pageContext.request.contextPath}/api/v1/user/me", {
            method: "GET",
            headers: {
              Authorization: "Bearer " + token,
              "Content-Type": "application/json",
            },
          })
            .then((response) => {
              if (response.ok) {
                return response.json();
              }
              throw new Error("Not authenticated");
            })
            .then((result) => {
              // Extract user data from response
              const data = result.data || result;

              // Show user name
              const userName = data.fullName || data.email.split("@")[0];
              userAccountText.textContent = userName;

              // Set href to profile page so user can click to go to profile
              userAccountBtn.href =
                "${pageContext.request.contextPath}/profile";

              // Also show dropdown menu on click (but don't prevent default navigation)
              let clickTimeout;
              userAccountBtn.addEventListener("click", (e) => {
                // If clicking quickly, go to profile
                // If hovering, show dropdown
                clearTimeout(clickTimeout);
                clickTimeout = setTimeout(() => {
                  // Single click goes to profile (default behavior)
                }, 200);
              });

              // Show dropdown menu on hover
              userAccountDropdown.addEventListener("mouseenter", () => {
                accountDropdownMenu.style.display = "block";
              });

              userAccountDropdown.addEventListener("mouseleave", () => {
                accountDropdownMenu.style.display = "none";
              });
            })
            .catch((error) => {
              // Token invalid - remove it and keep login link with returnUrl
              localStorage.removeItem("accessToken");
              const currentUrl = encodeURIComponent(window.location.pathname);
              userAccountBtn.href =
                "${pageContext.request.contextPath}/login?returnUrl=" +
                currentUrl;
              userAccountText.textContent = "Đăng nhập";
            });
        } else {
          // User not logged in - add returnUrl to login link
          const currentUrl = encodeURIComponent(window.location.pathname);
          userAccountBtn.href =
            "${pageContext.request.contextPath}/login?returnUrl=" + currentUrl;
          userAccountText.textContent = "Đăng nhập";
        }
      }

      // Logout handler
      document.addEventListener("DOMContentLoaded", function () {
        checkUserLogin();

        const logoutBtn = document.getElementById("logoutBtn");
        if (logoutBtn) {
          logoutBtn.addEventListener("click", function (e) {
            e.preventDefault();

            // Use global logout function from auth.js
            if (typeof logout === "function") {
              logout();
            } else {
              // Fallback: clear storage and redirect
              localStorage.removeItem("accessToken");
              localStorage.removeItem("refreshToken");
              localStorage.removeItem("user");
              window.location.href = "${pageContext.request.contextPath}/";
            }
          });
        }

        // Close dropdown when clicking outside
        document.addEventListener("click", function (e) {
          const dropdown = document.getElementById("userAccountDropdown");
          const menu = document.getElementById("accountDropdownMenu");

          if (dropdown && menu && !dropdown.contains(e.target)) {
            menu.style.display = "none";
          }
        });
      });
    </script>

    <!-- Additional Styles -->
    <style>
      /* User Account Dropdown Styles */
      .user-account-dropdown {
        position: relative;
      }

      /* Fix hover conflict - disable hover on parent when dropdown is shown */
      .user-account-dropdown:hover .header-action {
        background: transparent !important;
        transform: none !important;
      }

      .account-dropdown-menu {
        position: absolute;
        top: calc(100% + 5px);
        right: 0;
        background: white;
        border-radius: 8px;
        box-shadow: 0 8px 24px rgba(0, 0, 0, 0.2);
        min-width: 220px;
        padding: 8px 0;
        z-index: 10000; /* Very high to ensure it's above everything */
        border: 1px solid #e0e0e0;
        pointer-events: auto; /* Ensure dropdown is clickable */
      }

      .account-dropdown-menu::before {
        content: "";
        position: absolute;
        top: -8px;
        right: 20px;
        width: 0;
        height: 0;
        border-left: 8px solid transparent;
        border-right: 8px solid transparent;
        border-bottom: 8px solid white;
        z-index: 10001;
      }

      .account-dropdown-menu .dropdown-item {
        display: flex;
        align-items: center;
        padding: 12px 20px;
        color: #333;
        text-decoration: none;
        font-size: 14px;
        font-weight: 500;
        transition: all 0.2s;
        border-left: 3px solid transparent;
      }

      .account-dropdown-menu .dropdown-item:first-child {
        border-top-left-radius: 8px;
        border-top-right-radius: 8px;
      }

      .account-dropdown-menu .dropdown-item:last-child {
        border-bottom-left-radius: 8px;
        border-bottom-right-radius: 8px;
      }

      .account-dropdown-menu .dropdown-item:hover {
        background: linear-gradient(90deg, #fff5f0, #ffffff);
        border-left-color: #ff6b35;
        padding-left: 25px;
      }

      .account-dropdown-menu .dropdown-item i {
        margin-right: 12px;
        width: 18px;
        text-align: center;
        font-size: 16px;
        color: #ff6b35;
      }

      .account-dropdown-menu hr {
        margin: 8px 0;
        border: none;
        border-top: 1px solid #f0f0f0;
      }

      .promo-section {
        background: white;
        border-radius: 15px;
        padding: 30px;
        margin: 30px 0;
        box-shadow: 0 5px 20px rgba(0, 0, 0, 0.08);
      }

      .promo-header {
        text-align: center;
        margin-bottom: 30px;
      }

      .promo-header h2 {
        font-size: 2.5rem;
        font-weight: 800;
        color: #333;
        margin-bottom: 20px;
      }

      .promo-timer {
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 30px;
        flex-wrap: wrap;
      }

      .timer-box {
        background: linear-gradient(135deg, #ff6b35, #ee4d2d);
        color: white;
        padding: 15px 25px;
        border-radius: 15px;
        text-align: center;
        box-shadow: 0 5px 15px rgba(255, 107, 53, 0.3);
      }

      .timer-label {
        display: block;
        font-size: 14px;
        margin-bottom: 5px;
      }

      .timer-value {
        font-size: 24px;
        font-weight: 800;
      }

      .promo-slots {
        display: flex;
        gap: 15px;
        flex-wrap: wrap;
      }

      .slot {
        background: #f8f9fa;
        padding: 10px 20px;
        border-radius: 20px;
        font-weight: 600;
        color: #666;
        border: 2px solid #e0e0e0;
        transition: all 0.3s;
      }

      .slot:hover {
        border-color: #ff6b35;
        color: #ff6b35;
      }

      .promo-banners {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
        gap: 20px;
        margin-top: 30px;
      }

      .promo-banner {
        display: flex;
        align-items: center;
        padding: 30px;
        border-radius: 15px;
        color: white;
        font-weight: 700;
        text-align: center;
        min-height: 120px;
      }

      .flash-sale {
        background: linear-gradient(135deg, #ee4d2d, #ff6b35);
      }

      .online-only {
        background: linear-gradient(135deg, #ff6b35, #ffd23f);
        color: #333;
      }

      .banner-icon {
        font-size: 3rem;
        margin-right: 20px;
      }

      .banner-text h3 {
        font-size: 1.5rem;
        margin-bottom: 5px;
      }

      .banner-text p {
        font-size: 1.2rem;
        opacity: 0.9;
      }

      .section-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 30px;
      }

      .section-header h2 {
        font-size: 2rem;
        font-weight: 800;
        color: #333;
      }

      .api-status {
        margin: 40px 0;
      }

      .status-grid {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
        gap: 20px;
        margin-bottom: 30px;
      }

      .status-item {
        display: flex;
        align-items: center;
        gap: 15px;
        padding: 20px;
        background: #f8f9fa;
        border-radius: 12px;
      }

      .status-icon {
        width: 50px;
        height: 50px;
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 24px;
      }

      .status-icon.success {
        background: #28a745;
        color: white;
      }

      .status-info h4 {
        font-size: 16px;
        font-weight: 700;
        margin-bottom: 5px;
        color: #333;
      }

      .status-info p {
        font-size: 14px;
        color: #666;
        margin: 0;
      }

      .status-actions {
        display: flex;
        gap: 15px;
        justify-content: center;
        flex-wrap: wrap;
      }

      @media (max-width: 768px) {
        .promo-header h2 {
          font-size: 2rem;
        }

        .promo-timer {
          flex-direction: column;
          gap: 20px;
        }

        .promo-slots {
          justify-content: center;
        }

        .section-header {
          flex-direction: column;
          gap: 20px;
          text-align: center;
        }

        .status-grid {
          grid-template-columns: 1fr;
        }

        .status-actions {
          flex-direction: column;
          align-items: center;
        }
      }
    </style>
  </body>
</html>
