import React from 'react';
import { Link } from 'react-router-dom';
import './Header.css';

const Header: React.FC = () => {
  return (
    <header className="header">
      <div className="container">
        <div className="header-content">
          <div className="logo">
            <Link to="/">
              <h1>UTE PhoneHub</h1>
            </Link>
          </div>
          
          <nav className="navigation">
            <Link to="/" className="nav-link">Trang chủ</Link>
            <Link to="/endpoints" className="nav-link">API Endpoints</Link>
            <Link to="/swagger" className="nav-link" target="_blank">
              Swagger UI
            </Link>
          </nav>
        </div>
      </div>
    </header>
  );
};

export default Header;
