package com.utephonehub.repository;

import com.utephonehub.config.DatabaseConfig;
import com.utephonehub.entity.Voucher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class VoucherRepository {
    
    public Optional<Voucher> findByCode(String code) {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            Voucher voucher = em.createQuery(
                "SELECT v FROM Voucher v WHERE v.code = :code", Voucher.class)
                .setParameter("code", code)
                .getSingleResult();
            return Optional.of(voucher);
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            em.close();
        }
    }
    
    public boolean isVoucherValid(Voucher voucher) {
        // Check status
        if (voucher.getStatus() != Voucher.VoucherStatus.ACTIVE) {
            return false;
        }
        
        // Check expiry date
        if (voucher.getExpiryDate() != null && 
            voucher.getExpiryDate().isBefore(LocalDateTime.now())) {
            return false;
        }
        
        // Check max usage
        if (voucher.getMaxUsage() != null) {
            EntityManager em = DatabaseConfig.getEntityManager();
            try {
                long usageCount = em.createQuery(
                    "SELECT COUNT(o) FROM Order o WHERE o.voucher.id = :voucherId", Long.class)
                    .setParameter("voucherId", voucher.getId())
                    .getSingleResult();
                
                if (usageCount >= voucher.getMaxUsage()) {
                    return false;
                }
            } finally {
                em.close();
            }
        }
        
        return true;
    }
    
    public Voucher save(Voucher voucher) {
        EntityManager em = DatabaseConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (voucher.getId() == null) {
                em.persist(voucher);
            } else {
                voucher = em.merge(voucher);
            }
            tx.commit();
            return voucher;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    
    public Optional<Voucher> findById(Long id) {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            Voucher voucher = em.find(Voucher.class, id);
            return Optional.ofNullable(voucher);
        } finally {
            em.close();
        }
    }
    
    public List<Voucher> findAll() {
        EntityManager em = DatabaseConfig.getEntityManager();
        try {
            return em.createQuery(
                "SELECT v FROM Voucher v ORDER BY v.createdAt DESC", Voucher.class)
                .getResultList();
        } finally {
            em.close();
        }
    }
}
