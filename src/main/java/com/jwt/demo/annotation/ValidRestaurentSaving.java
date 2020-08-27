package com.jwt.demo.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.jwt.demo.validator.ValidRestaurentSavingValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;

@Target({ FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValidRestaurentSavingValidator.class })
@Documented
public @interface ValidRestaurentSaving {

    String message() default "invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String restaurentId() default "restaurentId";

    String restaurentName() default "restaurentName";

    String cuisines() default "cuisines";

    String currency() default "currency";

    String averageCostForTwo() default "averageCostForTwo";

    String hasTableBooking() default "hasTableBooking";

    String hasOnlineDelivery() default "hasOnlineDelivery";

    String aggregateRating() default "aggregateRating";

    String ratingColor() default "ratingColor";

    String ratingText() default "ratingText";

    String votes() default "votes";
}