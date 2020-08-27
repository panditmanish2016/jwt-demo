package com.jwt.demo.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface RestaurentSavingValidationDao {

    boolean isDuplicateRestaurentName(Map<String, Object> parameter);

}