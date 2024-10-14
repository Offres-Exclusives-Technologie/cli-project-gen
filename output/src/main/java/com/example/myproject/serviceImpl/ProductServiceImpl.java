package com.example.myproject.serviceImpl;

import com.example.myproject.service.ProductService;
import com.example.myproject.dto.ProductDTO;
import com.example.myproject.model.Product;
import com.example.myproject.converter.ProductConverter;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    // TODO: Add repository dependency

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        // TODO: Implement method
        return null;
    }

    @Override
    public ProductDTO getProductById(Long id) {
        // TODO: Implement method
        return null;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        // TODO: Implement method
        return null;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        // TODO: Implement method
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        // TODO: Implement method
    }
}