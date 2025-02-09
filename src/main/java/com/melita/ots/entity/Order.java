package com.melita.ots.entity;

import com.melita.ots.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Order {
    @Id
    private Long id;
    @ManyToOne
    private Customer customer;
    @OneToMany
    private List<Product> product;
    private OrderStatus status;

}
