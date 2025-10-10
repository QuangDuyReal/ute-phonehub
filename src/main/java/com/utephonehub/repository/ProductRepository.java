package com.utephonehub.repository;

import com.utephonehub.entity.Product;
import com.utephonehub.config.DatabaseConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Product Repository
 * Quản lý các thao tác database cho Product entity
 */
public class ProductRepository {
    
    private static final Logger logger = LogManager.getLogger(ProductRepository.class);
    
    /**
     * Lưu product mới
     * @param product Product entity
     * @return Product đã được lưu
     */
    public Product save(Product product) {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            DatabaseConfig.beginTransaction();
            if (product.getId() == null) {
                em.persist(product);
                logger.info("Created new product: {}", product.getName());
            } else {
                product = em.merge(product);
                logger.info("Updated product: {}", product.getName());
            }
            DatabaseConfig.commitTransaction();
            return product;
        } catch (Exception e) {
            DatabaseConfig.rollbackTransaction();
            logger.error("Error saving product: {}", product.getName(), e);
            throw new RuntimeException("Failed to save product", e);
        }
    }
    
    /**
     * Tìm product theo ID
     * @param id Product ID
     * @return Optional<Product>
     */
    public Optional<Product> findById(Long id) {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            Product product = em.find(Product.class, id);
            return Optional.ofNullable(product);
        } catch (Exception e) {
            logger.error("Error finding product by ID: {}", id, e);
            throw new RuntimeException("Failed to find product by ID", e);
        }
    }
    
    /**
     * Lấy tất cả products
     * @return List<Product>
     */
    public List<Product> findAll() {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            TypedQuery<Product> query = em.createQuery(
                "SELECT p FROM Product p ORDER BY p.createdAt DESC", Product.class);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error finding all products", e);
            throw new RuntimeException("Failed to find all products", e);
        }
    }
    
    /**
     * Lấy products với pagination
     * @param page Số trang (bắt đầu từ 0)
     * @param size Kích thước trang
     * @return List<Product>
     */
    public List<Product> findAll(int page, int size) {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            TypedQuery<Product> query = em.createQuery(
                "SELECT p FROM Product p ORDER BY p.createdAt DESC", Product.class);
            query.setFirstResult(page * size);
            query.setMaxResults(size);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error finding products with pagination", e);
            throw new RuntimeException("Failed to find products with pagination", e);
        }
    }
    
    /**
     * Lấy products theo category
     * @param categoryId Category ID
     * @return List<Product>
     */
    public List<Product> findByCategoryId(Long categoryId) {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            TypedQuery<Product> query = em.createQuery(
                "SELECT p FROM Product p WHERE p.category.id = :categoryId AND p.status = true ORDER BY p.createdAt DESC", 
                Product.class);
            query.setParameter("categoryId", categoryId);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error finding products by category: {}", categoryId, e);
            throw new RuntimeException("Failed to find products by category", e);
        }
    }
    
    /**
     * Lấy products theo brand
     * @param brandId Brand ID
     * @return List<Product>
     */
    public List<Product> findByBrandId(Long brandId) {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            TypedQuery<Product> query = em.createQuery(
                "SELECT p FROM Product p WHERE p.brand.id = :brandId AND p.status = true ORDER BY p.createdAt DESC", 
                Product.class);
            query.setParameter("brandId", brandId);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error finding products by brand: {}", brandId, e);
            throw new RuntimeException("Failed to find products by brand", e);
        }
    }
    
    /**
     * Tìm kiếm products theo tên
     * @param keyword Từ khóa tìm kiếm
     * @return List<Product>
     */
    public List<Product> searchByName(String keyword) {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            TypedQuery<Product> query = em.createQuery(
                "SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(:keyword) AND p.status = true ORDER BY p.createdAt DESC", 
                Product.class);
            query.setParameter("keyword", "%" + keyword + "%");
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error searching products by name: {}", keyword, e);
            throw new RuntimeException("Failed to search products by name", e);
        }
    }
    
    /**
     * Lọc products theo giá
     * @param minPrice Giá tối thiểu
     * @param maxPrice Giá tối đa
     * @return List<Product>
     */
    public List<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            TypedQuery<Product> query = em.createQuery(
                "SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice AND p.status = true ORDER BY p.price ASC", 
                Product.class);
            query.setParameter("minPrice", minPrice);
            query.setParameter("maxPrice", maxPrice);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error finding products by price range: {} - {}", minPrice, maxPrice, e);
            throw new RuntimeException("Failed to find products by price range", e);
        }
    }
    
    /**
     * Lấy products còn hàng
     * @return List<Product>
     */
    public List<Product> findInStock() {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            TypedQuery<Product> query = em.createQuery(
                "SELECT p FROM Product p WHERE p.stockQuantity > 0 AND p.status = true ORDER BY p.createdAt DESC", 
                Product.class);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error finding products in stock", e);
            throw new RuntimeException("Failed to find products in stock", e);
        }
    }
    
    /**
     * Lấy products mới nhất
     * @param limit Số lượng sản phẩm
     * @return List<Product>
     */
    public List<Product> findLatest(int limit) {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            TypedQuery<Product> query = em.createQuery(
                "SELECT p FROM Product p WHERE p.status = true ORDER BY p.createdAt DESC", 
                Product.class);
            query.setMaxResults(limit);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error finding latest products", e);
            throw new RuntimeException("Failed to find latest products", e);
        }
    }
    
    /**
     * Đếm tổng số products
     * @return Tổng số products
     */
    public long count() {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(p) FROM Product p WHERE p.status = true", Long.class);
            return query.getSingleResult();
        } catch (Exception e) {
            logger.error("Error counting products", e);
            throw new RuntimeException("Failed to count products", e);
        }
    }
    
    /**
     * Xóa product
     * @param id Product ID
     * @return true nếu xóa thành công
     */
    public boolean deleteById(Long id) {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            DatabaseConfig.beginTransaction();
            Product product = em.find(Product.class, id);
            if (product != null) {
                em.remove(product);
                DatabaseConfig.commitTransaction();
                logger.info("Deleted product: {}", product.getName());
                return true;
            }
            DatabaseConfig.rollbackTransaction();
            return false;
        } catch (Exception e) {
            DatabaseConfig.rollbackTransaction();
            logger.error("Error deleting product by ID: {}", id, e);
            throw new RuntimeException("Failed to delete product", e);
        }
    }
    
    /**
     * Cập nhật stock quantity
     * @param id Product ID
     * @param quantity Số lượng mới
     * @return true nếu cập nhật thành công
     */
    public boolean updateStock(Long id, Integer quantity) {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            DatabaseConfig.beginTransaction();
            Product product = em.find(Product.class, id);
            if (product != null) {
                product.setStockQuantity(quantity);
                em.merge(product);
                DatabaseConfig.commitTransaction();
                logger.info("Updated product stock: {} -> {}", product.getName(), quantity);
                return true;
            }
            DatabaseConfig.rollbackTransaction();
            return false;
        } catch (Exception e) {
            DatabaseConfig.rollbackTransaction();
            logger.error("Error updating product stock: {}", id, e);
            throw new RuntimeException("Failed to update product stock", e);
        }
    }
    
    /**
     * Tìm products theo bán chạy (dựa trên số lượng orders)
     * @param limit Số lượng sản phẩm
     * @return List<Product>
     */
    public List<Product> findBestSelling(int limit) {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            TypedQuery<Product> query = em.createQuery(
                "SELECT p FROM Product p WHERE p.status = true ORDER BY p.createdAt DESC", 
                Product.class);
            query.setMaxResults(limit);
            return query.getResultList();
        } catch (Exception e) {
            logger.error("Error finding best selling products", e);
            throw new RuntimeException("Failed to find best selling products", e);
        }
    }
    
    /**
     * Tìm products với filters phức tạp
     * @param categoryId Filter by category
     * @param brandId Filter by brand
     * @param minPrice Filter by min price
     * @param maxPrice Filter by max price
     * @param keyword Search keyword
     * @param sortField Field để sort
     * @param sortDirection Sort direction (asc/desc)
     * @param limit Số lượng kết quả
     * @param offset Offset cho pagination
     * @return List<Product>
     */
    public List<Product> findWithFilters(Long categoryId, Long brandId, 
                                        BigDecimal minPrice, BigDecimal maxPrice, 
                                        String keyword, String sortField, 
                                        String sortDirection, int limit, int offset) {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            // Build dynamic query with JOIN FETCH for lazy-loaded associations
            StringBuilder jpql = new StringBuilder(
                "SELECT DISTINCT p FROM Product p " +
                "LEFT JOIN FETCH p.category " +
                "LEFT JOIN FETCH p.brand " +
                "WHERE p.status = true"
            );
            
            if (categoryId != null) {
                jpql.append(" AND p.category.id = :categoryId");
            }
            if (brandId != null) {
                jpql.append(" AND p.brand.id = :brandId");
            }
            if (minPrice != null) {
                jpql.append(" AND p.price >= :minPrice");
            }
            if (maxPrice != null) {
                jpql.append(" AND p.price <= :maxPrice");
            }
            if (keyword != null && !keyword.trim().isEmpty()) {
                jpql.append(" AND LOWER(p.name) LIKE LOWER(:keyword)");
            }
            
            // Add sorting
            jpql.append(" ORDER BY p.");
            jpql.append(sortField != null ? sortField : "createdAt");
            jpql.append(" ");
            jpql.append("desc".equalsIgnoreCase(sortDirection) ? "DESC" : "ASC");
            
            TypedQuery<Product> query = em.createQuery(jpql.toString(), Product.class);
            
            // Set parameters
            if (categoryId != null) {
                query.setParameter("categoryId", categoryId);
            }
            if (brandId != null) {
                query.setParameter("brandId", brandId);
            }
            if (minPrice != null) {
                query.setParameter("minPrice", minPrice);
            }
            if (maxPrice != null) {
                query.setParameter("maxPrice", maxPrice);
            }
            if (keyword != null && !keyword.trim().isEmpty()) {
                query.setParameter("keyword", "%" + keyword + "%");
            }
            
            // Set pagination
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            
            return query.getResultList();
            
        } catch (Exception e) {
            logger.error("Error finding products with filters", e);
            throw new RuntimeException("Failed to find products with filters", e);
        }
    }
    
    /**
     * Đếm số products với filters
     * @param categoryId Filter by category
     * @param brandId Filter by brand
     * @param minPrice Filter by min price
     * @param maxPrice Filter by max price
     * @param keyword Search keyword
     * @return Số lượng products
     */
    public long countWithFilters(Long categoryId, Long brandId, 
                                 BigDecimal minPrice, BigDecimal maxPrice, 
                                 String keyword) {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            // Build dynamic query
            StringBuilder jpql = new StringBuilder("SELECT COUNT(p) FROM Product p WHERE p.status = true");
            
            if (categoryId != null) {
                jpql.append(" AND p.category.id = :categoryId");
            }
            if (brandId != null) {
                jpql.append(" AND p.brand.id = :brandId");
            }
            if (minPrice != null) {
                jpql.append(" AND p.price >= :minPrice");
            }
            if (maxPrice != null) {
                jpql.append(" AND p.price <= :maxPrice");
            }
            if (keyword != null && !keyword.trim().isEmpty()) {
                jpql.append(" AND LOWER(p.name) LIKE LOWER(:keyword)");
            }
            
            TypedQuery<Long> query = em.createQuery(jpql.toString(), Long.class);
            
            // Set parameters
            if (categoryId != null) {
                query.setParameter("categoryId", categoryId);
            }
            if (brandId != null) {
                query.setParameter("brandId", brandId);
            }
            if (minPrice != null) {
                query.setParameter("minPrice", minPrice);
            }
            if (maxPrice != null) {
                query.setParameter("maxPrice", maxPrice);
            }
            if (keyword != null && !keyword.trim().isEmpty()) {
                query.setParameter("keyword", "%" + keyword + "%");
            }
            
            return query.getSingleResult();
            
        } catch (Exception e) {
            logger.error("Error counting products with filters", e);
            throw new RuntimeException("Failed to count products with filters", e);
        }
    }
}
