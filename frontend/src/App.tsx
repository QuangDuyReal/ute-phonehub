import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/layout/Header';
import Footer from './components/layout/Footer';
import HomePage from './pages/HomePage';
import EndpointsPage from './pages/EndpointsPage';
import './App.css';

function App() {
  return (
    <Router>
      <div className="App">
        <Header />
        <main className="main-content">
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/endpoints" element={<EndpointsPage />} />
            <Route 
              path="/swagger" 
              element={
                <div style={{ 
                  padding: '2rem', 
                  textAlign: 'center',
                  minHeight: '50vh',
                  display: 'flex',
                  flexDirection: 'column',
                  justifyContent: 'center'
                }}>
                  <h2>Đang chuyển đến Swagger UI...</h2>
                  <p>Nếu không tự động chuyển, click 
                    <a 
                      href="http://localhost:8080/ute-phonehub/api-docs/" 
                      target="_blank" 
                      rel="noopener noreferrer"
                      style={{ margin: '0 5px', color: '#3498db' }}
                    >
                      vào đây
                    </a>
                  </p>
                </div>
              } 
            />
          </Routes>
        </main>
        <Footer />
      </div>
    </Router>
  );
}

export default App;
