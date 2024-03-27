package com.ecommerce.productmanagement.repository;

import com.ecommerce.productmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> findAllByOrderByPriceAsc();

    boolean existsByName(String name);

}
