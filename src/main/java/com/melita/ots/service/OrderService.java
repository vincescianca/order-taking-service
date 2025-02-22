package com.melita.ots.service;

import com.melita.ots.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

    public OrderService(KafkaTemplate<String, OrderDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Send the order to Kafka.
     *
     * @param orderRequest the order to send
     */
    public void createOrder(OrderDTO orderRequest) {
        try {
            logger.info("Sending order to Kafka: customerId={}", orderRequest.getCustomerId());
            kafkaTemplate.send("orders", orderRequest);
            logger.info("Order successfully sent to topic: {}", "orders");
        } catch (Exception e) {
            logger.error("Failed to send order to Kafka: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to send order to Kafka", e);
        }
    }
}