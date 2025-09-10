import React from 'react';
import EndpointCard from '../components/common/EndpointCard';
import endpointService from '../services/endpointService';
import './EndpointsPage.css';

const EndpointsPage: React.FC = () => {
  const endpoints = [
    {
      title: 'Trang chủ (Root)',
      endpoint: '/',
      description: 'Endpoint gốc hiển thị thông tin tổng quan về project UTE-PhoneHub',
      onTest: endpointService.getHome,
      color: '#e74c3c'
    },
    {
      title: 'Module Xác thực',
      endpoint: '/api/auth',
      description: 'Module quản lý đăng ký, đăng nhập, xác thực người dùng',
      onTest: endpointService.getAuth,
      color: '#3498db'
    },
    {
      title: 'Module Sản phẩm',
      endpoint: '/api/products',
      description: 'Module quản lý sản phẩm, danh mục, tìm kiếm và lọc sản phẩm',
      onTest: endpointService.getProducts,
      color: '#2ecc71'
    },
    {
      title: 'Module Giỏ hàng',
      endpoint: '/api/cart',
      description: 'Module quản lý giỏ hàng, thêm/xóa sản phẩm, tính toán tổng tiền',
      onTest: endpointService.getCart,
      color: '#f39c12'
    },
    {
      title: 'Module Đơn hàng',
      endpoint: '/api/orders',
      description: 'Module quản lý đơn hàng, quy trình đặt hàng và thanh toán',
      onTest: endpointService.getOrders,
      color: '#9b59b6'
    },
    {
      title: 'Module Đánh giá',
      endpoint: '/api/reviews',
      description: 'Module quản lý đánh giá và bình luận sản phẩm từ khách hàng',
      onTest: endpointService.getReviews,
      color: '#1abc9c'
    },
    {
      title: 'Module Quản trị',
      endpoint: '/api/admin',
      description: 'Module dành cho admin quản lý toàn bộ hệ thống',
      onTest: endpointService.getAdmin,
      color: '#34495e'
    },
    {
      title: 'Module Voucher',
      endpoint: '/api/vouchers',
      description: 'Module quản lý mã giảm giá và các chương trình khuyến mãi',
      onTest: endpointService.getVouchers,
      color: '#e67e22'
    }
  ];

  return (
    <div className="endpoints-page">
      <div className="container">
        <div className="page-header">
          <h1>API Endpoints Testing</h1>
          <p className="page-description">
            Trang này cho phép kiểm tra tất cả các API endpoints cơ bản của backend UTE-PhoneHub. 
            Mỗi module đại diện cho một chức năng chính của hệ thống.
          </p>
          
          <div className="info-box">
            <h3>📡 Thông tin Backend Server</h3>
            <p><strong>Base URL:</strong> http://localhost:8080/ute-phonehub</p>
            <p><strong>Server:</strong> Apache Tomcat 11</p>
            <p><strong>API Documentation:</strong> 
              <a 
                href="http://localhost:8080/ute-phonehub/api-docs/" 
                target="_blank" 
                rel="noopener noreferrer"
                className="swagger-link"
              >
                Swagger UI
              </a>
            </p>
          </div>
        </div>
        
        <div className="endpoints-grid">
          {endpoints.map((endpoint, index) => (
            <EndpointCard
              key={index}
              title={endpoint.title}
              endpoint={endpoint.endpoint}
              description={endpoint.description}
              onTest={endpoint.onTest}
              color={endpoint.color}
            />
          ))}
        </div>
        
        <div className="additional-info">
          <h2>📋 Hướng dẫn sử dụng</h2>
          <div className="instructions">
            <div className="instruction-item">
              <span className="step-number">1</span>
              <div>
                <h4>Đảm bảo Backend đang chạy</h4>
                <p>Kiểm tra Tomcat server đang chạy trên port 8080</p>
              </div>
            </div>
            
            <div className="instruction-item">
              <span className="step-number">2</span>
              <div>
                <h4>Click "Test API" để kiểm tra</h4>
                <p>Mỗi card đại diện cho một module, click để gọi API endpoint</p>
              </div>
            </div>
            
            <div className="instruction-item">
              <span className="step-number">3</span>
              <div>
                <h4>Xem kết quả</h4>
                <p>Kết quả JSON sẽ hiển thị ngay dưới nút test</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default EndpointsPage;
