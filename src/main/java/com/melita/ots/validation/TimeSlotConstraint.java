package com.melita.ots.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TimeSlotValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface TimeSlotConstraint {

    String message() default "Start time must be before end time.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}