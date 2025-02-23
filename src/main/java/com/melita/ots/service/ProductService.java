package com.melita.ots.service;

import com.melita.ots.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final RestTemplate restTemplate;

    @Value("${manager-service.base-url}")
    private String managerServiceBaseUrl;

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "products")
    public Set<ProductDTO> getProducts() {
        ResponseEntity<List<ProductDTO>> response
                = restTemplate.exchange(managerServiceBaseUrl+"api/products", HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductDTO>>() {}
        );

        return new HashSet<>(Objects.requireNonNull(response.getBody()));
    }
}