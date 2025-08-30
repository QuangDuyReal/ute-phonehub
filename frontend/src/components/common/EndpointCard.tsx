import React, { useState } from 'react';
import { EndpointInfo } from '../../services/endpointService';
import LoadingSpinner from './LoadingSpinner';
import './EndpointCard.css';

interface EndpointCardProps {
  title: string;
  endpoint: string;
  description: string;
  onTest: () => Promise<EndpointInfo>;
  color?: string;
}

const EndpointCard: React.FC<EndpointCardProps> = ({
  title,
  endpoint,
  description,
  onTest,
  color = '#3498db'
}) => {
  const [loading, setLoading] = useState(false);
  const [result, setResult] = useState<EndpointInfo | null>(null);
  const [error, setError] = useState<string | null>(null);

  const handleTest = async () => {
    setLoading(true);
    setError(null);
    setResult(null);

    try {
      const response = await onTest();
      setResult(response);
    } catch (err: any) {
      setError(err.message || 'Lỗi khi gọi API');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="endpoint-card">
      <div className="card-header" style={{ borderLeftColor: color }}>
        <h3>{title}</h3>
        <span className="endpoint-url">GET {endpoint}</span>
      </div>
      
      <div className="card-body">
        <p className="description">{description}</p>
        
        <button 
          className="test-button" 
          onClick={handleTest}
          disabled={loading}
          style={{ backgroundColor: color }}
        >
          {loading ? 'Đang gọi...' : 'Test API'}
        </button>
        
        {loading && <LoadingSpinner size="small" color={color} />}
        
        {result && (
          <div className="result-section success">
            <h4>✅ Kết quả thành công:</h4>
            <pre>{JSON.stringify(result, null, 2)}</pre>
          </div>
        )}
        
        {error && (
          <div className="result-section error">
            <h4>❌ Lỗi:</h4>
            <p>{error}</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default EndpointCard;
