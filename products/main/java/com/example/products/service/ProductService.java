package com.example.products.service;

import com.example.products.Model.Products;
import com.example.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Products saveProduct(Products product) {
        return repository.save(product);
    }

    public List<Products> saveProducts(List<Products> products) {
        return repository.saveAll(products);
    }

    public List<Products> getProducts() {
        return repository.findAll();
    }

    public Products getProductById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Products getProductByName(String name) {
        return repository.findByName(name);
    }

    public String deleteProduct(int id) {
        repository.deleteById(id);
        return "product removed !! " + id;
    }

    public Products updateProduct(Products product) {
        Products existingProduct = repository.findById(product.getId()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        return repository.save(existingProduct);
    }


}