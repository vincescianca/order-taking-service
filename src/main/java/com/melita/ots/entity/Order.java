package com.melita.ots.entity;

import com.melita.ots.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Order {
    @Id
    private Long id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Product product;
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus status;
}
