package com.jwt.demo.dao;

import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
public class RestaurentSavingValidationDaoImpl implements RestaurentSavingValidationDao {
    @Override
    public boolean isDuplicateRestaurentName(Map<String, Object> parameter) {
        return false;
    }
}