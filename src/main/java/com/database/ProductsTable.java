package com.database;

import com.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class ProductsTable {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_PRODUCT = "SELECT * FROM products WHERE location = ?";

    public List<Product> selectProductByLocation(String productLocation) {
        try {
            return jdbcTemplate.query(SELECT_PRODUCT, new Object[]{productLocation}, new BeanPropertyRowMapper<>(Product.class));
        } catch (EmptyResultDataAccessException ex) {
            return Collections.emptyList();
        }
    }
}
