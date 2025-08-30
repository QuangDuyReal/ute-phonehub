import React from 'react';
import { Link } from 'react-router-dom';
import './HomePage.css';

const HomePage: React.FC = () => {
  return (
    <div className="home-page">
      <div className="hero-section">
        <div className="container">
          <div className="hero-content">
            <h1>Chào mừng đến với UTE PhoneHub</h1>
            <p className="hero-subtitle">
              Website thương mại điện tử chuyên bán điện thoại di động
            </p>
            <p className="hero-description">
              Đây là phiên bản demo để kiểm tra các API endpoints của backend. 
              Dự án được xây dựng bằng Java Servlet và ReactJS.
            </p>
            
            <div className="cta-buttons">
              <Link to="/endpoints" className="btn btn-primary">
                Kiểm tra API Endpoints
              </Link>
              <a 
                href="http://localhost:8080/ute-phonehub/api-docs/" 
                target="_blank" 
                rel="noopener noreferrer"
                className="btn btn-secondary"
              >
                Xem Swagger UI
              </a>
            </div>
          </div>
        </div>
      </div>
      
      <div className="features-section">
        <div className="container">
          <h2>Tính năng chính</h2>
          <div className="features-grid">
            <div className="feature-card">
              <div className="feature-icon">🔐</div>
              <h3>Xác thực & Quản lý Tài khoản</h3>
              <p>Đăng ký, đăng nhập, quản lý thông tin cá nhân với bảo mật JWT</p>
            </div>
            
            <div className="feature-card">
              <div className="feature-icon">📱</div>
              <h3>Quản lý Sản phẩm</h3>
              <p>Hiển thị, tìm kiếm, lọc sản phẩm với giao diện thân thiện</p>
            </div>
            
            <div className="feature-card">
              <div className="feature-icon">🛒</div>
              <h3>Giỏ hàng & Đặt hàng</h3>
              <p>Quản lý giỏ hàng, quy trình đặt hàng hoàn chỉnh</p>
            </div>
            
            <div className="feature-card">
              <div className="feature-icon">⭐</div>
              <h3>Đánh giá & Bình luận</h3>
              <p>Hệ thống đánh giá sản phẩm từ người dùng thực tế</p>
            </div>
            
            <div className="feature-card">
              <div className="feature-icon">👨‍💼</div>
              <h3>Quản trị Admin</h3>
              <p>Giao diện quản lý cho admin với dashboard và báo cáo</p>
            </div>
            
            <div className="feature-card">
              <div className="feature-icon">🎟️</div>
              <h3>Voucher & Khuyến mãi</h3>
              <p>Hệ thống mã giảm giá và các chương trình khuyến mãi</p>
            </div>
          </div>
        </div>
      </div>
      
      <div className="tech-stack-section">
        <div className="container">
          <h2>Technology Stack</h2>
          <div className="tech-grid">
            <div className="tech-item">
              <h4>Backend</h4>
              <ul>
                <li>Java 17</li>
                <li>Java Servlet API</li>
                <li>JDBC + PostgreSQL</li>
                <li>JWT Authentication</li>
                <li>Swagger API Documentation</li>
              </ul>
            </div>
            
            <div className="tech-item">
              <h4>Frontend</h4>
              <ul>
                <li>ReactJS 18</li>
                <li>TypeScript</li>
                <li>React Router</li>
                <li>Axios</li>
                <li>CSS3 + Responsive Design</li>
              </ul>
            </div>
            
            <div className="tech-item">
              <h4>Tools & Deployment</h4>
              <ul>
                <li>Apache Tomcat 11</li>
                <li>Maven</li>
                <li>Git</li>
                <li>Postman</li>
                <li>VS Code</li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default HomePage;
