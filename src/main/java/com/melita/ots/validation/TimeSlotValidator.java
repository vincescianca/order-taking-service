package com.melita.ots.validation;

import com.melita.ots.dto.TimeSlotDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TimeSlotValidator implements ConstraintValidator<TimeSlotConstraint, TimeSlotDTO> {

    @Override
    public boolean isValid(TimeSlotDTO timeSlot, ConstraintValidatorContext context) {
        if (timeSlot == null || timeSlot.getStartTime() == null || timeSlot.getEndTime() == null) {
            return true;
        }
        return timeSlot.getStartTime().isBefore(timeSlot.getEndTime());
    }
}