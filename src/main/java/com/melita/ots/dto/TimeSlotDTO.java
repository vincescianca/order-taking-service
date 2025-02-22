package com.melita.ots.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Data
public class TimeSlotDTO {
    @NotNull(message = "Start time cannot be null.")
    private LocalDateTime startTime;

    @NotNull(message = "End time cannot be null.")
    private LocalDateTime endTime;


}