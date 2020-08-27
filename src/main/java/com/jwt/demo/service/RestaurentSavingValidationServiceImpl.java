package com.jwt.demo.service;

import java.util.Map;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Throwable.class)
public class RestaurentSavingValidationServiceImpl implements RestaurentSavingValidationService {

    @Override
    public boolean isDuplicateRestaurentName(Map<String, Object> parameter) {
        return false;
    }

}