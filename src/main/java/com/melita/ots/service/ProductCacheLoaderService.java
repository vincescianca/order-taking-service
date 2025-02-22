package com.melita.ots.service;

import com.melita.ots.cache.InMemoryCache;
import com.melita.ots.dto.ProductDTO;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductCacheLoaderService {

    private static final Logger logger = LoggerFactory.getLogger(ProductCacheLoaderService.class);

    private final RestTemplate restTemplate;
    private final InMemoryCache inMemoryCache;

    // URL of the local API endpoint to fetch all products
    private static final String PRODUCT_API_URL = "http://localhost:8080/api/products";

    public ProductCacheLoaderService(RestTemplate restTemplate, InMemoryCache inMemoryCache) {
        this.restTemplate = restTemplate;
        this.inMemoryCache = inMemoryCache;
    }

    /**
     * Pre-fetch product data from API and populate cache on application startup.
     */
    @PostConstruct
    public void initializeCache() {
        try {
            logger.info("Initializing product cache on startup");

            // Perform the REST call to fetch products
            List<ProductDTO> products = restTemplate.getForObject(PRODUCT_API_URL, List.class);
            if (products != null) {
                inMemoryCache.preloadCache(products);
                logger.info("Product cache initialized successfully");
            } else {
                logger.warn("No products were retrieved from the API");
            }
        } catch (Exception e) {
            logger.error("Failed to initialize product cache on startup: {}", e.getMessage(), e);
        }
    }
}