package com.jwt.demo.dao;

import com.jwt.demo.model.Restaurent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurentDaoRepository extends JpaRepository<Restaurent, Integer> {
    @Query("SELECT f FROM Restaurent f WHERE f.restaurentName = :restaurentName")
    Restaurent retrieveByName(@Param("restaurentName") String name);
}