package com.com.com.webaaa.domain.service;

import com.com.com.webaaa.domain.model.User;
import com.com.com.webaaa.domain.repository.RestDao;
import com.com.com.webaaa.domain.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestService {

    @Autowired
    UserDao dao;

    public User selectOne(String userId) {
        return dao.selectOne(userId);
    }

    public List<User> selectMany() {
        return dao.selectMany();
    }
}
