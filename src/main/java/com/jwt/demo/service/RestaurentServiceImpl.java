package com.jwt.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwt.demo.dao.RestaurentDao;

import com.jwt.demo.exception.ServiceException;
import com.jwt.demo.model.Restaurent;

@Transactional
@Service
public class RestaurentServiceImpl implements RestaurentService {

	@Autowired
	private RestaurentDao restaurentDao;

	@Override
	public List<Restaurent> listAllRestaurents() throws ServiceException {
		return restaurentDao.listAllRestaurents();
	}

	@Override
	public List<Restaurent> filterRestuarent(String cuisine) throws ServiceException {
		return restaurentDao.filterRestuarent(cuisine);
	}

	@Override
	public int saveOrUpdateRestaurents(List<Restaurent> restaurentListRequest) throws ServiceException {
		return restaurentDao.saveOrUpdateRestaurents(restaurentListRequest);
	}
}
