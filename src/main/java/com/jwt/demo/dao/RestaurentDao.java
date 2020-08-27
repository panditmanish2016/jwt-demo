package com.jwt.demo.dao;

import java.util.List;

import com.jwt.demo.exception.ServiceException;
import com.jwt.demo.model.Restaurent;

public interface RestaurentDao {

	List<Restaurent> listAllRestaurents() throws ServiceException;
	List<Restaurent> filterRestuarent(String cuisine) throws ServiceException;
	int saveOrUpdateRestaurents(List<Restaurent> restaurentListRequest) throws ServiceException;
}
