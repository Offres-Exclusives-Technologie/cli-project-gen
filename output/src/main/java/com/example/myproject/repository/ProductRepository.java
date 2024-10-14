package com.example.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.myproject.model.Product;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}