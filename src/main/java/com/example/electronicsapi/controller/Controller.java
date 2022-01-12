package com.example.electronicsapi.controller;

import com.example.electronicsapi.entities.Category;
import com.example.electronicsapi.entities.Discount;
import com.example.electronicsapi.entities.Order;
import com.example.electronicsapi.entities.Product;
import com.example.electronicsapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/product/{productId}")
    public Optional<Product> getProduct(@PathVariable int productId) {
        return productService.getProduct(productId);
    }

    @PostMapping(path = "/products/{category}")
    public String addProduct(@RequestBody Product product, @PathVariable String category) {
        return this.productService.addProduct(product);
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return this.productService.getCategories();
    }


    @GetMapping(path = "/products/{categoryName}")
    public List<Product> getCategoryProducts(@PathVariable String categoryName) {
        return this.productService.getCategoryProducts(categoryName);
    }

    @GetMapping(path = "/discount/{coupon}")
    public Optional<Discount> getDiscount(@PathVariable String coupon) {
        return productService.getDiscount(coupon);
    }

    @GetMapping(path = "/deactivate/{discount}")
//    @Transactional
    public int deactivateDiscount(@PathVariable(value = "discount") int id) {
        return productService.deactivateDiscount(id);
    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    }, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    }, path = "/orders/")
    public int addOrders(@RequestBody Order order) {

        //System.out.println(order.getProducts().size());

        return productService.addOrders(order);
    }

    @GetMapping(path = "/orders/getOrders")
    public List<Order> getOrders() {
        return productService.getOrders();
    }

}
