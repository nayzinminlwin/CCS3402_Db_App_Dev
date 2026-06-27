package com.ccs3402.adv_spring_security.repository;

import com.ccs3402.adv_spring_security.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
