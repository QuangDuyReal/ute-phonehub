package com.utephonehub.service;

import com.utephonehub.entity.*;
import com.utephonehub.repository.ProductRepository;
import com.utephonehub.repository.ReviewRepository;

import java.util.*;

public class ReviewService {
    
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    
    public ReviewService() {
        this.reviewRepository = new ReviewRepository();
        this.productRepository = new ProductRepository();
    }
    
    public Map<String, Object> getProductReviews(Long productId, int page, int limit) {
        // Check if product exists
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) {
            throw new RuntimeException("Không tìm thấy sản phẩm với ID: " + productId);
        }
        
        List<Review> reviews = reviewRepository.findByProductId(productId, page, limit);
        long totalReviews = reviewRepository.countByProductId(productId);
        
        List<Map<String, Object>> reviewList = new ArrayList<>();
        for (Review review : reviews) {
            Map<String, Object> reviewData = new HashMap<>();
            reviewData.put("id", review.getId());
            reviewData.put("rating", review.getRating());
            reviewData.put("comment", review.getComment());
            
            Map<String, Object> userData = new HashMap<>();
            userData.put("fullName", review.getUser().getFullName());
            reviewData.put("user", userData);
            
            int likeCount = review.getLikes() != null ? review.getLikes().size() : 0;
            reviewData.put("likeCount", likeCount);
            reviewData.put("createdAt", review.getCreatedAt());
            
            reviewList.add(reviewData);
        }
        
        // Calculate pagination
        int totalPages = (int) Math.ceil((double) totalReviews / limit);
        
        Map<String, Object> pagination = new HashMap<>();
        pagination.put("page", page);
        pagination.put("limit", limit);
        pagination.put("totalItems", totalReviews);
        pagination.put("totalPages", totalPages);
        
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("pagination", pagination);
        
        Map<String, Object> result = new HashMap<>();
        result.put("reviews", reviewList);
        result.put("metadata", metadata);
        
        return result;
    }
    
    public Map<String, Object> createReview(Long userId, Long productId, Integer rating, String comment) {
        // Check if product exists
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) {
            throw new RuntimeException("Không tìm thấy sản phẩm với ID: " + productId);
        }
        
        // Check if user has already reviewed this product
        if (reviewRepository.hasUserReviewedProduct(userId, productId)) {
            throw new RuntimeException("Người dùng đã đánh giá sản phẩm này trước đó");
        }
        
        // Check if user has purchased this product
        if (!reviewRepository.hasUserPurchasedProduct(userId, productId)) {
            throw new RuntimeException("Người dùng chưa mua sản phẩm này hoặc đơn hàng chưa hoàn thành");
        }
        
        // Validate rating
        if (rating < 1 || rating > 5) {
            throw new RuntimeException("Đánh giá phải từ 1 đến 5 sao");
        }
        
        // Create review
        Review review = new Review();
        
        User user = new User();
        user.setId(userId);
        review.setUser(user);
        
        Product product = productOpt.get();
        review.setProduct(product);
        
        review.setRating(rating);
        review.setComment(comment);
        
        Review savedReview = reviewRepository.save(review);
        
        // Build response
        Map<String, Object> reviewData = new HashMap<>();
        reviewData.put("id", savedReview.getId());
        reviewData.put("rating", savedReview.getRating());
        reviewData.put("comment", savedReview.getComment());
        
        Map<String, Object> userData = new HashMap<>();
        userData.put("fullName", user.getFullName());
        reviewData.put("user", userData);
        
        reviewData.put("likeCount", 0);
        reviewData.put("createdAt", savedReview.getCreatedAt());
        
        return reviewData;
    }
    
    public Map<String, Object> likeReview(Long userId, Long reviewId) {
        // Check if review exists
        Optional<Review> reviewOpt = reviewRepository.findById(reviewId);
        if (reviewOpt.isEmpty()) {
            throw new RuntimeException("Không tìm thấy đánh giá với ID: " + reviewId);
        }
        
        // Check if user has already liked this review
        Optional<ReviewLike> existingLike = reviewRepository.findReviewLike(reviewId, userId);
        if (existingLike.isPresent()) {
            throw new RuntimeException("Người dùng đã thích bài đánh giá này");
        }
        
        // Create like
        ReviewLike reviewLike = new ReviewLike();
        
        User user = new User();
        user.setId(userId);
        reviewLike.setUser(user);
        
        Review review = reviewOpt.get();
        reviewLike.setReview(review);
        
        reviewRepository.saveReviewLike(reviewLike);
        
        // Get updated like count
        int likeCount = reviewRepository.getLikeCount(reviewId);
        
        Map<String, Object> result = new HashMap<>();
        result.put("likeCount", likeCount);
        
        return result;
    }
    
    public Map<String, Object> unlikeReview(Long userId, Long reviewId) {
        // Check if review exists
        Optional<Review> reviewOpt = reviewRepository.findById(reviewId);
        if (reviewOpt.isEmpty()) {
            throw new RuntimeException("Không tìm thấy đánh giá với ID: " + reviewId);
        }
        
        // Check if user has liked this review
        Optional<ReviewLike> existingLike = reviewRepository.findReviewLike(reviewId, userId);
        if (existingLike.isEmpty()) {
            throw new RuntimeException("Người dùng chưa thích bài đánh giá này");
        }
        
        // Delete like
        reviewRepository.deleteReviewLike(existingLike.get().getId());
        
        // Get updated like count
        int likeCount = reviewRepository.getLikeCount(reviewId);
        
        Map<String, Object> result = new HashMap<>();
        result.put("likeCount", likeCount);
        
        return result;
    }
}
