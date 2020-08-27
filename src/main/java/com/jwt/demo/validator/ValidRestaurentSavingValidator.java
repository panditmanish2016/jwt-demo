package com.jwt.demo.validator;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidatorContext;

import com.jwt.demo.validator.util.GetFieldValueUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidRestaurentSavingValidator extends ValidRestaurentValidator<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidRestaurentSavingValidator.class);

    @Autowired
    private GetFieldValueUtil getFieldValueUtil;


    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        LOGGER.info("ValidRestaurentValidator.........");
        if (value instanceof List<?>) {
            for (int i = 0; i < ((List<?>) value).size(); i++) {
                Object restaurent1 = ((List<?>) value).get(i);
                Object restaurentName1 = getFieldValueUtil.getFieldValue(restaurent1, restaurentName);
                Object cuisines1 = getFieldValueUtil.getFieldValue(restaurent1, cuisines);
                Object currency1 = getFieldValueUtil.getFieldValue(restaurent1, currency);
                Object averageCostForTwo1 = getFieldValueUtil.getFieldValue(restaurent1, averageCostForTwo);
                Object hasTableBooking1 = getFieldValueUtil.getFieldValue(restaurent1, hasTableBooking);
                Object hasOnlineDelivery1 = getFieldValueUtil.getFieldValue(restaurent1, hasOnlineDelivery);
                Object aggregateRating1 = getFieldValueUtil.getFieldValue(restaurent1, aggregateRating);
                Object ratingColor1 = getFieldValueUtil.getFieldValue(restaurent1, ratingColor);
                Object ratingText1 = getFieldValueUtil.getFieldValue(restaurent1, ratingText);
                Object votes1 = getFieldValueUtil.getFieldValue(restaurent1, votes);
                if (!Optional.ofNullable(restaurentName1).isPresent()) {
                    context.buildConstraintViolationWithTemplate("required").addPropertyNode("restaurentName")
                            .inIterable().atIndex(i).addConstraintViolation();
                    return false;
                }
                if (!Optional.ofNullable(cuisines1).isPresent()) {
                    context.buildConstraintViolationWithTemplate("required").addPropertyNode("cuisines").inIterable()
                            .atIndex(i).addConstraintViolation();
                    return false;
                }
                if (!Optional.ofNullable(currency1).isPresent()) {
                    context.buildConstraintViolationWithTemplate("required").addPropertyNode("currency").inIterable()
                            .atIndex(i).addConstraintViolation();
                    return false;
                }
                if (!Optional.ofNullable(averageCostForTwo1).isPresent()) {
                    context.buildConstraintViolationWithTemplate("required").addPropertyNode("averageCostForTwo")
                            .inIterable().atIndex(i).addConstraintViolation();
                    return false;
                }
                if (!Optional.ofNullable(hasTableBooking1).isPresent()) {
                    context.buildConstraintViolationWithTemplate("required").addPropertyNode("hasTableBooking")
                            .inIterable().atIndex(i).addConstraintViolation();
                    return false;
                }
                if (!Optional.ofNullable(hasOnlineDelivery1).isPresent()) {
                    context.buildConstraintViolationWithTemplate("required").addPropertyNode("hasOnlineDelivery")
                            .inIterable().atIndex(i).addConstraintViolation();
                    return false;
                }
                if (!Optional.ofNullable(aggregateRating1).isPresent()) {
                    context.buildConstraintViolationWithTemplate("required").addPropertyNode("aggregateRating")
                            .inIterable().atIndex(i).addConstraintViolation();
                    return false;
                }
                if (!Optional.ofNullable(ratingColor1).isPresent()) {
                    context.buildConstraintViolationWithTemplate("required").addPropertyNode("ratingColor").inIterable()
                            .atIndex(i).addConstraintViolation();
                    return false;
                }
                if (!Optional.ofNullable(ratingText1).isPresent()) {
                    context.buildConstraintViolationWithTemplate("required").addPropertyNode("ratingText").inIterable()
                            .atIndex(i).addConstraintViolation();
                    return false;
                }
                if (!Optional.ofNullable(votes1).isPresent()) {
                    context.buildConstraintViolationWithTemplate("required").addPropertyNode("votes").inIterable()
                            .atIndex(i).addConstraintViolation();
                    return false;
                }
                for (int j = i + 1; j < ((List<?>) value).size(); j++) {
                    Object restaurent2 = ((List<?>) value).get(j);
                    Object restaurentName2 = getFieldValueUtil.getFieldValue(restaurent2, restaurentName);
                    Object cuisines2 = getFieldValueUtil.getFieldValue(restaurent2, cuisines);
                    Object currency2 = getFieldValueUtil.getFieldValue(restaurent2, currency);
                    Object averageCostForTwo2 = getFieldValueUtil.getFieldValue(restaurent2, averageCostForTwo);
                    Object hasTableBooking2 = getFieldValueUtil.getFieldValue(restaurent2, hasTableBooking);
                    Object hasOnlineDelivery2 = getFieldValueUtil.getFieldValue(restaurent2, hasOnlineDelivery);
                    Object aggregateRating2 = getFieldValueUtil.getFieldValue(restaurent2, aggregateRating);
                    Object ratingColor2 = getFieldValueUtil.getFieldValue(restaurent2, ratingColor);
                    Object ratingText2 = getFieldValueUtil.getFieldValue(restaurent2, ratingText);
                    Object votes2 = getFieldValueUtil.getFieldValue(restaurent2, votes);
                    if (!Optional.ofNullable(restaurentName2).isPresent()) {
                        context.buildConstraintViolationWithTemplate("required").addPropertyNode("restaurentName")
                                .inIterable().atIndex(i).addConstraintViolation();
                        return false;
                    }
                    if (((String) restaurentName1).equalsIgnoreCase((String) restaurentName2)) {
                        context.buildConstraintViolationWithTemplate("duplicate").addPropertyNode("restaurentName")
                                .inIterable().atIndex(j).addConstraintViolation();
                        return false;
                    }
                    if (!Optional.ofNullable(cuisines2).isPresent()) {
                        context.buildConstraintViolationWithTemplate("required").addPropertyNode("cuisines")
                                .inIterable().atIndex(i).addConstraintViolation();
                        return false;
                    }
                    if (!Optional.ofNullable(currency2).isPresent()) {
                        context.buildConstraintViolationWithTemplate("required").addPropertyNode("currency")
                                .inIterable().atIndex(i).addConstraintViolation();
                        return false;
                    }
                    if (!Optional.ofNullable(averageCostForTwo2).isPresent()) {
                        context.buildConstraintViolationWithTemplate("required").addPropertyNode("averageCostForTwo")
                                .inIterable().atIndex(i).addConstraintViolation();
                        return false;
                    }
                    if (!Optional.ofNullable(hasTableBooking2).isPresent()) {
                        context.buildConstraintViolationWithTemplate("required").addPropertyNode("hasTableBooking")
                                .inIterable().atIndex(i).addConstraintViolation();
                        return false;
                    }
                    if (!Optional.ofNullable(hasOnlineDelivery2).isPresent()) {
                        context.buildConstraintViolationWithTemplate("required").addPropertyNode("hasOnlineDelivery")
                                .inIterable().atIndex(i).addConstraintViolation();
                        return false;
                    }
                    if (!Optional.ofNullable(aggregateRating2).isPresent()) {
                        context.buildConstraintViolationWithTemplate("required").addPropertyNode("aggregateRating2")
                                .inIterable().atIndex(i).addConstraintViolation();
                        return false;
                    }
                    if (!Optional.ofNullable(ratingColor2).isPresent()) {
                        context.buildConstraintViolationWithTemplate("required").addPropertyNode("ratingColor")
                                .inIterable().atIndex(i).addConstraintViolation();
                        return false;
                    }
                    if (!Optional.ofNullable(ratingText2).isPresent()) {
                        context.buildConstraintViolationWithTemplate("required").addPropertyNode("ratingText")
                                .inIterable().atIndex(i).addConstraintViolation();
                        return false;
                    }
                    if (!Optional.ofNullable(votes2).isPresent()) {
                        context.buildConstraintViolationWithTemplate("required").addPropertyNode("votes").inIterable()
                                .atIndex(i).addConstraintViolation();
                        return false;
                    }
                }
            }
        }
        return true;
    }

}