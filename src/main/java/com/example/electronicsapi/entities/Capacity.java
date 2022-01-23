package com.example.electronicsapi.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "capacities")
public class Capacity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Nullable()
    private int id;
    private String size;
    private boolean available;
    private int price;
    private int quantity;

    @ManyToMany(mappedBy = "memoryCapacity", fetch = FetchType.LAZY)
    private List<Product> products;


}
