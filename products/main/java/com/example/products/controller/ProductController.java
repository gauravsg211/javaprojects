package com.example.products.controller;

import com.example.products.Model.Products;
import com.example.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/addProduct")
    public Products addProduct(@RequestBody Products product) {
        return service.saveProduct(product);
    }

    @PostMapping("/addProducts")
    public List<Products> addProducts(@RequestBody List<Products> products) {
        return service.saveProducts(products);
    }

    @GetMapping("/products")
    public List<Products> findAllProducts() {
        return service.getProducts();
    }

    @GetMapping("/productById/{id}")
    public Products findProductById(@PathVariable int id) {
        return service.getProductById(id);
    }

    @GetMapping("/product/{name}")
    public Products findProductByName(@PathVariable String name) {
        return service.getProductByName(name);
    }

    @PutMapping("update")
    public Products updateProduct(@RequestBody Products product) {
        return service.updateProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        return service.deleteProduct(id);
    }
}