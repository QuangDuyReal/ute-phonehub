import api from './api';

// Interface cho response từ backend endpoints
export interface EndpointInfo {
  message: string;
  module?: string;
  endpoints?: string[];
  timestamp?: string;
}

// Service để gọi các API endpoints cơ bản
export const endpointService = {
  // Gọi trang chủ (Root endpoint)
  getHome: async (): Promise<EndpointInfo> => {
    const response = await api.get('/api');
    return response.data;
  },

  // Gọi Auth module
  getAuth: async (): Promise<EndpointInfo> => {
    const response = await api.get('/api/auth');
    return response.data;
  },

  // Gọi Product module
  getProducts: async (): Promise<EndpointInfo> => {
    const response = await api.get('/api/products');
    return response.data;
  },

  // Gọi Cart module
  getCart: async (): Promise<EndpointInfo> => {
    const response = await api.get('/api/cart');
    return response.data;
  },

  // Gọi Order module
  getOrders: async (): Promise<EndpointInfo> => {
    const response = await api.get('/api/orders');
    return response.data;
  },

  // Gọi Review module
  getReviews: async (): Promise<EndpointInfo> => {
    const response = await api.get('/api/reviews');
    return response.data;
  },

  // Gọi Admin module
  getAdmin: async (): Promise<EndpointInfo> => {
    const response = await api.get('/api/admin');
    return response.data;
  },

  // Gọi Voucher module
  getVouchers: async (): Promise<EndpointInfo> => {
    const response = await api.get('/api/vouchers');
    return response.data;
  },
};

export default endpointService;
