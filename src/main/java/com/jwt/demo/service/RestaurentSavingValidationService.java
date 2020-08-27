package com.jwt.demo.service;

import java.util.Map;

public interface RestaurentSavingValidationService {
    boolean isDuplicateRestaurentName(Map<String, Object> parameter);
}