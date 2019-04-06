package com.com.com.webaaa.domain.service;

import com.com.com.webaaa.domain.model.User;
import com.com.com.webaaa.domain.repository.RestDao;
import com.com.com.webaaa.domain.repository.UserDao;
import com.com.com.webaaa.domain.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestService {

    @Autowired
    UserJpaRepository dao;

    public Optional<User> selectOne(String userId) {
        return dao.findById(userId);
    }

    public List<User> selectMany() {
        return dao.findAll();
    }
}
