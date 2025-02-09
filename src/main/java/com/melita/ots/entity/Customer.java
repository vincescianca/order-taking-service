package com.melita.ots.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Customer {

    @Id
    private Long id;

    private String name;
}
