package com.example.electronicsapi.controller;

import com.example.electronicsapi.entities.*;
import com.example.electronicsapi.services.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    private final ProductService productService;

    public Controller(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return this.productService.getProducts();
    }

    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable String productId) {
        return this.productService.getProduct(Long.parseLong(productId));
    }

    @PostMapping(path="/products/{category}")
    public String addProduct(@RequestBody Product product, @PathVariable String category) {
        return this.productService.addProduct(product);
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return this.productService.getCategories();
    }

    @DeleteMapping(path="/categories/{categoryName}")
    public int removeCategory(@PathVariable String categoryName) {
        return this.productService.removeCategory(categoryName);
    }

    @GetMapping(path="/products/{categoryName}")
    public List<Product> getCategoryProducts(@PathVariable String categoryName) {
        return this.productService.getCategoryProducts(categoryName);
    }

    @GetMapping(path="/discount/{coupon}")
    public Discount getDiscount(@PathVariable String coupon) {
        return this.productService.getDiscount(coupon);
    }

    @DeleteMapping(path="/discount/{couponID}")
    public Map<String, Boolean> removeDiscount(@PathVariable (value = "couponID") String id) {
        return this.productService.removeDiscount(id);
    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    }, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    }, path="/orders/")
    public int addOrders(@RequestBody Orders order) {
        System.out.println(order.getId());
        System.out.println(order.getContact().getName());
        System.out.println(order.getContact().getAddress());
        return this.productService.addOrders(order);
    }

    @GetMapping(path="/orders/getOrders")
    public List<Orders> getOrders() {
        return this.productService.getOrders();
    }
    
}
