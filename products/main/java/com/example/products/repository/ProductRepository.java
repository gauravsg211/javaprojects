package com.example.products.repository;


import com.example.products.Model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products,Integer> {
    Products findByName(String name);
}