import axios from 'axios';

// Cấu hình base URL của backend từ environment variables
const BASE_URL = process.env.REACT_APP_API_BASE_URL || 'http://localhost:8080/ute-phonehub';

// Tạo axios instance với cấu hình cơ bản
const api = axios.create({
  baseURL: BASE_URL,
  timeout: 30000, // Tăng timeout cho production
  headers: {
    'Content-Type': 'application/json',
  },
});

// Request interceptor để thêm JWT token nếu có
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Response interceptor để xử lý lỗi chung
api.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    if (error.response?.status === 401) {
      // Token hết hạn hoặc không hợp lệ
      localStorage.removeItem('token');
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

export default api;
export { BASE_URL };
