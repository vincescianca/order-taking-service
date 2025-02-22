package com.melita.ots.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {

    @NotBlank(message = "Customer id cannot be blank or null.")
    private String customerId;

    @NotBlank(message = "Installation address cannot be blank or null.")
    @Size(max = 255, message = "Installation address cannot exceed 255 characters.")
    private String installationAddress;

    @NotNull(message = "Preferred installation date and time is required.")
    private TimeSlotDTO timeSlot;

    @NotNull(message = "Products list cannot be null.")
    @Size(min = 1, message = "At least one product must be selected.")
    private List<Long> productsIds;
}