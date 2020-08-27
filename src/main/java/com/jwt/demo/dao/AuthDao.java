package com.jwt.demo.dao;

import com.jwt.demo.model.UserLogin;

import org.hibernate.NonUniqueResultException;

public interface AuthDao {
	public UserLogin getUserByUsername(String username) throws NonUniqueResultException;
}
