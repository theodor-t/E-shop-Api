package com.example.electronicsapi.repository;

import com.example.electronicsapi.entities.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {
    Optional<Discount> findDiscountByCode(String code);

    void deleteDiscountByCode(String code);
}
