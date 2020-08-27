package com.jwt.demo.validator;

import javax.validation.ConstraintValidator;

import com.jwt.demo.annotation.ValidRestaurentSaving;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ValidRestaurentValidator<T extends Object>
        implements ConstraintValidator<ValidRestaurentSaving, T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidRestaurentValidator.class);

    protected String message;

    protected String restaurentId;

    protected String restaurentName;

    protected String cuisines;

    protected String currency;

    protected String averageCostForTwo;

    protected String hasTableBooking;

    protected String hasOnlineDelivery;

    protected String aggregateRating;

    protected String ratingColor;

    protected String ratingText;

    protected String votes;

    @Override
    public void initialize(ValidRestaurentSaving constraintAnnotation) {
        LOGGER.info("ValidRestaurentValidator implements ConstraintValidator<ValidRestaurentSaving, T>");
        message = constraintAnnotation.message();
        restaurentId = constraintAnnotation.restaurentId();
        restaurentName = constraintAnnotation.restaurentName();
        cuisines = constraintAnnotation.cuisines();
        currency = constraintAnnotation.currency();
        averageCostForTwo = constraintAnnotation.averageCostForTwo();
        hasTableBooking = constraintAnnotation.hasTableBooking();
        hasOnlineDelivery = constraintAnnotation.hasOnlineDelivery();
        aggregateRating = constraintAnnotation.aggregateRating();
        ratingColor = constraintAnnotation.ratingColor();
        ratingText = constraintAnnotation.ratingText();
        votes = constraintAnnotation.votes();
    }
}
