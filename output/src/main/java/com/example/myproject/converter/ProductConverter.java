package com.example.myproject.converter;

import com.example.myproject.dto.ProductDTO;
import com.example.myproject.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public ProductDTO toDTO(Product entity) {
        if (entity == null) {
            return null;
        }

        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());

        return dto;
    }

    public Product toEntity(ProductDTO dto) {
        if (dto == null) {
            return null;
        }

        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());

        return entity;
    }
}