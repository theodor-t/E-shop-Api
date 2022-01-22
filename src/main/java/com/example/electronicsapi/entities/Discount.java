package com.example.electronicsapi.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "discounts")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int value;
    private String code;

    private boolean activated = false;

    @JsonIgnore
    @OneToOne(mappedBy = "discount", cascade = CascadeType.ALL, orphanRemoval = true)
    private Order order;

}
