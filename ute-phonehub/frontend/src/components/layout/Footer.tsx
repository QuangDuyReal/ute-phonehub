import React from 'react';
import './Footer.css';

const Footer: React.FC = () => {
  return (
    <footer className="footer">
      <div className="container">
        <div className="footer-content">
          <div className="footer-section">
            <h3>UTE PhoneHub</h3>
            <p>Website thương mại điện tử chuyên bán điện thoại di động</p>
          </div>
          
          <div className="footer-section">
            <h4>Thông tin</h4>
            <ul>
              <li>Dự án WEPR - HK5 2024-2025</li>
              <li>Trường Đại học Sư phạm Kỹ thuật TP.HCM</li>
              <li>Khoa Công nghệ Thông tin</li>
            </ul>
          </div>
          
          <div className="footer-section">
            <h4>Technology Stack</h4>
            <ul>
              <li>Backend: Java Servlet + PostgreSQL</li>
              <li>Frontend: ReactJS + TypeScript</li>
              <li>API: RESTful + Swagger</li>
            </ul>
          </div>
        </div>
        
        <div className="footer-bottom">
          <p>&copy; 2025 UTE PhoneHub. Đồ án môn Lập trình Web.</p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
