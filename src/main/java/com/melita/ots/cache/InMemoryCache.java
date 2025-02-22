package com.melita.ots.cache;

import com.melita.ots.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class InMemoryCache {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryCache.class);

    private final List<ProductDTO> productCache = new CopyOnWriteArrayList<>();

    /**
     * Get all cached products.
     *
     * @return unmodifiable list of products
     */
    public List<ProductDTO> getAllProducts() {
        return Collections.unmodifiableList(productCache);
    }

    /**
     * Preload the cache with a list of products.
     *
     * @param products the list of products to cache
     */
    public void preloadCache(List<ProductDTO> products) {
        logger.info("Preloading product cache with {} products", products.size());
        productCache.clear();
        productCache.addAll(products);
    }

    /**
     * Clear the cache (if needed for manual updates).
     */
    public void clearCache() {
        logger.info("Clearing the product cache");
        productCache.clear();
    }
}