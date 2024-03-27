package com.ecommerce.productmanagement.service;

import com.ecommerce.productmanagement.DTO.ProductDTO;
import com.ecommerce.productmanagement.exceptions.ProductAlreadyExists;
import com.ecommerce.productmanagement.exceptions.ProductNotFound;
import com.ecommerce.productmanagement.model.Product;
import com.ecommerce.productmanagement.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    public Optional<Product> findProductById(Long id) {
        Optional<Product> product0 = productRepository.findById(id);
        if(product0.isEmpty()) {
            return product0;
        } else {
            throw new ProductNotFound("Product Not Found!");
        }
    }

    public Optional<Product> saveProduct(ProductDTO productDTO) {
        if(existsByName(productDTO.name())) {
            throw new ProductAlreadyExists("Product Already Exists!");
        }
        Product product0 = new Product();
        BeanUtils.copyProperties(productDTO, product0);
        return Optional.of(productRepository.save(product0));
    }

    public Optional<Object> deleteProductById(Long id) {
        if(!productRepository.existsById(id)) {
            throw new ProductNotFound("Product Not Found!");
        }
        productRepository.deleteById(id);
        return Optional.of("Product Deleted!");
    }

    public boolean existsByName(String name) {
        if(productRepository.existsByName(name)) {
            return true;
        }
        return false;
    }
}
