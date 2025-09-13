package com.utephonehub.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Generic DAO interface providing common CRUD operations
 * All DAO classes should implement this interface for consistency
 * @param <T> Entity type
 * @param <ID> Primary key type
 */
public interface GenericDAO<T, ID> {
    
    /**
     * Create a new entity
     * @param entity Entity to create
     * @return Generated ID or entity with ID set
     * @throws SQLException if database error occurs
     */
    ID create(T entity) throws SQLException;
    
    /**
     * Create a new entity within a transaction
     * @param entity Entity to create
     * @param conn Database connection for transaction
     * @return Generated ID or entity with ID set
     * @throws SQLException if database error occurs
     */
    ID create(T entity, Connection conn) throws SQLException;
    
    /**
     * Find entity by ID
     * @param id Primary key
     * @return Entity or null if not found
     * @throws SQLException if database error occurs
     */
    T findById(ID id) throws SQLException;
    
    /**
     * Find all entities
     * @return List of all entities
     * @throws SQLException if database error occurs
     */
    List<T> findAll() throws SQLException;
    
    /**
     * Find entities with pagination
     * @param limit Maximum number of records to return (0 for no limit)
     * @param offset Number of records to skip
     * @return List of entities
     * @throws SQLException if database error occurs
     */
    List<T> findAll(int limit, int offset) throws SQLException;
    
    /**
     * Update an existing entity
     * @param entity Entity to update
     * @return true if updated successfully
     * @throws SQLException if database error occurs
     */
    boolean update(T entity) throws SQLException;
    
    /**
     * Update an entity within a transaction
     * @param entity Entity to update
     * @param conn Database connection for transaction
     * @return true if updated successfully
     * @throws SQLException if database error occurs
     */
    boolean update(T entity, Connection conn) throws SQLException;
    
    /**
     * Delete entity by ID
     * @param id Primary key
     * @return true if deleted successfully
     * @throws SQLException if database error occurs
     */
    boolean delete(ID id) throws SQLException;
    
    /**
     * Delete entity by ID within a transaction
     * @param id Primary key
     * @param conn Database connection for transaction
     * @return true if deleted successfully
     * @throws SQLException if database error occurs
     */
    boolean delete(ID id, Connection conn) throws SQLException;
    
    /**
     * Check if entity exists by ID
     * @param id Primary key
     * @return true if entity exists
     * @throws SQLException if database error occurs
     */
    boolean exists(ID id) throws SQLException;
    
    /**
     * Count total number of entities
     * @return Total count
     * @throws SQLException if database error occurs
     */
    long count() throws SQLException;
}
