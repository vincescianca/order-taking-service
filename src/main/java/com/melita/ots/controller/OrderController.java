package com.melita.ots.controller;

import com.melita.ots.cache.InMemoryCache;
import com.melita.ots.dto.OrderDTO;
import com.melita.ots.service.OrderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final InMemoryCache productCache;

    public OrderController(OrderService orderService, InMemoryCache productCache) {
        this.orderService = orderService;
        this.productCache = productCache;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        logger.info("Received new order from customer: {}", orderDTO.getCustomerId());

        // Validate product IDs using the cache
        List<Long> productIds = orderDTO.getProductsIds();
        if (!areProductsValid(productIds)) {
            logger.error("Product validation failed. One or more product IDs do not exist in the cache: {}", productIds);
            return ResponseEntity.badRequest().body("Invalid product IDs in the order.");
        }

        try {
            // Send the order to Kafka
            orderService.createOrder(orderDTO);
            logger.info("Order successfully sent for customer: {}", orderDTO.getCustomerId());
            return ResponseEntity.ok("Order created successfully.");
        } catch (Exception ex) {
            logger.error("Failed to process new order. Customer: {}, Error: {}", orderDTO.getCustomerId(), ex.getMessage(), ex);
            return ResponseEntity.internalServerError().body("Failed to process the order.");
        }
    }

     /**
     * Validate product IDs by checking their existence in the cache.
     */
    private boolean areProductsValid(List<Long> productIds) {
        return productIds.stream()
                .allMatch(productId -> productCache.getAllProducts().stream()
                        .anyMatch(product -> product.getId().equals(productId)));
    }
}