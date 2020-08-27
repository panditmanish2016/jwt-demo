package com.jwt.demo.service;

import java.util.List;

import com.jwt.demo.exception.ServiceException;
import com.jwt.demo.model.Restaurent;

public interface RestaurentService {

	List<Restaurent> listAllRestaurents() throws ServiceException;

	List<Restaurent> filterRestuarent(String cuisine) throws ServiceException;

	int saveOrUpdateRestaurents(List<Restaurent> restaurentListRequest) throws ServiceException;
}
