package com.melita.ots.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ProductDTO {
        private Long id;
        private String name;
}
