package com.jwt.demo.service;

import java.util.List;
import java.util.Optional;

import com.jwt.demo.dao.RestaurentDaoRepository;
import com.jwt.demo.exception.ServiceException;
import com.jwt.demo.model.Restaurent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurentServiceRepository {

	@Autowired
	private RestaurentDaoRepository restaurentDaoRepository;

	public List<Restaurent> listAllRestaurents() throws ServiceException {
		return restaurentDaoRepository.findAll();
	}

	public Restaurent retrieveByName(String name) throws ServiceException {
		return restaurentDaoRepository.retrieveByName(name);
	}

	public Restaurent retrieveById(int id) throws ServiceException {
		Optional<Restaurent> rs = restaurentDaoRepository.findById(id);
		if (rs.isPresent()) {
			return rs.get();
		}
		return null;
	}
}