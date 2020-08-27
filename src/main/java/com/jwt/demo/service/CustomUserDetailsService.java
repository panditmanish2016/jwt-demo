package com.jwt.demo.service;

import java.util.ArrayList;

import com.jwt.demo.dao.AuthDao;
import com.jwt.demo.model.UserLogin;

import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    AuthDao authDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, NonUniqueResultException {
        UserLogin userLogin = authDao.getUserByUsername(username);
        return new User(userLogin.getUserName(), userLogin.getPassWord(), new ArrayList<>());
    }

}