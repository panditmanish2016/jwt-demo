package com.jwt.demo.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jwt.demo.exception.ServiceException;
import com.jwt.demo.model.Restaurent;

@Repository
public class RestaurentDaoImpl implements RestaurentDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestaurentDaoImpl.class);

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public List<Restaurent> listAllRestaurents() throws ServiceException {
		LOGGER.trace("inside listAllRestaurents daoImpl");
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Query<Restaurent> query = session.createQuery("from Restaurent", Restaurent.class);
		List<Restaurent> list = query.list();
		session.close();
		return list;
	}

	@Override
	public List<Restaurent> filterRestuarent(String cuisine) throws ServiceException {
		LOGGER.trace("inside filterRestuarent daoImpl");
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Query<Restaurent> query = session.createQuery("FROM Restaurent WHERE cuisines like concat('%',:cuisines,'%')",
				Restaurent.class);
		query.setParameter("cuisines", cuisine);
		List<Restaurent> list = query.list();
		session.close();
		return list;
	}

	@Override
	public int saveOrUpdateRestaurents(List<Restaurent> restaurentListRequest) throws ServiceException {
		LOGGER.trace("inside saveOrUpdateRestaurents daoImpl");
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Transaction tx = session.beginTransaction();
		int i = 0;
		for (Restaurent restaurent : restaurentListRequest) {
			session.saveOrUpdate(restaurent);
			i++;
		}
		tx.commit();
		session.close();
		return i;
	}

}
