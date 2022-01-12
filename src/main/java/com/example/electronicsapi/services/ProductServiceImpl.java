package com.example.electronicsapi.services;

import com.example.electronicsapi.entities.*;
import com.example.electronicsapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DiscountRepository discountRepository;
    @Autowired
    ProductOrderRepository productOrderRepository;


    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProduct(int productId) {

        return productRepository.findById(productId);
    }

    @Override
    public String addProduct(Product product) {
        for (Product item : productRepository.findAll()) {
            if (item.getId() == product.getId()) {
                return "ID already exists in database";
            }
        }
        productRepository.save(product);
        return "Added with success!!";
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }


    @Override
    public List<Product> getCategoryProducts(String category) {
        List<Product> temp = new ArrayList<>();

        for (Product product : productRepository.findAll()) {
            if (product.getCategory().equals(category)) {
                temp.add(product);
            }
        }
        return temp;
    }

    @Override
    public Optional<Discount> getDiscount(String coupon) {

        return discountRepository.findDiscountByCode(coupon);
    }

    @Override
    public int deactivateDiscount(int code) {

        Optional<Discount> searchedDiscount = discountRepository.findById(code);

        searchedDiscount.ifPresent(discount -> discount.setUsed(true));

        return 1;
    }

    @Override
    public int addOrders(Order order) {

        List<ProductOrder> products = order.getProducts();

        for (ProductOrder currProduct : products)
            currProduct.setId(null);

        orderRepository.save(order);

        return 1;
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

}
