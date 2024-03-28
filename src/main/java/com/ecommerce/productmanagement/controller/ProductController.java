package com.ecommerce.productmanagement.controller;

import com.ecommerce.productmanagement.DTO.ProductDTO;
import com.ecommerce.productmanagement.exceptions.ProductAlreadyExists;
import com.ecommerce.productmanagement.exceptions.ProductNotFound;
import com.ecommerce.productmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.productmanagement.model.Product;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> list0 = productService.findAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(list0);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.findProductById(id).get());
        } catch (ProductNotFound error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Object> addProduct(@RequestBody ProductDTO productDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.saveProduct(productDTO));
        } catch (ProductAlreadyExists error) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error.getMessage());
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(name = "id") Long id, @RequestBody ProductDTO productDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id, productDTO).get());
        } catch (ProductNotFound error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProductById(id).get());
        } catch (ProductNotFound error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }
}
