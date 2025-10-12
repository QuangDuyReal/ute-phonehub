<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="pageTitle" value="Trang chủ" scope="request"/>
<c:set var="pageDescription" value="UTE Phone Hub - Cửa hàng điện thoại, laptop, phụ kiện chính hãng với giá tốt nhất" scope="request"/>
<!DOCTYPE html>
<html lang="vi">
  <head>
    <%@ include file="/WEB-INF/views/common/meta.jspf" %>
    <title><c:out value="${pageTitle}"/> - UTE Phone Hub</title>
  </head>
  <body>
    <%@ include file="/WEB-INF/views/common/header.jspf" %>

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
              <button class="product-add-to-cart" onclick="handleQuickAddToCart(this)" title="Thêm vào giỏ hàng">
                <i class="fas fa-shopping-cart"></i>
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
                <button class="btn-add-cart" onclick="handleBuyNow('samsung-galaxy-a16-5g')">Mua ngay</button>
                <button class="btn-quick-view" onclick="viewProductDetail('samsung-galaxy-a16-5g')" title="Xem chi tiết">
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
              <button class="product-add-to-cart" onclick="handleQuickAddToCart(this)" title="Thêm vào giỏ hàng">
                <i class="fas fa-shopping-cart"></i>
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
                <button class="btn-add-cart" onclick="handleBuyNow('vivo-v40-5g')">Mua ngay</button>
                <button class="btn-quick-view" onclick="viewProductDetail('vivo-v40-5g')" title="Xem chi tiết">
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
              <button class="product-add-to-cart" onclick="handleQuickAddToCart(this)" title="Thêm vào giỏ hàng">
                <i class="fas fa-shopping-cart"></i>
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
                <button class="btn-add-cart" onclick="handleBuyNow('airpods-max')">Mua ngay</button>
                <button class="btn-quick-view" onclick="viewProductDetail('airpods-max')" title="Xem chi tiết">
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
              <button class="product-add-to-cart" onclick="handleQuickAddToCart(this)" title="Thêm vào giỏ hàng">
                <i class="fas fa-shopping-cart"></i>
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
                <button class="btn-add-cart" onclick="handleBuyNow('thermal-printer')">Mua ngay</button>
                <button class="btn-quick-view" onclick="viewProductDetail('thermal-printer')" title="Xem chi tiết">
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
              <button class="product-add-to-cart" onclick="handleQuickAddToCart(this)" title="Thêm vào giỏ hàng">
                <i class="fas fa-shopping-cart"></i>
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
                <button class="btn-add-cart" onclick="handleBuyNow('befit-watch')">Mua ngay</button>
                <button class="btn-quick-view" onclick="viewProductDetail('befit-watch')" title="Xem chi tiết">
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
              <button class="product-add-to-cart" onclick="handleQuickAddToCart(this)" title="Thêm vào giỏ hàng">
                <i class="fas fa-shopping-cart"></i>
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
                <button class="btn-add-cart" onclick="handleBuyNow('realme-c75')">Mua ngay</button>
                <button class="btn-quick-view" onclick="viewProductDetail('realme-c75')" title="Xem chi tiết">
                  <i class="fas fa-eye"></i>
                </button>
              </div>
            </div>
          </div>
        </div>
      </section>

      <!-- API Status Section - REMOVED as per requirements -->
    </main>

    <%@ include file="/WEB-INF/views/common/footer.jspf" %>

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
        
        // Load featured products
        loadFeaturedProducts();
        
        // Initialize cart badge
        updateCartBadge();

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
      
      // Load featured products from API
      async function loadFeaturedProducts() {
        try {
          const response = await ProductAPI.getProducts({ 
            page: 1, 
            limit: 6,
            sortBy: 'newest'
          });
          
          if (response && response.success && response.data) {
            renderProducts(response.data);
          }
        } catch (error) {
          console.error('Error loading products:', error);
          // Keep hardcoded products if API fails
        }
      }
      
      // Render products dynamically
      function renderProducts(products) {
        const productGrid = document.querySelector('.product-grid');
        if (!productGrid || !products || products.length === 0) return;
        
        const contextPath = '${pageContext.request.contextPath}';
        
        productGrid.innerHTML = products.map(product => {
          // Build product card HTML
          let html = '<div class="product-card" data-product-id="' + product.id + '">';
          html += '<div class="product-image-container">';
          html += '<a href="' + contextPath + '/products/' + product.id + '">';
          html += '<img src="' + escapeHtml(product.thumbnailUrl || 'https://via.placeholder.com/300x200/ff6b35/ffffff?text=' + encodeURIComponent(product.name)) + '" ';
          html += 'alt="' + escapeHtml(product.name) + '" class="product-image" loading="lazy" />';
          html += '</a>';
          
          // Discount badge
          if (product.discount > 0) {
            html += '<div class="product-badges">';
            html += '<span class="badge badge-sale">-' + product.discount + '%</span>';
            html += '</div>';
          }
          
          html += '<button class="product-add-to-cart" onclick="handleQuickAddToCart(' + product.id + ')" title="Thêm vào giỏ hàng">';
          html += '<i class="fas fa-shopping-cart"></i>';
          html += '</button>';
          html += '</div>';
          
          // Product info
          html += '<div class="product-info">';
          html += '<div class="product-category">' + escapeHtml(product.categoryName || 'Sản phẩm') + '</div>';
          html += '<h3 class="product-title">';
          html += '<a href="' + contextPath + '/products/' + product.id + '">';
          html += escapeHtml(product.name);
          html += '</a></h3>';
          
          // Rating
          if (product.averageRating) {
            html += '<div class="product-rating"><div class="rating-stars">';
            for (let star = 1; star <= 5; star++) {
              html += '<i class="fas fa-star ' + (star <= product.averageRating ? 'star' : 'star empty') + '"></i>';
            }
            html += '</div>';
            html += '<span class="rating-text">(' + product.averageRating.toFixed(1) + ')</span>';
            html += '</div>';
          }
          
          // Price
          html += '<div class="product-price">';
          html += '<span class="price-current">' + formatPrice(product.price) + '</span>';
          if (product.originalPrice && product.originalPrice > product.price) {
            html += '<span class="price-original">' + formatPrice(product.originalPrice) + '</span>';
            html += '<span class="discount-percent">-' + calculateDiscount(product.originalPrice, product.price) + '%</span>';
          }
          html += '</div>';
          
          // Stock
          if (product.stockQuantity !== undefined) {
            const stockPercent = Math.min(100, (product.stockQuantity / 10) * 100);
            html += '<div class="product-stock">';
            html += '<div class="stock-bar">';
            html += '<div class="stock-progress" style="width: ' + stockPercent + '%"></div>';
            html += '</div>';
            html += '<div class="stock-text">Còn ' + product.stockQuantity + ' sản phẩm</div>';
            html += '</div>';
          }
          
          // Actions
          html += '<div class="product-actions">';
          html += '<button class="btn-add-cart" onclick="handleBuyNow(' + product.id + ')">Mua ngay</button>';
          html += '<button class="btn-quick-view" onclick="viewProductDetail(' + product.id + ')" title="Xem chi tiết">';
          html += '<i class="fas fa-eye"></i>';
          html += '</button>';
          html += '</div>';
          
          html += '</div></div>';
          return html;
        }).join('');
      }
      
      // Handle buy now - Add to cart and redirect
      async function handleBuyNow(productId) {
        if (!isLoggedIn()) {
          showToast('Vui lòng đăng nhập để mua hàng', 'warning');
          setTimeout(() => {
            window.location.href = '${pageContext.request.contextPath}/login?returnUrl=' + encodeURIComponent(window.location.pathname);
          }, 1500);
          return;
        }
        
        try {
          showLoading('Đang thêm vào giỏ hàng...');
          await CartAPI.addItem(productId, 1);
          // Redirect to cart
          window.location.href = '${pageContext.request.contextPath}/cart';
        } catch (error) {
          console.error('Error adding to cart:', error);
          showToast(error.message || 'Không thể thêm vào giỏ hàng', 'error');
          hideLoading();
        }
      }
      
      // Quick add to cart - without redirect
      async function handleQuickAddToCart(productId) {
        if (!isLoggedIn()) {
          showToast('Vui lòng đăng nhập để thêm vào giỏ hàng', 'warning');
          setTimeout(() => {
            window.location.href = '${pageContext.request.contextPath}/login?returnUrl=' + encodeURIComponent(window.location.pathname);
          }, 1500);
          return;
        }
        
        try {
          showLoading('Đang thêm vào giỏ hàng...');
          await CartAPI.addItem(productId, 1);
          showToast('Đã thêm vào giỏ hàng', 'success');
          updateCartBadge();
        } catch (error) {
          console.error('Error adding to cart:', error);
          showToast(error.message || 'Không thể thêm vào giỏ hàng', 'error');
        } finally {
          hideLoading();
        }
      }
      
      // View product detail
      function viewProductDetail(productId) {
        window.location.href = '${pageContext.request.contextPath}/products/' + productId;
      }
      
      // Update cart badge
      async function updateCartBadge() {
        if (!isLoggedIn()) {
          const badge = document.getElementById('cartBadge');
          if (badge) badge.style.display = 'none';
          return;
        }
        
        try {
          const response = await CartAPI.getCart();
          if (response && response.success && response.data) {
            const totalItems = response.data.totalItems || 0;
            const badge = document.getElementById('cartBadge');
            if (badge) {
              badge.textContent = totalItems;
              badge.style.display = totalItems > 0 ? 'flex' : 'none';
            }
          }
        } catch (error) {
          console.error('Error fetching cart:', error);
        }
      }
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
